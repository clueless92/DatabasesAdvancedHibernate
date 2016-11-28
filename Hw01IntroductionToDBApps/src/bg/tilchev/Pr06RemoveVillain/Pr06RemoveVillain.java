package bg.tilchev.Pr06RemoveVillain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class Pr06RemoveVillain {

    private static final String URL = "jdbc:mysql://localhost:3306/minions_db?useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "1234";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int villainId = Integer.parseInt(reader.readLine());
        String rawSQL = "SELECT v.name FROM villains AS v\n" +
                        " WHERE v.id = ?;";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            statement = connection.prepareStatement(rawSQL);
            statement.setInt(1, villainId);
            result = statement.executeQuery();
            if (!result.isBeforeFirst()) {
                System.out.println("No such villain was found");
                return;
            }
            result.next();
            String villainName = result.getString(1);
            rawSQL = "DELETE FROM villains_minions\n" +
                     " WHERE villains_minions.villain_id = ?;";
            statement = connection.prepareStatement(rawSQL);
            statement.setInt(1, villainId);
            int releasedMinions = statement.executeUpdate();
            rawSQL = "DELETE FROM villains\n" +
                     " WHERE villains.id = ?;";
            statement = connection.prepareStatement(rawSQL);
            statement.setInt(1, villainId);
            statement.executeUpdate();
            System.out.printf("%s was deleted%n", villainName);
            System.out.printf("%d minions released%n", releasedMinions);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

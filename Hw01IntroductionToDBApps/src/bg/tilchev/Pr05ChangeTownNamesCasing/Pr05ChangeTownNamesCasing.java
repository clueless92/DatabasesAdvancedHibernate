package bg.tilchev.Pr05ChangeTownNamesCasing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class Pr05ChangeTownNamesCasing {

    private static final String URL = "jdbc:mysql://localhost:3306/minions_db?useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "1234";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String country = reader.readLine();
        String rawSQL = "UPDATE towns \n" +
                              "   SET name = UPPER(name)\n" +
                              " WHERE towns.country = ?;";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            statement = connection.prepareStatement(rawSQL);
            statement.setString(1, country);
            int rowsAffected = statement.executeUpdate();
            StringBuilder outputBuilder = new StringBuilder();
            if (rowsAffected == 0) {
                System.out.println("No town names were affected.");
                return;
            } else {
                outputBuilder.append(String.format("%d town names were affected.%n", rowsAffected));
            }
            rawSQL = "SELECT name FROM towns WHERE country = ?;";
            statement = connection.prepareStatement(rawSQL);
            statement.setString(1, country);
            result = statement.executeQuery();
            outputBuilder.append("[");
            while (result.next()) {
                outputBuilder.append(result.getString(1));
                outputBuilder.append(", ");
            }
            outputBuilder.setLength(outputBuilder.length() - 2);
            outputBuilder.append("]");
            System.out.println(outputBuilder);
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

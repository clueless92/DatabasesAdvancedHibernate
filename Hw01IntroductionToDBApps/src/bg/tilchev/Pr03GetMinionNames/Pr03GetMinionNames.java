package bg.tilchev.Pr03GetMinionNames;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class Pr03GetMinionNames {

    private static final String URL = "jdbc:mysql://localhost:3306/minions_db?useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "1234";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int villainId = Integer.parseInt(reader.readLine());
        final String rawSQL = "SELECT v.name, m.id, m.name, m.age FROM villains AS v\n" +
                              "  LEFT JOIN villains_minions AS vm\n" +
                              "    ON v.id = vm.villain_id\n" +
                              "  LEFT JOIN minions AS m\n" +
                              "    ON vm.minion_id = m.id\n" +
                              " WHERE v.id = ?;";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            statement = connection.prepareStatement(rawSQL);
            statement.setInt(1, villainId);
            result = statement.executeQuery();
            int i = 1;
            if (!result.isBeforeFirst()) {
                System.out.printf("No villain with ID %d exists in the database.%n", villainId);
                return;
            }
            result.next();
            String vName = result.getString("v.name");
            System.out.printf("Villain: %s%n", vName);
            while (true) {
                String mName = result.getString("m.name");
                if(mName == null) {
                    System.out.println("<no minions>");
                    break;
                }
                int age = result.getInt("age");
                System.out.printf("%d. %s %d%n", i, mName, age);
                i++;
                if (!result.next()) {
                    break;
                }
            }
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

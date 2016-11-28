package bg.tilchev.Pr02GetVillainsNames;

import java.sql.*;

public class Pr02GetVillainsNames {

    private static final String URL = "jdbc:mysql://localhost:3306/minions_db?useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "1234";

    public static void main(String[] args) {
        final String rawSQL = "SELECT v.name, COUNT(*) AS minions_count FROM villains as v\n" +
                              " INNER JOIN villains_minions as vm\n" +
                              "    ON v.id = vm.villain_id\n" +
                              " GROUP BY v.id\n" +
                              "HAVING minions_count > 3";
        try(Connection conn = DriverManager.getConnection(URL, USER, PASS);
                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery(rawSQL)) {

            while (result.next()) {
                String name = result.getString("name");
                int count = result.getInt("minions_count");
                System.out.printf("%s %s%n", name, count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

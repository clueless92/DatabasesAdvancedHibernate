package bg.tilchev.Pr01InitialSetup;

import java.sql.*;

public class Pr01InitialSetup {

    private static final String URL = "jdbc:mysql://localhost:3306?useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "1234";

    public static void main(String[] args) {
        try(Connection conn = DriverManager.getConnection(URL, USER, PASS);
                Statement statement = conn.createStatement()) {
            String rawSQL = "DROP DATABASE IF EXISTS minions_db; ";
            statement.execute(rawSQL);

            rawSQL = "CREATE DATABASE minions_db; ";
            statement.execute(rawSQL);

            rawSQL = "USE minions_db; ";
            statement.execute(rawSQL);

            rawSQL = "CREATE TABLE towns(" +
                     "id INT PRIMARY KEY AUTO_INCREMENT, " +
                     "name VARCHAR(50), " +
                     "country VARCHAR(50)" +
                     "); ";
            statement.execute(rawSQL);

            rawSQL = "CREATE TABLE minions(" +
                     "id INT PRIMARY KEY AUTO_INCREMENT, " +
                     "name VARCHAR(50), " +
                     "age INT, " +
                     "town_id INT, " +
                     "CONSTRAINT fk_minions_towns " +
                     "FOREIGN KEY (town_id) " +
                     "REFERENCES towns(id)" +
                     "); ";
            statement.execute(rawSQL);

            rawSQL = "CREATE TABLE villains(" +
                     "id INT PRIMARY KEY AUTO_INCREMENT, " +
                     "name VARCHAR(50), " +
                     "evilness_factor ENUM('good', 'bad', 'evil', 'super evil')" +
                     "); ";
            statement.execute(rawSQL);

            rawSQL = "CREATE TABLE villains_minions(" +
                     "minion_id INT, " +
                     "villain_id INT, " +
                     "PRIMARY KEY (minion_id, villain_id), " +
                     "CONSTRAINT fk_minions_villains " +
                     "FOREIGN KEY (minion_id)" +
                     "REFERENCES minions(id)," +
                     "CONSTRAINT fk_villains_minions " +
                     "FOREIGN KEY (villain_id)" +
                     "REFERENCES villains(id)" +
                     "); ";
            statement.execute(rawSQL);

            for (int i = 1; i <= 5; i++) {
                rawSQL = "INSERT INTO villains VALUES" +
                         "(" + i + ", 'Villain" + i + "', 'super evil');";
                statement.execute(rawSQL);
                rawSQL = "INSERT INTO towns VALUES" +
                         "(" + i + ", 'Town" + i + "', 'Bulgaria');";
                statement.execute(rawSQL);
                rawSQL = "INSERT INTO minions VALUES" +
                         "(" + i + ", 'Minion" + i + "', " + i * 10 + ", " + i + ");";
                statement.execute(rawSQL);
            }

            for (int m = 1; m <= 5; m++) {
                for (int v = 5; v >= m; v--) {
                    rawSQL = "INSERT INTO villains_minions VALUES" +
                             "(" + m + ", " + v + ");";
                    statement.execute(rawSQL);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

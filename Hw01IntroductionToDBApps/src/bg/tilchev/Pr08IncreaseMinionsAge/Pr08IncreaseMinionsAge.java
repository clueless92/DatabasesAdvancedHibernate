package bg.tilchev.Pr08IncreaseMinionsAge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class Pr08IncreaseMinionsAge {

    private static final String URL = "jdbc:mysql://localhost:3306/minions_db?useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "1234";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] ids = reader.readLine().split(" ");
        String rawSQL = "DROP FUNCTION IF EXISTS camcase;\n" +
                        "    SET GLOBAL log_bin_trust_function_creators = TRUE;\n" +
                        "    DELIMITER |\n" +
                        "    CREATE FUNCTION camcase(str VARCHAR(128))\n" +
                        "    RETURNS VARCHAR(128)\n" +
                        "    BEGIN\n" +
                        "    DECLARE c CHAR(1);\n" +
                        "    DECLARE s VARCHAR(128);\n" +
                        "    DECLARE i INT DEFAULT 1;\n" +
                        "    DECLARE bool INT DEFAULT 1;\n" +
                        "    DECLARE punct CHAR(17) DEFAULT ' ()[]{},.-_!@;:?/';\n" +
                        "    SET s = LCASE(str);\n" +
                        "    WHILE i <= LENGTH(str) DO\n" +
                        "    BEGIN\n" +
                        "    SET c = SUBSTRING(s, i, 1);\n" +
                        "    IF LOCATE(c, punct) > 0 THEN\n" +
                        "    SET bool = 1;\n" +
                        "    ELSEIF bool = 1 THEN\n" +
                        "    BEGIN\n" +
                        "    IF c >= 'a' AND c <= 'z' THEN\n" +
                        "    BEGIN\n" +
                        "    SET s = CONCAT(LEFT(s, i - 1), UCASE(c), SUBSTRING(s, i + 1));\n" +
                        "    SET bool = 0;\n" +
                        "    END;\n" +
                        "    ELSEIF c >= '0' AND c <= '9' THEN\n" +
                        "    SET bool = 0;\n" +
                        "    END IF;\n" +
                        "    END;\n" +
                        "    END IF;\n" +
                        "    SET i = i + 1;\n" +
                        "    END;\n" +
                        "    END WHILE;\n" +
                        "    RETURN s;\n" +
                        "    END;\n" +
                        "    |\n" +
                        "    DELIMITER ;";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            // TODO: fix function SQL to be executable from here
//            statement = connection.prepareStatement(rawSQL);
//            statement.execute();
            rawSQL = "UPDATE minions\n" +
                     "   SET minions.name = camcase(minions.name),\n" +
                     "   \t minions.age = minions.age + 1\n" +
                     " WHERE minions.id = ?;";
            statement = connection.prepareStatement(rawSQL);
            for (String id : ids) {
                statement.setString(1, id);
                statement.executeUpdate();
            }
            rawSQL = "SELECT m.name, m.age\n" +
                     "  FROM minions AS m;";
            statement = connection.prepareStatement(rawSQL);
            result = statement.executeQuery();
            while (result.next()) {
                String minionName = result.getString("name");
                int minionAge = result.getInt("age");
                System.out.printf("%s %d%n", minionName, minionAge);
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

//    DROP FUNCTION IF EXISTS camcase;
//        SET GLOBAL log_bin_trust_function_creators = TRUE;
//        DELIMITER |
//        CREATE FUNCTION camcase(str VARCHAR(128))
//        RETURNS VARCHAR(128)
//        BEGIN
//        DECLARE c CHAR(1);
//        DECLARE s VARCHAR(128);
//        DECLARE i INT DEFAULT 1;
//        DECLARE bool INT DEFAULT 1;
//        DECLARE punct CHAR(17) DEFAULT ' ()[]{},.-_!@;:?/';
//        SET s = LCASE(str);
//        WHILE i <= LENGTH(str) DO
//        BEGIN
//        SET c = SUBSTRING(s, i, 1);
//        IF LOCATE(c, punct) > 0 THEN
//        SET bool = 1;
//        ELSEIF bool = 1 THEN
//        BEGIN
//        IF c >= 'a' AND c <= 'z' THEN
//        BEGIN
//        SET s = CONCAT(LEFT(s, i - 1), UCASE(c), SUBSTRING(s, i + 1));
//        SET bool = 0;
//        END;
//        ELSEIF c >= '0' AND c <= '9' THEN
//        SET bool = 0;
//        END IF;
//        END;
//        END IF;
//        SET i = i + 1;
//        END;
//        END WHILE;
//        RETURN s;
//        END;
//        |
//        DELIMITER ;

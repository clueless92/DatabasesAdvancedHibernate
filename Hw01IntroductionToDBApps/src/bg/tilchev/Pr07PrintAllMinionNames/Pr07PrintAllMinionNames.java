package bg.tilchev.Pr07PrintAllMinionNames;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Pr07PrintAllMinionNames {

    private static final String URL = "jdbc:mysql://localhost:3306/minions_db?useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "1234";

    public static void main(String[] args) {
        String rawSQL = "SELECT m.name FROM minions AS m;";
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        List<String> minionNames = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            statement = connection.createStatement();
            result = statement.executeQuery(rawSQL);
            while (result.next()) {
                String minionName = result.getString("name");
                minionNames.add(minionName);
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

        int size = minionNames.size();
        for (int i = 0, j = size - 1; ; i++, j--) {
            if (i >= j && size % 2 == 0) {
                break;
            }
            System.out.println(i + " " + minionNames.get(i));
            if (i == size / 2) {
                break;
            }
            System.out.println(j + " " + minionNames.get(j));
        }
    }
}

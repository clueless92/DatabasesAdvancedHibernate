package bg.tilchev;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Todor Ilchev on 2016-10-30.
 */
public class Connector {

    private static Connection connection;

    public static void initConnection(
            String driver, String username, String password, String host,
            String port, String dbName, String... params) throws SQLException {

        Properties properties = new Properties();
        properties.setProperty("user", username);
        properties.setProperty("password", password);
        String connectionString = "jdbc:" + driver + "://" + host + ":" + port + "/" + dbName;
        for (String param : params) {
            connectionString += param;
        }
        connection = DriverManager.getConnection(connectionString, properties);
    }

    public static Connection getConnection() {
        return connection;
    }

}

package bg.tilchev;

import bg.tilchev.entities.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Todor Ilchev on 2016-10-30.
 */
public class Main {

    private static final String DRIVER = "mysql";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";
    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String DB_NAME = "school";
    private static final String PARAMS = "?useSSL=false";

    public static void main(String[] args) {
        try {
            Connector.initConnection(DRIVER, USERNAME, PASSWORD, HOST, PORT, DB_NAME, PARAMS);
            Connection connection = Connector.getConnection();
            EntityManager em = new EntityManager(connection);

            User user = new User("Pesho", 23, new Date());
            user.setId(1L);
            em.persist(user);
            System.out.println(user);
            user.setName("Gesho");
            em.persist(user);
            User userIvan = new User("Ivan", 32, new Date());
            userIvan.setId(2L);
            em.persist(userIvan);

            User pesho = em.findFirst(User.class);
            System.out.println(pesho);

            Collection<User> usersFromDb = em.find(User.class);
            for (User currUser : usersFromDb) {
                System.out.println(currUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }
}

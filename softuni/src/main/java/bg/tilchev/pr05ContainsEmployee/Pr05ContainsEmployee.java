package bg.tilchev.pr05ContainsEmployee;

import bg.tilchev.entities.softuni.Employees;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Todor Ilchev on 2016-10-25.
 */
public class Pr05ContainsEmployee {

    public static void main(String[] args) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        String firstName = input[0];
        String lastName = input[1];
        Query query = em.createQuery("SELECT e FROM Employees AS e " +
                                     " WHERE e.firstName = :firstName " +
                                     "   AND e.lastName = :lastName");
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        List<Employees> result = query.getResultList();
        if (result.size() > 0) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        em.close();
        emf.close();
    }
}

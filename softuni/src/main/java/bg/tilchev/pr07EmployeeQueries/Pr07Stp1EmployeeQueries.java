package bg.tilchev.pr07EmployeeQueries;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Todor Ilchev on 2016-10-26.
 */
public class Pr07Stp1EmployeeQueries {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("SELECT e.firstName FROM Employees AS e " +
                                     " WHERE e.salary > 50000");
        List<String> result = query.getResultList();
        for (String name : result) {
            System.out.println(name);
        }

        em.close();
        emf.close();
    }
}

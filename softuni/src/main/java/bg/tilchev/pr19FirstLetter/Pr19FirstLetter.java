package bg.tilchev.pr19FirstLetter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Todor Ilchev on 2016-10-27.
 */
public class Pr19FirstLetter {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery(
                "SELECT SUBSTRING(wd.firstName, 1, 1) " +
                "  FROM WizzardDeposits AS wd " +
                " WHERE wd.depositGroup = 'Troll Chest' " +
                " GROUP BY SUBSTRING(wd.firstName, 1, 1) " +
                " ORDER BY SUBSTRING(wd.firstName, 1, 1) ASC");
        List<String> result = query.getResultList();

        for (String firstLetter : result) {
            System.out.println(firstLetter);
        }

        em.close();
        emf.close();
    }
}

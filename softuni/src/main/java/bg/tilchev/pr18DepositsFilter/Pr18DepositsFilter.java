package bg.tilchev.pr18DepositsFilter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Todor Ilchev on 2016-10-27.
 */
public class Pr18DepositsFilter {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery(
                "SELECT wd.depositGroup, SUM(wd.depositAmount) " +
                "  FROM WizzardDeposits AS wd " +
                " WHERE wd.magicWandCreator = 'Ollivander family' " +
                " GROUP BY wd.depositGroup " +
                "HAVING SUM(wd.depositAmount) < 150000 " +
                " ORDER BY SUM(wd.depositAmount) DESC");
        List<Object[]> result = query.getResultList();

        for (Object[] resultObj : result) {
            String depositGroup = (String) resultObj[0];
            BigDecimal depositSum = (BigDecimal) resultObj[1];
            System.out.printf("%s - %s%n", depositGroup, depositSum);
        }

        em.close();
        emf.close();
    }
}

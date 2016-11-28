package bg.tilchev.pr10DatabaseSearchQueries;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Todor Ilchev on 2016-10-27.
 */
public class Pr10Pt02DatabaseSearchQueries {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery(
                "SELECT a.addressText, t.name, COUNT(e.employeeId) " +
                "  FROM Addresses AS a " +
                " INNER JOIN Employees AS e " +
                "    ON a.addressId = e.addressId " +
                " INNER JOIN Towns AS t " +
                "    ON a.townId = t.townId " +
                " GROUP BY a.addressId " +
                " ORDER BY COUNT(e.employeeId) DESC, t.name ASC");
        List<Object[]> result = query.setMaxResults(10).getResultList();

        for (Object[] resultObj : result) {
            String addressText = (String) resultObj[0];
            String townName = (String) resultObj[1];
            Long employeesPerAddress = (Long) resultObj[2];
            System.out.printf("%s, %s - %d employees%n", addressText, townName, employeesPerAddress);
        }

        em.close();
        emf.close();
    }
}

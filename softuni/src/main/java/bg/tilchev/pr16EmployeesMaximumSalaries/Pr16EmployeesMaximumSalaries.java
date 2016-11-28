package bg.tilchev.pr16EmployeesMaximumSalaries;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Todor Ilchev on 2016-10-27.
 */
public class Pr16EmployeesMaximumSalaries {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("SELECT d.name, MAX(e.salary) AS maxSalary " +
                                     "  FROM Departments AS d " +
                                     " INNER JOIN Employees AS e " +
                                     "    ON d.departmentId = e.departmentId " +
                                     " GROUP BY d.name " +
                                     "HAVING MAX(e.salary) >= 30000 " +
                                     "   AND MAX(e.salary) <= 70000");
        List<Object[]> result = query.getResultList();

        for (Object[] resultObj : result) {
            String depName = (String) resultObj[0];
            BigDecimal depMaxSalary = (BigDecimal) resultObj[1];
            System.out.printf("%s %s%n", depName, depMaxSalary);
        }

        em.close();
        emf.close();
    }
}

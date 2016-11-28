package bg.tilchev.pr10DatabaseSearchQueries;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Todor Ilchev on 2016-10-27.
 */
public class Pr10Pt04DatabaseSearchQueries {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery(
                "SELECT d.name, m.lastName, COUNT(*) " +
                "  FROM Departments AS d " +
                " INNER JOIN Employees AS e " +
                "    ON d.departmentId = e.departmentId " +
                " INNER JOIN Employees AS m " +
                "    ON m.employeeId = d.managerId " +
                " GROUP BY d.departmentId " +
                "HAVING COUNT(*) > 5 " +
                " ORDER BY COUNT(*) ASC");
        List<Object[]> result = query.getResultList();

        System.out.println(result.size());
        for (Object[] resultObj : result) {
            String depName = (String) resultObj[0];
            String managerName = (String) resultObj[1]; // TODO: managerName is wrong
            Long employeesPerDep = (Long) resultObj[2];
            System.out.printf("--%s - Manager: %s, Employees: %d%n", depName, managerName, employeesPerDep);
        }

        em.close();
        emf.close();
    }
}

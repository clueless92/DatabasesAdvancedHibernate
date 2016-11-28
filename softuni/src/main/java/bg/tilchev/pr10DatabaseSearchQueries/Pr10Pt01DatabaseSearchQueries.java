package bg.tilchev.pr10DatabaseSearchQueries;

import bg.tilchev.entities.softuni.Employees;
import bg.tilchev.entities.softuni.Projects;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Todor Ilchev on 2016-10-26.
 */
public class Pr10Pt01DatabaseSearchQueries {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("SELECT e FROM Employees AS e " +
                                     " INNER JOIN EmployeesProjects AS ep " +
                                     "    ON ep.employeeId = e.id " +
                                     " INNER JOIN Projects AS p" +
                                     "    ON p.projectId = ep.projectId " +
                                     "   AND p.startDate IS NOT NULL " +
                                     "   AND p.endDate IS NOT NULL " +
                                     " WHERE YEAR(p.startDate) >= 2001 " +
                                     "   AND YEAR(p.endDate) <= 2003 ");
        List<Employees> result = query.getResultList();
        for (Employees employee : result) {
            int currManagerId = employee.getManagerId();
            query = em.createQuery("SELECT CONCAT(e.firstName, ' ', e.lastName) " +
                                   "  FROM Employees AS e " +
                                   " WHERE e.employeeId = :currManagerId");
            query.setParameter("currManagerId", currManagerId);
            int currEmployeeId = employee.getEmployeeId();
            String currManagerName = (String) query.getSingleResult();
            query = em.createQuery("SELECT p " +
                    "  FROM Projects AS p " +
                    " INNER JOIN EmployeesProjects AS ep" +
                    "    ON p.projectId = ep.projectId " +
                    "   AND p.endDate IS NOT NULL " +
                    "   AND p.startDate IS NOT NULL" +
                    " WHERE ep.employeeId = :currEmployeeId");
            query.setParameter("currEmployeeId", currEmployeeId);

            List<Projects> currProjects = query.getResultList();
            System.out.printf("%s %s, with manager %s and projects:%n",
                    employee.getFirstName(),
                    employee.getLastName(),
                    currManagerName);
            for (Projects currProject : currProjects) {
                System.out.printf("%s (%s to %s)%n",
                        currProject.getName(),
                        currProject.getStartDate(),
                        currProject.getEndDate());
            }
        }

        em.close();
        emf.close();
    }
}

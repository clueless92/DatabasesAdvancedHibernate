package bg.tilchev.pr10DatabaseSearchQueries;

import bg.tilchev.entities.softuni.Employees;
import bg.tilchev.entities.softuni.Projects;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Todor Ilchev on 2016-10-26.
 */
public class Pr10Pt03DatabaseSearchQueries {

    public static void main(String[] args) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int id = Integer.parseInt(reader.readLine());

        Query query = em.createQuery("SELECT e FROM Employees AS e " +
                                     " WHERE e.employeeId = :id ");
        query.setParameter("id", id);
        Employees wantedEmployee = (Employees) query.getSingleResult();
        query = em.createQuery("SELECT p " +
                "  FROM Projects AS p " +
                " INNER JOIN EmployeesProjects AS ep" +
                "    ON p.projectId = ep.projectId " +
                " WHERE ep.employeeId = :id " +
                " ORDER BY p.name ASC");
        query.setParameter("id", id);
        List<Projects> wantedProjects = query.getResultList();

        System.out.printf("%s %s - %s, with projects:%n",
                wantedEmployee.getFirstName(),
                wantedEmployee.getLastName(),
                wantedEmployee.getJobTitle());
        for (Projects wantedProject : wantedProjects) {
            System.out.println(wantedProject.getName());
        }

        em.close();
        emf.close();
    }
}

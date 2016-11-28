package bg.tilchev.pr12FindLatest10Projects;

import bg.tilchev.entities.softuni.Projects;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Todor Ilchev on 2016-10-26.
 */
public class Pr12FindLatest10Projects {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("SELECT p " +
                "  FROM Projects AS p " +
                " ORDER BY p.name ASC");
        List<Projects> projects = query.getResultList();

        for (Projects project : projects) {
            System.out.printf("%-30s | %s ... | %s - %s%n",
                    project.getName(),
                    project.getDescription().substring(0, 50),
                    project.getStartDate(),
                    project.getEndDate());
        }

        em.close();
        emf.close();
    }
}

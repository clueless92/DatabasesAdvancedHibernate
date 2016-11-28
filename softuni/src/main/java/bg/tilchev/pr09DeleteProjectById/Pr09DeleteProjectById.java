package bg.tilchev.pr09DeleteProjectById;

import javax.persistence.*;

/**
 * Created by Todor Ilchev on 2016-10-26.
 */
public class Pr09DeleteProjectById {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Query query = em.createQuery("DELETE FROM EmployeesProjects AS ep " +
                                     " WHERE ep.projectId = 2");
        query.executeUpdate();
        query = em.createQuery("DELETE FROM Projects AS p " +
                               " WHERE p.projectId = 2");
        query.executeUpdate();

        transaction.commit();
        em.close();
        emf.close();
    }
}

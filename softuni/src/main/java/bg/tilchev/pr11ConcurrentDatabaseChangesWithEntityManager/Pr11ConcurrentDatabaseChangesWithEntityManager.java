package bg.tilchev.pr11ConcurrentDatabaseChangesWithEntityManager;

import bg.tilchev.entities.softuni.Departments;

import javax.persistence.*;

/**
 * Created by Todor Ilchev on 2016-10-26.
 */
public class Pr11ConcurrentDatabaseChangesWithEntityManager {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em1 = emf.createEntityManager();
        EntityManager em2 = emf.createEntityManager();
        EntityTransaction transaction1 = em1.getTransaction();
        EntityTransaction transaction2 = em2.getTransaction();

        transaction1.begin();
        Departments mangali = new Departments();
        mangali.setName("Mangali");
        mangali.setManagerId(1);
        em1.persist(mangali);
//        em1.lock(mangali, LockModeType.PESSIMISTIC_WRITE);

        transaction2.begin();
        mangali.setName("Cigani");
        em2.merge(mangali);
//        em2.lock(mangali, LockModeType.PESSIMISTIC_WRITE);

        transaction1.commit();
        transaction2.commit();

        em1.close();
        em2.close();
        emf.close();
    }
}

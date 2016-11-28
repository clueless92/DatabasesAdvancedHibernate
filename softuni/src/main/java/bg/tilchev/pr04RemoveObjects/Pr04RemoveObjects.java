package bg.tilchev.pr04RemoveObjects;

import bg.tilchev.entities.softuni.Towns;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by Todor Ilchev on 2016-10-25.
 */
public class Pr04RemoveObjects {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        List<Towns> towns = em.createQuery("SELECT t FROM Towns AS t").getResultList();
        for (Towns town : towns) {
            if (town.getName().length() <= 5) {
                em.persist(town);
                String newName = town.getName().toLowerCase();
                town.setName(newName);
            }
            System.out.println(town.getName());
        }

        transaction.commit();
        em.close();
        emf.close();
    }
}

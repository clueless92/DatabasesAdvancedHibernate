package bg.tilchev.pr08AddingNewAddressAndUpdatingEmployee;

import bg.tilchev.entities.softuni.Addresses;
import bg.tilchev.entities.softuni.Employees;

import javax.persistence.*;

/**
 * Created by Todor Ilchev on 2016-10-26.
 */
public class Pr08AddingNewAddressAndUpdatingEmployee {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Addresses vitoshka15 = new Addresses();
        vitoshka15.setTownId(32);
        vitoshka15.setAddressText("Vitoshka 15");
        em.persist(vitoshka15);

        Query query = em.createQuery("SELECT e FROM Employees AS e " +
                " WHERE e.lastName = 'Nakov'");
        Employees shefa = (Employees) query.getSingleResult();
        em.merge(shefa);
        shefa.setAddressId(vitoshka15.getAddressId());

        transaction.commit();
        em.close();
        emf.close();
    }
}

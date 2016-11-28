package bg.tilchev.pr06DataRefresh;

import bg.tilchev.entities.softuni.Employees;

import javax.persistence.*;
import java.io.IOException;

/**
 * Created by Todor Ilchev on 2016-10-25.
 */
public class Pr06DataRefresh {

    public static void main(String[] args) throws IOException {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Query query = em.createQuery("SELECT e FROM Employees AS e " +
                                     " WHERE e.id = 4");
        Employees employee = (Employees) query.getSingleResult();
        String upperCaseName = employee.getFirstName().toUpperCase();
        employee.setFirstName(upperCaseName);
        em.refresh(employee);
        em.persist(employee);

        transaction.commit();
        em.close();
        emf.close();
        System.out.println(employee.getFirstName());
    }
}

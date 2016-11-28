package bg.tilchev.pr14RemoveTowns;

import javax.persistence.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Todor Ilchev on 2016-10-26.
 */
public class Pr14RemoveTowns {

    public static void main(String[] args) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int townId = Integer.parseInt(reader.readLine());

        Query query = em.createQuery("DELETE FROM Addresses AS a " +
                " WHERE a.townId = :townId");
        query.setParameter("townId", townId);
        query.executeUpdate();
        query = em.createQuery("DELETE FROM Towns AS t " +
                " WHERE t.townId = :townId");
        query.setParameter("townId", townId);
        query.executeUpdate();

        transaction.commit();
        em.close();
        emf.close();
    }
}

package bg.tilchev.pr15FindEmployeesByFirstName;

import bg.tilchev.entities.softuni.Employees;

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
public class Pr15FindEmployeesByFirstName {

    public static void main(String[] args) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String prefix = reader.readLine().toLowerCase();
        int len = prefix.length();

        Query query = em.createQuery(
                "SELECT e " +
                "  FROM Employees AS e " +
                " WHERE LOWER(SUBSTRING(e.firstName, 1, :len)) = :prefix");
        query.setParameter("len", len);
        query.setParameter("prefix", prefix);
        List<Employees> employees = query.getResultList();

        for (Employees employee : employees) {
            System.out.printf("%s %s - %s - (%s)%n",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getJobTitle(),
                    employee.getSalary());
        }

        em.close();
        emf.close();
    }
}

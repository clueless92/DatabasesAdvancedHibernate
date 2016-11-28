package bg.tilchev.pr07EmployeeQueries;

import bg.tilchev.entities.softuni.Employees;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Todor Ilchev on 2016-10-26.
 */
public class Pr07Stp2EmployeeQueries {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("SELECT e FROM Employees AS e " +
                                     " WHERE e.departmentId = 6 " +
                                     " ORDER BY e.salary ASC, " +
                                     "          e.firstName DESC");
        List<Employees> result = query.getResultList();
        for (Employees employee : result) {
            System.out.printf("%s %s from Research and Development, earns %s%n",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getSalary().toString());
        }

        em.close();
        emf.close();
    }
}

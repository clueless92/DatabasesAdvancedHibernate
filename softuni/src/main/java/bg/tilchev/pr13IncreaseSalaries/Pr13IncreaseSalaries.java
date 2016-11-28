package bg.tilchev.pr13IncreaseSalaries;

import bg.tilchev.entities.softuni.Employees;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

public class Pr13IncreaseSalaries {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Query query = em.createQuery("SELECT e " +
                                     "  FROM Employees AS e " +
                                     " INNER JOIN Departments AS d" +
                                     "    ON e.departmentId = d.departmentId" +
                                     " WHERE d.name = 'Engineering' " +
                                     "    OR d.name = 'Tool Design'" +
                                     "    OR d.name = 'Marketing'" +
                                     "    OR d.name = 'Information Services'");
        List<Employees> employees = query.getResultList();

        for (Employees employee : employees) {
            em.merge(employee);
            BigDecimal newSalary = employee.getSalary().multiply(new BigDecimal(1.12));
            employee.setSalary(newSalary);
        }

        transaction.commit();
        em.close();
        emf.close();
    }
}

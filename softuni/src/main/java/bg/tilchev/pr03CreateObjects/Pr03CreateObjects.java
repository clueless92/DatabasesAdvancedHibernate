package bg.tilchev.pr03CreateObjects;

import bg.tilchev.entities.softuni.Addresses;
import bg.tilchev.entities.softuni.Departments;
import bg.tilchev.entities.softuni.Employees;
import bg.tilchev.entities.softuni.Towns;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by Todor Ilchev on 2016-10-25.
 */
public class Pr03CreateObjects {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Departments departmentTraining = new Departments();
        departmentTraining.setName("Training Department");
        departmentTraining.setManagerId(1);
        em.persist(departmentTraining);
        int departmentId = departmentTraining.getDepartmentId();

        Towns burgas = new Towns();
        burgas.setName("Burgas");
        em.persist(burgas);
        int townId = burgas.getTownId();

        for (int i = 0; i < 5; i++) {
            Addresses address = new Addresses();
            address.setAddressText("Address" + i);
            address.setTownId(townId);
            em.persist(address);

            Employees employee = new Employees();
            employee.setFirstName("Pesho" + i);
            employee.setLastName("Peshov" + i);
            employee.setJobTitle("Bachkator" + i);
            employee.setSalary(new BigDecimal(i * 1000));
            employee.setHireDate(new Timestamp(System.currentTimeMillis()));
            employee.setDepartmentId(departmentId);
            int addressId = address.getAddressId();
            employee.setAddressId(addressId);
            em.persist(employee);
        }

        transaction.commit();
        em.close();
        emf.close();
    }
}

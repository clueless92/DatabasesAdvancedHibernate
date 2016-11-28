package bg.tilchev.entities.softuni;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by Todor Ilchev on 2016-10-25.
 */
@Entity
public class Employees {

    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;

    @Basic
    @Column(name = "first_name")
    private String firstName;

    @Basic
    @Column(name = "last_name")
    private String lastName;

    @Basic
    @Column(name = "middle_name")
    private String middleName;

    @Basic
    @Column(name = "job_title")
    private String jobTitle;

    @Basic
    @Column(name = "hire_date")
    private Timestamp hireDate;

    @Basic
    @Column(name = "salary")
    private BigDecimal salary;

    @Column(name = "department_id")
    private int departmentId;

    @Column(name = "address_id")
    private int addressId;

    @Column(name = "manager_id")
    private int managerId;

    public int getManagerId() {
        return this.managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getAddressId() {
        return this.addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getDepartmentId() {
        return this.departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getJobTitle() {
        return this.jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Timestamp getHireDate() {
        return this.hireDate;
    }

    public void setHireDate(Timestamp hireDate) {
        this.hireDate = hireDate;
    }

    public BigDecimal getSalary() {
        return this.salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Employees employees = (Employees) o;

        if (this.employeeId != employees.employeeId) return false;
        if (this.firstName != null ? !this.firstName.equals(employees.firstName) : employees.firstName != null) return false;
        if (this.lastName != null ? !this.lastName.equals(employees.lastName) : employees.lastName != null) return false;
        if (this.middleName != null ? !this.middleName.equals(employees.middleName) : employees.middleName != null) return false;
        if (this.jobTitle != null ? !this.jobTitle.equals(employees.jobTitle) : employees.jobTitle != null) return false;
        if (this.hireDate != null ? !this.hireDate.equals(employees.hireDate) : employees.hireDate != null) return false;
        if (this.salary != null ? !this.salary.equals(employees.salary) : employees.salary != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = this.employeeId;
        result = 31 * result + (this.firstName != null ? this.firstName.hashCode() : 0);
        result = 31 * result + (this.lastName != null ? this.lastName.hashCode() : 0);
        result = 31 * result + (this.middleName != null ? this.middleName.hashCode() : 0);
        result = 31 * result + (this.jobTitle != null ? this.jobTitle.hashCode() : 0);
        result = 31 * result + (this.hireDate != null ? this.hireDate.hashCode() : 0);
        result = 31 * result + (this.salary != null ? this.salary.hashCode() : 0);
        return result;
    }
}

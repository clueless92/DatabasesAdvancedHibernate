package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by Todor Ilchev on 2016-10-28.
 */
@Entity
public class Employees {

    private Integer employeeId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String jobTitle;
    private Integer departmentId;
    private Integer managerId;
    private Timestamp hireDate;
    private BigDecimal salary;
    private Integer addressId;
    private Collection<Departments> departmentsesByEmployeeId;
    private Departments departmentsByDepartmentId;
    private Employees employeesByManagerId;
    private Collection<Employees> employeesByEmployeeId;
    private Addresses addressesByAddressId;
    private Collection<EmployeesProjects> employeesProjectsesByEmployeeId;

    @Id
    @Column(name = "employee_id")
    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "middle_name")
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Basic
    @Column(name = "job_title")
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Basic
    @Column(name = "department_id")
    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    @Basic
    @Column(name = "manager_id")
    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    @Basic
    @Column(name = "hire_date")
    public Timestamp getHireDate() {
        return hireDate;
    }

    public void setHireDate(Timestamp hireDate) {
        this.hireDate = hireDate;
    }

    @Basic
    @Column(name = "salary")
    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Basic
    @Column(name = "address_id")
    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employees employees = (Employees) o;

        if (employeeId != null ? !employeeId.equals(employees.employeeId) : employees.employeeId != null) return false;
        if (firstName != null ? !firstName.equals(employees.firstName) : employees.firstName != null) return false;
        if (lastName != null ? !lastName.equals(employees.lastName) : employees.lastName != null) return false;
        if (middleName != null ? !middleName.equals(employees.middleName) : employees.middleName != null) return false;
        if (jobTitle != null ? !jobTitle.equals(employees.jobTitle) : employees.jobTitle != null) return false;
        if (departmentId != null ? !departmentId.equals(employees.departmentId) : employees.departmentId != null)
            return false;
        if (managerId != null ? !managerId.equals(employees.managerId) : employees.managerId != null) return false;
        if (hireDate != null ? !hireDate.equals(employees.hireDate) : employees.hireDate != null) return false;
        if (salary != null ? !salary.equals(employees.salary) : employees.salary != null) return false;
        if (addressId != null ? !addressId.equals(employees.addressId) : employees.addressId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = employeeId != null ? employeeId.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (jobTitle != null ? jobTitle.hashCode() : 0);
        result = 31 * result + (departmentId != null ? departmentId.hashCode() : 0);
        result = 31 * result + (managerId != null ? managerId.hashCode() : 0);
        result = 31 * result + (hireDate != null ? hireDate.hashCode() : 0);
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        result = 31 * result + (addressId != null ? addressId.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "employeesByManagerId")
    public Collection<Departments> getDepartmentsesByEmployeeId() {
        return departmentsesByEmployeeId;
    }

    public void setDepartmentsesByEmployeeId(Collection<Departments> departmentsesByEmployeeId) {
        this.departmentsesByEmployeeId = departmentsesByEmployeeId;
    }

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "department_id", nullable = false)
    public Departments getDepartmentsByDepartmentId() {
        return departmentsByDepartmentId;
    }

    public void setDepartmentsByDepartmentId(Departments departmentsByDepartmentId) {
        this.departmentsByDepartmentId = departmentsByDepartmentId;
    }

    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "employee_id")
    public Employees getEmployeesByManagerId() {
        return employeesByManagerId;
    }

    public void setEmployeesByManagerId(Employees employeesByManagerId) {
        this.employeesByManagerId = employeesByManagerId;
    }

    @OneToMany(mappedBy = "employeesByManagerId")
    public Collection<Employees> getEmployeesByEmployeeId() {
        return employeesByEmployeeId;
    }

    public void setEmployeesByEmployeeId(Collection<Employees> employeesByEmployeeId) {
        this.employeesByEmployeeId = employeesByEmployeeId;
    }

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    public Addresses getAddressesByAddressId() {
        return addressesByAddressId;
    }

    public void setAddressesByAddressId(Addresses addressesByAddressId) {
        this.addressesByAddressId = addressesByAddressId;
    }

    @OneToMany(mappedBy = "employeesByEmployeeId")
    public Collection<EmployeesProjects> getEmployeesProjectsesByEmployeeId() {
        return employeesProjectsesByEmployeeId;
    }

    public void setEmployeesProjectsesByEmployeeId(Collection<EmployeesProjects> employeesProjectsesByEmployeeId) {
        this.employeesProjectsesByEmployeeId = employeesProjectsesByEmployeeId;
    }
}

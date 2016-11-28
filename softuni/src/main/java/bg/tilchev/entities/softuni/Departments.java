package bg.tilchev.entities.softuni;

import javax.persistence.*;

/**
 * Created by Todor Ilchev on 2016-10-25.
 */
@Entity
public class Departments {

    @Id
    @Column(name = "department_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int departmentId;

    @Basic
    @Column(name = "name")
    private String name;

    @Column(name = "manager_id")
    private int managerId;

    public int getDepartmentId() {
        return this.departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getManagerId() {
        return this.managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Departments that = (Departments) o;

        if (this.departmentId != that.departmentId) return false;
        if (this.name != null ? !this.name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = this.departmentId;
        result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
        return result;
    }
}

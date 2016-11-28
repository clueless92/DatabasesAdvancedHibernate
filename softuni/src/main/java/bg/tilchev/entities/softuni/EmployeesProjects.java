package bg.tilchev.entities.softuni;

import javax.persistence.*;

/**
 * Created by Todor Ilchev on 2016-10-25.
 */
@Entity
@Table(name = "employees_projects", schema = "soft_uni", catalog = "")
@IdClass(EmployeesProjectsPK.class)
public class EmployeesProjects {

    private int employeeId;
    private int projectId;

    @Id
    @Column(name = "employee_id")
    public int getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Id
    @Column(name = "project_id")
    public int getProjectId() {
        return this.projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        EmployeesProjects that = (EmployeesProjects) o;

        if (this.employeeId != that.employeeId) return false;
        if (this.projectId != that.projectId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = this.employeeId;
        result = 31 * result + this.projectId;
        return result;
    }
}

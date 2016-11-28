package bg.tilchev.entities.softuni;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Todor Ilchev on 2016-10-25.
 */
public class EmployeesProjectsPK implements Serializable {

    private int employeeId;
    private int projectId;

    @Column(name = "employee_id")
    @Id
    public int getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Column(name = "project_id")
    @Id
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

        EmployeesProjectsPK that = (EmployeesProjectsPK) o;

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

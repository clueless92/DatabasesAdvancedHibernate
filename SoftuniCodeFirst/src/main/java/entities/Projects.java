package entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by Todor Ilchev on 2016-10-28.
 */
@Entity
public class Projects {

    private Integer projectId;
    private String name;
    private String description;
    private Timestamp startDate;
    private Timestamp endDate;
    private Collection<EmployeesProjects> employeesProjectsesByProjectId;

    @Id
    @Column(name = "project_id")
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "start_date")
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "end_date")
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Projects projects = (Projects) o;

        if (projectId != null ? !projectId.equals(projects.projectId) : projects.projectId != null) return false;
        if (name != null ? !name.equals(projects.name) : projects.name != null) return false;
        if (description != null ? !description.equals(projects.description) : projects.description != null)
            return false;
        if (startDate != null ? !startDate.equals(projects.startDate) : projects.startDate != null) return false;
        if (endDate != null ? !endDate.equals(projects.endDate) : projects.endDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = projectId != null ? projectId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "projectsByProjectId")
    public Collection<EmployeesProjects> getEmployeesProjectsesByProjectId() {
        return employeesProjectsesByProjectId;
    }

    public void setEmployeesProjectsesByProjectId(Collection<EmployeesProjects> employeesProjectsesByProjectId) {
        this.employeesProjectsesByProjectId = employeesProjectsesByProjectId;
    }
}

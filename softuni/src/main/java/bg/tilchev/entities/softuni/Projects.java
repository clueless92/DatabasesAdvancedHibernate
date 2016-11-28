package bg.tilchev.entities.softuni;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Todor Ilchev on 2016-10-25.
 */
@Entity
public class Projects {

    private int projectId;
    private String name;
    private String description;
    private Timestamp startDate;
    private Timestamp endDate;

    @Id
    @Column(name = "project_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getProjectId() {
        return this.projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "start_date")
    public Timestamp getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "end_date")
    public Timestamp getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Projects projects = (Projects) o;

        if (this.projectId != projects.projectId) return false;
        if (this.name != null ? !this.name.equals(projects.name) : projects.name != null) return false;
        if (this.description != null ? !this.description.equals(projects.description) : projects.description != null)
            return false;
        if (this.startDate != null ? !this.startDate.equals(projects.startDate) : projects.startDate != null) return false;
        if (this.endDate != null ? !this.endDate.equals(projects.endDate) : projects.endDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = this.projectId;
        result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
        result = 31 * result + (this.description != null ? this.description.hashCode() : 0);
        result = 31 * result + (this.startDate != null ? this.startDate.hashCode() : 0);
        result = 31 * result + (this.endDate != null ? this.endDate.hashCode() : 0);
        return result;
    }
}

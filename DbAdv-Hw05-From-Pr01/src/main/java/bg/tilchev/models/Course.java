package bg.tilchev.models;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Todor Ilchev on 2016-11-08.
 */

@Entity
@Table(name = "courses")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(columnDefinition = "TEXT DEFAULT NULL")
    private String description;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @Column
    private BigDecimal price;

    @ManyToMany(mappedBy = "courses", targetEntity = Student.class,
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Student> students;

    @OneToMany(mappedBy = "course", targetEntity = Resource.class,
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Resource> resources;

    @OneToMany(mappedBy = "course", targetEntity = Homework.class,
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Homework> homeworkSubmissions;

    public Course() {
        super();
        this.setStudents(new HashSet<>());
        this.setResources(new HashSet<>());
        this.setHomeworkSubmissions(new HashSet<>());
    }

    public Course(String name, String description, Date startDate, Date endDate, BigDecimal price) {
        this();
        this.setName(name);
        this.setDescription(description);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.setPrice(price);
    }

    public Long getId() {
        return this.id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<Student> getStudents() {
        return this.students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Resource> getResources() {
        return this.resources;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }

    public Set<Homework> getHomeworkSubmissions() {
        return this.homeworkSubmissions;
    }

    public void setHomeworkSubmissions(Set<Homework> homeworkSubmissions) {
        this.homeworkSubmissions = homeworkSubmissions;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + this.id +
                ", name='" + this.name + '\'' +
                ", description='" + this.description + '\'' +
                ", startDate=" + this.startDate +
                ", endDate=" + this.endDate +
                ", price=" + this.price +
                '}';
    }
}

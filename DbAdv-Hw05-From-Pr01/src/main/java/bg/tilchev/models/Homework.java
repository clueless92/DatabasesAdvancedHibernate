package bg.tilchev.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Todor Ilchev on 2016-11-08.
 */

@Entity
@Table(name = "homeworks")
public class Homework implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column
    @Enumerated(value = EnumType.STRING)
    private ContentType contentType;

    @Column
    private Date submissionDate;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    public Homework() {
        super();
    }

    public Homework(String content, ContentType contentType, Date submissionDate, Course course, Student student) {
        this(content, contentType, submissionDate);
        this.setCourse(course);
        this.setStudent(student);
    }

    public Homework(String content, ContentType contentType, Date submissionDate) {
        this.setContent(content);
        this.setContentType(contentType);
        this.setSubmissionDate(submissionDate);
    }

    public Long getId() {
        return this.id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ContentType getContentType() {
        return this.contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public Date getSubmissionDate() {
        return this.submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    private Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "id=" + this.id +
                ", content='" + this.content + '\'' +
                ", contentType=" + this.contentType +
                ", submissionDate=" + this.submissionDate +
                '}';
    }
}

package bg.tilchev.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Todor Ilchev on 2016-11-08.
 */

@Entity
@Table(name = "resources")
public class Resource implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column
    @Enumerated(value = EnumType.STRING)
    private TypeOfResource typeOfResource;

    @Column
    private String url;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    @OneToMany(mappedBy = "resource", targetEntity = Licence.class,
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Licence> licences;

    public Resource() {
        super();
        this.setLicences(new HashSet<>());
    }

    public Resource(String name, TypeOfResource typeOfResource, String url) {
        this.setName(name);
        this.setTypeOfResource(typeOfResource);
        this.setUrl(url);
    }

    public Resource(String name, TypeOfResource typeOfResource, String url, Course course) {
        this(name, typeOfResource, url);
        this.setCourse(course);
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

    public TypeOfResource getTypeOfResource() {
        return this.typeOfResource;
    }

    public void setTypeOfResource(TypeOfResource typeOfResource) {
        this.typeOfResource = typeOfResource;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Set<Licence> getLicences() {
        return this.licences;
    }

    public void setLicences(Set<Licence> licences) {
        this.licences = licences;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + this.id +
                ", name='" + this.name + '\'' +
                ", typeOfResource=" + this.typeOfResource +
                ", url='" + this.url + '\'' +
                '}';
    }
}

package bg.tilchev.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Todor Ilchev on 2016-11-10.
 */

@Entity
@Table(name = "doctors")
public class Doctor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 100)
    private String speciality;

    @OneToMany(mappedBy = "doctor", targetEntity = Visitation.class)
    private Set<Visitation> visitations;

    public Doctor() {
        super();
        this.setVisitations(new HashSet<>());
    }

    public Doctor(String name, String speciality) {
        this();
        this.name = name;
        this.speciality = speciality;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return this.speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Set<Visitation> getVisitations() {
        if (this.visitations == null) {
            this.visitations = new HashSet<>();
        }
        return this.visitations;
    }

    public void setVisitations(Set<Visitation> visitations) {
        this.visitations = visitations;
    }

    public void addVisitation(Visitation visitation) {
        this.getVisitations().add(visitation);
    }
}

package bg.tilchev.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Todor Ilchev on 2016-11-19.
 */

@Entity
@Table(name = "planets")
public class Planet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sun_id", referencedColumnName = "id")
    private Star sun;

    @ManyToOne(optional = false)
    @JoinColumn(name = "solar_system_id", referencedColumnName = "id")
    private SolarSystem solarSystem;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "homePlanet",
        fetch = FetchType.LAZY, targetEntity = Person.class)
    private Set<Person> persons;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "originPlanet",
        fetch = FetchType.LAZY, targetEntity = Anomaly.class)
    private Set<Anomaly> originatedAnomalies;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teleportPlanet",
        fetch = FetchType.LAZY, targetEntity = Anomaly.class)
    private Set<Anomaly> teleportedAnomalies;

    public Planet() {
        super();
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

    public Star getSun() {
        return this.sun;
    }

    public void setSun(Star sun) {
        this.sun = sun;
    }

    public SolarSystem getSolarSystem() {
        return this.solarSystem;
    }

    public void setSolarSystem(SolarSystem solarSystem) {
        this.solarSystem = solarSystem;
    }

    public Set<Person> getPersons() {
        if(this.persons == null) {
            this.setPersons(new LinkedHashSet<>());
        }
        return this.persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    public void addPerson(Person person) {
        this.getPersons().add(person);
    }

    public Set<Anomaly> getOriginatedAnomalies() {
        if(this.originatedAnomalies == null) {
            this.setOriginatedAnomalies(new LinkedHashSet<>());
        }
        return this.originatedAnomalies;
    }

    public void setOriginatedAnomalies(Set<Anomaly> originatedAnomalies) {
        this.originatedAnomalies = originatedAnomalies;
    }

    public void addOriginatedAnomaly(Anomaly originatedAnomaly) {
        this.getOriginatedAnomalies().add(originatedAnomaly);
    }

    public Set<Anomaly> getTeleportedAnomalies() {
        if(this.teleportedAnomalies == null) {
            this.setTeleportedAnomalies(new LinkedHashSet<>());
        }
        return this.teleportedAnomalies;
    }

    public void setTeleportedAnomalies(Set<Anomaly> teleportedAnomalies) {
        this.teleportedAnomalies = teleportedAnomalies;
    }

    public void addTeleportedAnomaly(Anomaly teleportedAnomaly) {
        this.getTeleportedAnomalies().add(teleportedAnomaly);
    }
}

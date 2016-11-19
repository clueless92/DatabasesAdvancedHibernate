package bg.tilchev.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Todor Ilchev on 2016-11-19.
 */

@Entity
@Table(name = "persons")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "home_planet_id", referencedColumnName = "id")
    private Planet homePlanet;

    @ManyToMany(mappedBy = "victims", targetEntity = Anomaly.class,
        cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Anomaly> anomalies;

    public Person() {
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

    public Planet getHomePlanet() {
        return this.homePlanet;
    }

    public void setHomePlanet(Planet homePlanet) {
        this.homePlanet = homePlanet;
    }

    public Set<Anomaly> getAnomalies() {
        if(this.anomalies == null) {
            this.setAnomalies(new LinkedHashSet<>());
        }
        return this.anomalies;
    }

    public void setAnomalies(Set<Anomaly> anomalies) {
        this.anomalies = anomalies;
    }

    public void addAnomaly(Anomaly anomaly) {
        this.getAnomalies().add(anomaly);
    }
}

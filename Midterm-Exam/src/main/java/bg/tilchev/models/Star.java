package bg.tilchev.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Todor Ilchev on 2016-11-19.
 */

@Entity
@Table(name = "stars")
public class Star implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "solar_system_id", referencedColumnName = "id")
    private SolarSystem solarSystem;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sun",
        fetch = FetchType.LAZY, targetEntity = Planet.class)
    private Set<Planet> planets;

    public Star() {
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

    public SolarSystem getSolarSystem() {
        return this.solarSystem;
    }

    public void setSolarSystem(SolarSystem solarSystem) {
        this.solarSystem = solarSystem;
    }

    public Set<Planet> getPlanets() {
        if(this.planets == null) {
            this.setPlanets(new LinkedHashSet<>());
        }
        return this.planets;
    }

    public void setPlanets(Set<Planet> planets) {
        this.planets = planets;
    }

    public void addPlanet(Planet planet) {
        this.getPlanets().add(planet);
    }
}

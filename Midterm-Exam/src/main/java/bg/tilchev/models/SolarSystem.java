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
@Table(name = "solar_systems")
public class SolarSystem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "solarSystem",
        fetch = FetchType.LAZY, targetEntity = Planet.class)
    private Set<Planet> planets;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "solarSystem",
        fetch = FetchType.LAZY, targetEntity = Star.class)
    private Set<Star> stars;

    public SolarSystem() {
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

    public Set<Star> getStars() {
        if(this.stars == null) {
            this.setStars(new LinkedHashSet<>());
        }
        return this.stars;
    }

    public void setStars(Set<Star> stars) {
        this.stars = stars;
    }

    public void addStar(Star star) {
        this.getStars().add(star);
    }
}

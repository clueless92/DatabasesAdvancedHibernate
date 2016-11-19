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
@Table(name = "anomalies")
public class Anomaly implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "origin_planet_id", referencedColumnName = "id")
    private Planet originPlanet;

    @ManyToOne(optional = false)
    @JoinColumn(name = "teleport_planet_id", referencedColumnName = "id")
    private Planet teleportPlanet;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "anomaly_victims",
        joinColumns = @JoinColumn(name = "anomaly_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"))
    private Set<Person> victims;

    public Anomaly() {
        super();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Planet getOriginPlanet() {
        return this.originPlanet;
    }

    public void setOriginPlanet(Planet originPlanet) {
        this.originPlanet = originPlanet;
    }

    public Planet getTeleportPlanet() {
        return this.teleportPlanet;
    }

    public void setTeleportPlanet(Planet teleportPlanet) {
        this.teleportPlanet = teleportPlanet;
    }

    public Set<Person> getVictims() {
        if(this.victims == null) {
            this.setVictims(new LinkedHashSet<>());
        }
        return this.victims;
    }

    public void setVictims(Set<Person> victims) {
        this.victims = victims;
    }

    public void addVictim(Person victim) {
        this.getVictims().add(victim);
    }
}

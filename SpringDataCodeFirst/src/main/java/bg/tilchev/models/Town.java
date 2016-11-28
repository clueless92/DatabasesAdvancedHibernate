package bg.tilchev.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Todor Ilchev on 2016-11-10.
 */
@Entity
@Table(name = "towns")
public class Town implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String country;

    @OneToMany(mappedBy = "currentTown", targetEntity = User.class,
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<User> usersCurrentlyInTown;

    @OneToMany(mappedBy = "homeTown", targetEntity = User.class,
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<User> usersBornInTown;

    public Town() {
        super();
        this.setUsersBornInTown(new HashSet<>());
        this.setUsersCurrentlyInTown(new HashSet<>());
    }

    public Town(String name, String country) {
        this();
        this.setName(name);
        this.setCountry(country);
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

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<User> getUsersCurrentlyInTown() {
        return this.usersCurrentlyInTown;
    }

    public void setUsersCurrentlyInTown(Set<User> usersCurrentlyInTown) {
        this.usersCurrentlyInTown = usersCurrentlyInTown;
    }

    public Set<User> getUsersBornInTown() {
        return this.usersBornInTown;
    }

    public void setUsersBornInTown(Set<User> usersBornInTown) {
        this.usersBornInTown = usersBornInTown;
    }
}

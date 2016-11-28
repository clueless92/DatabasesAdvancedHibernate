package bg.tilchev.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Todor Ilchev on 2016-11-10.
 */

@Entity
@Table(name = "licences")
public class Licence implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "resource_id", referencedColumnName = "id")
    private Resource resource;

    public Licence() {
        super();
    }

    public Licence(String name) {
        this.setName(name);
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

    public Resource getResource() {
        return this.resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}

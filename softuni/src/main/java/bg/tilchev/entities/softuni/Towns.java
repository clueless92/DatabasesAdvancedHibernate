package bg.tilchev.entities.softuni;

import javax.persistence.*;

/**
 * Created by Todor Ilchev on 2016-10-25.
 */
@Entity
public class Towns {

    @Id
    @Column(name = "town_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int townId;

    @Basic
    @Column(name = "name")
    private String name;

    public int getTownId() {
        return this.townId;
    }

    public void setTownId(int townId) {
        this.townId = townId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Towns towns = (Towns) o;

        if (this.townId != towns.townId) return false;
        if (this.name != null ? !this.name.equals(towns.name) : towns.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = this.townId;
        result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
        return result;
    }
}

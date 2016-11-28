package entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Todor Ilchev on 2016-10-28.
 */
@Entity
public class Towns {

    private Integer townId;
    private String name;
    private Collection<Addresses> addressesByTownId;

    @Id
    @Column(name = "town_id")
    public Integer getTownId() {
        return townId;
    }

    public void setTownId(Integer townId) {
        this.townId = townId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Towns towns = (Towns) o;

        if (townId != null ? !townId.equals(towns.townId) : towns.townId != null) return false;
        if (name != null ? !name.equals(towns.name) : towns.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = townId != null ? townId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "townsByTownId")
    public Collection<Addresses> getAddressesByTownId() {
        return addressesByTownId;
    }

    public void setAddressesByTownId(Collection<Addresses> addressesByTownId) {
        this.addressesByTownId = addressesByTownId;
    }
}

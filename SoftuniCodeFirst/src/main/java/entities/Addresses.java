package entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Todor Ilchev on 2016-10-28.
 */
@Entity
public class Addresses {

    private Integer addressId;
    private String addressText;
    private Integer townId;
    private Towns townsByTownId;
    private Collection<Employees> employeesByAddressId;

    @Id
    @Column(name = "address_id")
    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    @Basic
    @Column(name = "address_text")
    public String getAddressText() {
        return addressText;
    }

    public void setAddressText(String addressText) {
        this.addressText = addressText;
    }

    @Basic
    @Column(name = "town_id")
    public Integer getTownId() {
        return townId;
    }

    public void setTownId(Integer townId) {
        this.townId = townId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Addresses addresses = (Addresses) o;

        if (addressId != null ? !addressId.equals(addresses.addressId) : addresses.addressId != null) return false;
        if (addressText != null ? !addressText.equals(addresses.addressText) : addresses.addressText != null)
            return false;
        if (townId != null ? !townId.equals(addresses.townId) : addresses.townId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = addressId != null ? addressId.hashCode() : 0;
        result = 31 * result + (addressText != null ? addressText.hashCode() : 0);
        result = 31 * result + (townId != null ? townId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "town_id", referencedColumnName = "town_id")
    public Towns getTownsByTownId() {
        return townsByTownId;
    }

    public void setTownsByTownId(Towns townsByTownId) {
        this.townsByTownId = townsByTownId;
    }

    @OneToMany(mappedBy = "addressesByAddressId")
    public Collection<Employees> getEmployeesByAddressId() {
        return employeesByAddressId;
    }

    public void setEmployeesByAddressId(Collection<Employees> employeesByAddressId) {
        this.employeesByAddressId = employeesByAddressId;
    }
}

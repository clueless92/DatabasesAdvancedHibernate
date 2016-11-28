package bg.tilchev.entities.softuni;

import javax.persistence.*;

/**
 * Created by Todor Ilchev on 2016-10-25.
 */
@Entity
public class Addresses {

    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;

    @Basic
    @Column(name = "address_text")
    private String addressText;

    @Column(name = "town_id")
    private int townId;

    public int getTownId() {
        return this.townId;
    }

    public void setTownId(int townId) {
        this.townId = townId;
    }

    public int getAddressId() {
        return this.addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getAddressText() {
        return this.addressText;
    }

    public void setAddressText(String addressText) {
        this.addressText = addressText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Addresses addresses = (Addresses) o;

        if (this.addressId != addresses.addressId) return false;
        if (this.addressText != null ? !this.addressText.equals(addresses.addressText) : addresses.addressText != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = this.addressId;
        result = 31 * result + (this.addressText != null ? this.addressText.hashCode() : 0);
        return result;
    }
}

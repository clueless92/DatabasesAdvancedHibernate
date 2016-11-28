package bg.tilchev.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Created by Todor Ilchev on 2016-11-11.
 */

@Entity
//@DiscriminatorValue("CC")
@Table(name = "credit_cards")
@PrimaryKeyJoinColumn(name = "id")
public class CreditCard extends BillingDetails {

    @Column
    private String cardType;

    @Column
    private byte expirationMonth;

    @Column
    private short expirationYear;

    public CreditCard() {
        super();
    }

    public String getCardType() {
        return this.cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public byte getExpirationMonth() {
        return this.expirationMonth;
    }

    public void setExpirationMonth(byte expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public short getExpirationYear() {
        return this.expirationYear;
    }

    public void setExpirationYear(short expirationYear) {
        this.expirationYear = expirationYear;
    }
}

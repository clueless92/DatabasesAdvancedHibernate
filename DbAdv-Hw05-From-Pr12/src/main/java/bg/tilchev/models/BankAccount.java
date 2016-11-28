package bg.tilchev.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Created by Todor Ilchev on 2016-11-11.
 */

@Entity
//@DiscriminatorValue("BA")
@Table(name = "bank_accounts")
@PrimaryKeyJoinColumn(name = "id")
public class BankAccount extends BillingDetails {

    @Column
    private String bankName;

    @Column
    private String swiftCode;

    public BankAccount() {
        super();
    }

    public String getBankName() {
        return this.bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSwiftCode() {
        return this.swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }
}

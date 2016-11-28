package bg.tilchev.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by Todor Ilchev on 2016-11-11.
 */

@Entity
@Table(name = "checking_accounts")
@PrimaryKeyJoinColumn(name = "id")
public class CheckingAccount extends BankAccount {

    @Column
    private BigDecimal fee;

    public CheckingAccount() {
        super();
    }

    public BigDecimal getFee() {
        return this.fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public CheckingAccount(BigDecimal balance, BigDecimal fee) {
        super(balance);
        this.fee = fee;
    }

    public void deductFee() {
        this.setBalance(this.getBalance().subtract(this.fee));
    }
}

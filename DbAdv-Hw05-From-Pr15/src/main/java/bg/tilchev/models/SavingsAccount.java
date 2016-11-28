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
@Table(name = "savings_accounts")
@PrimaryKeyJoinColumn(name = "id")
public class SavingsAccount extends BankAccount {

    @Column
    private BigDecimal interestRate;

    public SavingsAccount() {
        super();
    }

    public BigDecimal getInterestRate() {
        return this.interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public SavingsAccount(BigDecimal balance, BigDecimal interestRate) {
        super(balance);
        this.interestRate = interestRate;
    }

    public void addInterest() {
        this.setBalance(this.getBalance().add(this.interestRate));
    }
}

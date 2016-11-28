package bg.tilchev.entities.gringotts;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by Todor Ilchev on 2016-10-27.
 */
@Entity
@Table(name = "wizzard_deposits", schema = "gringotts", catalog = "")
public class WizzardDeposits {

    private int id;
    private String firstName;
    private String lastName;
    private String notes;
    private Integer age;
    private String magicWandCreator;
    private Short magicWandSize;
    private String depositGroup;
    private Date depositStartDate;
    private BigDecimal depositAmount;
    private BigDecimal depositInterest;
    private BigDecimal depositCharge;
    private Date depositExpirationDate;
    private Boolean isDepositExpired;

    @Id
    @Column(name = "id")
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "notes")
    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Basic
    @Column(name = "age")
    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "magic_wand_creator")
    public String getMagicWandCreator() {
        return this.magicWandCreator;
    }

    public void setMagicWandCreator(String magicWandCreator) {
        this.magicWandCreator = magicWandCreator;
    }

    @Basic
    @Column(name = "magic_wand_size")
    public Short getMagicWandSize() {
        return this.magicWandSize;
    }

    public void setMagicWandSize(Short magicWandSize) {
        this.magicWandSize = magicWandSize;
    }

    @Basic
    @Column(name = "deposit_group")
    public String getDepositGroup() {
        return this.depositGroup;
    }

    public void setDepositGroup(String depositGroup) {
        this.depositGroup = depositGroup;
    }

    @Basic
    @Column(name = "deposit_start_date")
    public Date getDepositStartDate() {
        return this.depositStartDate;
    }

    public void setDepositStartDate(Date depositStartDate) {
        this.depositStartDate = depositStartDate;
    }

    @Basic
    @Column(name = "deposit_amount")
    public BigDecimal getDepositAmount() {
        return this.depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    @Basic
    @Column(name = "deposit_interest")
    public BigDecimal getDepositInterest() {
        return this.depositInterest;
    }

    public void setDepositInterest(BigDecimal depositInterest) {
        this.depositInterest = depositInterest;
    }

    @Basic
    @Column(name = "deposit_charge")
    public BigDecimal getDepositCharge() {
        return this.depositCharge;
    }

    public void setDepositCharge(BigDecimal depositCharge) {
        this.depositCharge = depositCharge;
    }

    @Basic
    @Column(name = "deposit_expiration_date")
    public Date getDepositExpirationDate() {
        return this.depositExpirationDate;
    }

    public void setDepositExpirationDate(Date depositExpirationDate) {
        this.depositExpirationDate = depositExpirationDate;
    }

    @Basic
    @Column(name = "is_deposit_expired")
    public Boolean getDepositExpired() {
        return this.isDepositExpired;
    }

    public void setDepositExpired(Boolean depositExpired) {
        this.isDepositExpired = depositExpired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        WizzardDeposits that = (WizzardDeposits) o;

        if (this.id != that.id) return false;
        if (this.firstName != null ? !this.firstName.equals(that.firstName) : that.firstName != null) return false;
        if (this.lastName != null ? !this.lastName.equals(that.lastName) : that.lastName != null) return false;
        if (this.notes != null ? !this.notes.equals(that.notes) : that.notes != null) return false;
        if (this.age != null ? !this.age.equals(that.age) : that.age != null) return false;
        if (this.magicWandCreator != null ? !this.magicWandCreator.equals(that.magicWandCreator) : that.magicWandCreator != null)
            return false;
        if (this.magicWandSize != null ? !this.magicWandSize.equals(that.magicWandSize) : that.magicWandSize != null)
            return false;
        if (this.depositGroup != null ? !this.depositGroup.equals(that.depositGroup) : that.depositGroup != null) return false;
        if (this.depositStartDate != null ? !this.depositStartDate.equals(that.depositStartDate) : that.depositStartDate != null)
            return false;
        if (this.depositAmount != null ? !this.depositAmount.equals(that.depositAmount) : that.depositAmount != null)
            return false;
        if (this.depositInterest != null ? !this.depositInterest.equals(that.depositInterest) : that.depositInterest != null)
            return false;
        if (this.depositCharge != null ? !this.depositCharge.equals(that.depositCharge) : that.depositCharge != null)
            return false;
        if (this.depositExpirationDate != null ? !this.depositExpirationDate.equals(that.depositExpirationDate) : that
                .depositExpirationDate != null)
            return false;
        if (this.isDepositExpired != null ? !this.isDepositExpired.equals(that.isDepositExpired) : that.isDepositExpired != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = this.id;
        result = 31 * result + (this.firstName != null ? this.firstName.hashCode() : 0);
        result = 31 * result + (this.lastName != null ? this.lastName.hashCode() : 0);
        result = 31 * result + (this.notes != null ? this.notes.hashCode() : 0);
        result = 31 * result + (this.age != null ? this.age.hashCode() : 0);
        result = 31 * result + (this.magicWandCreator != null ? this.magicWandCreator.hashCode() : 0);
        result = 31 * result + (this.magicWandSize != null ? this.magicWandSize.hashCode() : 0);
        result = 31 * result + (this.depositGroup != null ? this.depositGroup.hashCode() : 0);
        result = 31 * result + (this.depositStartDate != null ? this.depositStartDate.hashCode() : 0);
        result = 31 * result + (this.depositAmount != null ? this.depositAmount.hashCode() : 0);
        result = 31 * result + (this.depositInterest != null ? this.depositInterest.hashCode() : 0);
        result = 31 * result + (this.depositCharge != null ? this.depositCharge.hashCode() : 0);
        result = 31 * result + (this.depositExpirationDate != null ? this.depositExpirationDate.hashCode() : 0);
        result = 31 * result + (this.isDepositExpired != null ? this.isDepositExpired.hashCode() : 0);
        return result;
    }
}

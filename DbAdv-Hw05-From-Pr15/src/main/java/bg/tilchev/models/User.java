package bg.tilchev.models;

import bg.tilchev.validation.Email;
import bg.tilchev.validation.Password;
import bg.tilchev.validation.Username;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Todor Ilchev on 2016-11-11.
 */

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    @Username(minLength = 3,
            maxLength = 50,
            canStartWithNumber = false,
            containsOnlyLettersAndNumbers = true,
            message = "Incorrect username")
    private String username;

    @Column(length = 50)
    @Password(minLength = 6,
            maxLength = 50,
            containsDigit = true,
            containsLowercase = true,
            containsUppercase = true,
            containsSpecialSymbols = false,
            message = "Incorrect password")
    private String password;

    @Column(length = 50)
    @Email(message = "Incorrect email")
    private String email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
                mappedBy = "user", targetEntity = BankAccount.class)
    private Set<BankAccount> bankAccounts;

    public User() {
        super();
        this.setBankAccounts(new TreeSet<>());
    }

    public User(String username, String password, String email) {
        this();
        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
    }

    public Long getId() {
        return this.id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<BankAccount> getBankAccounts() {
        if (this.bankAccounts == null) {
            this.bankAccounts = new TreeSet<>();
        }
        return Collections.unmodifiableSet(this.bankAccounts);
    }

    private void setBankAccounts(Set<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public void addBankAccount(BankAccount bankAccount) {
        this.bankAccounts.add(bankAccount);
    }
}

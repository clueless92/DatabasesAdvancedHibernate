package bg.tilchev.io;

import bg.tilchev.models.BankAccount;
import bg.tilchev.models.CreditCard;
import bg.tilchev.models.User;
import bg.tilchev.repos.BillingDetailsRepo;
import bg.tilchev.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Todor Ilchev on 2016-11-11.
 */

@Component
@Transactional
public class Terminal implements CommandLineRunner {

    private final BillingDetailsRepo billingDetailsRepo;
    private final UserRepo userRepo;

    @Autowired
    public Terminal(BillingDetailsRepo billingDetailsRepo, UserRepo userRepo) {
        this.billingDetailsRepo = billingDetailsRepo;
        this.userRepo = userRepo;
    }

    @Override
    public void run(String... strings) throws Exception {
        User user = new User();
        user.setFirstName("Pesho");
        user.setLastName("Ivanov");
        user.setPassword("tajna");
        user.setEmail("pesho@abv.bg");
        this.userRepo.saveAndFlush(user);

        CreditCard creditCard = new CreditCard();
        creditCard.setOwner(user);
        creditCard.setCardType("Masterchef");
        creditCard.setExpirationMonth((byte) 5);
        creditCard.setExpirationYear((short) 2020);
        this.billingDetailsRepo.saveAndFlush(creditCard);

        BankAccount bankAccount = new BankAccount();
        bankAccount.setOwner(user);
        bankAccount.setBankName("BRB");
        bankAccount.setSwiftCode("COD4");
        this.billingDetailsRepo.saveAndFlush(bankAccount);
    }
}

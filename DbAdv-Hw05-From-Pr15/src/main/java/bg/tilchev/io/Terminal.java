package bg.tilchev.io;

import bg.tilchev.models.BankAccount;
import bg.tilchev.models.CheckingAccount;
import bg.tilchev.models.SavingsAccount;
import bg.tilchev.models.User;
import bg.tilchev.services.BankAccountService;
import bg.tilchev.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Todor Ilchev on 2016-11-11.
 */

@Component
@Transactional
public class Terminal implements CommandLineRunner {

    private static final String MODELS_PACKAGE_NAME = "bg.tilchev.models.";

    private final BankAccountService bankAccountService;
    private final UserService userService;

    @Autowired
    public Terminal(BankAccountService bankAccountService, UserService userService) {
        this.bankAccountService = bankAccountService;
        this.userService = userService;
    }

    private static User loggedUser;

    @Override
    public void run(String... strings) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("App loaded!");
        while (true) {
            String[] input = reader.readLine().split(" ");
            try {
                if (this.parseInput(input)) {
                    break;
                }
            } catch (IllegalStateException ise) {
                System.out.println(ise.getMessage());
            } catch (ConstraintViolationException cve) {
                for (ConstraintViolation<?> cv : cve.getConstraintViolations()) {
                    System.out.println(cv.getMessage());
                }
            }
        }
    }

    private boolean parseInput(String[] input) {
        String command = input[0].toLowerCase();
        if(command.equals("exit")) {
            this.userService.flushRepo();
            System.out.println("Session ended!");
            return true;
        } else if(command.equals("register")) {
            this.register(input);
        } else if(command.equals("login")) {
            this.login(input);
        } else if(command.equals("logout")) {
            this.logout(input);
        } else if(command.equals("add")) {
            this.add(input);
        } else if(command.equals("listaccounts")) {
            this.listAccounts(input);
        } else if(command.equals("deposit")) {
            this.deposit(input);
        } else if(command.equals("withdraw")) {
            this.withdraw(input);
        } else if(command.equals("deductfee")) {
            this.deductFee(input);
        } else if(command.equals("addinterest")) {
            this.addInterest(input);
        }
        return false;
    }

    private void addInterest(String[] input) {
        Long id = Long.parseLong(input[1]);
        SavingsAccount account = (SavingsAccount) this.bankAccountService.retrieve(id);
        account.addInterest();
        System.out.printf("Deducted fee of %s. Current balance: %s%n", id, account.getBalance());
    }

    private void deductFee(String[] input) {
        Long id = Long.parseLong(input[1]);
        CheckingAccount account = (CheckingAccount) this.bankAccountService.retrieve(id);
        account.deductFee();
        System.out.printf("Added interest to %s. Current balance: %s%n", id, account.getBalance());
    }

    private void withdraw(String[] input) {
        Long id = Long.parseLong(input[1]);
        BigDecimal money = new BigDecimal(input[2]);
        BankAccount account = this.bankAccountService.retrieve(id);
        account.withdraw(money);
        System.out.printf("Account %s has balance of %s%n", id, account.getBalance());
    }

    private void deposit(String[] input) {
        Long id = Long.parseLong(input[1]);
        BigDecimal money = new BigDecimal(input[2]);
        BankAccount account = this.bankAccountService.retrieve(id);
        account.deposit(money);
        System.out.printf("Account %s has balance of %s%n", id, account.getBalance());
    }

    private void listAccounts(String[] input) {
        if(loggedUser == null) {
            throw new IllegalStateException("No logged in user.");
        }
        Set<BankAccount> bankAccounts = loggedUser.getBankAccounts()
                .stream().sorted((acc1, acc2) -> Long.compare(acc1.getId(), acc2.getId())).collect(Collectors.toSet());
        System.out.printf("Accounts for user %s%n", loggedUser.getUsername());
        for (BankAccount bankAccount : bankAccounts) {
            System.out.println(bankAccount);
        }
    }

    private void add(String[] input) {
        if(loggedUser == null) {
            throw new IllegalStateException("No logged in user.");
        }
        String accType = MODELS_PACKAGE_NAME + input[1];
        BigDecimal initialBalance = new BigDecimal(input[2]);
        BigDecimal feeOrInterest = new BigDecimal(input[3]);
        try {
            Class bankAccType = Class.forName(accType);
            Constructor ctor = bankAccType.getConstructor(BigDecimal.class, BigDecimal.class);
            BankAccount bankAccount = (BankAccount) ctor.newInstance(initialBalance, feeOrInterest);
            bankAccount.setUser(loggedUser);
            this.bankAccountService.persist(bankAccount);
            System.out.printf("Succesfully added account with number %s%n", bankAccount.getId());
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

    private void logout(String[] input) {
        if(loggedUser == null) {
            throw new IllegalStateException("Cannot log out. No user was logged in.");
        }
        System.out.printf("User %s successfully logged out%n", loggedUser.getUsername());
        loggedUser = null;
    }

    private void login(String[] input) {
        if(loggedUser != null) {
            throw new IllegalStateException("Cannot login user while another is logged in!");
        }
        String username = input[1];
        String password = input[2];
        User user = this.userService.retrieve(username, password);
        if(user == null) {
            throw new IllegalStateException("Incorrect username / password");
        }
        loggedUser = user;
        System.out.printf("Succesfully logged in %s%n", username);
    }

    private void register(String[] input) {
        if(loggedUser != null) {
            throw new IllegalStateException("Cannot register new user while another is logged in!");
        }
        String username = input[1];
        String password = input[2];
        String email = input[3];
        User newUser = new User(username, password, email);
        this.userService.persist(newUser);
        System.out.printf("%s was registered in the system%n", username);
    }
}

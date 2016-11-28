package bg.tilchev.serviceImpls;

import bg.tilchev.models.BankAccount;
import bg.tilchev.repos.BankAccountRepo;
import bg.tilchev.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Todor Ilchev on 2016-11-11.
 */

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepo bankAccountRepo;

    @Autowired
    public BankAccountServiceImpl(BankAccountRepo bankAccountRepo) {
        this.bankAccountRepo = bankAccountRepo;
    }

    @Override
    public void persist(BankAccount bankAccount) {
        this.bankAccountRepo.save(bankAccount);
    }

    @Override
    public BankAccount retrieve(Long id) {
        return this.bankAccountRepo.findOne(id);
    }
}

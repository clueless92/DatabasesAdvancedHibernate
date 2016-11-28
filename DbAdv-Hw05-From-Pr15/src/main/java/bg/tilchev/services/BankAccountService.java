package bg.tilchev.services;

import bg.tilchev.models.BankAccount;

/**
 * Created by Todor Ilchev on 2016-11-11.
 */

public interface BankAccountService {

    void persist(BankAccount bankAccount);

    BankAccount retrieve(Long id);
}

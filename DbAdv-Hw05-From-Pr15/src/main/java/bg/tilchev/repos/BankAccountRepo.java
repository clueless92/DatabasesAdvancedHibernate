package bg.tilchev.repos;

import bg.tilchev.models.BankAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Todor Ilchev on 2016-11-11.
 */

@Repository
public interface BankAccountRepo extends CrudRepository<BankAccount, Long> {

}

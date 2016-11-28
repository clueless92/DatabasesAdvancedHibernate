package bg.tilchev.repos;

import bg.tilchev.models.WizzardDeposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Todor Ilchev on 2016-11-01.
 */

@Repository
public interface WizzardDepositRepo extends JpaRepository<WizzardDeposit, Long> {

}

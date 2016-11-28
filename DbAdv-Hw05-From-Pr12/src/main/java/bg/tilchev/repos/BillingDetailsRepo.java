package bg.tilchev.repos;

import bg.tilchev.models.BillingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Todor Ilchev on 2016-11-11.
 */

@Repository
public interface BillingDetailsRepo extends JpaRepository<BillingDetails, Long> {

}

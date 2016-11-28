package bg.tilchev.repos;

import bg.tilchev.models.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Todor Ilchev on 2016-11-03.
 */

@Repository
public interface TownRepo extends JpaRepository<Town, Long> {

}

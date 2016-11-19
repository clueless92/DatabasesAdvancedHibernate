package bg.tilchev.repos;

import bg.tilchev.models.Star;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Todor Ilchev on 2016-11-19.
 */

@Repository
public interface StarRepo extends JpaRepository<Star, Long> {

    Star findByName(String name);
}

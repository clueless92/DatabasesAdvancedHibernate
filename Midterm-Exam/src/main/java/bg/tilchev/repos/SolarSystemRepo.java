package bg.tilchev.repos;

import bg.tilchev.models.SolarSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Todor Ilchev on 2016-11-19.
 */

@Repository
public interface SolarSystemRepo extends JpaRepository<SolarSystem, Long> {

    SolarSystem findByName(String name);
}

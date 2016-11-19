package bg.tilchev.repos;

import bg.tilchev.models.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Todor Ilchev on 2016-11-19.
 */

@Repository
public interface PlanetRepo extends JpaRepository<Planet, Long> {

    Planet findByName(String name);

    @Query("SELECT p FROM Planet AS p WHERE p.originatedAnomalies.size = 0")
    List<Planet> findPlanetsWithNoOriginatedAnomalies();
}

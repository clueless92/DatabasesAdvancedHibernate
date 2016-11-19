package bg.tilchev.repos;

import bg.tilchev.models.Anomaly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Todor Ilchev on 2016-11-19.
 */

@Repository
public interface AnomalyRepo extends JpaRepository<Anomaly, Long> {

    @Query("SELECT a FROM Anomaly AS a" +
           " ORDER BY a.victims.size DESC")
    List<Anomaly> findAnomalyThatAffectedTheMostPeople();
}

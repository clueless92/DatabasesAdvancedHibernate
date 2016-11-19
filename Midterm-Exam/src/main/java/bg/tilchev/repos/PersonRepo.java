package bg.tilchev.repos;

import bg.tilchev.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Todor Ilchev on 2016-11-19.
 */

@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {

    Person findByName(String name);

    // fuckin Ivo-malies
    @Query("SELECT p FROM Person AS p WHERE p.anomalies.size = 0")
    List<Person> findPeopleWhoHaveNotBeenVictimsOfAnomalies();
}

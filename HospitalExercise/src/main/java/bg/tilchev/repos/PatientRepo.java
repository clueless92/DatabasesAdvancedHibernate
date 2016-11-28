package bg.tilchev.repos;

import bg.tilchev.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Todor Ilchev on 2016-11-10.
 */

@Repository
public interface PatientRepo extends JpaRepository<Patient, Long> {

}

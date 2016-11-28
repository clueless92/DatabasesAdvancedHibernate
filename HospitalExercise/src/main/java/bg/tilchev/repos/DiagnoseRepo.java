package bg.tilchev.repos;

import bg.tilchev.models.Diagnose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Todor Ilchev on 2016-11-10.
 */

@Repository
public interface DiagnoseRepo extends JpaRepository<Diagnose, Long> {

}

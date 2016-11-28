package bg.tilchev.repos;

import bg.tilchev.models.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Todor Ilchev on 2016-11-08.
 */

@Repository
public interface ResourceRepo extends JpaRepository<Resource, Long> {


}

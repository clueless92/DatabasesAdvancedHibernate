package bg.tilchev.repos;

import bg.tilchev.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Todor Ilchev on 2016-11-11.
 */

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

    User findByUsernameAndPassword(String username, String password);
}

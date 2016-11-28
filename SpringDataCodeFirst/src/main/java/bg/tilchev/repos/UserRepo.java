package bg.tilchev.repos;

import bg.tilchev.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Todor Ilchev on 2016-11-03.
 */

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    List<User> findByEmail(String email);

    int countByProfilePictureGreaterThan(byte[] size);
}

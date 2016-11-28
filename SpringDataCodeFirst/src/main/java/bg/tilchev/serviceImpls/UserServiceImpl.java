package bg.tilchev.serviceImpls;

import bg.tilchev.models.User;
import bg.tilchev.repos.UserRepo;
import bg.tilchev.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Todor Ilchev on 2016-11-03.
 */

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void persist(User user) {
        this.userRepo.saveAndFlush(user);
    }

    @Override
    public List<User> findUsersByEmail(String email) {
        return this.userRepo.findByEmail(email);
    }

    @Override
    public int countOfPicturesAbove(byte[] size) {
        return this.userRepo.countByProfilePictureGreaterThan(size);
    }
}

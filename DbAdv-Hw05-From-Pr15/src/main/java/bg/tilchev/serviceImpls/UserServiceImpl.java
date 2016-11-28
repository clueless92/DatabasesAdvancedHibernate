package bg.tilchev.serviceImpls;

import bg.tilchev.models.User;
import bg.tilchev.repos.UserRepo;
import bg.tilchev.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Todor Ilchev on 2016-11-11.
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
        this.userRepo.save(user);
    }

    @Override
    public User retrieve(String username, String password) {
        return this.userRepo.findByUsernameAndPassword(username, password);
    }
}

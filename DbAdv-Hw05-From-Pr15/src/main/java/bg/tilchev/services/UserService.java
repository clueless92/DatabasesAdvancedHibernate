package bg.tilchev.services;

import bg.tilchev.models.User;

/**
 * Created by Todor Ilchev on 2016-11-11.
 */

public interface UserService {

    void persist(User user);

    User retrieve(String username, String password);
}

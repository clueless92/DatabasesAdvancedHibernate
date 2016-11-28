package bg.tilchev.services;

import bg.tilchev.models.User;

import java.util.List;

/**
 * Created by Todor Ilchev on 2016-11-03.
 */
public interface UserService {

    void persist(User user);

    List<User> findUsersByEmail(String email);

    int countOfPicturesAbove(byte[] size);
}

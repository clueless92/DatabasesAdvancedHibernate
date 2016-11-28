package bg.tilchev.services;

import bg.tilchev.models.Resource;

/**
 * Created by Todor Ilchev on 2016-11-08.
 */

public interface ResourceService {

    void persist(Resource course);

    long count();

}

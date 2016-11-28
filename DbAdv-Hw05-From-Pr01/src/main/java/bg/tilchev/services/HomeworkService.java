package bg.tilchev.services;

import bg.tilchev.models.Homework;

/**
 * Created by Todor Ilchev on 2016-11-08.
 */

public interface HomeworkService {

    void persist(Homework course);

    long count();
}

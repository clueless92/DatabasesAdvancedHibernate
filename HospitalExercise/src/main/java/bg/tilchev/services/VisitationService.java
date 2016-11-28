package bg.tilchev.services;

import bg.tilchev.models.Visitation;

/**
 * Created by Todor Ilchev on 2016-11-10.
 */
public interface VisitationService {

    void persist(Visitation visitation);

    long count();
}

package bg.tilchev.services;

import bg.tilchev.models.Diagnose;

/**
 * Created by Todor Ilchev on 2016-11-10.
 */
public interface DiagnoseService {

    void persist(Diagnose diagnose);

    long count();
}

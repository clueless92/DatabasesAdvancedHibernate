package bg.tilchev.services;

import bg.tilchev.models.Patient;

/**
 * Created by Todor Ilchev on 2016-11-10.
 */
public interface PatientService {

    void persist(Patient patient);

    long count();
}

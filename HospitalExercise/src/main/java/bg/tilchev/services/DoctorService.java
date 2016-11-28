package bg.tilchev.services;

import bg.tilchev.models.Doctor;

/**
 * Created by Todor Ilchev on 2016-11-10.
 */
public interface DoctorService {

    void persist(Doctor doctor);

    long count();
}

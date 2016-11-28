package bg.tilchev.serviceimpls;

import bg.tilchev.models.Doctor;
import bg.tilchev.repos.DoctorRepo;
import bg.tilchev.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Todor Ilchev on 2016-11-10.
 */

@Service
public class DoctorServiceImpl implements DoctorService {

    private DoctorRepo doctorRepo;

    @Autowired
    public DoctorServiceImpl(DoctorRepo doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    @Override
    public void persist(Doctor doctor) {
        this.doctorRepo.saveAndFlush(doctor);
    }

    @Override
    public long count() {
        return this.doctorRepo.count();
    }
}

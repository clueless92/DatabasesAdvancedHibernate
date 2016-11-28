package bg.tilchev.serviceimpls;

import bg.tilchev.models.Patient;
import bg.tilchev.repos.PatientRepo;
import bg.tilchev.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Todor Ilchev on 2016-11-10.
 */

@Service
public class PatientServiceImpl implements PatientService {

    private PatientRepo patientRepo;

    @Autowired
    public PatientServiceImpl(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }

    @Override
    public void persist(Patient patient) {
        this.patientRepo.saveAndFlush(patient);
    }

    @Override
    public long count() {
        return this.patientRepo.count();
    }
}

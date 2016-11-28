package bg.tilchev.serviceimpls;

import bg.tilchev.models.Medicament;
import bg.tilchev.repos.MedicamentRepo;
import bg.tilchev.services.MedicamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Todor Ilchev on 2016-11-10.
 */

@Service
public class MedicamentServiceImpl implements MedicamentService {

    private MedicamentRepo medicamentRepo;

    @Autowired
    public MedicamentServiceImpl(MedicamentRepo medicamentRepo) {
        this.medicamentRepo = medicamentRepo;
    }

    @Override
    public void persist(Medicament medicament) {
        this.medicamentRepo.saveAndFlush(medicament);
    }

    @Override
    public long count() {
        return this.medicamentRepo.count();
    }
}

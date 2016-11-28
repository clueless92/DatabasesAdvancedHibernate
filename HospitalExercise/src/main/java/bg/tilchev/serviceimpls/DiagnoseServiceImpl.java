package bg.tilchev.serviceimpls;

import bg.tilchev.models.Diagnose;
import bg.tilchev.repos.DiagnoseRepo;
import bg.tilchev.services.DiagnoseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Todor Ilchev on 2016-11-10.
 */

@Service
public class DiagnoseServiceImpl implements DiagnoseService {

    private DiagnoseRepo diagnoseRepo;

    @Autowired
    public DiagnoseServiceImpl(DiagnoseRepo diagnoseRepo) {
        this.diagnoseRepo = diagnoseRepo;
    }

    @Override
    public void persist(Diagnose diagnose) {
        this.diagnoseRepo.saveAndFlush(diagnose);
    }

    @Override
    public long count() {
        return this.diagnoseRepo.count();
    }
}

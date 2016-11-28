package bg.tilchev.serviceimpls;

import bg.tilchev.models.Visitation;
import bg.tilchev.repos.VisitationRepo;
import bg.tilchev.services.VisitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Todor Ilchev on 2016-11-10.
 */

@Service
public class VisitationServiceImpl implements VisitationService {

    private VisitationRepo visitationRepo;

    @Autowired
    public VisitationServiceImpl(VisitationRepo visitationRepo) {
        this.visitationRepo = visitationRepo;
    }

    @Override
    public void persist(Visitation visitation) {
        this.visitationRepo.saveAndFlush(visitation);
    }

    @Override
    public long count() {
        return this.visitationRepo.count();
    }
}

package bg.tilchev.serviceImpls;

import bg.tilchev.models.Town;
import bg.tilchev.repos.TownRepo;
import bg.tilchev.services.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Todor Ilchev on 2016-11-03.
 */

@Service
public class TownServiceImpl implements TownService {

    private final TownRepo townRepo;

    @Autowired
    public TownServiceImpl(TownRepo townRepo) {
        this.townRepo = townRepo;
    }

    @Override
    public void persist(Town town) {
        this.townRepo.saveAndFlush(town);
    }
}

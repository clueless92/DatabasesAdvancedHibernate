package bg.tilchev.serviceImpls;

import bg.tilchev.models.WizzardDeposit;
import bg.tilchev.repos.WizzardDepositRepo;
import bg.tilchev.services.WizzardDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Todor Ilchev on 2016-11-01.
 */

@Service
public class WizzardDepositServiceImpl implements WizzardDepositService {

    private final WizzardDepositRepo wizzardDepositRepo;

    @Autowired
    public WizzardDepositServiceImpl(WizzardDepositRepo wizzardDepositRepo) {
        this.wizzardDepositRepo = wizzardDepositRepo;
    }

    @Override
    public void persist(WizzardDeposit wizzardDeposit) {
        this.wizzardDepositRepo.saveAndFlush(wizzardDeposit);
    }
}

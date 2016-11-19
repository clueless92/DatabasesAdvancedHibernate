package bg.tilchev.serviceImpls;

import bg.tilchev.dto.json.StarImportJsonDto;
import bg.tilchev.models.SolarSystem;
import bg.tilchev.models.Star;
import bg.tilchev.repos.StarRepo;
import bg.tilchev.services.SolarSystemService;
import bg.tilchev.services.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Todor Ilchev on 2016-11-19.
 */

@Service
public class StarServiceImpl implements StarService {

    private final StarRepo starRepo;

    private final SolarSystemService solarSystemService;

    @Autowired
    public StarServiceImpl(StarRepo starRepo, SolarSystemService solarSystemService) {
        this.starRepo = starRepo;
        this.solarSystemService = solarSystemService;
    }

    @Override
    public void persistJSON(StarImportJsonDto starDto) {
        String solarSystemName = starDto.getSolarSystem();
        String starName = starDto.getName();
        if(solarSystemName == null || starName == null) {
            throw new IllegalStateException("Error: Invalid data.");
        }
        SolarSystem solarSystem = this.solarSystemService.findByName(solarSystemName);
        if(solarSystem == null) {
            throw new IllegalStateException("Error: Invalid data.");
//            solarSystem = new SolarSystem();
//            solarSystem.setName(solarSystemName);
        }
        Star star = new Star();
        star.setName(starName);
        star.setSolarSystem(solarSystem);
        this.starRepo.saveAndFlush(star);
    }

    @Override
    public Star findByName(String name) {
        return this.starRepo.findByName(name);
    }
}

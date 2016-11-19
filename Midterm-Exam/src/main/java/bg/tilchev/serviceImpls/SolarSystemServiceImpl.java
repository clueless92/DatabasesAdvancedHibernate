package bg.tilchev.serviceImpls;

import bg.tilchev.dto.json.SolarSystemImportJsonDto;
import bg.tilchev.models.SolarSystem;
import bg.tilchev.repos.SolarSystemRepo;
import bg.tilchev.services.SolarSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Todor Ilchev on 2016-11-19.
 */

@Service
public class SolarSystemServiceImpl implements SolarSystemService {

    private final SolarSystemRepo solarSystemRepo;

    @Autowired
    public SolarSystemServiceImpl(SolarSystemRepo solarSystemRepo) {
        this.solarSystemRepo = solarSystemRepo;
    }

    @Override
    public void persistJSON(SolarSystemImportJsonDto solarSystemDto) {
        String solarSystemName = solarSystemDto.getName();
        if(solarSystemName == null) {
            throw new IllegalStateException("Error: Invalid data.");
        }
        SolarSystem solarSystem = new SolarSystem();
        solarSystem.setName(solarSystemName);
        this.solarSystemRepo.saveAndFlush(solarSystem);
    }

    @Override
    public SolarSystem findByName(String name) {
        return this.solarSystemRepo.findByName(name);
    }
}

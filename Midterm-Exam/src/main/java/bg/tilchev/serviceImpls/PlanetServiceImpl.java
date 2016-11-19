package bg.tilchev.serviceImpls;

import bg.tilchev.dto.json.PlanetExportJsonDto;
import bg.tilchev.dto.json.PlanetImportJsonDto;
import bg.tilchev.models.Planet;
import bg.tilchev.models.SolarSystem;
import bg.tilchev.models.Star;
import bg.tilchev.repos.PlanetRepo;
import bg.tilchev.services.PlanetService;
import bg.tilchev.services.SolarSystemService;
import bg.tilchev.services.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Todor Ilchev on 2016-11-19.
 */

@Service
public class PlanetServiceImpl implements PlanetService {

    private final PlanetRepo planetRepo;

    private final SolarSystemService solarSystemService;

    private final StarService starService;

    @Autowired
    public PlanetServiceImpl(PlanetRepo planetRepo, SolarSystemService solarSystemService, StarService starService) {
        this.planetRepo = planetRepo;
        this.solarSystemService = solarSystemService;
        this.starService = starService;
    }

    @Override
    public void persistJSON(PlanetImportJsonDto planetDto) {
        String solarSystemName = planetDto.getSolarSystem();
        String sunName = planetDto.getSun();
        String planetName = planetDto.getName();
        if (planetName == null || sunName == null || solarSystemName == null) {
            throw new IllegalStateException("Error: Invalid data.");
        }
        SolarSystem solarSystem = this.solarSystemService.findByName(solarSystemName);
        if (solarSystem == null) {
            throw new IllegalStateException("Error: Invalid data.");
//            solarSystem = new SolarSystem();
//            solarSystem.setName(solarSystemName);
        }
        Star sun = this.starService.findByName(sunName);
        if(sun == null) {
            throw new IllegalStateException("Error: Invalid data.");
//            sun = new Star();
//            sun.setName(sunName);
//            sun.setSolarSystem(solarSystem);
        }
        Planet planet = new Planet();
        planet.setName(planetName);
        planet.setSolarSystem(solarSystem);
        planet.setSun(sun);
        this.planetRepo.saveAndFlush(planet);
    }

    @Override
    public Planet findByName(String name) {
        return this.planetRepo.findByName(name);
    }

    @Override
    public List<PlanetExportJsonDto> findPlanetsWithNoOriginatedAnomalies() {
        List<Planet> planets = this.planetRepo.findPlanetsWithNoOriginatedAnomalies();
        List<PlanetExportJsonDto> output = new ArrayList<>();
        for (Planet planet : planets) {
            PlanetExportJsonDto planetExportJsonDto = new PlanetExportJsonDto();
            planetExportJsonDto.setName(planet.getName());
            output.add(planetExportJsonDto);
        }
        return output;
    }
}

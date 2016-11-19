package bg.tilchev.services;

import bg.tilchev.dto.json.PlanetExportJsonDto;
import bg.tilchev.dto.json.PlanetImportJsonDto;
import bg.tilchev.models.Planet;

import java.util.List;

/**
 * Created by Todor Ilchev on 2016-11-19.
 */
public interface PlanetService {

    void persistJSON(PlanetImportJsonDto planetDto);

    Planet findByName(String name);

    List<PlanetExportJsonDto> findPlanetsWithNoOriginatedAnomalies();
}

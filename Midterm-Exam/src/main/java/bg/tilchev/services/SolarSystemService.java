package bg.tilchev.services;

import bg.tilchev.dto.json.SolarSystemImportJsonDto;
import bg.tilchev.models.SolarSystem;

/**
 * Created by Todor Ilchev on 2016-11-19.
 */
public interface SolarSystemService {

    void persistJSON(SolarSystemImportJsonDto solarSystemDto);

    SolarSystem findByName(String name);
}

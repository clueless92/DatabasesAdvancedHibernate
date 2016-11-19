package bg.tilchev.services;

import bg.tilchev.dto.json.StarImportJsonDto;
import bg.tilchev.models.Star;

/**
 * Created by Todor Ilchev on 2016-11-19.
 */
public interface StarService {

    void persistJSON(StarImportJsonDto starDto);

    Star findByName(String name);
}

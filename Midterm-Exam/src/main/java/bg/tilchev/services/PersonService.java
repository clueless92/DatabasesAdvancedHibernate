package bg.tilchev.services;

import bg.tilchev.dto.json.PersonExportJsonDto;
import bg.tilchev.dto.json.PersonImportJsonDto;
import bg.tilchev.dto.xml.VictimImportXmlDto;
import bg.tilchev.models.Person;

import java.util.List;

/**
 * Created by Todor Ilchev on 2016-11-19.
 */
public interface PersonService {

    void persistJSON(PersonImportJsonDto personDto);

    void persistXML(VictimImportXmlDto victim);

    Person findByName(String name);

    List<PersonExportJsonDto> findPeopleWhoHaveNotBeenVictimsOfAnomalies();
}

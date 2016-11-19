package bg.tilchev.serviceImpls;

import bg.tilchev.dto.json.PersonExportJsonDto;
import bg.tilchev.dto.json.PersonImportJsonDto;
import bg.tilchev.dto.json.PlanetExportJsonDto;
import bg.tilchev.dto.xml.VictimImportXmlDto;
import bg.tilchev.models.Person;
import bg.tilchev.models.Planet;
import bg.tilchev.repos.PersonRepo;
import bg.tilchev.services.PersonService;
import bg.tilchev.services.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Todor Ilchev on 2016-11-19.
 */

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepo personRepo;

    private final PlanetService planetService;

    @Autowired
    public PersonServiceImpl(PersonRepo personRepo, PlanetService planetService) {
        this.personRepo = personRepo;
        this.planetService = planetService;
    }

    @Override
    public void persistJSON(PersonImportJsonDto personDto) {
        String planetName = personDto.getHomePlanet();
        String personName = personDto.getName();
        if (planetName == null || personName == null) {
            throw new IllegalStateException("Error: Invalid data.");
        }
        Planet planet = this.planetService.findByName(planetName);
        if (planet == null) {
            throw new IllegalStateException("Error: Invalid data.");
//            planet = new Planet();
//            planet.setName(planetName);
        }
        Person person = new Person();
        person.setName(personName);
        person.setHomePlanet(planet);
        this.personRepo.saveAndFlush(person);
    }

    @Override
    public void persistXML(VictimImportXmlDto victim) {
        String victimName = victim.getName();
        if (victimName == null) {
            throw new IllegalStateException("Error: Invalid data.");
        }
        Person person = new Person();
        person.setName(victimName);
        this.personRepo.saveAndFlush(person);
    }

    @Override
    public Person findByName(String name) {
        return this.personRepo.findByName(name);
    }

    @Override
    public List<PersonExportJsonDto> findPeopleWhoHaveNotBeenVictimsOfAnomalies() {
        List<Person> persons = this.personRepo.findPeopleWhoHaveNotBeenVictimsOfAnomalies();
        List<PersonExportJsonDto> output = new ArrayList<>();
        for (Person person : persons) {
            PersonExportJsonDto pesho = new PersonExportJsonDto();
            pesho.setName(person.getName());
            Planet planet = person.getHomePlanet();
            PlanetExportJsonDto peshoPlanet = new PlanetExportJsonDto();
            peshoPlanet.setName(planet.getName());
            pesho.setHomePlanet(peshoPlanet);
            output.add(pesho);
        }
        return output;
    }
}

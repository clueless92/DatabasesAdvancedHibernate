package bg.tilchev.io;

import bg.tilchev.dto.json.*;
import bg.tilchev.dto.xml.AnomalyListExportXmlDto;
import bg.tilchev.dto.xml.AnomalyListImportXmlDto;
import bg.tilchev.parsers.JSONParser;
import bg.tilchev.parsers.XMLParser;
import bg.tilchev.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBException;
import java.util.List;

/**
 * Created by Todor Ilchev on 2016-11-19.
 */

@Component
@Transactional
public class Terminal implements CommandLineRunner {

    private final XMLParser xmlParser;
    private final JSONParser jsonParser;

    private final AnomalyService anomalyService;
    private final PersonService personService;
    private final PlanetService planetService;
    private final SolarSystemService solarSystemService;
    private final StarService starService;

    @Autowired
    public Terminal(
            XMLParser xmlParser,
            JSONParser jsonParser,
            AnomalyService anomalyService,
            PersonService personService,
            PlanetService planetService,
            SolarSystemService solarSystemService,
            StarService starService) {
        this.xmlParser = xmlParser;
        this.jsonParser = jsonParser;
        this.anomalyService = anomalyService;
        this.personService = personService;
        this.planetService = planetService;
        this.solarSystemService = solarSystemService;
        this.starService = starService;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.importTestData();
        this.exportTestData();
    }

    private void exportTestData() {
        this.exportPlanetsWithNoOriginatedAnomalies();
        this.exportPeopleWhoWereNeverVictimsOfAnomalies();
        this.exportMostLeathalAnomaly();
        this.exportAllAnomaliesToXml();
    }

    private void importTestData() {
        this.importSolarSystemsJSON();
        this.importStarsJSON();
        this.importPlanetsJSON();
        this.importPersonsJSON();
        this.importAnomaliesJSON();
        this.importAnomalyVictimsJSON();
        this.importAnomaliesXML();
    }

    private void exportMostLeathalAnomaly() {
        final String filePath = "src/main/resources/files/output/json/anomaly.json";
        AnomalyExportJsonDto anomaly = this.anomalyService.findMostDangerousAnomaly();
        this.jsonParser.writeToJSON(anomaly, filePath);
    }

    private void exportPeopleWhoWereNeverVictimsOfAnomalies() {
        final String filePath = "src/main/resources/files/output/json/people.json";
        List<PersonExportJsonDto> people = this.personService.findPeopleWhoHaveNotBeenVictimsOfAnomalies();
        this.jsonParser.writeToJSON(people, filePath);
    }

    private void exportPlanetsWithNoOriginatedAnomalies() {
        final String filePath = "src/main/resources/files/output/json/planets.json";
        List<PlanetExportJsonDto> planets = this.planetService.findPlanetsWithNoOriginatedAnomalies();
        this.jsonParser.writeToJSON(planets, filePath);
    }

    private void importAnomalyVictimsJSON() {
        final String testStrAnomalyVictims = "src/main/resources/files/input/json/anomaly-victims.json";
        AnomalyVictimsImportJsonDto[] anomalyVictimsImportDtos = this.jsonParser.readFromJSON(AnomalyVictimsImportJsonDto[].class, testStrAnomalyVictims);
        for (AnomalyVictimsImportJsonDto anomalyVictimsImportJsonDto : anomalyVictimsImportDtos) {
            if(anomalyVictimsImportJsonDto == null) {
                break;
            }
            try {
                this.anomalyService.connectAnomaliesToPersonsJSON(anomalyVictimsImportJsonDto);
                System.out.println("Successfully imported data.");
            } catch (IllegalStateException ise) {
                System.out.println(ise.getMessage());
            }
        }
    }

    private void importAnomaliesJSON() {
        final String testStrAnomalies = "src/main/resources/files/input/json/anomalies.json";
        AnomalyImportJsonDto[] anomaliesImportDtos = this.jsonParser.readFromJSON(AnomalyImportJsonDto[].class, testStrAnomalies);
        for (AnomalyImportJsonDto anomalyImportJsonDto : anomaliesImportDtos) {
            if(anomalyImportJsonDto == null) {
                break;
            }
            try {
                this.anomalyService.persistJSON(anomalyImportJsonDto);
                System.out.println("Successfully imported data.");
            } catch (IllegalStateException ise) {
                System.out.println(ise.getMessage());
            }
        }
    }

    private void importPersonsJSON() {
        final String testStrPersons = "src/main/resources/files/input/json/persons.json";
        PersonImportJsonDto[] personImportJsonDtos = this.jsonParser.readFromJSON(PersonImportJsonDto[].class, testStrPersons);
        for (PersonImportJsonDto personImportJsonDto : personImportJsonDtos) {
            if(personImportJsonDto == null) {
                break;
            }
            try {
                this.personService.persistJSON(personImportJsonDto);
                System.out.println("Successfully imported data.");
            } catch (IllegalStateException ise) {
                System.out.println(ise.getMessage());
            }
        }
    }

    private void importPlanetsJSON() {
        final String testStrPlanets = "src/main/resources/files/input/json/planets.json";
        PlanetImportJsonDto[] planetImportJsonDtos = this.jsonParser.readFromJSON(PlanetImportJsonDto[].class, testStrPlanets);
        for (PlanetImportJsonDto planetImportJsonDto : planetImportJsonDtos) {
            if(planetImportJsonDto == null) {
                break;
            }
            try {
                this.planetService.persistJSON(planetImportJsonDto);
                System.out.println("Successfully imported data.");
            } catch (IllegalStateException ise) {
                System.out.println(ise.getMessage());
            }
        }
    }

    private void importStarsJSON() {
        final String testStrStar = "src/main/resources/files/input/json/stars.json";
        StarImportJsonDto[] starImportJsonDtos = this.jsonParser.readFromJSON(StarImportJsonDto[].class, testStrStar);
        for (StarImportJsonDto starImportJsonDto : starImportJsonDtos) {
            if(starImportJsonDto == null) {
                break;
            }
            try {
                this.starService.persistJSON(starImportJsonDto);
                System.out.println("Successfully imported data.");
            } catch (IllegalStateException ise) {
                System.out.println(ise.getMessage());
            }
        }
    }

    private void importSolarSystemsJSON() {
        final String testStrSolarSystem = "src/main/resources/files/input/json/solar-systems.json";
        SolarSystemImportJsonDto[] solarSystemImportJsonDtos =
                this.jsonParser.readFromJSON(SolarSystemImportJsonDto[].class, testStrSolarSystem);
        for (SolarSystemImportJsonDto solarSystemImportJsonDto : solarSystemImportJsonDtos) {
            if(solarSystemImportJsonDto == null) {
                break;
            }
            try {
                this.solarSystemService.persistJSON(solarSystemImportJsonDto);
                System.out.println("Successfully imported data.");
            } catch (IllegalStateException ise) {
                System.out.println(ise.getMessage());
            }
        }
    }

    private void importAnomaliesXML() {
        final String testStrAnomaly = "src/main/resources/files/input/xml/new-anomalies.xml";
        AnomalyListImportXmlDto anomaliesToPersist = null;
        try {
            Class<AnomalyListImportXmlDto> clazz = AnomalyListImportXmlDto.class;
            anomaliesToPersist = this.xmlParser.readFromXml(clazz, testStrAnomaly);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        this.anomalyService.persistXML(anomaliesToPersist);
    }

    private void exportAllAnomaliesToXml() {
        final String filePath = "src/main/resources/files/output/xml/anomalies.xml";
        AnomalyListExportXmlDto anomaliesToPersist = this.anomalyService.findAllXml();
        try {
            this.xmlParser.writeToXml(anomaliesToPersist, filePath);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}

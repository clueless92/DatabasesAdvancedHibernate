package bg.tilchev.serviceImpls;

import bg.tilchev.dto.json.AnomalyExportJsonDto;
import bg.tilchev.dto.json.AnomalyImportJsonDto;
import bg.tilchev.dto.json.AnomalyVictimsImportJsonDto;
import bg.tilchev.dto.json.PlanetExportJsonDto;
import bg.tilchev.dto.xml.*;
import bg.tilchev.models.Anomaly;
import bg.tilchev.models.Person;
import bg.tilchev.models.Planet;
import bg.tilchev.repos.AnomalyRepo;
import bg.tilchev.services.AnomalyService;
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
public class AnomalyServiceImpl implements AnomalyService {

    private final AnomalyRepo anomalyRepo;

    private final PlanetService planetService;

    private final PersonService personService;

    @Autowired
    public AnomalyServiceImpl(AnomalyRepo anomalyRepo, PlanetService planetService, PersonService personService) {
        this.anomalyRepo = anomalyRepo;
        this.planetService = planetService;
        this.personService = personService;
    }

    @Override
    public void persistJSON(AnomalyImportJsonDto anomalyDto) {
        String originPlanetName = anomalyDto.getOriginPlanet();
        String teleportPlanetName = anomalyDto.getTeleportPlanet();
        if (originPlanetName == null || teleportPlanetName == null) {
            throw new IllegalStateException("Error: Invalid data.");
        }
        Planet originPlanet = this.planetService.findByName(originPlanetName);
        if (originPlanet == null) {
            throw new IllegalStateException("Error: Invalid data.");
//            originPlanet.setName(originPlanetName);
        }
        Planet teleportPlanet = this.planetService.findByName(teleportPlanetName);
        if (teleportPlanet == null) {
            throw new IllegalStateException("Error: Invalid data.");
//            teleportPlanet.setName(teleportPlanetName);
        }
        Anomaly anomaly = new Anomaly();
        anomaly.setOriginPlanet(originPlanet);
        anomaly.setTeleportPlanet(teleportPlanet);

        this.anomalyRepo.saveAndFlush(anomaly);
    }

    @Override
    public void connectAnomaliesToPersonsJSON(AnomalyVictimsImportJsonDto anomalyVictimsImportJsonDto) {
        String personName = anomalyVictimsImportJsonDto.getPerson();
        Long anomalyId = anomalyVictimsImportJsonDto.getId();
        if (personName == null || anomalyId == null) {
            throw new IllegalStateException("Error: Invalid data.");
        }
        Anomaly anomaly = this.anomalyRepo.findOne(anomalyId);
        if(anomaly == null) {
            throw new IllegalStateException("Error: Invalid data.");
        }
        Person person = this.personService.findByName(personName);
        if(person == null) {
            throw new IllegalStateException("Error: Invalid data.");
        }
        anomaly.addVictim(person);
        person.addAnomaly(anomaly);
    }

    @Override
    public AnomalyExportJsonDto findMostDangerousAnomaly() {
        Anomaly anomaly = this.anomalyRepo.findAnomalyThatAffectedTheMostPeople().get(0);
        AnomalyExportJsonDto output = new AnomalyExportJsonDto();
        output.setId(anomaly.getId());
        output.setVictimsCount(anomaly.getVictims().size());
        Planet originPlanet = anomaly.getOriginPlanet();
        Planet teleportPlanet = anomaly.getTeleportPlanet();
        PlanetExportJsonDto originPlanetDto = new PlanetExportJsonDto();
        originPlanetDto.setName(originPlanet.getName());
        PlanetExportJsonDto teleportPlanetDto = new PlanetExportJsonDto();
        teleportPlanetDto.setName(teleportPlanet.getName());
        output.setOriginPlanet(originPlanetDto);
        output.setTeleportPlanet(teleportPlanetDto);

        return output;
    }

    @Override
    public void persistXML(AnomalyListImportXmlDto anomaliesXml) {
        for (AnomalyImportXmlDto anomalyDto : anomaliesXml.getAnomalies()) {
            String originPlanetName = anomalyDto.getOriginPlanet();
            String teleportPlanetName = anomalyDto.getTeleportPlanet();
            if (originPlanetName == null || teleportPlanetName == null) {
                System.out.println("Error: Invalid data.");
                continue;
            }
            Planet originPlanet = this.planetService.findByName(originPlanetName);
            Planet teleportPlanet = this.planetService.findByName(teleportPlanetName);
            if(originPlanet == null || teleportPlanet == null) {
                System.out.println("Error: Invalid data.");
                continue;
            }
            Anomaly anomaly = new Anomaly();
            anomaly.setOriginPlanet(originPlanet);
            anomaly.setTeleportPlanet(teleportPlanet);
            boolean allVictimsAreValid = true;
            for (VictimImportXmlDto victimDto : anomalyDto.getVictims()) {
                String victimName = victimDto.getName();
                if(victimName == null) {
                    allVictimsAreValid = false;
                    break;
                }
                Person victim = this.personService.findByName(victimDto.getName());
                if(victim == null) {
                    allVictimsAreValid = false;
                    break;
                }
                anomaly.addVictim(victim);
            }
            if(allVictimsAreValid) {
                this.anomalyRepo.saveAndFlush(anomaly);
                System.out.println("Successfully imported data.");
            } else {
                System.out.println("Error: Invalid data.");
            }
        }
    }

    @Override
    public AnomalyListExportXmlDto findAllXml() {
        List<Anomaly> anomalies = this.anomalyRepo.findAll();
        AnomalyListExportXmlDto output = new AnomalyListExportXmlDto();
        List<AnomalyExportXmlDto> anomalyDtos = new ArrayList<>();
        for (Anomaly anomaly : anomalies) {
            String originPlanet = anomaly.getOriginPlanet().getName();
            String teleportPlanet = anomaly.getTeleportPlanet().getName();
            Long anomalyId = anomaly.getId();
            List<VictimExportXmlDto> victims = new ArrayList<>();
            for (Person person : anomaly.getVictims()) {
                VictimExportXmlDto victim = new VictimExportXmlDto();
                victim.setName(person.getName());
                victims.add(victim);
            }
            AnomalyExportXmlDto anomalyToAdd = new AnomalyExportXmlDto();
            anomalyToAdd.setId(anomalyId);
            anomalyToAdd.setOriginPlanet(originPlanet);
            anomalyToAdd.setTeleportPlanet(teleportPlanet);
            anomalyToAdd.setVictims(victims);
            anomalyDtos.add(anomalyToAdd);
        }
        output.setAnomalies(anomalyDtos);
        return output;
    }
}

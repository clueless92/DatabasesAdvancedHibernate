package bg.tilchev.services;

import bg.tilchev.dto.json.AnomalyExportJsonDto;
import bg.tilchev.dto.json.AnomalyImportJsonDto;
import bg.tilchev.dto.json.AnomalyVictimsImportJsonDto;
import bg.tilchev.dto.xml.AnomalyListExportXmlDto;
import bg.tilchev.dto.xml.AnomalyListImportXmlDto;

/**
 * Created by Todor Ilchev on 2016-11-19.
 */
public interface AnomalyService {

    void persistJSON(AnomalyImportJsonDto anomalyDto);

    void connectAnomaliesToPersonsJSON(AnomalyVictimsImportJsonDto anomalyVictimsImportJsonDto);

    void persistXML(AnomalyListImportXmlDto anomalyXmlDto);

    AnomalyExportJsonDto findMostDangerousAnomaly();

    AnomalyListExportXmlDto findAllXml();
}

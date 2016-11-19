package bg.tilchev.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Todor Ilchev on 2016-11-19.
 */

@XmlRootElement(name = "anomalies")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnomalyListExportXmlDto implements Serializable {

    @XmlElement(name = "anomaly")
    private List<AnomalyExportXmlDto> anomalies;

    public AnomalyListExportXmlDto() {
        super();
        this.setAnomalies(new ArrayList<>());
    }

    public List<AnomalyExportXmlDto> getAnomalies() {
        return this.anomalies;
    }

    public void setAnomalies(List<AnomalyExportXmlDto> anomalies) {
        this.anomalies = anomalies;
    }
}

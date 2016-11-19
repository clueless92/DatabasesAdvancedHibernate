package bg.tilchev.dto.xml;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Todor Ilchev on 2016-11-19.
 */

@XmlRootElement(name = "anomalies")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnomalyListImportXmlDto implements Serializable {

    @XmlElement(name = "anomaly")
    private List<AnomalyImportXmlDto> anomalies;

    public AnomalyListImportXmlDto() {
        super();
    }

    public List<AnomalyImportXmlDto> getAnomalies() {
        return this.anomalies;
    }

    public void setAnomalies(List<AnomalyImportXmlDto> anomalies) {
        this.anomalies = anomalies;
    }
}

package bg.tilchev.dto.xml;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Todor Ilchev on 2016-11-19.
 */

@XmlRootElement(name = "anomaly")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnomalyExportXmlDto implements Serializable {

    @XmlAttribute(name = "id")
    private Long id;

    @XmlAttribute(name = "origin-planet", required = true)
    private String originPlanet;

    @XmlAttribute(name = "teleport-planet", required = true)
    private String teleportPlanet;

    @XmlElementWrapper(name = "victims")
    @XmlElement(name = "victim")
    private List<VictimExportXmlDto> victims;

    public AnomalyExportXmlDto() {
        super();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginPlanet() {
        return this.originPlanet;
    }

    public void setOriginPlanet(String originPlanet) {
        this.originPlanet = originPlanet;
    }

    public String getTeleportPlanet() {
        return this.teleportPlanet;
    }

    public void setTeleportPlanet(String teleportPlanet) {
        this.teleportPlanet = teleportPlanet;
    }

    public List<VictimExportXmlDto> getVictims() {
        return this.victims;
    }

    public void setVictims(List<VictimExportXmlDto> victims) {
        this.victims = victims;
    }
}

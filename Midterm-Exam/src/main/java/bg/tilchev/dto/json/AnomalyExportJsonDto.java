package bg.tilchev.dto.json;

import java.io.Serializable;

/**
 * Created by Todor Ilchev on 2016-11-19.
 */
public class AnomalyExportJsonDto implements Serializable {

    private Long id;

    private PlanetExportJsonDto originPlanet;

    private PlanetExportJsonDto teleportPlanet;

    private Integer victimsCount;

    public AnomalyExportJsonDto() {
        super();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlanetExportJsonDto getOriginPlanet() {
        return this.originPlanet;
    }

    public void setOriginPlanet(PlanetExportJsonDto originPlanet) {
        this.originPlanet = originPlanet;
    }

    public PlanetExportJsonDto getTeleportPlanet() {
        return this.teleportPlanet;
    }

    public void setTeleportPlanet(PlanetExportJsonDto teleportPlanet) {
        this.teleportPlanet = teleportPlanet;
    }

    public Integer getVictimsCount() {
        return this.victimsCount;
    }

    public void setVictimsCount(Integer victimsCount) {
        this.victimsCount = victimsCount;
    }
}

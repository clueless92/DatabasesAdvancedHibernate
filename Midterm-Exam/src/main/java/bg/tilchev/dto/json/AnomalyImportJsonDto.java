package bg.tilchev.dto.json;

import java.io.Serializable;

/**
 * Created by Todor Ilchev on 2016-11-19.
 */
public class AnomalyImportJsonDto implements Serializable {

    private String originPlanet;

    private String teleportPlanet;

    public AnomalyImportJsonDto() {
        super();
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
}

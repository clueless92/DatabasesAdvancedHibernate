package bg.tilchev.dto.json;

import java.io.Serializable;

/**
 * Created by Todor Ilchev on 2016-11-19.
 */
public class PlanetImportJsonDto implements Serializable {

    private String name;

    private String sun;

    private String solarSystem;

    public PlanetImportJsonDto() {
        super();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSun() {
        return this.sun;
    }

    public void setSun(String sun) {
        this.sun = sun;
    }

    public String getSolarSystem() {
        return this.solarSystem;
    }

    public void setSolarSystem(String solarSystem) {
        this.solarSystem = solarSystem;
    }
}

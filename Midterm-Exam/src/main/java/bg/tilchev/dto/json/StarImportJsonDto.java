package bg.tilchev.dto.json;

import java.io.Serializable;

/**
 * Created by Todor Ilchev on 2016-11-19.
 */
public class StarImportJsonDto implements Serializable {

    private String name;

    private String solarSystem;

    public StarImportJsonDto() {
        super();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSolarSystem() {
        return this.solarSystem;
    }

    public void setSolarSystem(String solarSystem) {
        this.solarSystem = solarSystem;
    }
}

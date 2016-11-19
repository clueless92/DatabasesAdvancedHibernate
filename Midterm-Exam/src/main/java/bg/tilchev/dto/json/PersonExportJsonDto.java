package bg.tilchev.dto.json;

import java.io.Serializable;

/**
 * Created by Todor Ilchev on 2016-11-19.
 */
public class PersonExportJsonDto implements Serializable {

    private String name;

    private PlanetExportJsonDto homePlanet;

    public PersonExportJsonDto() {
        super();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlanetExportJsonDto getHomePlanet() {
        return this.homePlanet;
    }

    public void setHomePlanet(PlanetExportJsonDto homePlanet) {
        this.homePlanet = homePlanet;
    }
}

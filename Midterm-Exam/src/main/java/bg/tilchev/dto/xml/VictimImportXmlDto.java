package bg.tilchev.dto.xml;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * Created by Todor Ilchev on 2016-11-19.
 */

@XmlRootElement(name = "victim")
@XmlAccessorType(XmlAccessType.FIELD)
public class VictimImportXmlDto implements Serializable {

    @XmlAttribute(name = "name", required = true)
    private String name;

    public VictimImportXmlDto() {
        super();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

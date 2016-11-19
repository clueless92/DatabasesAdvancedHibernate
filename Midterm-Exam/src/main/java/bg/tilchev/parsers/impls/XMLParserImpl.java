package bg.tilchev.parsers.impls;

import bg.tilchev.parsers.XMLParser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by Todor Ilchev on 2016-11-19.
 */

public class XMLParserImpl implements XMLParser {

    @Override
    public <E> E readFromXml(Class<E> clazz, String filePath) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        File file = new File(filePath);
        E entity = (E) unmarshaller.unmarshal(file);
        return entity;
    }

    @Override
    public <E> void writeToXml(E entity, String filePath) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(entity.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        File file = new File(filePath);
        marshaller.marshal(entity, file);
    }
}

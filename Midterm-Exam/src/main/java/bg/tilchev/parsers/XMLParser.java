package bg.tilchev.parsers;

import javax.xml.bind.JAXBException;

/**
 * Created by Todor Ilchev on 2016-11-19.
 */
public interface XMLParser {

    <E> E readFromXml(Class<E> clazz, String filePath) throws JAXBException;

    <E> void writeToXml(E entity, String filePath) throws JAXBException;
}

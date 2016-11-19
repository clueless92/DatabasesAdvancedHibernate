package bg.tilchev.parsers;

/**
 * Created by Todor Ilchev on 2016-11-19.
 */
public interface JSONParser {

    <E> E[] readFromJSON(Class<E[]> classes, String filePath);

    <E> void writeToJSON(E entity, String filePath);
}

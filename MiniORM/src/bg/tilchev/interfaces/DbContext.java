package bg.tilchev.interfaces;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Todor Ilchev on 2016-10-30.
 */
public interface DbContext {

    /**
     * Insert or update entity depending if it is attached to the context
     * @param entity the entity to be inserted or updated
     * @return true if the object was successfully persisted in the database and false otherwise
     * @throws ReflectiveOperationException
     * @throws SQLException
     */
    <E> boolean persist(E entity)
        throws ReflectiveOperationException, SQLException;

    /**
     *
     * @param table
     * @return collection of all entity objects of type E
     * @throws ReflectiveOperationException
     * @throws SQLException
     */
    <E> Collection<E> find(Class<E> table)
            throws ReflectiveOperationException, SQLException;

    /**
     *
     * @param table
     * @param where SQL WHERE clause
     * @return collection of all entity objects of type E matching the criteria given in "where"
     * @throws ReflectiveOperationException
     * @throws SQLException
     */
    <E> Collection<E> find(Class<E> table, String where)
            throws ReflectiveOperationException, SQLException;

    /**
     *
     * @param table
     * @return the first entity object of type E
     * @throws ReflectiveOperationException
     * @throws SQLException
     */
    <E> E findFirst(Class<E> table)
            throws ReflectiveOperationException, SQLException;

    /**
     *
     * @param table
     * @param where SQL WHERE clause
     * @return the first entity object of type E matching the criteria given in "where"
     * @throws ReflectiveOperationException
     * @throws SQLException
     */
    <E> E findFirst(Class<E> table, String where)
            throws ReflectiveOperationException, SQLException;
}

package bg.tilchev;

import bg.tilchev.interfaces.DbContext;
import bg.tilchev.persistence.Column;
import bg.tilchev.persistence.Entity;
import bg.tilchev.persistence.Id;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Todor Ilchev on 2016-10-30.
 */
public class EntityManager implements DbContext {

    private Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public <E> boolean persist(E entity) throws ReflectiveOperationException, SQLException {

        Class<?> entityType = entity.getClass();
        Field primary = this.getId(entityType);
        if (primary == null) {
            return false;
        }
        primary.setAccessible(true);
        Long value = (Long) primary.get(entity);

        this.doCreate(entity, primary);

        if (value == null || !this.containsEntryWithId(value, entityType)) {
            return this.doInsert(entity, primary);
        }

        return this.doUpdate(entity, primary);
    }

    @Override
    public <E> Collection<E> find(Class<E> table) throws ReflectiveOperationException, SQLException {
        return this.find(table, null);
    }

    @Override
    public <E> Collection<E> find(Class<E> table, String where) throws ReflectiveOperationException, SQLException {

        String tableName = this.getTableName(table);
        StringBuilder sqlSelect = new StringBuilder("SELECT * FROM ");
        sqlSelect.append(tableName);
        sqlSelect.append(" WHERE 1 = 1");
        if(where != null){
            sqlSelect.append(" AND ");
            sqlSelect.append(where);
        }

        List<E> entities = new ArrayList<>();

        Statement statement = this.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlSelect.toString());
        while (resultSet.next()){
            E entity = table.newInstance();
            this.fillEntity(resultSet, entity);
            entities.add(entity);
        }

        return Collections.unmodifiableCollection(entities);
    }

    @Override
    public <E> E findFirst(Class<E> table) throws ReflectiveOperationException, SQLException {
        return this.findFirst(table, null);
    }

    @Override
    public <E> E findFirst(Class<E> table, String where) throws ReflectiveOperationException, SQLException {

        Statement statement = this.connection.createStatement();
        StringBuilder rawQuery = new StringBuilder("SELECT * FROM ");
        String tableName = this.getTableName(table);
        rawQuery.append(tableName);
        rawQuery.append(" WHERE 1 = 1 ");
        if (where != null) {
            rawQuery.append("AND ");
            rawQuery.append(where);
        }
        rawQuery.append(" LIMIT 1;");
        ResultSet resultSet = statement.executeQuery(rawQuery.toString());
        E entity = table.newInstance();
        resultSet.next();
        this.fillEntity(resultSet, entity);

        return entity;
    }

    private <E> String getTableName(Class<E> entity) {
        String tableName;
        Entity classAnnotation = entity.getAnnotation(Entity.class);
        if (classAnnotation == null) {
            tableName = entity.getSimpleName();
        } else {
            tableName = classAnnotation.name();
        }
        return "`" + tableName + "`";
    }

    private String getFieldName(Field field) {
        String fieldName;
        Column fieldAnnotation = field.getAnnotation(Column.class);
        if (fieldAnnotation == null) {
            fieldName = field.getName();
        } else {
            fieldName = fieldAnnotation.name();
        }
        return fieldName;
    }

    private <E> Field getId(Class<E> entity) {
        Field[] fields = entity.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Id.class)) {
                return field;
            }
        }
        return null;
    }

    private <E> boolean doCreate(E entity, Field primary) throws ReflectiveOperationException, SQLException {

        Class<?> entityType = entity.getClass();
        String tableName = this.getTableName(entityType);
        Field[] fields = entityType.getDeclaredFields();
        StringBuilder rawQuery = new StringBuilder("CREATE TABLE IF NOT EXISTS " + tableName + " (");

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String fieldName = "`" + this.getFieldName(field) + "`";
            String fieldType = this.getSQLType(field, primary);
            rawQuery.append(fieldName).append(" ").append(fieldType);
            if (i != fields.length - 1) {
                rawQuery.append(", ");
            } else {
                rawQuery.append(");");
            }
        }

        Statement statement = this.connection.createStatement();
        statement.execute(rawQuery.toString());
        return true;
    }

    private <E> boolean doUpdate(E entity, Field primary) throws IllegalAccessException, SQLException {
        Class<?> entityType = entity.getClass();
        String tableName = this.getTableName(entityType);
        StringBuilder rawQuery = new StringBuilder("UPDATE " + tableName + " SET ");
        Field[] fields = entityType.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            String fieldName = "`" +this.getFieldName(field) + "`";
            Object value = field.get(entity);
            if (value instanceof java.util.Date) {
                SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
                value = simpleDate.format(value);
            }
            rawQuery.append(fieldName);
            rawQuery.append(" = '");
            rawQuery.append(value);
            rawQuery.append("'");
            if (i != fields.length - 1) {
                rawQuery.append(", ");
            }
        }
        String primayName = "`" + this.getFieldName(primary) + "`";
        long id = (long) primary.get(entity);
        rawQuery.append(" WHERE ");
        rawQuery.append(primayName);
        rawQuery.append(" = ");
        rawQuery.append(id);
        rawQuery.append(";");
        Statement statement = this.connection.createStatement();
        statement.executeUpdate(rawQuery.toString());
        return true;
    }

    private <E> boolean doInsert(E entity, Field primary) throws IllegalAccessException, SQLException {
        Class<?> entityType = entity.getClass();
        String tableName = this.getTableName(entityType);
        StringBuilder rawQuery = new StringBuilder("INSERT INTO " + tableName + "(");
        Field[] fields = entityType.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if(field.getName().equals(primary.getName())) {
                continue;
            }
            String fieldName = "`" + this.getFieldName(field) + "`";
            rawQuery.append(fieldName);
            if (i != fields.length - 1) {
                rawQuery.append(", ");
            } else {
                rawQuery.append(")");
            }
        }

        rawQuery.append(" VALUES (");

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if(field.getName().equals(primary.getName())) {
                continue;
            }
            field.setAccessible(true);
            Object value = field.get(entity);
            if (value instanceof java.util.Date) {
                SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
                value = simpleDate.format(value);
            }
            rawQuery.append("'");
            rawQuery.append(value);
            rawQuery.append("'");
            if (i != fields.length - 1) {
                rawQuery.append(", ");
            } else {
                rawQuery.append(");");
            }
        }
        Statement statement = this.connection.createStatement();
        statement.executeUpdate(rawQuery.toString());
        return true;
    }

    private String getSQLType(Field field, Field primary) {
        field.setAccessible(true);
        if (field.getName().equals(primary.getName())) {
            return "BIGINT PRIMARY KEY AUTO_INCREMENT ";
        }
        String fieldTypeName = field.getType().getSimpleName().toLowerCase();
        switch (fieldTypeName) {
            case "integer":
            case "int":
                return "INT";
            case "long":
                return "BIGINT";
            case "string":
                return "VARCHAR(50)";
            case "date":
                return "DATE";
            default:
                return "TEXT";
        }
    }

    private <E> boolean containsEntryWithId(Long id, Class<E> table) throws ReflectiveOperationException, SQLException {
        Statement statement = this.connection.createStatement();
        StringBuilder rawQuery = new StringBuilder("SELECT * FROM ");
        String tableName = this.getTableName(table);
        Field primary = this.getId(table);
        String primaryName = "`" + this.getFieldName(primary) + "`";
        rawQuery.append(tableName);
        rawQuery.append(" WHERE ");
        rawQuery.append(primaryName);
        rawQuery.append(" = '");
        rawQuery.append(id);
        rawQuery.append("';");
        ResultSet resultSet = statement.executeQuery(rawQuery.toString());
        return resultSet.isBeforeFirst();
    }

    private <E> void fillEntity(ResultSet resultSet, E entity) throws SQLException, IllegalAccessException {
        Field[] fields = entity.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if (!(field.isAnnotationPresent(Id.class) || field.isAnnotationPresent(Column.class))) {
                continue;
            }
            field.setAccessible(true);
            String fieldName = this.getFieldName(field);
            Class fieldType = field.getType();
            Object value = fieldType.cast(resultSet.getObject(fieldName));
            field.set(entity, value);
//            field.set(value, entity); //!!!
        }
    }
}

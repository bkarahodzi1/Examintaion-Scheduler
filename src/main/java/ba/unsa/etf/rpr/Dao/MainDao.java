package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Exceptions.HospitalException;
import ba.unsa.etf.rpr.Domain.Id;

import java.sql.*;
import java.util.*;

/**
 * Abstract class that implements core DAO CRUD methods for every entity
 * @param <T>
 *
 * @author Berin Karahodžić
 */
public abstract class MainDao<T extends Id> implements Dao<T>{
    public static Connection getConnection() {
        return connection;
    }

    private static Connection connection;
    private String tableName;
    static {
        try {
            Properties p = new Properties();
            p.load(ClassLoader.getSystemResource("application.properties").openStream());
            String url = p.getProperty("db.connection_string");
            String username = p.getProperty("db.username");
            String password = p.getProperty("db.password");
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println("Nemoguce uraditi konekciju na bazu");
            e.printStackTrace();
        }
    }
    public MainDao(String name) {
        this.tableName=name;
    }
    /**
     * Method for mapping ResultSet into Object
     * @param rs - result set from database
     * @return a Bean object for specific table
     * @throws HospitalException in case of error with db
     */
    public abstract T convertRow(ResultSet rs) throws HospitalException;
    /**
     * Method for mapping Object into Map
     * @param object - a bean object for specific table
     * @return key, value sorted map of object
     */
    public abstract Map<String, Object> convertObject(T object);

    public T getById(int id) throws HospitalException {
        String query = "SELECT * FROM "+this.tableName+" WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                T result = convertRow(rs);
                rs.close();
                return result;
            } else {
                throw new HospitalException("Object not found");
            }
        } catch (SQLException e) {
            throw new HospitalException(e.getMessage(), e);
        }
    }
    public List<T> getAll() throws HospitalException {
        String query = "SELECT * FROM "+ tableName;
        List<T> results = new ArrayList<T>();
        try{
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                T object = convertRow(rs);
                results.add(object);
            }
            rs.close();
            return results;
        }catch (SQLException e){
            throw new HospitalException(e.getMessage(), e);
        }
    }

    public void deleteAll() throws HospitalException {
        String sql = "DELETE FROM "+tableName;
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.executeUpdate();
        }catch (SQLException e){
            throw new HospitalException(e.getMessage(), e);
        }
    }

    public void delete(int id) throws HospitalException {
        String sql = "DELETE FROM "+tableName+" WHERE id = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            throw new HospitalException(e.getMessage(), e);
        }
    }

    public T add(T item) throws HospitalException{
        Map<String, Object> row = convertObject(item);
        Map.Entry<String, String> columns = prepareInsertParts(row);

        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ").append(tableName);
        builder.append(" (").append(columns.getKey()).append(") ");
        builder.append("VALUES (").append(columns.getValue()).append(")");

        try{
            PreparedStatement stmt = connection.prepareStatement(builder.toString(), Statement.RETURN_GENERATED_KEYS);
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {
                if (entry.getKey().equals("id")) continue;
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            item.setId(rs.getInt(1));

            return item;
        }catch (SQLException e){
            throw new HospitalException(e.getMessage(), e);
        }
    }

    public T update(T item) throws HospitalException{
        Map<String, Object> row = convertObject(item);
        String updateColumns = prepareUpdateParts(row);
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ")
                .append(tableName)
                .append(" SET ")
                .append(updateColumns)
                .append(" WHERE id = ?");

        try{
            PreparedStatement stmt = connection.prepareStatement(builder.toString());
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {
                if (entry.getKey().equals("id")) continue;
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.setObject(counter, item.getId());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            throw new HospitalException(e.getMessage(), e);
        }
    }
    /**
     * Accepts KV storage of column names and return CSV of columns and question marks for insert statement
     */
    private Map.Entry<String, String> prepareInsertParts(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();
        StringBuilder questions = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, Object> entry: row.entrySet()) {
            counter++;
            if (entry.getKey().equals("id")) continue;
            columns.append(entry.getKey());
            questions.append("?");
            if (row.size() != counter) {
                columns.append(",");
                questions.append(",");
            }
        }
        return new AbstractMap.SimpleEntry<>(columns.toString(), questions.toString());
    }
    /**
     * Prepare columns for update statement id=?, name=?, ...
     * @param row - row to be converted intro string
     * @return String for update statement
     */
    private String prepareUpdateParts(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, Object> entry: row.entrySet()) {
            counter++;
            if (entry.getKey().equals("id")) continue;
            columns.append(entry.getKey()).append("= ?");
            if (row.size() != counter) {
                columns.append(",");
            }
        }
        return columns.toString();
    }
}

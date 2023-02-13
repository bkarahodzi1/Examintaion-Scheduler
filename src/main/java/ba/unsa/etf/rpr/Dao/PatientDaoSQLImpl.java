package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Exceptions.HospitalException;
import ba.unsa.etf.rpr.Domain.Patient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL's implementation of DAO
 *
 * @author Berin Karahodžić
 */
public class PatientDaoSQLImpl extends MainDao<Patient> implements PatientDao{
    private static final PatientDaoSQLImpl singleton = new PatientDaoSQLImpl();

    public static PatientDaoSQLImpl getSingleton(){
        return singleton;
    }
    public PatientDaoSQLImpl() {
        super("patient");
    }

    @Override
    public Patient convertRow(ResultSet rs) throws HospitalException {
        try{
            Patient pat = new Patient();
            pat.setId(rs.getInt("id"));
            pat.setName(rs.getString("name"));
            pat.setAddress(rs.getString("address"));
            pat.setPlace(rs.getString("place"));
            pat.setPhone_num(rs.getString("phone_num"));
            pat.setBirth_date(rs.getDate("birth_date"));
            pat.setHealth_insurance(rs.getBoolean("health_insurance"));
            return pat;
        } catch (SQLException e){
            throw new HospitalException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> convertObject(Patient object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id",object.getId());
        row.put("name",object.getName());
        row.put("address",object.getAddress());
        row.put("place",object.getPlace());
        row.put("phone_num",object.getPhone_num());
        row.put("birth_date",object.getBirth_date());
        row.put("health_insurance",object.isHealth_insurance());
        return row;
    }
}

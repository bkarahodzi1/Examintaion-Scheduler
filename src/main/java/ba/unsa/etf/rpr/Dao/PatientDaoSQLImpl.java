package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Exceptions.HospitalException;
import ba.unsa.etf.rpr.domain.Patient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class PatientDaoSQLImpl extends MainDao<Patient>{

    public PatientDaoSQLImpl() {
        super("patient");
    }

    @Override
    public Patient convertRow(ResultSet rs) throws HospitalException {
        try{
            Patient pat = new Patient();
            pat.setId(rs.getInt("IDP"));
            pat.setName(rs.getString("Name"));
            pat.setSurname(rs.getString("Surname"));
            pat.setAddress(rs.getString("Address"));
            pat.setPlace(rs.getString("Place"));
            pat.setPhone_num(rs.getString("Phone"));
            pat.setBirth_date(rs.getDate("Birth date"));
            pat.setHealth_insurance(rs.getBoolean("Health insurance"));
            return pat;
        } catch (SQLException e){
            throw new HospitalException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> convertObject(Patient object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("IDP",object.getId());
        row.put("Name",object.getName());
        row.put("Surname",object.getSurname());
        row.put("Address",object.getAddress());
        row.put("Place",object.getPlace());
        row.put("Phone",object.getPhone_num());
        row.put("Birth Date",object.getBirth_date());
        row.put("Health insurance",object.isHealth_insurance());
        return row;
    }
}

package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Exceptions.HospitalException;
import ba.unsa.etf.rpr.domain.Doctor;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class DoctorDao extends MainDao<Doctor> {
    public DoctorDao() throws SQLException {
        super("Doctor");
    }

    @Override
    public Doctor convertRow(ResultSet rs) throws HospitalException {
        try{
            Doctor doc = new Doctor();
            doc.setId(rs.getInt("IDD"));
            doc.setName(rs.getString("Name"));
            doc.setSurname(rs.getString("Surname"));
            doc.setPhone_num(rs.getString("Phone"));
            doc.setSeniority(rs.getInt("Seniority"));
            doc.setSpecialization(rs.getString("Specialization"));
            return doc;
        } catch (SQLException e){
            throw new HospitalException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> convertObject(Doctor object) {
        Map<String, Object> row = new TreeMap<String, Object>();
        row.put("IDD",object.getId());
        row.put("Name",object.getName());
        row.put("Surname",object.getSurname());
        row.put("Phone",object.getPhone_num());
        row.put("Seniority",object.getSeniority());
        row.put("Specialization",object.getSpecialization());
        return row;
    }
}

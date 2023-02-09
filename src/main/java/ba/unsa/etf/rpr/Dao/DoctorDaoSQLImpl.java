package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Exceptions.HospitalException;
import ba.unsa.etf.rpr.domain.Doctor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class DoctorDaoSQLImpl extends MainDao<Doctor> implements DoctorDao{
    public DoctorDaoSQLImpl() {
        super("doctor");
    }

    @Override
    public Doctor convertRow(ResultSet rs) throws HospitalException {
        try{
            Doctor doc = new Doctor();
            doc.setId(rs.getInt("id"));
            doc.setName(rs.getString("name"));
            doc.setSurname(rs.getString("surname"));
            doc.setPhone_num(rs.getString("phone"));
            doc.setSeniority(rs.getInt("seniority"));
            doc.setSpecialization(rs.getString("specialization"));
            doc.setUsername(rs.getString("username"));
            doc.setPassword(rs.getString("password"));
            return doc;
        } catch (SQLException e){
            throw new HospitalException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> convertObject(Doctor object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id",object.getId());
        row.put("name",object.getName());
        row.put("surname",object.getSurname());
        row.put("phone",object.getPhone_num());
        row.put("seniority",object.getSeniority());
        row.put("specialization",object.getSpecialization());
        row.put("username",object.getUsername());
        row.put("password",object.getPassword());
        return row;
    }
}

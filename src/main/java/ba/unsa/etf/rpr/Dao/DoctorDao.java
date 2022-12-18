package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Exceptions.HospitalException;
import ba.unsa.etf.rpr.domain.Doctor;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class DoctorDao extends MainDao<Doctor> {
    public DoctorDao() throws SQLException {
        super("Doctor");
    }

    @Override
    public Doctor convertRow(ResultSet rs) throws HospitalException {
        return null;
    }

    @Override
    public Map<String, Object> convertObject(Doctor object) {
        return null;
    }
}

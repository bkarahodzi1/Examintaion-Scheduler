package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Domain.Doctor;
import ba.unsa.etf.rpr.Exceptions.HospitalException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DoctorDao extends Dao<Doctor> {

    public Doctor getByUsername(String user) throws HospitalException;
    public boolean usernameExists(String user)throws HospitalException;
}

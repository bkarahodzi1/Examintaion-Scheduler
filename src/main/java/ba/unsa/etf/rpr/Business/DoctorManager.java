package ba.unsa.etf.rpr.Business;

import ba.unsa.etf.rpr.Dao.Dao;
import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Domain.Doctor;
import ba.unsa.etf.rpr.Exceptions.HospitalException;

import java.util.List;

public class DoctorManager {
    public Doctor getByUsername(String user) throws HospitalException {
        return DaoFactory.DoctorDao().getByUsername(user);
    }
    public boolean usernameExists(String user) throws HospitalException{
        return DaoFactory.DoctorDao().usernameExists(user);
    }
    public Doctor getById(int id) throws HospitalException {
        return DaoFactory.DoctorDao().getById(id);
    }
    public List<Doctor> getAll() throws HospitalException {
        return DaoFactory.DoctorDao().getAll();
    }
    public Doctor add(Doctor item) throws HospitalException{
        return DaoFactory.DoctorDao().add(item);
    }
    public Doctor update(Doctor item) throws HospitalException{
        return DaoFactory.DoctorDao().update(item);
    }
}

package ba.unsa.etf.rpr.Business;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Domain.Patient;
import ba.unsa.etf.rpr.Exceptions.HospitalException;

import java.util.List;

public class PatientManager {
    /***
     * Business Logic Layer for management of Patients
     *
     * @author Berin Karahodžić
     */
    public Patient getById(int id) throws HospitalException {
        return DaoFactory.PatientDao().getById(id);
    }
    public List<Patient> getAll() throws HospitalException {
        return DaoFactory.PatientDao().getAll();
    }
    public void deleteAll() throws HospitalException {
        DaoFactory.PatientDao().deleteAll();
    }
    public void delete(int id) throws HospitalException {
        DaoFactory.PatientDao().delete(id);
    }
    public Patient add(Patient item) throws HospitalException{
        return DaoFactory.PatientDao().add(item);
    }
    public Patient update(Patient item) throws HospitalException{
        return DaoFactory.PatientDao().update(item);
    }
}

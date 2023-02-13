package ba.unsa.etf.rpr.Business;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Domain.Doctor;
import ba.unsa.etf.rpr.Exceptions.HospitalException;

import java.util.List;

/***
 * Business Logic Layer for management of Doctors
 *
 * @author Berin Karahodžić
 */
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
    public static boolean isPasswordValid(String password){
        return (password.length()>=5 && password.matches(".*[a-zA-Z].*") && password.matches(".*[0-9].*"));
    }
    public static boolean isSeniorityValid(String seniority){
        int SenInt = Integer.parseInt(seniority);
        return (!seniority.equals("") && !seniority.matches(".*[a-zA-z].*") && SenInt>=0 && SenInt<=45);
    }
    public void delete(int id) throws HospitalException {
        DaoFactory.DoctorDao().delete(id);
    }
    public static boolean doPasswordsMatch(String password1, String password2){
        return(password2.equals(password1));
    }
}

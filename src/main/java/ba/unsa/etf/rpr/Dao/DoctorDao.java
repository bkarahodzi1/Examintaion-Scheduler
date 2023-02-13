package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Domain.Doctor;
import ba.unsa.etf.rpr.Exceptions.HospitalException;

/**
 * Dao interface for Doctor domain bean
 *
 * @author Berin Karahodžić
 */
public interface DoctorDao extends Dao<Doctor> {
    /**
     * Return doctor by username
     * @param user
     * @return
     * @throws HospitalException
     */
    public Doctor getByUsername(String user) throws HospitalException;

    /**
     * check if a username exists
     * @param user
     * @return
     * @throws HospitalException
     */
    public boolean usernameExists(String user)throws HospitalException;
}

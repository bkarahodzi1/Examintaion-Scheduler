package ba.unsa.etf.rpr.Business;

import ba.unsa.etf.rpr.Dao.Dao;
import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Domain.Examination;
import ba.unsa.etf.rpr.Exceptions.HospitalException;

import java.util.List;
/***
 * Business Logic Layer for management of Examinations
 *
 * @author Berin Karahodžić
 */
public class ExaminationManager {
    public List<Examination> getByDoctor(String user) throws HospitalException{
        return DaoFactory.ExaminationDao().getByDoctor(user);
    }
    public Examination getById(int id) throws HospitalException {
        return DaoFactory.ExaminationDao().getById(id);
    }
    public List<Examination> getAll() throws HospitalException {
        return DaoFactory.ExaminationDao().getAll();
    }
    public void deleteAll() throws HospitalException {
        DaoFactory.ExaminationDao().deleteAll();
    }
    public void delete(int id) throws HospitalException {
        DaoFactory.ExaminationDao().delete(id);
    }
    public Examination add(Examination item) throws HospitalException{
        return DaoFactory.ExaminationDao().add(item);
    }
    public Examination update(Examination item) throws HospitalException{
        return DaoFactory.ExaminationDao().update(item);
    }
}

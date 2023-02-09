package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Exceptions.HospitalException;
import ba.unsa.etf.rpr.domain.Patient;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws HospitalException {
        DaoFactory.PatientDao().deleteAll();
    }
}

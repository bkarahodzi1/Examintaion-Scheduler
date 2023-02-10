package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Exceptions.HospitalException;
import ba.unsa.etf.rpr.domain.Doctor;
import ba.unsa.etf.rpr.domain.Patient;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws HospitalException {
        Doctor d1 = new Doctor();
        d1.setName("asdfa");
        d1.setSpecialization("svasdf");
        d1.setSeniority(5);
        d1.setId(0);
        d1.setSurname("asdfasdf");
        d1.setUsername("Berin");
        d1.setPassword("asdfasdfas");
        DaoFactory.DoctorDao().add(d1);
    }
}

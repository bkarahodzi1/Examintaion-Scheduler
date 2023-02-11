package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Exceptions.HospitalException;
import ba.unsa.etf.rpr.Domain.Doctor;

public class Main {
    public static void main(String[] args) throws HospitalException {
        Doctor d1 = new Doctor();
        d1.setName("asdfa");
        d1.setSpecialization("svasdf");
        d1.setSeniority(5);
        d1.setId(0);
        d1.setUsername("Berin");
        d1.setPassword("asdfasdfas");
        DaoFactory.DoctorDao().add(d1);
    }
}

package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Exceptions.HospitalException;
import ba.unsa.etf.rpr.domain.Patient;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws HospitalException {
        Patient p= new Patient();
        p.setId(1);
        p.setHealth_insurance(false);
        p.setAddress("asdfa2");
        p.setPlace("adsfa2");
        p.setName("agwdfad2");
        p.setSurname("sdafgafdsg2");
        p.setBirth_date(new Date());
        p.setPhone_num("fasdfasdf2");
        DaoFactory.PatientDao().add(p);
        List lista = DaoFactory.PatientDao().getAll();
        for(Object temp : lista)
        {
            System.out.println(temp);
        }
        System.out.println();
    }
}

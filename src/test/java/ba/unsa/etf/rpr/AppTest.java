package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.Business.DoctorManager;
import ba.unsa.etf.rpr.Business.ExaminationManager;
import ba.unsa.etf.rpr.Business.PatientManager;
import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Dao.DoctorDao;
import ba.unsa.etf.rpr.Dao.ExaminationDao;
import ba.unsa.etf.rpr.Dao.PatientDao;
import ba.unsa.etf.rpr.Domain.Doctor;
import ba.unsa.etf.rpr.Domain.Examination;
import ba.unsa.etf.rpr.Domain.Patient;
import ba.unsa.etf.rpr.Exceptions.HospitalException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Unit test for simple App.
 */
public class AppTest {
    DoctorManager doctorManager = new DoctorManager();
    ExaminationManager examinationManager = new ExaminationManager();
    PatientManager patientManager = new PatientManager();
    public static final Doctor dokta = new Doctor();
    public static final DoctorDao mockDocDao = mock(DoctorDao.class);
    public static final PatientDao mockPatDao = mock(PatientDao.class);
    public static final ExaminationDao mockExmDao = mock(ExaminationDao.class);

    @BeforeAll
    static void setup() throws HospitalException {
        MockedStatic<DaoFactory> daoFactory = Mockito.mockStatic(DaoFactory.class);

        daoFactory.when(DaoFactory::DoctorDao).thenReturn(mockDocDao);
        daoFactory.when(DaoFactory::PatientDao).thenReturn(mockPatDao);
        daoFactory.when(DaoFactory::ExaminationDao).thenReturn(mockExmDao);
    }
    @Test
    void doPasswordsMatchTest(){
        String password1 = new String("nesto");
        String password2 = new String("nesto2");
        assertTrue(DoctorManager.doPasswordsMatch(password1,password1));
        assertFalse(DoctorManager.doPasswordsMatch(password1,password2));
    }
    @Test
    void getAllTest() throws HospitalException {
        Doctor doctor = new Doctor(0, "berin", 5, "Cardiology", "berin", "pass123");
        List<Doctor> doctors = new ArrayList<Doctor>();
        doctors.add(doctor);
        when(mockDocDao.getAll()).thenReturn(doctors);

        List<Doctor> doctors1 = doctorManager.getAll();

        assertEquals(doctors1,doctors);
    }
    @Test
    void getByDoctorTest() throws HospitalException {
        Patient patient = new Patient(0,"b","b","b","0",new Date(),true);
        Doctor doctor = new Doctor(0, "berin", 5, "Cardiology", "berin", "pass123");
        Examination examination = new Examination(0,new Date(),patient,doctor,"glup","pamet");
        List<Examination> examinations = new ArrayList<Examination>();
        examinations.add(examination);
        when(mockExmDao.getByDoctor(doctor.getName())).thenReturn(examinations);

        List<Examination> examinations1 = examinationManager.getByDoctor(doctor.getName());
        assertEquals(examinations1,examinations);
    }

    @Test
    void getByIdTest() throws HospitalException {

        Doctor doctor = new Doctor(0, "berin", 5, "Cardiology", "berin", "pass123");
        when(mockDocDao.getById(0)).thenReturn(doctor);

        Doctor doctor1 = doctorManager.getById(0);

        verify(mockDocDao).getById(eq(0));
        assertEquals(doctor, doctor1);
    }

    @Test
    void updateTest() throws HospitalException {
        Doctor doctor = new Doctor(0, "berin", 5, "Cardiology", "berin", "pass123");
        when(mockDocDao.update(doctor)).thenReturn(doctor);

        Doctor doctor1 = doctorManager.update(doctor);
        assertEquals(doctor,doctor1);

    }

    @Test
    void addTest() throws HospitalException {
        Doctor doctor = new Doctor(0, "berin", 5, "Cardiology", "berin", "pass123");
        when(mockDocDao.add(doctor)).thenReturn(doctor);

        Doctor doctor1 = doctorManager.add(doctor);
        assertEquals(doctor,doctor1);
    }

    @Test
    void usernameExistsTest() throws HospitalException {
        Doctor doctor = new Doctor(0, "berin", 5, "Cardiology", "berin", "pass123");
        List<Doctor> doctors = new ArrayList<Doctor>();
        doctors.add(doctor);
        when(mockDocDao.usernameExists(doctor.getUsername())).thenReturn(!doctor.getUsername().isEmpty());

        assertTrue(doctorManager.usernameExists(doctor.getUsername()));
    }

    @Test
    void getByUsernameTest() throws HospitalException {
        Doctor doctor = new Doctor(0, "berin", 5, "Cardiology", "berin", "pass123");
        List<Doctor> doctors = new ArrayList<Doctor>();
        doctors.add(doctor);
        when(mockDocDao.getByUsername(doctor.getUsername())).thenReturn(doctor);

        Doctor doctor1 = doctorManager.getByUsername(doctor.getUsername());
        assertEquals(doctor,doctor1);
    }

    @Test
    void isPasswordValidTest() throws HospitalException {
        DoctorManager fakeDoctor = mock(DoctorManager.class);
        when(fakeDoctor.getById(0)).thenReturn(new Doctor(0, "berin", 5, "Cardiology", "berin", "psw2"));
        Assertions.assertFalse(DoctorManager.isPasswordValid(fakeDoctor.getById(0).getPassword()));
        when(fakeDoctor.getById(0)).thenReturn(new Doctor(0, "berin", 5, "Cardiology", "berin", "123456"));
        Assertions.assertFalse(DoctorManager.isPasswordValid(fakeDoctor.getById(0).getPassword()));
        when(fakeDoctor.getById(0)).thenReturn(new Doctor(0, "berin", 5, "Cardiology", "berin", "123"));
        Assertions.assertFalse(DoctorManager.isPasswordValid(fakeDoctor.getById(0).getPassword()));
        when(fakeDoctor.getById(0)).thenReturn(new Doctor(0, "berin", 5, "Cardiology", "berin", "pswr"));
        Assertions.assertFalse(DoctorManager.isPasswordValid(fakeDoctor.getById(0).getPassword()));
        when(fakeDoctor.getById(0)).thenReturn(new Doctor(0, "berin", 5, "Cardiology", "berin", "nekipassword"));
        Assertions.assertFalse(DoctorManager.isPasswordValid(fakeDoctor.getById(0).getPassword()));
        when(fakeDoctor.getById(0)).thenReturn(new Doctor(0, "berin", 5, "Cardiology", "berin", "pass123"));
        Assertions.assertTrue(DoctorManager.isPasswordValid(fakeDoctor.getById(0).getPassword()));
    }

    @Test
    void isSeniorityValidTest() throws HospitalException {
        DoctorManager fakeDoctor = mock(DoctorManager.class);
        when(fakeDoctor.getById(0)).thenReturn(new Doctor(0, "berin", -1, "Cardiology", "berin", "pass123"));
        String seniority = String.valueOf(fakeDoctor.getById(0).getSeniority());
        Assertions.assertFalse(DoctorManager.isSeniorityValid(seniority));
        when(fakeDoctor.getById(0)).thenReturn(new Doctor(0, "berin", 50, "Cardiology", "berin", "pass123"));
        seniority = String.valueOf(fakeDoctor.getById(0).getSeniority());
        Assertions.assertFalse(DoctorManager.isSeniorityValid(seniority));
        when(fakeDoctor.getById(0)).thenReturn(new Doctor(0, "berin", 150, "Cardiology", "berin", "pass123"));
        seniority = String.valueOf(fakeDoctor.getById(0).getSeniority());
        Assertions.assertFalse(DoctorManager.isSeniorityValid(seniority));
        when(fakeDoctor.getById(0)).thenReturn(new Doctor(0, "berin", 5, "Cardiology", "berin", "pass123"));
        seniority = String.valueOf(fakeDoctor.getById(0).getSeniority());
        Assertions.assertTrue(DoctorManager.isSeniorityValid(seniority));
    }
}

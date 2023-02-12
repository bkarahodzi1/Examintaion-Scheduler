package ba.unsa.etf.rpr.Dao;

public class DaoFactory {
    private static final DoctorDao DoctorDao = new DoctorDaoSQLImpl();
    private static final ExaminationDao ExaminationDao = ExaminationDaoSQLImpl.getSingleton();
    private static final PatientDao PatientDao = new PatientDaoSQLImpl();

    private DaoFactory(){
    }

    public static DoctorDao DoctorDao(){
        return DoctorDao;
    }

    public static ExaminationDao ExaminationDao(){
        return ExaminationDao;
    }

    public static PatientDao PatientDao(){
        return PatientDao;
    }
}

package ba.unsa.etf.rpr.Dao;

/**
 * Factory for Dao singleton implementation
 *
 * @author Berin Karahodžić
 */
public class DaoFactory {
    private static final DoctorDao DoctorDao = DoctorDaoSQLImpl.getSingleton();
    private static final ExaminationDao ExaminationDao = ExaminationDaoSQLImpl.getSingleton();
    private static final PatientDao PatientDao = PatientDaoSQLImpl.getSingleton();

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

package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Exceptions.HospitalException;
import ba.unsa.etf.rpr.domain.Examination;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class ExaminationDao extends MainDao<Examination> {

    public ExaminationDao() {
        super("examination");
    }

    @Override
    public Examination convertRow(ResultSet rs) throws HospitalException {
        try{
            Examination ex = new Examination();
            ex.setId(rs.getInt("IDE"));
            ex.setDate(rs.getDate("Date"));
            PatientDao patientDao=new PatientDao();
            ex.setPatient(patientDao.getById(rs.getInt("IDP")));
            DoctorDaoSQLImpl doctorDaoSQLImpl =new DoctorDaoSQLImpl();
            ex.setDoctor(doctorDaoSQLImpl.getById(rs.getInt("IDD")));
            ex.setDiagnosis(rs.getString("Diagnosis"));
            ex.setTreatment(rs.getString("Treatment"));
            return ex;
        } catch (SQLException e){
            throw new HospitalException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> convertObject(Examination object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("IDE",object.getId());
        row.put("Date",object.getDate());
        row.put("IDP",object.getPatient());
        row.put("IDD",object.getDoctor());
        row.put("Diagnosis",object.getDiagnosis());
        row.put("Treatment",object.getTreatment());
        return row;
    }
}

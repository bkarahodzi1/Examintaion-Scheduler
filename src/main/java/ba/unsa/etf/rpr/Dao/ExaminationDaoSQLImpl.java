package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Exceptions.HospitalException;
import ba.unsa.etf.rpr.domain.Examination;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class ExaminationDaoSQLImpl extends MainDao<Examination> implements ExaminationDao{

    public ExaminationDaoSQLImpl() {
        super("examination");
    }

    @Override
    public Examination convertRow(ResultSet rs) throws HospitalException {
        try{
            Examination ex = new Examination();
            ex.setId(rs.getInt("id"));
            ex.setDate(rs.getDate("date"));
            ex.setPatient(DaoFactory.PatientDao().getById(rs.getInt("patient")));
            ex.setDoctor(DaoFactory.DoctorDao().getById(rs.getInt("doctor")));
            ex.setDiagnosis(rs.getString("diagnosis"));
            ex.setTreatment(rs.getString("treatment"));
            return ex;
        } catch (SQLException e){
            throw new HospitalException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> convertObject(Examination object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id",object.getId());
        row.put("date",object.getDate());
        row.put("patient",object.getPatient());
        row.put("doctor",object.getDoctor());
        row.put("diagnosis",object.getDiagnosis());
        row.put("treatment",object.getTreatment());
        return row;
    }
}

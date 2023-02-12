package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Exceptions.HospitalException;
import ba.unsa.etf.rpr.Domain.Examination;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public List<Examination> getByDoctor(String user) throws HospitalException {
        String query = "SELECT * FROM examination e, doctor d WHERE e.doctor=d.id";
        List<Examination> results = new ArrayList<Examination>();
        try{
            PreparedStatement stmt = MainDao.getConnection().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Examination object = convertRow(rs);
                results.add(object);
            }
            rs.close();
            return results;
        }catch (SQLException e){
            throw new HospitalException(e.getMessage(), e);
        }
    }
}

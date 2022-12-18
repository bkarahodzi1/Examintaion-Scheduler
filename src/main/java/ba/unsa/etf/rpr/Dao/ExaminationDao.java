package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Exceptions.HospitalException;
import ba.unsa.etf.rpr.domain.Doctor;
import ba.unsa.etf.rpr.domain.Examination;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class ExaminationDao extends MainDao<Examination> {

    public ExaminationDao(String name) {
        super(name);
    }

    @Override
    public Examination convertRow(ResultSet rs) throws HospitalException {
        return null;
    }

    @Override
    public Map<String, Object> convertObject(Examination object) {
        return null;
    }
}

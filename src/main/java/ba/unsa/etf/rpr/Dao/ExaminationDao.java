package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Domain.Examination;
import ba.unsa.etf.rpr.Domain.Id;
import ba.unsa.etf.rpr.Exceptions.HospitalException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ExaminationDao extends Dao<Examination> {
    public List<Examination> getByDoctor() throws HospitalException;
}

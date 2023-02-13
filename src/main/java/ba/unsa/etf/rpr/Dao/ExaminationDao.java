package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Domain.Examination;
import ba.unsa.etf.rpr.Domain.Id;
import ba.unsa.etf.rpr.Exceptions.HospitalException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Dao interface for Quote domain bean
 *
 * @author Berin Karahodžić
 */
public interface ExaminationDao extends Dao<Examination> {
    /**
     * Return all examinations of a certain doctor
     * @param user
     * @return
     * @throws HospitalException
     */
    public List<Examination> getByDoctor(String user) throws HospitalException;
}

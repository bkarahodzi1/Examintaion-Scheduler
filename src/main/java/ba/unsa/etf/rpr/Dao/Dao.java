package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Exceptions.HospitalException;

import java.util.List;

public interface Dao<T> {

    T getById(int id) throws HospitalException;


    T add(T item) throws HospitalException;


    T update(T item) throws HospitalException;


    void delete(int id) throws HospitalException;


    List<T> getAll() throws HospitalException;
}

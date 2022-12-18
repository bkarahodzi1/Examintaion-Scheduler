package ba.unsa.etf.rpr.Exceptions;

public class HospitalException extends Exception{
    public HospitalException(String s,Exception e){
        super(s,e);
    }
    public HospitalException(String s){
        super(s);
    }
}

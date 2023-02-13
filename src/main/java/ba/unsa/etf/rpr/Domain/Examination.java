package ba.unsa.etf.rpr.Domain;

import java.util.Date;
import java.util.Objects;
/**
 * Class that contains an examination's information
 *
 * @author Berin Karahodžić
 */
public class Examination implements Id{
    private int id;
    private Date date;
    private Patient patient;
    private Doctor doctor;

    private String diagnosis, treatment;

    public Examination() {
    }

    public Examination(int id, Date date, Patient patient, Doctor doctor, String diagnosis, String treatment) {
        this.id = id;
        this.date = date;
        this.patient = patient;
        this.doctor = doctor;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
    }

    public Examination(Date date, Patient patient, String diagnosis, String treatment) {
        this.date = date;
        this.patient = patient;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    @Override
    public String toString() {
        return "Examination {" +
                "id=" + id +
                ", date=" + date +
                ", patient=" + patient +
                ", doctor=" + doctor +
                ", diagnosis='" + diagnosis + '\'' +
                ", treatment='" + treatment + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Examination that = (Examination) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, patient, doctor, diagnosis, treatment);
    }
}

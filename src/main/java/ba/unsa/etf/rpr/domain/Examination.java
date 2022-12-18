package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Examination implements Id{
    private int id;
    private String diagnosis, treatment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Examination{" +
                "id=" + id +
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
        return Objects.hash(id, diagnosis, treatment);
    }
}

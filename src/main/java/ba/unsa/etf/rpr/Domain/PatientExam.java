package ba.unsa.etf.rpr.Domain;

import java.util.Objects;

public class PatientExam {
    private String name;
    private String diagnosis;
    public PatientExam(String name, String diagnosis){
        this.name=name;
        this.diagnosis=diagnosis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientExam that = (PatientExam) o;
        return Objects.equals(name, that.name) && Objects.equals(diagnosis, that.diagnosis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, diagnosis);
    }

    @Override
    public String toString() {
        return "PatientExam{" +
                "name='" + name + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                '}';
    }
}

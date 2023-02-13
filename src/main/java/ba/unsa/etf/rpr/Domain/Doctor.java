package ba.unsa.etf.rpr.Domain;

import java.util.Objects;

/**
 * Class that describes the user
 *
 * @author Berin Karahodžić
 */

public class Doctor implements Id {
    private int id, seniority;
    private String name, specialization, username, password;

    public Doctor() {
    }

    public Doctor(int id, String name, int seniority, String specialization, String username, String password) {
        this.id = id;
        this.seniority = seniority;
        this.name = name;
        this.specialization = specialization;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeniority() {
        return seniority;
    }

    public void setSeniority(int seniority) {
        this.seniority = seniority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "Doctor {" +
                "id=" + id +
                ", seniority=" + seniority +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return id == doctor.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, seniority, name, specialization, username, password);
    }

}

package ba.unsa.etf.rpr.Domain;

import java.util.Date;
import java.util.Objects;

public class Patient implements Id{
    private int id;
    private String name,place,address,phone_num;
    private Date birth_date;
    private boolean health_insurance;

    public Patient() {

    }

    public Patient(String name, String place, String address, String phone_num, Date birth_date, boolean health_insurance) {
        this.name = name;
        this.place = place;
        this.address = address;
        this.phone_num = phone_num;
        this.birth_date = birth_date;
        this.health_insurance = health_insurance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public boolean isHealth_insurance() {
        return health_insurance;
    }

    public void setHealth_insurance(boolean health_insurance) {
        this.health_insurance = health_insurance;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return id == patient.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, place, address, phone_num, birth_date, health_insurance);
    }


}

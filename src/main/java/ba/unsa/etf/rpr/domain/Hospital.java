package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Hospital implements Id{
    private int id,doctors;
    private String name, place, address, phone_num;
    private boolean parking;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDoctors() {
        return doctors;
    }

    public void setDoctors(int doctors) {
        this.doctors = doctors;
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

    public String getAdress() {
        return address;
    }

    public void setAdress(String adress) {
        this.address = adress;
    }

    public String getPhonenum() {
        return phone_num;
    }

    public void setPhonenum(String phonenum) {
        this.phone_num = phonenum;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hospital hospital = (Hospital) o;
        return id == hospital.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, doctors, name, place, address, phone_num, parking);
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "id=" + id +
                ", doctors=" + doctors +
                ", name='" + name + '\'' +
                ", place='" + place + '\'' +
                ", adress='" + address + '\'' +
                ", phonenum='" + phone_num + '\'' +
                ", parking=" + parking +
                '}';
    }

}

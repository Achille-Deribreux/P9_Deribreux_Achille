package com.mediscreen.assessment.dto;

import java.util.Objects;

public class PatientDTO {
    private Integer id;
    private String given;
    private String family;
    private String dob;
    private String sex;
    private String address;
    private String phone;

    public PatientDTO() {
    }

    public PatientDTO(String given, String family, String dob, String sex, String address, String phone) {
        this.given = given;
        this.family = family;
        this.dob = dob;
        this.sex = sex;
        this.address = address;
        this.phone = phone;
    }

    public PatientDTO(Integer id, String given, String family, String dob, String sex, String address, String phone) {
        this.id = id;
        this.given = given;
        this.family = family;
        this.dob = dob;
        this.sex = sex;
        this.address = address;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGiven() {
        return given;
    }

    public void setGiven(String given) {
        this.given = given;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "PatientDTO{" +
                "id=" + id +
                ", given='" + given + '\'' +
                ", family='" + family + '\'' +
                ", dob=" + dob +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientDTO that = (PatientDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(given, that.given) && Objects.equals(family, that.family) && Objects.equals(dob, that.dob) && Objects.equals(sex, that.sex) && Objects.equals(address, that.address) && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, given, family, dob, sex, address, phone);
    }
}

package com.mediscreen.notes.model;

import java.util.Date;


public class Patient {

    private Integer id;
    private String givenName;
    private String familyName;
    private Date birthdate;
    private Gender sex;
    private String address;
    private String phoneNumber;

    public Patient() {
    }

    public Patient(String givenName, String familyName, Date birthdate, Gender sex, String address, String phoneNumber) {
        this.givenName = givenName;
        this.familyName = familyName;
        this.birthdate = birthdate;
        this.sex = sex;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Gender getSex() {
        return sex;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

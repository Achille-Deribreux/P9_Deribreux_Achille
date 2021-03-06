package com.mediscreen.patient.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Table(name = "patients")
@Entity
public class Patient {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "given_name")
    private String givenName;

    @Column(name = "family_name")
    private String familyName;

    @Column(name = "birth_date")
    private Date birthdate;

    @Column(name="gender")
    private Gender sex;

    @Column(name="address")
    private String address;

    @Column(name = "phone_number")
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

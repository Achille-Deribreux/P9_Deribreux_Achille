package com.mediscreen.patient.utils;

import com.mediscreen.patient.dto.PatientDTO;
import com.mediscreen.patient.model.Patient;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Mapper {

    private Mapper() {
    }

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public static Patient mapPatientDtoToPatient(PatientDTO patientDTO) throws ParseException {
        Patient patient = new Patient();
        if(patientDTO.getId() != null){
            patient.setId(patientDTO.getId());
        }
        patient.setGivenName(patientDTO.getGiven());
        patient.setFamilyName(patientDTO.getFamily());
        patient.setAddress(patientDTO.getAddress());
        patient.setBirthdate(format.parse(patientDTO.getDob()));
        patient.setSex(patientDTO.getSex());
        patient.setPhoneNumber(patientDTO.getPhone());
        return patient;
    }


    public static PatientDTO mapPatientDtoToPatient(Patient patient){
        PatientDTO patientDTO = new PatientDTO();
        if(patient.getId() != null){
            patientDTO.setId(patient.getId());
        }
        patientDTO.setGiven(patient.getGivenName());
        patientDTO.setFamily(patient.getFamilyName());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setDob(format.format(patient.getBirthdate()));
        patientDTO.setPhone(patient.getPhoneNumber());
        patientDTO.setSex(patient.getSex());
        return patientDTO;
    }
}

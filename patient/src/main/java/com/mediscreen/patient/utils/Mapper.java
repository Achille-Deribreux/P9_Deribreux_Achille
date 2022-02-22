package com.mediscreen.patient.utils;

import com.mediscreen.patient.dto.PatientDTO;
import com.mediscreen.patient.model.Patient;

public class Mapper {

    private Mapper() {
    }

    public static Patient mapPatientDtoToPatient(PatientDTO patientDTO){
        Patient patient = new Patient();
        if(patientDTO.getId() != null){
            patient.setId(patientDTO.getId());
        }
        patient.setGivenName(patientDTO.getGiven());
        patient.setFamilyName(patientDTO.getFamily());
        patient.setAddress(patientDTO.getAddress());
        patient.setBirthdate(patientDTO.getDob());
        patient.setSex(patientDTO.getSex());
        patient.setPhoneNumber(patientDTO.getPhone());
        return patient;
    }

    public static PatientDTO mapPatientDTOToPatient(Patient patient){
        PatientDTO patientDTO = new PatientDTO();
        if(patient.getId() != null){
            patientDTO.setId(patient.getId());
        }
        patientDTO.setGiven(patient.getGivenName());
        patientDTO.setFamily(patient.getFamilyName());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setDob(patient.getBirthdate());
        patientDTO.setPhone(patient.getPhoneNumber());
        patientDTO.setSex(patient.getSex());
        return patientDTO;
    }
}

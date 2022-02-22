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
}

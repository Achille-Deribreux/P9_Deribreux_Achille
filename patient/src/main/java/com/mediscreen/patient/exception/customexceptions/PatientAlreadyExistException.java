package com.mediscreen.patient.exception.customexceptions;

public class PatientAlreadyExistException extends RuntimeException{
    public PatientAlreadyExistException(String name) {
        super("Patient already exists for name : " + name);
    }
}

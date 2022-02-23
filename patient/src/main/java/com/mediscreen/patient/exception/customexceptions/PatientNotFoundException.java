package com.mediscreen.patient.exception.customexceptions;

public class PatientNotFoundException extends RuntimeException{

    public PatientNotFoundException(String message) {
        super("User not found for :"+message);
    }
}

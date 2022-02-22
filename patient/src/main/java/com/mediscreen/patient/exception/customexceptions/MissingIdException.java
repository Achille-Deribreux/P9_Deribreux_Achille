package com.mediscreen.patient.exception.customexceptions;

public class MissingIdException extends RuntimeException {
    public MissingIdException() {
        super("Impossible to update patient, the id is missing");
    }
}

package com.mediscreen.notes.exception.customexceptions;

public class MissingIdException extends RuntimeException {
    public MissingIdException() {
        super("Impossible to update note, the id is missing");
    }
}

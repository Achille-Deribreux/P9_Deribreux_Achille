package com.mediscreen.notes.exception.customexceptions;

public class NoteNotFoundException extends RuntimeException{
    public NoteNotFoundException(String id) {
        super("Note not found for id : " + id);
    }
}

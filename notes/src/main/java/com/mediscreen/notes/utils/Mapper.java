package com.mediscreen.notes.utils;

import com.mediscreen.notes.dto.NoteDTO;
import com.mediscreen.notes.model.Note;

public class Mapper {

    private Mapper() {
    }

    public static NoteDTO mapNoteToNoteDto(Note note){
        NoteDTO noteDTO = new NoteDTO();
        if(note.getId() != null){
            noteDTO.setId(note.getId());
        }
        noteDTO.setNotes(note.getComment());
        noteDTO.setPatId(note.getPatientId());
        return noteDTO;
    }

    public static Note mapNoteDtoToNote(NoteDTO noteDTO){
        Note note = new Note();
        if(noteDTO.getId() != null){
            note.setId(noteDTO.getId());
        }
        note.setPatientId(noteDTO.getPatId());
        note.setComment(noteDTO.getNotes());
        return note;
    }
}

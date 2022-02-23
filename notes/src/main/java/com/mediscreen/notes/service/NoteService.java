package com.mediscreen.notes.service;

import com.mediscreen.notes.exception.customexceptions.NoteNotFoundException;
import com.mediscreen.notes.model.Note;
import com.mediscreen.notes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public List<Note> getAllNotes(){
        return noteRepository.findAll();
    }

    public Note getNoteById(String noteId){
        return noteRepository.findById(noteId).orElseThrow(()-> new NoteNotFoundException(noteId));
    }

    public List<Note> getAllNotesByPatientId(Integer patientId){
        return noteRepository.findAllByPatientId(patientId);
    }

    public Note addNote(Note note){
        return noteRepository.insert(note);
    }

    public Note updateNote(Note note){
        getNoteById(note.getId());
        return noteRepository.save(note);
    }

    public void deleteNote(String id){
        Note noteToDelete = getNoteById(id);
        noteRepository.delete(noteToDelete);
    }
}

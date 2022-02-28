package com.mediscreen.notes.service;

import com.mediscreen.notes.exception.customexceptions.NoteNotFoundException;
import com.mediscreen.notes.model.Note;
import com.mediscreen.notes.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    private final Logger logger = LoggerFactory.getLogger(NoteService.class);

    /**
     * Method which returns a list of all the notes that are stocked in the db
     * @return list of all the notes
     */
    public List<Note> getAllNotes(){
        logger.info("get all notes from repository");
        return noteRepository.findAll();
    }

    /**
     * Method which returns a note based on his id
     * @param noteId id of the note that you want
     * @return the wanted note
     */
    public Note getNoteById(String noteId){
        logger.info("get note from repository with id {} ",noteId);
        return noteRepository.findById(noteId).orElseThrow(()-> new NoteNotFoundException(noteId));
    }

    /**
     * Method which returns a list of all the notes of a patient that are stocked in the db
     * @param patientId id of the patient for which you want the notes
     * @return list of all the notes of the patient that you want
     */
    public List<Note> getAllNotesByPatientId(Integer patientId){
        logger.info("get all notes from repository for patient id : {}",patientId);
        return noteRepository.findAllByPatientId(patientId);
    }

    /**
     * Method which add a note in the db
     * @param note note that you want to add
     * @return added note (with id)
     */
    public Note addNote(Note note){
        logger.info("add note to db for patient id : {}",note.getPatientId());
        return noteRepository.insert(note);
    }

    /**
     * Method which update a note in the db
     * @param note note that you want to update (with id)
     * @return the updated note
     */
    public Note updateNote(Note note){
        getNoteById(note.getId());
        logger.info("update note in db with id : {}",note.getId());
        return noteRepository.save(note);
    }

    /**
     * Method who delete a note based on his id
     * @param id id of the note that you want to delete
     */
    public void deleteNote(String id){
        logger.info("delete note with id : {}",id);
        Note noteToDelete = getNoteById(id);
        noteRepository.delete(noteToDelete);
    }
}

package com.mediscreen.notes.controller;

import com.mediscreen.notes.dto.NoteDTO;
import com.mediscreen.notes.exception.customexceptions.MissingIdException;
import com.mediscreen.notes.model.Note;
import com.mediscreen.notes.service.NoteService;
import com.mediscreen.notes.utils.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/patHistory")
public class NoteController {

    @Autowired
    private NoteService noteService;

    private final Logger logger = LoggerFactory.getLogger(NoteController.class);

    /**
     * This method answer to a request /getAllNotes and returns a list of all the notes
     * @return a list of all the notes and status code 200 if everything is ok
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<Note>> getAllNotes(){
        logger.info("get request received at /note/getAll, call notes service to get all notes");
        return new ResponseEntity<>(noteService.getAllNotes(), HttpStatus.OK);
    }

    /**
     * This method answer to a request /getAllNotesByPatientId and returns a list of all the notes for a given id
     * @param patientId id of the patient for which we want the notes
     * @return a list of all the patient's notes and status code 200 if everything is ok
     */
    @GetMapping("/getAllNotesByPatientId")
    public ResponseEntity<List<Note>> getAllNotesByPatientId(@RequestParam(value = "patientId")Integer patientId){
        logger.info("get request received at /note/getAllNotesByPatientId, call notes service to get all notes for patient id : {}",patientId);
        return new ResponseEntity<>(noteService.getAllNotesByPatientId(patientId), HttpStatus.OK);
    }

    /**
     * This method answer to a request /getNoteById and returns a note based on an id
     * @param noteId id of the note that you want
     * @return the wanted note and status code 200 if everything is ok
     */
    @GetMapping("/getById")
    public ResponseEntity<Note> getNoteById(@RequestParam(value = "noteId")String noteId){
        logger.info("get request received at /note/getById, call note service to get note for id :{}}",noteId);
        return new ResponseEntity<>(noteService.getNoteById(noteId), HttpStatus.OK);
    }

    /**
     * This method answer to a request /add and add a note to the db
     * @param noteDto note that you want to add
     * @return the added note (with id) and status code 201 if everything is ok
     */
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Note> addNote(NoteDTO noteDto){
        logger.info("post request received at /note/add, call note service to add note for patient id : {}",noteDto.getPatId());
        Note note = Mapper.mapNoteDtoToNote(noteDto);
        note.setCreationDateTime(LocalDateTime.now());
        return new ResponseEntity<>(noteService.addNote(note),HttpStatus.CREATED);
    }

    /**
     * This method answer to a request /addJson and add a note to the db
     * @param noteDto note that you want to add
     * @return the added note (with id) and status code 201 if everything is ok
     */
    @PostMapping(value = "/addJson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Note> addNoteJson(@RequestBody NoteDTO noteDto){
        logger.info("post request received at /note/add, call note service to add note for patient id : {}",noteDto.getPatId());
        Note note = Mapper.mapNoteDtoToNote(noteDto);
        note.setCreationDateTime(LocalDateTime.now());
        return new ResponseEntity<>(noteService.addNote(note),HttpStatus.CREATED);
    }

    /**
     * This method answer to a request at /update and update a note in the db
     * @param noteDto note that you want to update (with id)
     * @return the updated note and status code 201 if everything is ok
     */
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Note> updateNote(@RequestBody NoteDTO noteDto){
        logger.info("post request received at /note/update, call note service to update note with id : {}",noteDto.getId());
        if(noteDto.getId() == null){
            logger.error("impossible to update, no id for note of patient id: {}",noteDto.getPatId());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        else {
            Note note = Mapper.mapNoteDtoToNote(noteDto);
            note.setCreationDateTime(LocalDateTime.now());
            return new ResponseEntity<>(noteService.updateNote(note), HttpStatus.CREATED);
        }
    }

    /**
     * This method answer to a request at /delete and delete a note in the db for a given id
     * @param noteId id of the note that you want to delete
     * @return response entity with status code 200 if everything is ok
     */
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteNote(@RequestParam(value = "noteId")String noteId){
        logger.info("delete request received at /note/delete for note id : {}",noteId);
        noteService.deleteNote(noteId);
        return new ResponseEntity<>("Note successfully deleted", HttpStatus.OK);
    }

    /**
     * This method answer to a request at /deleteAllByPatientId and delete all the notes in the db for a given patient
     * @param patientId id of the patient for which you want to delete all notes
     * @return response entity with status code 200 if everything is ok
     */
    @DeleteMapping("/deleteAllByPatietnId")
    public ResponseEntity<String> deleteAllNotesByPatientId(@RequestParam(value = "patientId") Integer patientId){
        logger.info("delete request received at /note/deleteAllByPatientId for patient id : {}",patientId);
        noteService.deleteAllByPatientId(patientId);
        return new ResponseEntity<>("All the patient's notes successfully deleted", HttpStatus.OK);
    }
}

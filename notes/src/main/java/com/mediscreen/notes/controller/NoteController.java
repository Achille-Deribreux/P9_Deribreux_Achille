package com.mediscreen.notes.controller;

import com.mediscreen.notes.model.Note;
import com.mediscreen.notes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping("/getAllNotes")
    public ResponseEntity<List<Note>> getAllNotes(){
        return new ResponseEntity<>(noteService.getAllNotes(), HttpStatus.OK);
    }


    @GetMapping("/getAllNotesByPatientId")
    public ResponseEntity<List<Note>> getAllNotesByPatientId(@RequestParam(value = "patientId")Integer patientId){
        return new ResponseEntity<>(noteService.getAllNotesByPatientId(patientId), HttpStatus.OK);
    }

    @GetMapping("/getNoteById")
    public ResponseEntity<Note> getNoteById(@RequestParam(value = "noteId")String noteId){
        return new ResponseEntity<>(noteService.getNoteById(noteId), HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<Note> addNote(@RequestBody Note note){
        return new ResponseEntity<>(noteService.addNote(note),HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Note> updateNote(@RequestBody Note note){
        return new ResponseEntity<>(noteService.updateNote(note),HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteNote(@RequestParam(value = "noteId")String noteId){
        noteService.deleteNote(noteId);
        return new ResponseEntity<>("Note successfully deleted", HttpStatus.OK);
    }
}

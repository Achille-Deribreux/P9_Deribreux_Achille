package com.mediscreen.notes.service;

import com.mediscreen.notes.exception.customexceptions.NoteNotFoundException;
import com.mediscreen.notes.model.Note;
import com.mediscreen.notes.repository.NoteRepository;
import com.mediscreen.notes.testutils.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;

@SpringBootTest
 class NoteServiceTest {

    @Mock
    NoteRepository noteRepository;

    @InjectMocks
    NoteService noteService;

    @Test
    void getAllNotesTest() {
        //Given
        List<Note> expected = TestData.getNoteList();
        List<Note> result;
        //When
        Mockito.when(noteRepository.findAll()).thenReturn(expected);
        result = noteService.getAllNotes();
        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void getNoteByIdTest() {
        //Given
        String id = "azertyu";
        Note expected = TestData.getNoteOne();
        Note result;
        //When
        Mockito.when(noteRepository.findById(id)).thenReturn(Optional.of(expected));
        result = noteService.getNoteById(id);
        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void getNoteByIdExceptionTest() {
        //Given
        String id = "azertyu";
        //When&Then
        Mockito.when(noteRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NoteNotFoundException.class,() -> noteService.getNoteById(id));
    }

    @Test
    void getAllNotesByPatientIdTest() {
        //Given
        Integer patientId = 1;
        List<Note> expected = TestData.getNoteList();
        List<Note> result;
        //When
        Mockito.when(noteRepository.findAllByPatientId(patientId)).thenReturn(expected);
        result = noteService.getAllNotesByPatientId(patientId);
        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void addNoteTest() {
        //Given
        Note noteToAdd = TestData.getNoteOne();
        //When
        Mockito.when(noteRepository.insert(noteToAdd)).thenReturn(noteToAdd);
        noteService.addNote(noteToAdd);
        //Then
        Mockito.verify(noteRepository,Mockito.times(1)).insert(noteToAdd);
    }

    @Test
    void updateNoteTest() {
        //Given
        Note noteToUpdate = TestData.getNoteOne();
        //When
        Mockito.when(noteRepository.save(noteToUpdate)).thenReturn(noteToUpdate);
        Mockito.when(noteRepository.findById(noteToUpdate.getId())).thenReturn(Optional.of(noteToUpdate));
        noteService.updateNote(noteToUpdate);
        //Then
        Mockito.verify(noteRepository,Mockito.times(1)).save(noteToUpdate);
    }

    @Test
    void deleteNoteTest() {
        //Given
        String id = "azretrg";
        Note note = TestData.getNoteOne();
        //When
        Mockito.when(noteRepository.findById(id)).thenReturn(Optional.of(note));
        doNothing().when(noteRepository).delete(note);
        noteService.deleteNote(id);
        //Then
        Mockito.verify(noteRepository,Mockito.times(1)).delete(note);
    }

    @Test
    void deleteAllByPatientId() {
        //Given
        Integer patientId = 2;
        List<Note> noteList = TestData.getNoteList();
        //When
        Mockito.when(noteRepository.findAllByPatientId(patientId)).thenReturn(noteList);
        doNothing().when(noteRepository).deleteAll(noteList);
        noteService.deleteAllByPatientId(patientId);
        //Then
        Mockito.verify(noteRepository,Mockito.times(1)).deleteAll(noteList);
    }
}

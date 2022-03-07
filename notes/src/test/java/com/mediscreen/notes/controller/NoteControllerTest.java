package com.mediscreen.notes.controller;

import com.mediscreen.notes.dto.NoteDTO;
import com.mediscreen.notes.model.Note;
import com.mediscreen.notes.service.NoteService;
import com.mediscreen.notes.testutils.Converter;
import com.mediscreen.notes.testutils.TestData;
import com.mediscreen.notes.utils.Mapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NoteController.class)
class NoteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    NoteService noteService;

    @Test
    void getAllNotesTest() throws Exception {
        mockMvc.perform(get("/patHistory/getAll")).andExpect(status().isOk());
    }

    @Test
    void getAllNotesByPatientIdTest() throws Exception {
        //Given
        Integer patientId = 1;
        //When
        Mockito.when(noteService.getAllNotesByPatientId(1)).thenReturn(TestData.getNoteList());
        //Then
        mockMvc.perform(get("/patHistory/getAllNotesByPatientId").param("patientId", String.valueOf(patientId))).andExpect(status().isOk());
    }

    @Test
    void getNoteByIdTest() throws Exception {
        //Given
        String noteId = "ertyui";
        //When
        Mockito.when(noteService.getNoteById(noteId)).thenReturn(TestData.getNoteOne());
        //Then
        mockMvc.perform(get("/patHistory/getById").param("noteId", noteId)).andExpect(status().isOk());
    }

    @Test
    void addJsonNoteTest() throws Exception {
        //Given
        Note noteToAdd = TestData.getNoteTwo();
        NoteDTO noteDTOToAdd = Mapper.mapNoteToNoteDto(noteToAdd);
        //When
        Mockito.when(noteService.addNote(noteToAdd)).thenReturn(noteToAdd);
        //Then
        mockMvc.perform(post("/patHistory/addJson").contentType(MediaType.APPLICATION_JSON).content(Converter.asJsonString(noteDTOToAdd))).andExpect(status().isCreated());
    }

    @Test
    void updateNoteTest() throws Exception {
        //Given
        Note noteToUpdate = TestData.getNoteTwo();
        NoteDTO noteDTOToUpdate = Mapper.mapNoteToNoteDto(noteToUpdate);
        //When
        Mockito.when(noteService.updateNote(noteToUpdate)).thenReturn(noteToUpdate);
        //Then
        mockMvc.perform(put("/patHistory/update").contentType(MediaType.APPLICATION_JSON).content(Converter.asJsonString(noteDTOToUpdate))).andExpect(status().isCreated());
    }

    @Test
    void deleteNoteTest() throws Exception {
        //Given
        String noteId = "rtyuio";
        //When
        Mockito.when(noteService.getNoteById(noteId)).thenReturn(TestData.getNoteOne());
        doNothing().when(noteService).deleteNote(noteId);
        //Then
        mockMvc.perform(delete("/patHistory/delete").param("noteId",noteId)).andExpect(status().isOk());
    }
}

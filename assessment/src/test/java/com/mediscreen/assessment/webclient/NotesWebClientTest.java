package com.mediscreen.assessment.webclient;

import com.mediscreen.assessment.model.Note;
import com.mediscreen.assessment.testutils.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootTest
class NotesWebClientTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    NotesWebClient notesWebClient;

    @Value("${mediscreen.main.notesurl}")
    private String BASE_URL_LOCALHOST_NOTES;

    private final String GET_ALL_NOTES_BY_ID_URL = "/getAllNotesByPatientId";
    private final String QUERY_PARAM_PATIENT_ID = "?patientId=";

    @BeforeEach
    void setUp() {
        notesWebClient.setBASE_URL_LOCALHOST_NOTES(BASE_URL_LOCALHOST_NOTES);
    }

    @Test
    void getAllNotesByPatientIdTest() {
        //Given
        int patientId = 1;
        List<Note> expected = TestData.getNoteList();
        List<Note> result;
        //When
        Mockito.when(restTemplate.exchange(BASE_URL_LOCALHOST_NOTES+GET_ALL_NOTES_BY_ID_URL+ QUERY_PARAM_PATIENT_ID+patientId,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Note>>() {}))
                .thenReturn(new ResponseEntity<>(TestData.getNoteList(), HttpStatus.OK));
        result = notesWebClient.getAllNotesByPatientId(patientId);
        //Then
        Assertions.assertEquals(expected,result);
    }
}

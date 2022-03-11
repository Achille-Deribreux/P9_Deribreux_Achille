package com.mediscreen.patient.webclient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.mockito.Mockito.doNothing;

@SpringBootTest
public class NotesWebClientTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    NotesWebClient notesWebClient;

    private final String BASE_URL_LOCALHOST_NOTES = "http://localhost:8082/patHistory";
    private final String DELETE_ALL_BY_PATIENT_ID_URL = "/deleteAllByPatietnId";
    private final String QUERY_PARAM_PATIENT_ID = "?patientId=";

    @Test
    void deleteAllNotesByPatientIdTest() {
        //Given
        int patientId = 1;
        //When
        Mockito.when(restTemplate.exchange(
                BASE_URL_LOCALHOST_NOTES+
                        DELETE_ALL_BY_PATIENT_ID_URL+
                        QUERY_PARAM_PATIENT_ID+patientId
                , HttpMethod.DELETE,null,new ParameterizedTypeReference<String>() {})).thenReturn(new ResponseEntity<>("ok",HttpStatus.OK));

        notesWebClient.deleteAllNotesByPatientId(patientId);
        //Then
        Mockito.verify(restTemplate,Mockito.times(1)).exchange(
                BASE_URL_LOCALHOST_NOTES+
                        DELETE_ALL_BY_PATIENT_ID_URL+
                        QUERY_PARAM_PATIENT_ID+patientId
                , HttpMethod.DELETE,null,new ParameterizedTypeReference<String>() {});
    }
}

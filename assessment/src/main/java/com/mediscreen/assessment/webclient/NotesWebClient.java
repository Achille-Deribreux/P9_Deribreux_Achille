package com.mediscreen.assessment.webclient;

import com.mediscreen.assessment.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class NotesWebClient {

    @Autowired
    private RestTemplate restTemplate;

    private final String BASE_URL_LOCALHOST_NOTES = "localhost:8082/patHistory";
    private final String GET_ALL_NOTES_BY_ID_URL = "/getAllNotesByPatientId";
    private final String QUERY_PARAM_PATIENT_ID = "?patientId=";


    public List<Note> getAllNotesByPatientId(int patientId){
        ResponseEntity<List<Note>> result = restTemplate.exchange(
                BASE_URL_LOCALHOST_NOTES+
                        GET_ALL_NOTES_BY_ID_URL+
                        QUERY_PARAM_PATIENT_ID+patientId
                , HttpMethod.GET,null,new ParameterizedTypeReference<List<Note>>() {});
        return result.getBody();
    }
}

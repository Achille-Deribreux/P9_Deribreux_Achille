package com.mediscreen.patient.webclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NotesWebClient {

    @Autowired
    private RestTemplate restTemplate;

    private final String BASE_URL_LOCALHOST_NOTES = "http://localhost:8082/patHistory";
    private final String DELETE_ALL_BY_PATIENT_ID_URL = "/deleteAllByPatietnId";
    private final String QUERY_PARAM_PATIENT_ID = "?patientId=";

    public void deleteAllNotesByPatientId(int patientId){
        ResponseEntity<String> result = restTemplate.exchange(
                BASE_URL_LOCALHOST_NOTES+
                        DELETE_ALL_BY_PATIENT_ID_URL+
                        QUERY_PARAM_PATIENT_ID+patientId
                , HttpMethod.DELETE,null,new ParameterizedTypeReference<String>() {});
        result.getBody();
    }
}

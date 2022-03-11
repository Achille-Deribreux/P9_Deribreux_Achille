package com.mediscreen.patient.webclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NotesWebClient {

    private final Logger logger = LoggerFactory.getLogger(NotesWebClient.class);

    @Autowired
    private RestTemplate restTemplate;

    private final String BASE_URL_LOCALHOST_NOTES = "http://localhost:8082/patHistory";
    private final String DELETE_ALL_BY_PATIENT_ID_URL = "/deleteAllByPatientId";
    private final String QUERY_PARAM_PATIENT_ID = "?patientId=";

    /**
     * Method which make a request to the note MS to delete all the notes for a given patientId
     * @param patientId id of the patient for which you want to delete the notes
     */
    public void deleteAllNotesByPatientId(int patientId){
        logger.info("call the notes MS to delete all notes for patient : {}",patientId);
        ResponseEntity<String> result = restTemplate.exchange(
                BASE_URL_LOCALHOST_NOTES+
                        DELETE_ALL_BY_PATIENT_ID_URL+
                        QUERY_PARAM_PATIENT_ID+patientId
                , HttpMethod.DELETE,null,new ParameterizedTypeReference<String>() {});
        result.getBody();
    }
}

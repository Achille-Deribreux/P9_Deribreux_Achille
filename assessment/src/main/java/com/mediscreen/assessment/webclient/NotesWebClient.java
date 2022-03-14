package com.mediscreen.assessment.webclient;

import com.mediscreen.assessment.model.Note;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class NotesWebClient {

    private final Logger logger = LoggerFactory.getLogger(NotesWebClient.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${mediscreen.main.notesurl}")
    private String BASE_URL_LOCALHOST_NOTES;

    private final String GET_ALL_NOTES_BY_ID_URL = "/getAllNotesByPatientId";
    private final String QUERY_PARAM_PATIENT_ID = "?patientId=";

    public void setBASE_URL_LOCALHOST_NOTES(String BASE_URL_LOCALHOST_NOTES) {
        this.BASE_URL_LOCALHOST_NOTES = BASE_URL_LOCALHOST_NOTES;
    }

    /**
     * Method which make a request to the notes MS to get all the notes for a given patient
     * @param patientId id of the patient for which you want the notes
     * @return a list of amm the patient's notes
     */
    public List<Note> getAllNotesByPatientId(int patientId){
        logger.info("call the note MS to get all the notes of patientId : {}",patientId);
        ResponseEntity<List<Note>> result = restTemplate.exchange(
                BASE_URL_LOCALHOST_NOTES+
                        GET_ALL_NOTES_BY_ID_URL+
                        QUERY_PARAM_PATIENT_ID+patientId
                , HttpMethod.GET,null,new ParameterizedTypeReference<List<Note>>() {});
        return result.getBody();
    }
}

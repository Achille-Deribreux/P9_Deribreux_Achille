package com.mediscreen.assessment.webclient;

import com.mediscreen.assessment.dto.PatientDTO;
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
public class PatientWebClient {

    private final Logger logger = LoggerFactory.getLogger(PatientWebClient.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${mediscreen.main.patientsurl}")
    private String BASE_URL_LOCALHOST_PATIENT;

    private final String GET_PATIENT_BY_ID_URL = "/getById";
    private final String GET_ALL_PATIENTS_URL = "/getAll";
    private final String QUERY_PARAM_PATIENT_ID = "?id=";

    public void setBASE_URL_LOCALHOST_PATIENT(String BASE_URL_LOCALHOST_PATIENT) {
        this.BASE_URL_LOCALHOST_PATIENT = BASE_URL_LOCALHOST_PATIENT;
    }

    /**
     * Method which make a request to the patient MS to get a patient by his id
     * @param patientId id of the patient that you want
     * @return the wanted patient
     */
    public PatientDTO getPatientById(int patientId){
        logger.info("call the patient MS to get the patient with id : {}",patientId);
        ResponseEntity<PatientDTO> result = restTemplate.exchange(
                BASE_URL_LOCALHOST_PATIENT+
                        GET_PATIENT_BY_ID_URL+
                        QUERY_PARAM_PATIENT_ID+patientId
                , HttpMethod.GET,null,new ParameterizedTypeReference<PatientDTO>() {});
        return result.getBody();
    }

    /**
     * Method which make a request to the patient MS to get a list of all the patients
     * @return list of all the patients
     */
    public List<PatientDTO> getAllPatients(){
        logger.info("call the patient MS to get all patients");
        ResponseEntity<List<PatientDTO>> result = restTemplate.exchange(
                BASE_URL_LOCALHOST_PATIENT+
                        GET_ALL_PATIENTS_URL
                , HttpMethod.GET,null,new ParameterizedTypeReference<List<PatientDTO>>() {});
        return result.getBody();
    }
}

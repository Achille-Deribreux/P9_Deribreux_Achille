package com.mediscreen.assessment.webclient;

import com.mediscreen.assessment.dto.PatientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class PatientWebClient {

    @Autowired
    private RestTemplate restTemplate;

    private final String BASE_URL_LOCALHOST_PATIENT = "http://localhost:8081/patient";
    private final String GET_PATIENT_BY_ID_URL = "/getById";
    private final String QUERY_PARAM_PATIENT_ID = "?id=";


    public PatientDTO getPatientById(int patientId){
        ResponseEntity<PatientDTO> result = restTemplate.exchange(
                BASE_URL_LOCALHOST_PATIENT+
                        GET_PATIENT_BY_ID_URL+
                        QUERY_PARAM_PATIENT_ID+patientId
                , HttpMethod.GET,null,new ParameterizedTypeReference<PatientDTO>() {});
        return result.getBody();
    }
}

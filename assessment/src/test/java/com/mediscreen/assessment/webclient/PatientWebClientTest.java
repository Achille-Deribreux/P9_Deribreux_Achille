package com.mediscreen.assessment.webclient;

import com.mediscreen.assessment.dto.PatientDTO;
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
public class PatientWebClientTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    PatientWebClient patientWebClient;

    @Value("${mediscreen.main.patientsurl}")
    private String BASE_URL_LOCALHOST_PATIENT;

    private final String GET_PATIENT_BY_ID_URL = "/getById";
    private final String GET_ALL_PATIENTS_URL = "/getAll";
    private final String QUERY_PARAM_PATIENT_ID = "?id=";

    @BeforeEach
    void setUp() {
        patientWebClient.setBASE_URL_LOCALHOST_PATIENT(BASE_URL_LOCALHOST_PATIENT);
    }

    @Test
    void getPatientByIdTest() {
        //Given
        int patientId = 1;
        PatientDTO expected = TestData.getOnePatientDTO();
        PatientDTO result;
        //When
        Mockito.when(restTemplate.exchange(
                BASE_URL_LOCALHOST_PATIENT+
                        GET_PATIENT_BY_ID_URL+
                        QUERY_PARAM_PATIENT_ID+patientId
                , HttpMethod.GET,null,new ParameterizedTypeReference<PatientDTO>() {}))
                .thenReturn(new ResponseEntity<>(TestData.getOnePatientDTO(), HttpStatus.OK));
        result = patientWebClient.getPatientById(1);
        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void getAllPatientsTest() {
        //Given
        List<PatientDTO> expected = TestData.getPatientDtoList();
        List<PatientDTO> result;
        //When
        Mockito.when(restTemplate.exchange(
                BASE_URL_LOCALHOST_PATIENT+
                        GET_ALL_PATIENTS_URL
                , HttpMethod.GET,null,new ParameterizedTypeReference<List<PatientDTO>>() {}))
                .thenReturn(new ResponseEntity<>(TestData.getPatientDtoList(), HttpStatus.OK));
        result = patientWebClient.getAllPatients();
        //Then
        Assertions.assertEquals(expected,result);
    }
}

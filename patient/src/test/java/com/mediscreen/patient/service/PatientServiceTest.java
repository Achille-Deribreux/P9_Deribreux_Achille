package com.mediscreen.patient.service;

import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.repository.PatientRepository;
import com.mediscreen.patient.testutils.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @Test
    void getAllPatientTest() {
        //Given
        List<Patient> expected = TestData.getPatientList();
        List<Patient> result;
        //When
        Mockito.when(patientRepository.findAll()).thenReturn(expected);
        result = patientService.getAllPatient();
        //Then
        Assertions.assertEquals(expected,result);
    }
}

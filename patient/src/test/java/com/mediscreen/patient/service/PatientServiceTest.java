package com.mediscreen.patient.service;

import com.mediscreen.patient.dto.PatientDTO;
import com.mediscreen.patient.exception.customexceptions.PatientAlreadyExistException;
import com.mediscreen.patient.exception.customexceptions.PatientNotFoundException;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.repository.PatientRepository;
import com.mediscreen.patient.testutils.TestData;
import com.mediscreen.patient.webclient.NotesWebClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;

@SpringBootTest
class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private NotesWebClient notesWebClient;

    @InjectMocks
    private PatientService patientService;

    @Test
    void getAllPatientTest() {
        //Given
        List<PatientDTO> expected = TestData.getPatientDTOList();
        List<PatientDTO> result;
        //When
        Mockito.when(patientRepository.findAll()).thenReturn(TestData.getPatientList());
        result = patientService.getAllPatient();
        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void getPatientByIdTest() {
        //Given
        Integer id = 1;
        Patient expected = TestData.getPatientOne();
        Patient result;
        //When
        Mockito.when(patientRepository.findById(id)).thenReturn(Optional.of(expected));
        result = patientService.getPatientById(id);
        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void getPatientByIdExceptionTest() {
        //Given
        Integer id = 1;
        //When & Then
        assertThrows(PatientNotFoundException.class, () -> patientService.getPatientById(id));
    }

    @Test
    void getPatientByNameTest() {
        //Given
        String givenName = "Achille";
        String familyName = "Deribreux";
        Patient expected = TestData.getPatientOne();
        Patient result;
        //When
        Mockito.when(patientRepository.findByGivenNameAndFamilyName(givenName,familyName)).thenReturn(Optional.of(expected));
        result = patientService.getPatientByName(givenName,familyName);
        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void getPatientByNameExceptionTest() {
        //When & Then
        assertThrows(PatientNotFoundException.class, () -> patientService.getPatientByName("azrt","aeztty"));
    }

    @Test
    void getPatientByFamilyNameTest() {
        //Given
        String familyName = "Deribreux";
        Patient expected = TestData.getPatientOne();
        Patient result;
        //When
        Mockito.when(patientRepository.findByFamilyName(familyName)).thenReturn(Optional.of(expected));
        result = patientService.getPatientByFamilyName(familyName);
        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void getPatientByFamilyNameExceptionTest() {
        //When & Then
        assertThrows(PatientNotFoundException.class, () -> patientService.getPatientByFamilyName("aeztty"));
    }

    @Test
    void addPatientTest() {
        //Given
        Patient patientToAdd = TestData.getPatientOne();
        //When
        patientService.addPatient(patientToAdd);
        //Then
        Mockito.verify(patientRepository,Mockito.times(1)).save(patientToAdd);
    }

    @Test
    void addPatientExceptionTest() {
        //Given
        String givenName = "Achille";
        String familyName = "Deribreux";
        Patient patientToAdd = TestData.getPatientOne();
        //When
        Mockito.when(patientRepository.findByGivenNameAndFamilyName(givenName,familyName)).thenReturn(Optional.of(patientToAdd));
        // Then
        assertThrows(PatientAlreadyExistException.class, () -> patientService.addPatient(patientToAdd));
    }

    @Test
    void updatePatientTest() {
        //Given
        Patient patientToUpdate = TestData.getPatientOne();
        Integer id = 1;
        //When
        patientToUpdate.setId(id);
        Mockito.when(patientRepository.findById(id)).thenReturn(Optional.of(patientToUpdate));
        patientService.updatePatient(patientToUpdate);
        //Then
        Mockito.verify(patientRepository,Mockito.times(1)).save(patientToUpdate);
    }

    @Test
    void deletePatientByIdTest() {
        //Given
        Patient patientToDelete = TestData.getPatientOne();
        Integer id = 1;
        //When
        patientToDelete.setId(id);
        Mockito.when(patientRepository.findById(id)).thenReturn(Optional.of(patientToDelete));
        doNothing().when(notesWebClient).deleteAllNotesByPatientId(id);
        patientService.deletePatientById(id);
        //Then
        Mockito.verify(patientRepository,Mockito.times(1)).delete(patientToDelete);
    }
}

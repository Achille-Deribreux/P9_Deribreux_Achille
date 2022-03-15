package com.mediscreen.assessment.service;

import com.mediscreen.assessment.model.Risk;
import com.mediscreen.assessment.testutils.TestData;
import com.mediscreen.assessment.webclient.NotesWebClient;
import com.mediscreen.assessment.webclient.PatientWebClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@SpringBootTest
 class AssessmentServiceTest {

    @Autowired
    AssessmentService assessmentService;

    @MockBean
    PatientWebClient patientWebClient;

    @MockBean
    NotesWebClient notesWebClient;

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    void getAllAssessmentsTest() throws ParseException {
        //Given
        Map<Integer, String> expected = TestData.getPatientAssessmentMap();
        Map<Integer, String> result;
        //When
        Mockito.when(patientWebClient.getAllPatients()).thenReturn(TestData.getPatientDtoList());
        Mockito.when(patientWebClient.getPatientById(1)).thenReturn(TestData.getOnePatientDTO());
        Mockito.when(patientWebClient.getPatientById(2)).thenReturn(TestData.getTwoPatientDTO());
        Mockito.when(notesWebClient.getAllNotesByPatientId(1)).thenReturn(new ArrayList<>());
        Mockito.when(notesWebClient.getAllNotesByPatientId(2)).thenReturn(new ArrayList<>());
        result = assessmentService.getAllAssessments();
        //Then
        Assertions.assertEquals(expected,result);

    }

    @Test
    void calculateTermsTest() {
        //Given
        int patId = 1;
        Integer expected = 4;
        Integer result;
        //When
        Mockito.when(notesWebClient.getAllNotesByPatientId(patId)).thenReturn(TestData.getNoteList());
        result = assessmentService.calculateTerms(patId);
        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void getAgeTest() throws ParseException {
        //Given
        Date date = format.parse("2010-03-01");
        long expected = 12;
        long result;
        //When
        result = assessmentService.getAge(date);
        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void getRiskNoneTest() throws ParseException {
        //Given
        Risk expected = Risk.NONE;
        Risk result;
        //When
        Mockito.when(patientWebClient.getPatientById(1)).thenReturn(TestData.getOnePatientDTO());
        Mockito.when(notesWebClient.getAllNotesByPatientId(1)).thenReturn(new ArrayList<>());
        result = assessmentService.getRisk(1);
        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void getRiskDangerMaleLess30MoreThan3OccurrencesTest() throws ParseException {
        //Given
        Risk expected = Risk.DANGER;
        Risk result;
        //When
        Mockito.when(patientWebClient.getPatientById(1)).thenReturn(TestData.getOnePatientDTO());
        Mockito.when(notesWebClient.getAllNotesByPatientId(1)).thenReturn(TestData.getNoteList());
        result = assessmentService.getRisk(1);
        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void getRiskEarlyMoreThan8OccurrencesTest() throws ParseException {
        //Given
        Risk expected = Risk.EARLY;
        Risk result;
        //When
        Mockito.when(patientWebClient.getPatientById(1)).thenReturn(TestData.getOnePatientDTO());
        Mockito.when(notesWebClient.getAllNotesByPatientId(1)).thenReturn(TestData.get8OccurrencesNotesList());
        result = assessmentService.getRisk(1);
        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void getRiskDangerMaleLess30MoreThan5OccurrencesTest() throws ParseException {
        //Given
        Risk expected = Risk.EARLY;
        Risk result;
        //When
        Mockito.when(patientWebClient.getPatientById(1)).thenReturn(TestData.getOnePatientDTO());
        Mockito.when(notesWebClient.getAllNotesByPatientId(1)).thenReturn(TestData.get7OccurrencesNotesList());
        result = assessmentService.getRisk(1);
        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void getRiskEarlyFemaleLess30MoreThan7OccurrencesTest() throws ParseException {
        //Given
        Risk expected = Risk.EARLY;
        Risk result;
        //When
        Mockito.when(patientWebClient.getPatientById(3)).thenReturn(TestData.getFemalePatientDTO());
        Mockito.when(notesWebClient.getAllNotesByPatientId(3)).thenReturn(TestData.get7OccurrencesNotesList());
        result = assessmentService.getRisk(3);
        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void getRiskDangerFemaleLess30MoreThan7OccurrencesTest() throws ParseException {
        //Given
        Risk expected = Risk.DANGER;
        Risk result;
        //When
        Mockito.when(patientWebClient.getPatientById(3)).thenReturn(TestData.getFemalePatientDTO());
        Mockito.when(notesWebClient.getAllNotesByPatientId(3)).thenReturn(TestData.getNoteList());
        result = assessmentService.getRisk(3);
        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void getRiskDangerMore30MoreThan6OccurrencesTest() throws ParseException {
        //Given
        Risk expected = Risk.DANGER;
        Risk result;
        //When
        Mockito.when(patientWebClient.getPatientById(4)).thenReturn(TestData.getOldPatientDTO());
        Mockito.when(notesWebClient.getAllNotesByPatientId(4)).thenReturn(TestData.get7OccurrencesNotesList());
        result = assessmentService.getRisk(4);
        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void getRiskEarlyMore30MoreThan6OccurrencesTest() throws ParseException {
        //Given
        Risk expected = Risk.EARLY;
        Risk result;
        //When
        Mockito.when(patientWebClient.getPatientById(4)).thenReturn(TestData.getOldPatientDTO());
        Mockito.when(notesWebClient.getAllNotesByPatientId(4)).thenReturn(TestData.get8OccurrencesNotesList());
        result = assessmentService.getRisk(4);
        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void getRiskBorderlineMore30MoreThan2OccurrencesTest() throws ParseException {
        //Given
        Risk expected = Risk.BORDERLINE;
        Risk result;
        //When
        Mockito.when(patientWebClient.getPatientById(4)).thenReturn(TestData.getOldPatientDTO());
        Mockito.when(notesWebClient.getAllNotesByPatientId(4)).thenReturn(TestData.getNoteList());
        result = assessmentService.getRisk(4);
        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void getFullResponseTest() throws ParseException {
        //Given
        int patientId = 1;
        String expected = "achille deribreux (age 2) diabetes assessment is : None";
        String result;
        //When
        Mockito.when(patientWebClient.getPatientById(patientId)).thenReturn(TestData.getOnePatientDTO());
        Mockito.when(notesWebClient.getAllNotesByPatientId(patientId)).thenReturn(new ArrayList<>());
        result = assessmentService.getFullResponse(patientId);
        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void getFullResponseByFamilyName() throws ParseException {
        //Given
        String familyName = "deribreux";
        String expected = "achille deribreux (age 2) diabetes assessment is : None";
        String result;
        //When
        Mockito.when(patientWebClient.getPatientById(1)).thenReturn(TestData.getOnePatientDTO());
        Mockito.when(patientWebClient.getPatientByFamilyName(familyName)).thenReturn(TestData.getOnePatientDTO());
        Mockito.when(notesWebClient.getAllNotesByPatientId(1)).thenReturn(new ArrayList<>());
        result = assessmentService.getFullResponseByFamilyName(familyName);
        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    void getAssessmentResponseTest() {
        Assertions.assertEquals("None",assessmentService.getAssessmentResponse(Risk.NONE));
        Assertions.assertEquals("Borderline",assessmentService.getAssessmentResponse(Risk.BORDERLINE));
        Assertions.assertEquals("In danger",assessmentService.getAssessmentResponse(Risk.DANGER));
        Assertions.assertEquals("Early onset",assessmentService.getAssessmentResponse(Risk.EARLY));
    }
}

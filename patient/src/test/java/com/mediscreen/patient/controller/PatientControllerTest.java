package com.mediscreen.patient.controller;

import com.mediscreen.patient.dto.PatientDTO;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.service.PatientService;
import com.mediscreen.patient.testutils.TestData;
import com.mediscreen.patient.utils.Converter;
import com.mediscreen.patient.utils.Mapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(PatientController.class)
class PatientControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PatientService patientService;

    @Test
    void getAllPatientsTest() throws Exception {
        mockMvc.perform(get("/patient/getAll")).andExpect(status().isOk());
    }

    @Test
    void getPatientByIdTest() throws Exception {
        Mockito.when(patientService.getPatientById(1)).thenReturn(TestData.getPatientOne());
        mockMvc.perform(get("/patient/getById").param("id","1")).andExpect(status().isOk());
    }

    @Test
    void getPatientByNameTest() throws Exception {
        String givenName = "Achille";
        String familyName = "Deribreux";
        Mockito.when(patientService.getPatientByName(givenName,familyName)).thenReturn(TestData.getPatientOne());
        mockMvc.perform(get("/patient/getByName").param("givenName",givenName).param("familyName",familyName)).andExpect(status().isOk());
    }

    @Test
    void addPatientJsonTest() throws Exception {
        //Given
        Patient patient = TestData.getPatientOne();
        PatientDTO patientToAdd = Mapper.mapPatientToPatientDto(patient);
        //When
        Mockito.when(patientService.addPatient(patient)).thenReturn(patient);
        //Then
        mockMvc.perform(post("/patient/addJson").contentType(MediaType.APPLICATION_JSON).content(Converter.asJsonString(patientToAdd))).andExpect(status().isCreated());
    }

    @Test
    void addPatientTest() throws Exception {
        //Given
        Patient patient = TestData.getPatientOne();
        PatientDTO patientToAdd = Mapper.mapPatientToPatientDto(patient);
        //When
        Mockito.when(patientService.addPatient(patient)).thenReturn(patient);
        //Then
        mockMvc.perform(post("/patient/add").contentType(MediaType.APPLICATION_FORM_URLENCODED).content("family=Deribreux&given=Achille&dob=2000-03-11&sex=M&address=1 rue des developpeurs&phone=0123456789")).andExpect(status().isCreated());
    }

    @Test
    void updatePatientTest() throws Exception {
        //Given
        Patient patient = TestData.getPatientOne();
        patient.setId(1);
        PatientDTO patientToUpdate = Mapper.mapPatientToPatientDto(patient);
        //When
        Mockito.when(patientService.updatePatient(patient)).thenReturn(patient);
        //Then
        mockMvc.perform(put("/patient/update").contentType(MediaType.APPLICATION_JSON).content(Converter.asJsonString(patientToUpdate))).andExpect(status().isCreated());
    }

    @Test
    void updatePatientExceptionTest() throws Exception {
        //Given
        Patient patient = TestData.getPatientOne();
        PatientDTO patientToUpdate = Mapper.mapPatientToPatientDto(patient);
        //When
        Mockito.when(patientService.updatePatient(patient)).thenReturn(patient);
        //Then
        mockMvc.perform(put("/patient/update").contentType(MediaType.APPLICATION_JSON).content(Converter.asJsonString(patientToUpdate))).andExpect(status().isBadRequest());
    }

    @Test
    void deleteByIdTest() throws Exception {
        //Given
        Integer id = 1;
        Patient patient = TestData.getPatientOne();
        patient.setId(id);
        //When
        Mockito.when(patientService.getPatientById(id)).thenReturn(patient);
        doNothing().when(patientService).deletePatientById(id);
        //Then
        mockMvc.perform(delete("/patient/delete").param("id","1")).andExpect(status().isOk());
    }
}

package com.mediscreen.assessment.controller;

import com.mediscreen.assessment.model.Risk;
import com.mediscreen.assessment.service.AssessmentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AssessmentController.class)
class AssessmentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AssessmentService assessmentService;

    @Test
    void getRiskByIdTest() throws Exception {
        //Given
        Integer id = 1;
        //When
        Mockito.when(assessmentService.getRisk(id)).thenReturn(Risk.BORDERLINE);
        //Then
        mockMvc.perform(get("/getRiskById").param("id", String.valueOf(id))).andExpect(status().isOk());
    }

    @Test
    void getAllAssessmentsTest() throws Exception {
        //Given
        Map<Integer,String> allAssessmentsMap = new HashMap<>();
        //When
        Mockito.when(assessmentService.getAllAssessments()).thenReturn(allAssessmentsMap);
        //Then
        mockMvc.perform(get("/getAllAssessments")).andExpect(status().isOk());
    }

    //TODO TEST URL ENCODED VALUE
}

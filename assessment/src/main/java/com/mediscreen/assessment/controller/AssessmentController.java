package com.mediscreen.assessment.controller;

import com.mediscreen.assessment.dto.PatientDTO;
import com.mediscreen.assessment.model.Patient;
import com.mediscreen.assessment.model.Risk;
import com.mediscreen.assessment.service.AssessmentService;
import com.mediscreen.assessment.utils.Mapper;
import com.mediscreen.assessment.webclient.PatientWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;

@Controller
public class AssessmentController {

    @Autowired
    private AssessmentService assessmentService;

    @GetMapping("/getRiskById")
    public ResponseEntity<Risk> getRiskById(@RequestParam(value="id") Integer patientId) throws ParseException {
        return new ResponseEntity<>(assessmentService.getRisk(patientId),HttpStatus.OK);
    }

    @PostMapping(value="/assess/id", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getPatientAssessment(Integer patId) throws ParseException {
        return new ResponseEntity<>(assessmentService.getFullResponse(patId),HttpStatus.OK);
    }
}

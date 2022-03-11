package com.mediscreen.assessment.controller;

import com.mediscreen.assessment.dto.PatientDTO;
import com.mediscreen.assessment.model.Patient;
import com.mediscreen.assessment.model.Risk;
import com.mediscreen.assessment.service.AssessmentService;
import com.mediscreen.assessment.utils.Mapper;
import com.mediscreen.assessment.webclient.PatientWebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.Map;

@Controller
public class AssessmentController {

    @Autowired
    private AssessmentService assessmentService;

    private final Logger logger = LoggerFactory.getLogger(AssessmentController.class);

    /**
     * This method answer to a request at /getRiskById and returns the risk of diabetes for a given patient
     * @param patientId id of the patient for which you wants the risk
     * @return the risk for the patient and status code 200 if everything is ok
     */
    @GetMapping("/getRiskById")
    public ResponseEntity<Risk> getRiskById(@RequestParam(value="id") Integer patientId) throws ParseException {
        logger.info("get request received at /getRiskById, call the assessmentService to get the risks for patient {}",patientId);
        return new ResponseEntity<>(assessmentService.getRisk(patientId),HttpStatus.OK);
    }

    /**
     * This method answer to a request at /getAllAssessments and returns a map of all patient's id in key and the risks for each patient in value
     * @return map of all patient's id in key and the risks for each patient in value
     */
    @GetMapping("/getAllAssessments")
    public HttpEntity<Map<Integer, String>> getAllAssessments() throws ParseException {
        logger.info("get request received at /getAllAssessments, call the assessmentService to get the risks for each patient");
        return new ResponseEntity<>(assessmentService.getAllAssessments(),HttpStatus.OK);
    }

    /**
     * This method answer to a post request at /assess/id and returns a string with the name, the age and the risk of a given patient
     * @param patId id of the patient for which you want to have risk
     * @return  string with the name, the age and the risk of a given patient
     */
    @PostMapping(value="/assess/id", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getPatientAssessment(Integer patId) throws ParseException {
        logger.info("get request received at /assess/id, call the assessmentService to get the risks for patient {}",patId);
        return new ResponseEntity<>(assessmentService.getFullResponse(patId),HttpStatus.OK);
    }
}

package com.mediscreen.patient.controller;

import com.mediscreen.patient.dto.PatientDTO;
import com.mediscreen.patient.exception.customexceptions.MissingIdException;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.service.PatientService;
import com.mediscreen.patient.utils.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    private Logger logger = LoggerFactory.getLogger(PatientService.class);

    /**
     * This method answer to a request at /patient/getAll
     * @return list of all the patients and status code 200 if everything is ok
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<Patient>> getAllPatients(){
        logger.info("get request received at /patients/getAll, call patient service to get all patientsList");
        return new ResponseEntity<>(patientService.getAllPatient(), HttpStatus.OK);
    }

    /**
     * This method answer to a request at /patient/getById
     * @param id id of the patient that you want to get
     * @return the wanted patient and status code 200 if everything is ok
     */
    @GetMapping("/getById")
    public ResponseEntity<Patient> getPatientById(@RequestParam(value = "id") Integer id){
        logger.info("get request received at /patients/getById, call patient service to get patient for id : "+id);
        return new ResponseEntity<>(patientService.getPatientById(id),HttpStatus.OK);
    }

    /**
     * This method answer to a request at /patient/getByName
     * @param givenName firstname of the patient that you want
     * @param familyName lastname of the patient that you want
     * @return the wanted patient and status code 200 if everything is ok
     */
    @GetMapping("/getByName")
    public ResponseEntity<Patient> getPatientByName(@RequestParam(value = "givenName") String givenName,@RequestParam(value = "familyName") String familyName ){
        logger.info("get request received at /patients/getByName, call patient service to get patient for name : "+givenName+" "+familyName);
        return new ResponseEntity<>(patientService.getPatientByName(givenName,familyName),HttpStatus.OK);
    }

    /**
     * This method answer to a request at /patient/add
     * @param patient patient that you want to add
     * @return the added patient (with id) and status code 201 if everything is ok
     */
    @PostMapping("/add")
    public ResponseEntity<Patient> addPatient(@RequestBody PatientDTO patient){
        logger.info("post request received at /patients/add, call patient service to add patient for name : "+patient.getGiven()+" "+patient.getFamily());
        Patient patientToAdd = Mapper.mapPatientDtoToPatient(patient);
        return new ResponseEntity<>(patientService.addPatient(patientToAdd),HttpStatus.CREATED);
    }

    /**
     * This method answer to a request at /patient/update
     * @param patient patient that you want to update (with id)
     * @return the updated patient and status code 201 if everything is ok
     */
    @PutMapping("/update")
    public ResponseEntity<Patient> updatePatient(@RequestBody PatientDTO patient){
        logger.info("put request received at /patients/update, call patient service to update patient for name : "+patient.getGiven()+" "+patient.getFamily());
        if(patient.getId() == null){
            logger.error("impossible to update, no id for patient : "+patient.getGiven()+" "+patient.getFamily());
            throw new MissingIdException();
        }
        Patient patientToUpdate = Mapper.mapPatientDtoToPatient(patient);
        return new ResponseEntity<>(patientService.updatePatient(patientToUpdate),HttpStatus.CREATED);
    }

    /**
     * This method answer to a request at /patient/delete
     * @param id id of the patient that you want to delete
     * @return response entity with status code 200 if everything is ok
     */
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteById(@RequestParam(value = "id")Integer id){
        logger.info("delete request received at /patients/delete for id :"+id);
        patientService.deletePatientById(id);
        return new ResponseEntity<>("successfully deleted", HttpStatus.OK);
    }
}

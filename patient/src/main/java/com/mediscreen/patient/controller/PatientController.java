package com.mediscreen.patient.controller;

import com.mediscreen.patient.exception.customexceptions.MissingIdException;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {

    //TODO CHANGE PATIENT IN DTO

    @Autowired
    private PatientService patientService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Patient>> getAllPatients(){
        return new ResponseEntity<>(patientService.getAllPatient(), HttpStatus.OK);
    }

    @GetMapping("/getById")
    public ResponseEntity<Patient> getPatientById(@RequestParam(value = "id") Integer id){
        return new ResponseEntity<>(patientService.getPatientById(id),HttpStatus.OK);
    }

    @GetMapping("/getByName")
    public ResponseEntity<Patient> getPatientByName(@RequestParam(value = "givenName") String givenName,@RequestParam(value = "familyName") String familyName ){
        return new ResponseEntity<>(patientService.getPatientByName(givenName,familyName),HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient){
        return new ResponseEntity<>(patientService.addPatient(patient),HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient){
        if(patient.getId() == null){
            throw new MissingIdException();
        }
        return new ResponseEntity<>(patientService.updatePatient(patient),HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteById(@RequestParam(value = "id")Integer id){
        patientService.deletePatientById(id);
        return new ResponseEntity<>("successfully deleted", HttpStatus.OK);
    }
}

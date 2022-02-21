package com.mediscreen.patient.service;

import com.mediscreen.patient.exception.customexceptions.PatientAlreadyExistException;
import com.mediscreen.patient.exception.customexceptions.PatientNotFoundException;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientsRepository;

    private Logger logger = LoggerFactory.getLogger(PatientService.class);

    /**
     * Method which returns a list of all the patients in database
     * @return list of all the patients
     */
    public List<Patient> getAllPatient(){
        logger.info("get all patients from the repository");
        List<Patient> patientList = new ArrayList<>();
        patientsRepository.findAll().forEach(patientList::add);
        return patientList;
    }

    /**
     * Method which search a patient based on his id
     * @param id id of the patient that you want
     * @return the wanted patient
     */
    public Patient getPatientById(Integer id){
        logger.info("search for patient : "+ id);
        return patientsRepository.findById(id).orElseThrow(()->new PatientNotFoundException("id : "+id));
    }

    /**
     * Method which search a patient based on his name
     * @param givenName givenName of the patient that you want
     * @param familyName familyName of the patient that you want
     * @return the wanted patient
     */
    public Patient getPatientByName(String givenName, String familyName){
        logger.info("search for patient :" +givenName+" "+familyName);
        return patientsRepository.findByGivenNameAndFamilyName(givenName,familyName).orElseThrow(()-> new PatientNotFoundException("name : "+givenName+" "+familyName));
    }

    /**
     * Method which add a patient in the database
     * @param patient the patient that you want to add
     * @return the added patient (with id)
     */
    public Patient addPatient(Patient patient){
        try{
            logger.info("check if patient : "+patient.getGivenName()+" "+patient.getFamilyName()+" is present in the database");
            getPatientByName(patient.getGivenName(), patient.getFamilyName());
            throw new PatientAlreadyExistException(patient.getGivenName()+patient.getFamilyName());
        }catch (PatientNotFoundException e){
            logger.info("save patient : "+patient.getGivenName()+" "+patient.getFamilyName()+" in the database");
            return patientsRepository.save(patient);
        }
    }

    /**
     * Method which updates a patient in the database
     * @param patient that you want to update (with id)
     * @return the updated patient
     */
    public Patient updatePatient(Patient patient){
        getPatientById(patient.getId());
        logger.info("save patient with id :"+patient.getId());
        return patientsRepository.save(patient);
    }

    /**
     * Method which delete a patient based on his id
     * @param id id of the patient that you want to delete
     */
    public void deletePatientById(Integer id){
        Patient patientToDelete = getPatientById(id);
        logger.info("delete patient with id : "+patientToDelete.getId());
        patientsRepository.delete(patientToDelete);
    }




}

package com.mediscreen.patient.service;

import com.mediscreen.patient.dto.PatientDTO;
import com.mediscreen.patient.exception.customexceptions.PatientAlreadyExistException;
import com.mediscreen.patient.exception.customexceptions.PatientNotFoundException;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.repository.PatientRepository;
import com.mediscreen.patient.utils.Mapper;
import com.mediscreen.patient.webclient.NotesWebClient;
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

    @Autowired
    private NotesWebClient notesWebClient;

    private Logger logger = LoggerFactory.getLogger(PatientService.class);

    /**
     * Method which returns a list of all the patients in database
     * @return list of all the patients
     */
    public List<PatientDTO> getAllPatient(){
        logger.info("get all patients from the repository");
        List<PatientDTO> patientList = new ArrayList<>();
        for(Patient patient : patientsRepository.findAll()){
            patientList.add(Mapper.mapPatientToPatientDto(patient));
        }
        return patientList;
    }

    /**
     * Method which search a patient based on his id
     * @param id id of the patient that you want
     * @return the wanted patient
     */
    public Patient getPatientById(Integer id){
        logger.info("search for patient : {} ", id);
        return patientsRepository.findById(id).orElseThrow(()->new PatientNotFoundException("id : "+id));
    }

    /**
     * Method which search a patient based on his name
     * @param givenName givenName of the patient that you want
     * @param familyName familyName of the patient that you want
     * @return the wanted patient
     */
    public Patient getPatientByName(String givenName, String familyName){
        logger.info("search for patient : {} {}",givenName,familyName);
        return patientsRepository.findByGivenNameAndFamilyName(givenName,familyName).orElseThrow(()-> new PatientNotFoundException("name : "+givenName+" "+familyName));
    }

    /**
     * Method which search a patient based on his name
     * @param familyName familyName of the patient that you want
     * @return the wanted patient
     */
    public Patient getPatientByFamilyName(String familyName){
        logger.info("search for patient : {}",familyName);
        return patientsRepository.findByFamilyName(familyName).orElseThrow(()-> new PatientNotFoundException("name :   "+familyName));
    }

    /**
     * Method which add a patient in the database
     * @param patient the patient that you want to add
     * @return the added patient (with id)
     */
    public Patient addPatient(Patient patient){
        try{
            logger.info("check if patient : {} {} is present in the database",patient.getGivenName(),patient.getFamilyName());
            getPatientByName(patient.getGivenName(), patient.getFamilyName());
            throw new PatientAlreadyExistException(patient.getGivenName()+patient.getFamilyName());
        }catch (PatientNotFoundException e){
            logger.info("save patient : {} {} in the database",patient.getGivenName(),patient.getFamilyName());
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
        logger.info("save patient with id : {}",patient.getId());
        return patientsRepository.save(patient);
    }

    /**
     * Method which delete a patient based on his id
     * @param id id of the patient that you want to delete
     */
    public void deletePatientById(Integer id){
        Patient patientToDelete = getPatientById(id);
        logger.info("delete patient with id : {}",id);
        notesWebClient.deleteAllNotesByPatientId(id);
        patientsRepository.delete(patientToDelete);
    }


}

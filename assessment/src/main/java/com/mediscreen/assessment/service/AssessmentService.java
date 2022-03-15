package com.mediscreen.assessment.service;

import com.mediscreen.assessment.dto.PatientDTO;
import com.mediscreen.assessment.model.*;
import com.mediscreen.assessment.utils.Mapper;
import com.mediscreen.assessment.webclient.NotesWebClient;
import com.mediscreen.assessment.webclient.PatientWebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AssessmentService {

    @Autowired
    private PatientWebClient patientWebClient;

    @Autowired
    private NotesWebClient notesWebClient;

    private final Logger logger = LoggerFactory.getLogger(AssessmentService.class);

    /**
     * Method which get assessment for each patient
     * @return map with patientId as key and risk for the patient as value
     */
    public Map<Integer, String> getAllAssessments() throws ParseException {
        Map<Integer,String> allAssessmentsMap = new HashMap<>();
        logger.info("call patient web client to getAllPatients in order to generate all assessments");
        for(PatientDTO patientDTO : patientWebClient.getAllPatients()){
            Patient patient = Mapper.mapPatientDtoToPatient(patientDTO);
            allAssessmentsMap.put(patient.getId(),getAssessmentResponse(getRisk(patient.getId())));
        }
        return allAssessmentsMap;
    }

    /**
     * Method which calculates the amount of risky terms that are present in the notes for a given patient
     * @param patientId id of the patient for which you want to calculate the terms
     * @return number of terms that are present in the patient's notes
     */
    public Integer calculateTerms(Integer patientId){
        logger.info("calculate terms for patient {}",patientId );
        logger.info("call notes web client to get all notes for patient {}",patientId);
        List<Note> patientsNotes = notesWebClient.getAllNotesByPatientId(patientId);
        int counter = 0;
        for(Note patientNote :  patientsNotes){
            for(String term : Terms.getTerms()){
                if(patientNote.getComment().contains(term)){
                    counter ++;
                }
            }
        }
        return counter;
    }

    /**
     * Method who calculates the age of a patient, based on his birthdate
     * @param birthdate birthdate of the patient
     * @return age of the patient
     */
    public long getAge(Date birthdate){
        logger.info("calculate age from birthdate : {}",birthdate);
        LocalDate birth = birthdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period interval = Period.between(birth,LocalDate.now());
        return interval.getYears();
    }

    /**
     * Method who calculates the risk of a patient based on his sex, age and the sum of risky terms that are present in his notes
     * @param patientId id of the patient for which you want to calculate the risk
     * @return diabetes risk of the patient
     */
    public Risk getRisk(Integer patientId) throws ParseException {
        logger.info("get risk level for patient id : {}",patientId);
        Integer termOccurrences = calculateTerms(patientId);
        Patient patient = Mapper.mapPatientDtoToPatient(patientWebClient.getPatientById(patientId));

        if(getAge(patient.getBirthdate()) > 30 && termOccurrences >= 8 ){
            //OK
            return Risk.EARLY;
        }
        else if(patient.getSex().equals(Gender.MALE) && getAge(patient.getBirthdate()) < 30 && termOccurrences >= 5 ){
            //OK
            return Risk.EARLY;
        }
        else if(patient.getSex().equals(Gender.FEMALE) && getAge(patient.getBirthdate()) < 30 && termOccurrences >= 7 ){
            //OK
            return Risk.EARLY;
        }
        else if(patient.getSex().equals(Gender.MALE) && getAge(patient.getBirthdate()) < 30 && termOccurrences >= 3 ){
            //OK
            return Risk.DANGER;
        }
        else if(patient.getSex().equals(Gender.FEMALE) && getAge(patient.getBirthdate()) < 30 && termOccurrences >= 4 ){
            //OK
            return Risk.DANGER;
        }
        else if(getAge(patient.getBirthdate()) > 30 && termOccurrences >= 6 ){

            return Risk.DANGER;
        }
        else if(termOccurrences >= 2 && getAge(patient.getBirthdate()) > 30){
            return Risk.BORDERLINE;
        }
        else{
            //OK
            return Risk.NONE;
        }
    }

    /**
     * Method who calculates the risk for a patient and generates a full sentence with the risk, the age and the name of the patient, based on the patient's family name
     * @param familyName family name of the patient for which you want the sentence
     * @return sentence with the risk, the age and the name of the patient
     */
    public String getFullResponseByFamilyName(String familyName) throws ParseException {
        logger.info("get full response (risk, name and age) for patient family name : {}",familyName);
        Patient patient = Mapper.mapPatientDtoToPatient(patientWebClient.getPatientByFamilyName(familyName));
        return patient.getGivenName()+" "+patient.getFamilyName()+" (age "+getAge(patient.getBirthdate())+") diabetes assessment is : "+getAssessmentResponse(getRisk(patient.getId()));
    }

    /**
     * Method who calculates the risk for a patient and generates a full sentence with the risk, the age and the name of the patient, based on the patient's id
     * @param patientId id of the patient for which you want the sentence
     * @return sentence with the risk, the age and the name of the patient
     */
    public String getFullResponse(Integer patientId) throws ParseException {
        logger.info("get full response (risk, name and age) for patientId{}",patientId);
        Patient patient = Mapper.mapPatientDtoToPatient(patientWebClient.getPatientById(patientId));
        return patient.getGivenName()+" "+patient.getFamilyName()+" (age "+getAge(patient.getBirthdate())+") diabetes assessment is : "+getAssessmentResponse(getRisk(patientId));
    }

    /**
     * Method which respond the risk in a full string format for a given risk (in Risk format)
     * @param risk the risk you want to convert in String
     * @return  full string format of the given risk
     */
    public String getAssessmentResponse(Risk risk){
        logger.info("convert risk {} into a full risk string format",risk);
        if(risk.equals(Risk.NONE)){
            return "None";
        }
        else if(risk.equals(Risk.BORDERLINE)){
            return "Borderline";
        }
        else if(risk.equals(Risk.DANGER)){
            return "In danger";
        }
        else{
            return "Early onset";
        }
    }
}
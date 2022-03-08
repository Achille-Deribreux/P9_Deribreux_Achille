package com.mediscreen.assessment.service;

import com.mediscreen.assessment.dto.PatientDTO;
import com.mediscreen.assessment.model.*;
import com.mediscreen.assessment.utils.Mapper;
import com.mediscreen.assessment.webclient.NotesWebClient;
import com.mediscreen.assessment.webclient.PatientWebClient;
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

    public Map<Integer, String> getAllAssessments() throws ParseException {
        Map<Integer,String> allAssessmentsMap = new HashMap<>();

        for(PatientDTO patientDTO : patientWebClient.getAllPatients()){
            Patient patient = Mapper.mapPatientDtoToPatient(patientDTO);
            allAssessmentsMap.put(patient.getId(),getAssessmentResponse(getRisk(patient.getId())));
        }
        return allAssessmentsMap;
    }

    public Integer calculateTerms(Integer patientId){
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

    public long getAge(Date birthdate){
        LocalDate birth = birthdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period interval = Period.between(birth,LocalDate.now());
        return interval.getYears();
    }

    public Risk getRisk(Integer patientId) throws ParseException {
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

    public String getFullResponse(Integer patientId) throws ParseException {
        Patient patient = Mapper.mapPatientDtoToPatient(patientWebClient.getPatientById(patientId));
        return patient.getGivenName()+" "+patient.getFamilyName()+" (age "+getAge(patient.getBirthdate())+") diabetes assessment is : "+getAssessmentResponse(getRisk(patientId));
    }

    public String getAssessmentResponse(Risk risk){
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

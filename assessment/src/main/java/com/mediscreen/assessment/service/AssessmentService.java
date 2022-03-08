package com.mediscreen.assessment.service;

import com.mediscreen.assessment.model.*;
import com.mediscreen.assessment.utils.Mapper;
import com.mediscreen.assessment.webclient.NotesWebClient;
import com.mediscreen.assessment.webclient.PatientWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.*;
import java.util.Date;
import java.util.List;

import static java.time.temporal.ChronoUnit.YEARS;

@Service
public class AssessmentService {

    @Autowired
    private PatientWebClient patientWebClient;

    @Autowired
    private NotesWebClient notesWebClient;

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
        Period interval = Period.between(LocalDate.now(),birth);
        return interval.getYears();
    }

    public Risk getRisk(Integer patientId) throws ParseException {
        Integer termOccurences = calculateTerms(patientId);
        Patient patient = Mapper.mapPatientDtoToPatient(patientWebClient.getPatientById(patientId));

        if(getAge(patient.getBirthdate()) > 30 && termOccurences >= 8 ){
            return Risk.EARLY;
        }
        else if(patient.getSex().equals(Gender.MALE) && getAge(patient.getBirthdate()) < 30 && termOccurences >= 5 ){
            return Risk.EARLY;
        }
        else if(patient.getSex().equals(Gender.FEMALE) && getAge(patient.getBirthdate()) < 30 && termOccurences >= 7 ){
            return Risk.EARLY;
        }
        else if(patient.getSex().equals(Gender.MALE) && getAge(patient.getBirthdate()) < 30 && termOccurences >= 3 ){
            return Risk.DANGER;
        }
        else if(patient.getSex().equals(Gender.FEMALE) && getAge(patient.getBirthdate()) < 30 && termOccurences >= 4 ){
            return Risk.DANGER;
        }
        else if(getAge(patient.getBirthdate()) > 30 && termOccurences >= 6 ){
            return Risk.DANGER;
        }
        else if(termOccurences >= 2 && getAge(patient.getBirthdate()) > 30){
            return Risk.BORDERLINE;
        }
        else{
            return Risk.NONE;
        }
    }

    public String getFullResponse(Integer patientId) throws ParseException {
        Patient patient = Mapper.mapPatientDtoToPatient(patientWebClient.getPatientById(patientId));
        return patient.getGivenName()+" "+patient.getFamilyName()+"(age"+getAge(patient.getBirthdate())+")"+getAssessmentResponse(getRisk(patientId));
    }

    public String getAssessmentResponse(Risk risk){
        if(risk.equals(Risk.NONE)){
            return "diabetes assessment is: None";
        }
        else if(risk.equals(Risk.BORDERLINE)){
            return "diabetes assessment is: Borderline";
        }
        else if(risk.equals(Risk.DANGER)){
            return "diabetes assessment is: In danger";
        }
        else{
            return "diabetes assessment is: Early onset";
        }
    }
}

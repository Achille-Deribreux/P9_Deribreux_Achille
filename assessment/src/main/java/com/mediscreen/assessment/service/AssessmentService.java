package com.mediscreen.assessment.service;

import com.mediscreen.assessment.model.*;
import com.mediscreen.assessment.utils.Mapper;
import com.mediscreen.assessment.webclient.NotesWebClient;
import com.mediscreen.assessment.webclient.PatientWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static java.time.temporal.ChronoUnit.YEARS;
import java.text.ParseException;
import java.time.*;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
        return YEARS.between(Instant.now(), birth);
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
}

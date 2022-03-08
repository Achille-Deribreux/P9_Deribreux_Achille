package com.mediscreen.assessment.testutils;

import com.mediscreen.assessment.dto.PatientDTO;
import com.mediscreen.assessment.model.Note;
import com.mediscreen.assessment.model.Patient;

import java.time.LocalDateTime;
import java.util.*;

public class TestData {

    public static PatientDTO getOnePatientDTO(){
        return new PatientDTO(1,"achille","deribreux","2020-03-01","M","rue de la gare","234567");
    }

    public static PatientDTO getTwoPatientDTO(){
        return new PatientDTO(2,"jules","deribreux","2010-03-01","M","rue de la gare","234567");
    }

    public static PatientDTO getFemalePatientDTO(){
        return new PatientDTO(3,"Clarisse","deribreux","2000-03-01","F","rue de la gare","234567");
    }

    public static PatientDTO getOldPatientDTO(){
        return new PatientDTO(4,"vieux","deribreux","1900-03-01","F","rue de la gare","234567");
    }

    public static List<PatientDTO> getPatientDtoList(){
        return new ArrayList<>(
                Arrays.asList(getOnePatientDTO(),getTwoPatientDTO())
        );
    }

    public static Map<Integer, String> getPatientAssessmentMap(){
        Map<Integer, String> hashmap = new HashMap<>();
        hashmap.put(1,"None");
        hashmap.put(2,"None");
        return hashmap;
    }

    public static Note getNoteOne(){
        return new Note("skvjbqjhdbv",1, LocalDateTime.of(2022,2,24,18,13,32),"This is a comment Poids kdsjvhsk Anormal ...");
    }

    public static Note getNoteTwo(){
        return new Note("dfghdbsj",1, null,"Cholestérol mongodbidbdehsq Réaction");
    }

    public static List<Note> getNoteList(){
        return new ArrayList<>(Arrays.asList(getNoteOne(),getNoteTwo()));
    }

    public static List<Note> get7OccurrencesNotesList(){
        return new ArrayList<>(Arrays.asList(
         new Note("dfghdbsj",1, null,"Cholestérol Taille Poids Réaction Rechute Vertige Anticorps")
        ));
    }

    public static List<Note> get8OccurrencesNotesList(){
        return new ArrayList<>(Arrays.asList(
                new Note("dfghdbsj",1, null,"Cholestérol Microalbumine Taille Poids Réaction Rechute Vertige Anticorps")
        ));
    }
}

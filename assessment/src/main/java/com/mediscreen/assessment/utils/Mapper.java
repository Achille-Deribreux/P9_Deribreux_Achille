package com.mediscreen.assessment.utils;

import com.mediscreen.assessment.dto.NoteDTO;
import com.mediscreen.assessment.dto.PatientDTO;
import com.mediscreen.assessment.model.Gender;
import com.mediscreen.assessment.model.Note;
import com.mediscreen.assessment.model.Patient;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Mapper {

    private Mapper() {
    }

    public static NoteDTO mapNoteToNoteDto(Note note){
        NoteDTO noteDTO = new NoteDTO();
        if(note.getId() != null){
            noteDTO.setId(note.getId());
        }
        noteDTO.setNotes(note.getComment());
        noteDTO.setPatId(note.getPatientId());
        return noteDTO;
    }

    public static Note mapNoteDtoToNote(NoteDTO noteDTO){
        Note note = new Note();
        if(noteDTO.getId() != null){
            note.setId(noteDTO.getId());
        }
        note.setPatientId(noteDTO.getPatId());
        note.setComment(noteDTO.getNotes());
        return note;
    }

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public static Patient mapPatientDtoToPatient(PatientDTO patientDTO) throws ParseException {
        Patient patient = new Patient();
        if(patientDTO.getId() != null){
            patient.setId(patientDTO.getId());
        }
        if(patientDTO.getSex().equals("F")){
            patient.setSex(Gender.FEMALE);
        }
        else if(patientDTO.getSex().equals("M")){
            patient.setSex(Gender.MALE);
        }
        patient.setGivenName(patientDTO.getGiven());
        patient.setFamilyName(patientDTO.getFamily());
        patient.setAddress(patientDTO.getAddress());
        patient.setBirthdate(format.parse(patientDTO.getDob()));
        patient.setPhoneNumber(patientDTO.getPhone());
        return patient;
    }


    public static PatientDTO mapPatientToPatientDto(Patient patient){
        PatientDTO patientDTO = new PatientDTO();
        if(patient.getId() != null){
            patientDTO.setId(patient.getId());
        }
        patientDTO.setGiven(patient.getGivenName());
        patientDTO.setFamily(patient.getFamilyName());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setDob(format.format(patient.getBirthdate()));
        patientDTO.setPhone(patient.getPhoneNumber());
        if(patient.getSex().equals(Gender.FEMALE)){
            patientDTO.setSex("F");
        }
        else if(patient.getSex().equals(Gender.MALE)){
            patientDTO.setSex("M");
        }
        return patientDTO;
    }
}

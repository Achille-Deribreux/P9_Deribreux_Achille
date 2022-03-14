package com.mediscreen.patient.testutils;

import com.mediscreen.patient.dto.PatientDTO;
import com.mediscreen.patient.model.Gender;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.utils.Mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class TestData {
    public static List<Patient> getPatientList(){
        return new ArrayList<>(Arrays.asList(getPatientOne(),getPatientTwo()));
    }

    public static List<PatientDTO> getPatientDTOList(){
        return new ArrayList<>(Arrays.asList(
                Mapper.mapPatientToPatientDto(getPatientOne()),
                Mapper.mapPatientToPatientDto(getPatientTwo())
        ));
    }

    public static Patient getPatientOne(){
        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.MONTH, 3);
        c1.set(Calendar.DATE, 11);
        c1.set(Calendar.YEAR, 2000);

        return new Patient(
                "Achille",
                "Deribreux",
                c1.getTime(),
                Gender.MALE,
                "1 rue des developpeurs",
                "0123456789"
        );
    }

    public static Patient getPatientTwo(){
        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.MONTH, 6);
        c1.set(Calendar.DATE, 7);
        c1.set(Calendar.YEAR, 2004);
        return new Patient(
                "Jules",
                "Deribreux",
                c1.getTime(),
                Gender.MALE,
                "2 rue des artistes",
                "0123456789"
        );
    }
}

package com.mediscreen.patient.testutils;

import com.mediscreen.patient.model.Patient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestData {
    public static List<Patient> getPatientList(){
        return new ArrayList<>(Arrays.asList(getPatientOne(),getPatientTwo()));
    }

    public static Patient getPatientOne(){
        return new Patient(
                "Achille",
                "Deribreux",
                LocalDate.of(2000,03,11),
                "men",
                "1 rue des developpeurs",
                "0123456789"
        );
    }

    public static Patient getPatientTwo(){
        return new Patient(
                "Jules",
                "Deribreux",
                LocalDate.of(2004,07,06),
                "men",
                "2 rue des artistes",
                "0123456789"
        );
    }
}

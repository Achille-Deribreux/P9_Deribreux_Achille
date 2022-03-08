package com.mediscreen.assessment.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Terms {

    private Terms() {
    }

    public static List<String> getTerms(){
        return new ArrayList<>(
                Arrays.asList(
                        "Hémoglobine A1C",
                        "Microalbumine",
                        "Taille",
                        "Poids",
                        "Fumeur",
                        "Anormal",
                        "Cholestérol",
                        "Vertige",
                        "Rechute",
                        "Réaction",
                        "Anticorps"
                )
        );
    }
}

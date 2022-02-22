package com.mediscreen.patient.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Converter {
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

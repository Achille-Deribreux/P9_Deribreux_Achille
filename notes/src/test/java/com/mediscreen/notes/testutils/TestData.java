package com.mediscreen.notes.testutils;

import com.mediscreen.notes.model.Note;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestData {

    public static Note getNoteOne(){
        return new Note("mongodbidhdsjvb",1, LocalDateTime.of(2022,2,24,18,13,32),"This is a comment ...");
    }

    public static Note getNoteTwo(){
        return new Note("mongodbidbdehsq",2, null,"This is a comment ...");
    }

    public static List<Note> getNoteList(){
        return new ArrayList<>(Arrays.asList(getNoteOne(),getNoteOne()));
    }
}

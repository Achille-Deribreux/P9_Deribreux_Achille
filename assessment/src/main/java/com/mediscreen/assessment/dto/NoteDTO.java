package com.mediscreen.assessment.dto;

public class NoteDTO {

    private String id;
    private Integer patId;
    private String notes;

    public NoteDTO() {
    }

    public NoteDTO(String id, Integer patId, String notes) {
        this.id = id;
        this.patId = patId;
        this.notes = notes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPatId() {
        return patId;
    }

    public void setPatId(Integer patId) {
        this.patId = patId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

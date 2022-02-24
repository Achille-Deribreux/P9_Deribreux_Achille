package com.mediscreen.notes.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class NoteDTO {

    private String id;
    private Integer patId;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime creationDateTime;
    private String notes;

    public NoteDTO() {
    }

    public NoteDTO(String id, Integer patId, LocalDateTime creationDateTime, String notes) {
        this.id = id;
        this.patId = patId;
        this.creationDateTime = creationDateTime;
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

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

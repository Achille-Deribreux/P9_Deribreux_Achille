package com.mediscreen.assessment.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


public class Note {

    private String id;
    private Integer patientId;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime creationDateTime;
    private String comment;

    public Note() {
    }

    public Note(Integer patientId, LocalDateTime creationDateTime, String comment) {
        this.patientId = patientId;
        this.creationDateTime = creationDateTime;
        this.comment = comment;
    }

    public Note(String id, Integer patientId, LocalDateTime creationDateTime, String comment) {
        this.id = id;
        this.patientId = patientId;
        this.creationDateTime = creationDateTime;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id='" + id + '\'' +
                ", patientId=" + patientId +
                ", creationDateTime=" + creationDateTime +
                ", comment='" + comment + '\'' +
                '}';
    }
}

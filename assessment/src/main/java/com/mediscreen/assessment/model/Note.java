package com.mediscreen.assessment.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Objects;


public class Note {

    private String id;
    private Integer patientId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(id, note.id) && Objects.equals(patientId, note.patientId) && Objects.equals(creationDateTime, note.creationDateTime) && Objects.equals(comment, note.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patientId, creationDateTime, comment);
    }
}

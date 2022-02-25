package com.mediscreen.notes.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Document(collection="notes")
public class Note {

    @Id
    private String id;

    @Field("patientId")
    private Integer patientId;

    @Field("creationDateTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime creationDateTime;

    @Field("comment")
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

package com.anhtt.eTutor.dto;

import java.sql.Timestamp;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class RegistrationDTO {
    private UUID id;
    private Timestamp createAt;
    private String status;
    @JsonDeserialize
    private UUID courseId;
    

    public RegistrationDTO() {
    }

    public RegistrationDTO(UUID id, Timestamp createAt, String status, UUID courseId) {
        this.id = id;
        this.createAt = createAt;
        this.status = status;
        this.courseId = courseId;
        
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getCourseId() {
        return courseId;
    }

    public void setCourseId(UUID courseId) {
        this.courseId = courseId;
    }

    
}

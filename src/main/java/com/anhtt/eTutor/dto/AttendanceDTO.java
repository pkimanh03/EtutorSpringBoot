package com.anhtt.eTutor.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * Using for update and get attendance
 */

public class AttendanceDTO {
    private UUID id;
    @JsonIgnore
    private Timestamp createAt;
    String status;
    @JsonIgnore
    private UUID tutorId;
    @JsonIgnore
    private UUID studentId;
    @JsonIgnore
    private UUID classHourId;

    public AttendanceDTO() {
    }

    public AttendanceDTO(UUID id, Timestamp createAt, String status, UUID tutorId, UUID studentId, UUID classHourId) {
        this.id = id;
        this.createAt = createAt;
        this.status = status;
        this.tutorId = tutorId;
        this.studentId = studentId;
        this.classHourId = classHourId;
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

    public UUID getTutorId() {
        return tutorId;
    }

    public void setTutorId(UUID tutorId) {
        this.tutorId = tutorId;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public UUID getClassHourId() {
        return classHourId;
    }

    public void setClassHourId(UUID classHourId) {
        this.classHourId = classHourId;
    }
}

package com.anhtt.eTutor.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.util.UUID;

public class AttendanceInsertDTO {
    private UUID tutorId;
    private UUID studentId;
    private UUID classHourId;

    public AttendanceInsertDTO() {
    }

    public AttendanceInsertDTO(UUID tutorId, UUID studentId, UUID classHourId) {
        this.tutorId = tutorId;
        this.studentId = studentId;
        this.classHourId = classHourId;
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

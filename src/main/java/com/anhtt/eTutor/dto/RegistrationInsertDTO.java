package com.anhtt.eTutor.dto;

import java.util.UUID;

public class RegistrationInsertDTO {
    private UUID courseId;
    private UUID studentId;

    public RegistrationInsertDTO() {
    }

    public RegistrationInsertDTO(UUID courseId, UUID studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }

    public UUID getCourseId() {
        return courseId;
    }

    public void setCourseId(UUID courseId) {
        this.courseId = courseId;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }
}

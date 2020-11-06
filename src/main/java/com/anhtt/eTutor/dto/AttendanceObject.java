package com.anhtt.eTutor.dto;

import java.util.UUID;

public class AttendanceObject {
    private String email;
    private UUID classhourId;

    public AttendanceObject(String email, UUID classhourId) {
        this.email = email;
        this.classhourId = classhourId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getClasshourId() {
        return classhourId;
    }

    public void setClasshourId(UUID classhourId) {
        this.classhourId = classhourId;
    }
}

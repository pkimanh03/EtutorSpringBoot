package com.anhtt.eTutor.dto;

import java.util.UUID;

/**
 * Using for update and show class hour
 * */
public class ClassHourDTO {
    private UUID id;
    private String attendanceStatus;
    private String status;
    private UUID registrationId;
    private UUID slotId;

    public ClassHourDTO(UUID id, String attendanceStatus, String status, UUID registrationId, UUID slotId) {
        this.id = id;
        this.attendanceStatus = attendanceStatus;
        this.status = status;
        this.registrationId = registrationId;
        this.slotId = slotId;
    }

    public ClassHourDTO() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(UUID registrationId) {
        this.registrationId = registrationId;
    }

    public UUID getSlotId() {
        return slotId;
    }

    public void setSlotId(UUID slotId) {
        this.slotId = slotId;
    }
}

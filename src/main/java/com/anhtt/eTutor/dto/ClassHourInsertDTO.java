package com.anhtt.eTutor.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.util.UUID;

public class ClassHourInsertDTO {
    private UUID registrationId;
    private UUID slotId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Timestamp classhourDate;

    public ClassHourInsertDTO(UUID registrationId, UUID slotId) {
        this.registrationId = registrationId;
        this.slotId = slotId;
    }

    public ClassHourInsertDTO() {
    }

    public Timestamp getClasshourDate() {
        return classhourDate;
    }

    public void setClasshourDate(Timestamp classhourDate) {
        this.classhourDate = classhourDate;
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

package com.anhtt.eTutor.dto;

import javax.persistence.Column;
import java.util.UUID;

public class AvailableTimeDTO {
    private UUID id;
    private String slotInWeek;
    private String email;
    private boolean isDeleted;

    public AvailableTimeDTO() {
    }

    public AvailableTimeDTO(UUID id, String slotInWeek, String email, boolean isDeleted) {
        this.id = id;
        this.slotInWeek = slotInWeek;
        this.email = email;
        this.isDeleted = isDeleted;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSlotInWeek() {
        return slotInWeek;
    }

    public void setSlotInWeek(String slotInWeek) {
        this.slotInWeek = slotInWeek;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}

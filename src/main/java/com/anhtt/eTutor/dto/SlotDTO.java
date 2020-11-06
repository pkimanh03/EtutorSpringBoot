package com.anhtt.eTutor.dto;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.UUID;

public class SlotDTO {
    private UUID id;
    private String name;
    private String description;
    private double endTime;
    private double startTime;
    private String dayInWeek;
    private String timeInSlot;

    public SlotDTO() {
    }

    public SlotDTO(UUID id, String name, String description, double endTime, double startTime, String dayInWeek) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.endTime = endTime;
        this.startTime = startTime;
        this.dayInWeek = dayInWeek;
    }

    public String getTimeInSlot() {
        return timeInSlot;
    }

    public void setTimeInSlot(String timeInSlot) {
        this.timeInSlot = timeInSlot;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getEndTime() {
        return endTime;
    }

    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }

    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public String getDayInWeek() {
        return dayInWeek;
    }

    public void setDayInWeek(String dayInWeek) {
        this.dayInWeek = dayInWeek;
    }
}

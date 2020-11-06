package com.anhtt.eTutor.dto;

import java.sql.Timestamp;
import java.util.UUID;

public class TimeTableObject {
	private UUID id;
	private Timestamp date;
    private String slotName;
    private String attendanceStatus;
    private String dateString;

    public TimeTableObject() {
    }

    public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

    public TimeTableObject(Timestamp date, String slotName, String attendanceStatus) {
        this.date = date;
        this.slotName = slotName;
        this.attendanceStatus = attendanceStatus;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getSlotName() {
        return slotName;
    }

    public void setSlotName(String slotName) {
        this.slotName = slotName;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }
}

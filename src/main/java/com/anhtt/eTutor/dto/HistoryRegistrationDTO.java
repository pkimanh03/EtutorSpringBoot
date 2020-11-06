package com.anhtt.eTutor.dto;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class HistoryRegistrationDTO {
    private UUID registrationId;
    private UUID tutorId;
    private UUID courseId;
    private String tutorName;
	private String status;
    private Timestamp createAt;
    private String courseName;
    private long coursePrice;
    private String createTimeString;
    private List<TimeTableObject> timeTableObjectList;

    public HistoryRegistrationDTO() {
    }

    public HistoryRegistrationDTO(UUID registrationId, String status, Timestamp createAt, String courseName, long coursePrice, List<TimeTableObject> timeTableObjectList) {
        this.registrationId = registrationId;
        this.status = status;
        this.createAt = createAt;
        this.courseName = courseName;
        this.coursePrice = coursePrice;
        this.timeTableObjectList = timeTableObjectList;
    }
    
    public UUID getTutorId() {
		return tutorId;
	}

	public void setTutorId(UUID tutorId) {
		this.tutorId = tutorId;
	}

	public UUID getCourseId() {
		return courseId;
	}

	public void setCourseId(UUID courseId) {
		this.courseId = courseId;
	}
    public String getTutorName() {
		return tutorName;
	}

	public void setTutorName(String tutorName) {
		this.tutorName = tutorName;
	}

	public String getCreateTimeString() {
        return createTimeString;
    }

    public void setCreateTimeString(String createTimeString) {
        this.createTimeString = createTimeString;
    }

    public UUID getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(UUID registrationId) {
        this.registrationId = registrationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public long getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(long coursePrice) {
        this.coursePrice = coursePrice;
    }

    public List<TimeTableObject> getTimeTableObjectList() {
        return timeTableObjectList;
    }

    public void setTimeTableObjectList(List<TimeTableObject> timeTableObjectList) {
        this.timeTableObjectList = timeTableObjectList;
    }
}

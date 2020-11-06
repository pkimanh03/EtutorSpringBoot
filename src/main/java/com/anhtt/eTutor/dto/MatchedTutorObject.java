package com.anhtt.eTutor.dto;

import java.util.List;
import java.util.UUID;

public class MatchedTutorObject {
    private UUID tutorId;
    private String fullname;
    private String avatar;
    private List<CourseDTO> courseList;
//    private String courseDescription;
//    private long coursePrice;

    public MatchedTutorObject() {
    }

    public MatchedTutorObject(UUID tutorId, String fullname, String avatar, List<CourseDTO> courseList) {
        this.tutorId = tutorId;
        this.fullname = fullname;
        this.avatar = avatar;
        this.courseList = courseList;
    }

    public UUID getTutorId() {
        return tutorId;
    }

    public void setTutorId(UUID tutorId) {
        this.tutorId = tutorId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<CourseDTO> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<CourseDTO> courseList) {
        this.courseList = courseList;
    }
}

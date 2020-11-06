package com.anhtt.eTutor.dto;

import org.apache.catalina.LifecycleState;

import java.util.List;

public class RegistrationInDayObject {
    private String date;
    private int countCourse;
    private List<String> courseNameList;

    public RegistrationInDayObject() {

    }

    public RegistrationInDayObject(String date, List<String> courseNameList) {
        this.date = date;
        this.courseNameList = courseNameList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getCourseNameList() {
        return courseNameList;
    }

    public void setCourseNameList(List<String> courseNameList) {
        this.courseNameList = courseNameList;
    }

    public int getCountCourse() {
        return countCourse;
    }

    public void setCountCourse(int countCourse) {
        this.countCourse = countCourse;
    }
}

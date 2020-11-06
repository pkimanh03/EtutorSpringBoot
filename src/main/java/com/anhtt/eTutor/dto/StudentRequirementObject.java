package com.anhtt.eTutor.dto;

import java.util.List;

public class StudentRequirementObject {
    private String courseName;
    private List<String> slotList;

    public StudentRequirementObject() {
    }

    public StudentRequirementObject(String courseName, List<String> slotList) {
        this.courseName = courseName;
        this.slotList = slotList;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<String> getSlotList() {
        return slotList;
    }

    public void setSlotList(List<String> slotList) {
        this.slotList = slotList;
    }
}

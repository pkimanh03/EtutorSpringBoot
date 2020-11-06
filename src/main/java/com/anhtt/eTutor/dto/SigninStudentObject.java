package com.anhtt.eTutor.dto;

public class SigninStudentObject {
    private String email;
    private String fullname;
    private String uid;

    public SigninStudentObject() {
    }

    public SigninStudentObject(String email, String fullname, String uid) {
        this.email = email;
        this.fullname = fullname;
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}

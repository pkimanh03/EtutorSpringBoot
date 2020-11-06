package com.anhtt.eTutor.dto;

public class StudentInsertDTO {
    private String fullname;
    private int age;
    private String address;
    private String phoneNumber;
    private String favoriteMajors;
    private String email;
    private String password;

    public StudentInsertDTO() {
    }

    public StudentInsertDTO(String fullname, int age, String address, String phoneNumber, String favoriteMajors, String email, String password) {
        this.fullname = fullname;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.favoriteMajors = favoriteMajors;
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFavoriteMajors() {
        return favoriteMajors;
    }

    public void setFavoriteMajors(String favoriteMajors) {
        this.favoriteMajors = favoriteMajors;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

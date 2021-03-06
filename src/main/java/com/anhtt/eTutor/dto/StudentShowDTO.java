package com.anhtt.eTutor.dto;

import java.util.UUID;

public class StudentShowDTO {
    private UUID id;
    private String fullname;
    private int age;
    private String address;
    private String phoneNumber;
    private String favoriteMajors;
    private String email;

    public StudentShowDTO() {
    }

    public StudentShowDTO(UUID id, String fullname, int age, String address, String phoneNumber, String favoriteMajors, String email) {
        this.id = id;
        this.fullname = fullname;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.favoriteMajors = favoriteMajors;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

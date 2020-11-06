package com.anhtt.eTutor.dto;

public class TutorInsertDTO {
    private String fullname;
    private int age;
    private String address;
    private String drivingLicenseImg;
    private String idCardImg;
    private String avatar;
    private String email;
    private String password;
    private String idCardNumber;
    private String phoneNumber;

    public TutorInsertDTO() {
    }

    public TutorInsertDTO(String fullname, int age, String address, String drivingLicenseImg, String idCardImg, String avatar, String email, String password, String idCardNumber, String phoneNumber) {
        this.fullname = fullname;
        this.age = age;
        this.address = address;
        this.drivingLicenseImg = drivingLicenseImg;
        this.idCardImg = idCardImg;
        this.avatar = avatar;
        this.email = email;
        this.password = password;
        this.idCardNumber = idCardNumber;
        this.phoneNumber = phoneNumber;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getDrivingLicenseImg() {
        return drivingLicenseImg;
    }

    public void setDrivingLicenseImg(String drivingLicenseImg) {
        this.drivingLicenseImg = drivingLicenseImg;
    }

    public String getIdCardImg() {
        return idCardImg;
    }

    public void setIdCardImg(String idCardImg) {
        this.idCardImg = idCardImg;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

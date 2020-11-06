package com.anhtt.eTutor.dto;

import java.util.UUID;

public class TutorShowProfileDTO {
	private UUID id;
    private String fullname;
    private int age;
    private String address;
    private String drivingLicenseImg;
    private String idCardImg;
    private String avatar;
    private String email;
    private String phoneNumber;
	public TutorShowProfileDTO() {
		super();
	}
	public TutorShowProfileDTO(UUID id, String fullname, int age, String address, String drivingLicenseImg,
			String idCardImg, String avatar, String email, String phoneNumber) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.age = age;
		this.address = address;
		this.drivingLicenseImg = drivingLicenseImg;
		this.idCardImg = idCardImg;
		this.avatar = avatar;
		this.email = email;
		this.phoneNumber = phoneNumber;
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
    
    
}

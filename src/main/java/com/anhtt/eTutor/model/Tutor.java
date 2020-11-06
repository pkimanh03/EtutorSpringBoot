package com.anhtt.eTutor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "Tutor")
public class Tutor {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "UNIQUEIDENTIFIER")
    @JsonIgnore
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(name = "fullname", nullable = false, columnDefinition = "NVARCHAR(max)")
    private String fullname;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "address", nullable = false, columnDefinition = "NVARCHAR(max)")
    private String address;

    @Column(name = "drivingLicenseImg", nullable = false)
    private String drivingLicenseImg;

    @Column(name = "idCardImg", nullable = false)
    private String idCardImg;

    @Column(name = "avatar", nullable = false)
    private String avatar;

    @Column(name = "idCardNumber", nullable = false, unique = true)
    private String idCardNumber;

    @Column(name = "phoneNumber", nullable = false, unique = true)
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accountId", referencedColumnName = "id", nullable = false)
    private Account tutorAccount;

    @OneToMany(mappedBy = "tutor", fetch = FetchType.EAGER)
    private Collection<Standard> standardCollection;

    @ManyToMany(mappedBy = "tutorCollection")
    private Collection<Majors> majorsCollection;

    @OneToMany(mappedBy = "tutor")
    private Collection<Rating> ratingCollection;

    @OneToMany(mappedBy = "tutor")
    private Collection<Course> courseCollection;

    @OneToMany(mappedBy = "tutor")
    private Collection<Attendance> attendanceCollection;

    @Value(value = "false")
    @Column(name = "isDeleted")
    private boolean isDelete;

    @OneToMany(mappedBy = "tutor")
    private Collection<AvailableTime> availableTimeCollection;

    public Tutor() {
    }

    public Tutor(String fullname, int age, String address, String drivingLicenseImg, String idCardImg, String avatar, String idCardNumber, String phoneNumber, Account tutorAccount, Collection<Standard> standardCollection, Collection<Majors> majorsCollection, Collection<Rating> ratingCollection, Collection<Course> courseCollection, Collection<Attendance> attendanceCollection, Collection<AvailableTime> availableTimeCollection) {
        this.fullname = fullname;
        this.age = age;
        this.address = address;
        this.drivingLicenseImg = drivingLicenseImg;
        this.idCardImg = idCardImg;
        this.avatar = avatar;
        this.idCardNumber = idCardNumber;
        this.phoneNumber = phoneNumber;
        this.tutorAccount = tutorAccount;
        this.standardCollection = standardCollection;
        this.majorsCollection = majorsCollection;
        this.ratingCollection = ratingCollection;
        this.courseCollection = courseCollection;
        this.attendanceCollection = attendanceCollection;
        this.availableTimeCollection = availableTimeCollection;
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

    public Collection<AvailableTime> getAvailableTimeCollection() {
        return availableTimeCollection;
    }

    public void setAvailableTimeCollection(Collection<AvailableTime> availableTimeCollection) {
        this.availableTimeCollection = availableTimeCollection;
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

    public Account getTutorAccount() {
        return tutorAccount;
    }

    public void setTutorAccount(Account tutorAccount) {
        this.tutorAccount = tutorAccount;
    }

    public Collection<Standard> getStandardCollection() {
        return standardCollection;
    }

    public void setStandardCollection(Collection<Standard> standardCollection) {
        this.standardCollection = standardCollection;
    }

    public Collection<Majors> getMajorsCollection() {
        return majorsCollection;
    }

    public void setMajorsCollection(Collection<Majors> majorsCollection) {
        this.majorsCollection = majorsCollection;
    }

    public Collection<Rating> getRatingCollection() {
        return ratingCollection;
    }

    public void setRatingCollection(Collection<Rating> ratingCollection) {
        this.ratingCollection = ratingCollection;
    }

    public Collection<Course> getCourseCollection() {
        return courseCollection;
    }

    public void setCourseCollection(Collection<Course> courseCollection) {
        this.courseCollection = courseCollection;
    }

    public Collection<Attendance> getAttendanceCollection() {
        return attendanceCollection;
    }

    public void setAttendanceCollection(Collection<Attendance> attendanceCollection) {
        this.attendanceCollection = attendanceCollection;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }
}

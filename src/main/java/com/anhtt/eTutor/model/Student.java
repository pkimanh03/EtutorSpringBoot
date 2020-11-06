package com.anhtt.eTutor.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Student")
public class Student {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "UNIQUEIDENTIFIER")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(name = "fullname", columnDefinition = "NVARCHAR(max)")
    private String fullname;

    @Column(name = "age")
    private int age;

    @Column(name = "address", columnDefinition = "NVARCHAR(max)")
    private String address;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "favoriteMajors", columnDefinition = "NVARCHAR(max)")
    private String favoriteMajors;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accountId", referencedColumnName = "id", nullable = false)
    private Account studentAccount;

    @Value(value = "false")
    @Column(name = "isDeleted")
    private boolean isDelete;

    public Student() {
    }

    public Student(String fullname, int age, String address, String phoneNumber, String favoriteMajors,
            Account studentAccount, boolean isDelete) {
        this.fullname = fullname;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.favoriteMajors = favoriteMajors;
        this.studentAccount = studentAccount;
        this.isDelete = isDelete;
    }

    public Student(String fullname, int age, String address, String phoneNumber, String favoriteMajors,
            boolean isDelete) {
        this.fullname = fullname;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.favoriteMajors = favoriteMajors;
        this.isDelete = isDelete;
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

    public Account getStudentAccount() {
        return studentAccount;
    }

    public void setStudentAccount(Account studentAccount) {
        this.studentAccount = studentAccount;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }
}

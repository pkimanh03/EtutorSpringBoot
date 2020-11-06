package com.anhtt.eTutor.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "Registration")
@EnableJpaAuditing
public class Registration extends AuditingEntityListener {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "UNIQUEIDENTIFIER")
    @org.hibernate.annotations.Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(name = "createAt")
    @CreatedDate
    private Timestamp createAt;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "courseId", referencedColumnName = "id", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "studentId", referencedColumnName = "id", nullable = false)
    private Student student;

    @OneToMany(mappedBy = "registration")
    private Collection<ClassHour> classHourCollection;

    @Value(value = "false")
    @Column(name = "isDeleted")
    private boolean isDeleted;

    public Registration() {
    }

    public Registration(Timestamp createAt, String status, Course course, Student student, Collection<ClassHour> classHourCollection) {
        this.createAt = createAt;
        this.status = status;
        this.course = course;
        this.student = student;
        this.classHourCollection = classHourCollection;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Collection<ClassHour> getClassHourCollection() {
        return classHourCollection;
    }

    public void setClassHourCollection(Collection<ClassHour> classHourCollection) {
        this.classHourCollection = classHourCollection;
    }
}

package com.anhtt.eTutor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "Attendance")
public class Attendance extends AuditingEntityListener {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "UNIQUEIDENTIFIER")
    private UUID id;

    @Column(name = "createAt", nullable = false)
    @CreatedDate
    private Timestamp createAt;

    @Value(value = "NOT_YET")
    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "tutorId", referencedColumnName = "id")
    private Tutor tutor;

    @ManyToOne
    @JoinColumn(name = "studentId", referencedColumnName = "id")
    private Student student;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "classHourId", referencedColumnName = "id", nullable = false)
    private ClassHour classHour;

    @Value(value = "false")
    @Column(name = "isDeleted")
    private boolean isDelete;

    public Attendance() {
    }

    public Attendance(Timestamp createAt, String status, Tutor tutor, Student student, ClassHour classHour) {
        this.createAt = createAt;
        this.status = status;
        this.tutor = tutor;
        this.student = student;
        this.classHour = classHour;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
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

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ClassHour getClassHour() {
        return classHour;
    }

    public void setClassHour(ClassHour classHour) {
        this.classHour = classHour;
    }
}

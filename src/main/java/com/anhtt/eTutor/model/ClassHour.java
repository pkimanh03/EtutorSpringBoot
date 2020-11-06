package com.anhtt.eTutor.model;

import com.anhtt.eTutor.utils.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "ClassHour")
public class ClassHour {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "UNIQUEIDENTIFIER")
    @JsonIgnore
    private UUID id;

    @Column(name = "classhourDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:sssZ")
    private Timestamp classhourDate;

    @Value(value = Constants.CLASSHOUR_CREATED)
    @Column(name = "status", nullable = false)
    String status;

    @ManyToOne
    @JoinColumn(name = "registrationId", referencedColumnName = "id", nullable = false)
    private Registration registration;

    @ManyToOne
    @JoinColumn(name = "slotId", referencedColumnName = "id", nullable = false)
    private Slot slot;

    @OneToOne(mappedBy = "classHour")
    private Attendance attendance;

    @Value(value = "false")
    @Column(name = "isDeleted")
    private boolean isDeleted;
    public ClassHour() {
    }

    public ClassHour(Timestamp classhourDate, Registration registration, Slot slot, Attendance attendance) {
        this.classhourDate = classhourDate;
        this.registration = registration;
        this.slot = slot;
        this.attendance = attendance;
    }

    public Timestamp getClasshourDate() {
        return classhourDate;
    }

    public void setClasshourDate(Timestamp classhourDate) {
        this.classhourDate = classhourDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}

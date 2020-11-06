package com.anhtt.eTutor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "Slot")
public class Slot {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "UNIQUEIDENTIFIER")
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "startTime", nullable = false)
    private double startTime;

    @Column(name = "endTime", nullable = false)
    private double endTime;

    @OneToMany(mappedBy = "slot")
    private Collection<ClassHour> classHourCollection;

    @Column(name = "dayInWeek")
    private String dayInWeek;

    @Value(value = "false")
    @Column(name = "isDeleted")
    private boolean isDeleted;

    public Slot() {
    }

    public Slot(String name, double startTime, double endTime, Collection<ClassHour> classHourCollection, String dayInWeek) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classHourCollection = classHourCollection;
        this.dayInWeek = dayInWeek;
    }

    public Slot(String name, double startTime, double endTime, String dayInWeek) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayInWeek = dayInWeek;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public double getEndTime() {
        return endTime;
    }

    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }

    public String getDayInWeek() {
        return dayInWeek;
    }

    public void setDayInWeek(String dayInWeek) {
        this.dayInWeek = dayInWeek;
    }

    public Collection<ClassHour> getClassHourCollection() {
        return classHourCollection;
    }

    public void setClassHourCollection(Collection<ClassHour> classHourCollection) {
        this.classHourCollection = classHourCollection;
    }
}

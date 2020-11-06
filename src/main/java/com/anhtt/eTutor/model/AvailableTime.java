package com.anhtt.eTutor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "AvailableTime")
public class AvailableTime {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "UNIQUEIDENTIFIER")
    @JsonIgnore
    private UUID id;

    @Column(name = "slotInWeek", nullable = false, columnDefinition = "VARCHAR(MAX)")
    private String slotInWeek;

    @ManyToOne
    @JoinColumn(name = "tutorId", referencedColumnName = "id")
    private Tutor tutor;

    @Value(value = "false")
    @Column(name = "isDeleted")
    private boolean isDeleted;

    public AvailableTime() {
    }

    public AvailableTime(String slotInWeek, Tutor tutor) {
        this.slotInWeek = slotInWeek;
        this.tutor = tutor;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSlotInWeek() {
        return slotInWeek;
    }

    public void setSlotInWeek(String slotInWeek) {
        this.slotInWeek = slotInWeek;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}

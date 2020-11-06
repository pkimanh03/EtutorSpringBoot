package com.anhtt.eTutor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Rating")
public class Rating {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "UNIQUEIDENTIFIER")
    private UUID id;

    @Column(name = "point", length = 3, nullable = false)
    private int point;

    @Column(name = "comment", columnDefinition = "NVARCHAR(max)")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "tutorId", referencedColumnName = "id")
    private Tutor tutor;

    @ManyToOne
    @JoinColumn(name = "studentId", referencedColumnName = "id")
    private Student student;

    @Value(value = "false")
    @Column(name = "isDeleted")
    private boolean isDeleted;

    public Rating() {
    }

    public Rating(int point, String comment, Tutor tutor, Student student) {
        this.point = point;
        this.comment = comment;
        this.tutor = tutor;
        this.student = student;
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

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
}

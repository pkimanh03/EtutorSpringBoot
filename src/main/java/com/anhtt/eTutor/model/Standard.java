package com.anhtt.eTutor.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "Standard")
public class Standard {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "UNIQUEIDENTIFIER")
    private UUID id;

    @Column(name = "name", nullable = false, columnDefinition = "NVARCHAR(max)")
    private String name;

    @Column(name = "certificationImage", nullable = false)
    private String certificationImage;

    @Column(name = "issueDate", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date issueDate;

    @Column(name = "expiredDate", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date expiredDate;

    @Column(name = "description", columnDefinition = "NVARCHAR(max)")
    private String description;

    @ManyToOne
    @JoinColumn(name = "tutorId", referencedColumnName = "id")
    private Tutor tutor;

    @ManyToOne
    @JoinColumn(name = "majorId", referencedColumnName = "id")
    private Majors majors;

    @Value(value = "false")
    @Column(name = "isDeleted")
    private boolean isDeleted;

    public Standard() {
    }

    public Standard(String name, String certificationImage, Date issueDate, Date expiredDate, String description, Tutor tutor, Majors majors) {
        this.name = name;
        this.certificationImage = certificationImage;
        this.issueDate = issueDate;
        this.expiredDate = expiredDate;
        this.description = description;
        this.tutor = tutor;
        this.majors = majors;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCertificationImage() {
        return certificationImage;
    }

    public void setCertificationImage(String certificationImage) {
        this.certificationImage = certificationImage;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public Majors getMajors() {
        return majors;
    }

    public void setMajors(Majors majors) {
        this.majors = majors;
    }
}

package com.anhtt.eTutor.dto;

import java.sql.Date;
import java.util.UUID;

public class StandardDTO {
    private UUID id;
    private String name;
    private String certificationImage;
    private Date issueDate;
    private Date expiredDate;
    private String description;
    private UUID tutorId;
    private UUID majorsId;

    public StandardDTO() {
    }

    public StandardDTO(UUID id, String name, String certificationImage, Date issueDate, Date expiredDate, String description, UUID tutorId, UUID majorsId) {
        this.id = id;
        this.name = name;
        this.certificationImage = certificationImage;
        this.issueDate = issueDate;
        this.expiredDate = expiredDate;
        this.description = description;
        this.tutorId = tutorId;
        this.majorsId = majorsId;
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

    public UUID getTutorId() {
        return tutorId;
    }

    public void setTutorId(UUID tutorId) {
        this.tutorId = tutorId;
    }

    public UUID getMajorsId() {
        return majorsId;
    }

    public void setMajorsId(UUID majorsId) {
        this.majorsId = majorsId;
    }
}

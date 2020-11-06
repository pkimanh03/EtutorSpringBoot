package com.anhtt.eTutor.dto;

import java.sql.Date;
import java.util.UUID;

public class StandardInsertDTO {
    private String name;
    private String certificationImage;
    private Date issueDate;
    private Date expiredDate;
    private String description;
    private UUID majorsId;

    public StandardInsertDTO() {
    }

    
    public StandardInsertDTO(String name, String certificationImage,
			String description) {
		super();
		this.name = name;
		this.certificationImage = certificationImage;
		
		this.description = description;
	}


	public StandardInsertDTO(String name, String certificationImage, Date issueDate, Date expiredDate, String description, UUID majorsId) {
        this.name = name;
        this.certificationImage = certificationImage;
        this.issueDate = issueDate;
        this.expiredDate = expiredDate;
        this.description = description;
        this.majorsId = majorsId;
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

    public UUID getMajorsId() {
        return majorsId;
    }

    public void setMajorsId(UUID majorsId) {
        this.majorsId = majorsId;
    }
}

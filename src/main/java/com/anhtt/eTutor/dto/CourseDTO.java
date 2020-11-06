package com.anhtt.eTutor.dto;

import java.util.UUID;

public class CourseDTO {
    private UUID id;
    private String name;
    private String description;
    private String image;
    private UUID majorsId;
    private Long price;

    public CourseDTO() {
    }
    
    

    public CourseDTO(String name, String description, String image) {
		super();
		this.name = name;
		this.description = description;
		this.image = image;
	}



	public CourseDTO(UUID id, String name, String description, String image, UUID majorsId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.majorsId = majorsId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public UUID getMajorsId() {
        return majorsId;
    }

    public void setMajorsId(UUID majorsId) {
        this.majorsId = majorsId;
    }
}

package com.anhtt.eTutor.dto;

import java.util.UUID;

/**
 * Using for insert API*/
public class CourseInsertDTO {
    private String name;
    private String description;
    private String image;
    private UUID majorsId;
    private Long price;

    public CourseInsertDTO() {
    }

    public CourseInsertDTO(String name, String description, String image, UUID majorsId, Long price) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.majorsId = majorsId;
        this.price = price;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
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

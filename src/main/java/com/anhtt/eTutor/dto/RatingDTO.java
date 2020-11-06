package com.anhtt.eTutor.dto;

import java.util.UUID;

public class RatingDTO {
    private UUID id;
    private int point;
    private String comment;
    

    public RatingDTO() {
    }

    public RatingDTO(UUID id, int point, String comment) {
        this.id = id;
        this.point = point;
        this.comment = comment;
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
}

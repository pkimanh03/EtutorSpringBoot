package com.anhtt.eTutor.dto;

import java.util.UUID;

public class RatingInsertDTO {
    private int point;
    private String comment;

    public RatingInsertDTO(int point, String comment) {
        this.point = point;
        this.comment = comment;
    }

    public RatingInsertDTO() {
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

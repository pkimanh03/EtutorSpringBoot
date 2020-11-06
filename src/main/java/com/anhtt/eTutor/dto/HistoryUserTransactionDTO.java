package com.anhtt.eTutor.dto;

import java.sql.Timestamp;
import java.util.UUID;

public class HistoryUserTransactionDTO {
    private UUID id;
    private String transactionType;
    private Timestamp createAt;
    private Long amount;
    private String courseName;
    private String createTimeString;

    public HistoryUserTransactionDTO() {
    }

    public HistoryUserTransactionDTO(UUID id, String transactionType, Timestamp createAt, Long amount, String courseName) {
        this.id = id;
        this.transactionType = transactionType;
        this.createAt = createAt;
        this.amount = amount;
        this.courseName = courseName;
    }

    public String getCreateTimeString() {
        return createTimeString;
    }

    public void setCreateTimeString(String createTimeString) {
        this.createTimeString = createTimeString;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}

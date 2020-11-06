package com.anhtt.eTutor.dto;

import java.sql.Timestamp;
import java.util.UUID;

public class UserTransactionDTO {
    private UUID id;
    private UUID transactionTypeId;
    private Timestamp createAt;
    private Long amount;
    private UUID accountId;

    public UserTransactionDTO() {
    }

    public UserTransactionDTO(UUID id, UUID transactionTypeId, Timestamp createAt, Long amount, UUID accountId) {
        this.id = id;
        this.transactionTypeId = transactionTypeId;
        this.createAt = createAt;
        this.amount = amount;
        this.accountId = accountId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(UUID transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
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

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }
}

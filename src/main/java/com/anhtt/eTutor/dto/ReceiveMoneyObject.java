package com.anhtt.eTutor.dto;

import java.util.UUID;

public class ReceiveMoneyObject {
    private String email;
    private long amount;

    public ReceiveMoneyObject() {
    }

    public ReceiveMoneyObject(String email, long amount) {
        this.email = email;
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}

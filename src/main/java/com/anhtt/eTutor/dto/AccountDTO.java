package com.anhtt.eTutor.dto;

import java.util.UUID;

/**
 * Using for update and get account
 */
public class AccountDTO {
    private UUID id;
    private String email;
    private String password;
    private String tokenGmail;
    private Long balance;

    public AccountDTO(UUID id, String email, String password, String tokenGmail, Long balance) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.tokenGmail = tokenGmail;
        this.balance = balance;
    }

    public AccountDTO() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTokenGmail() {
        return tokenGmail;
    }

    public void setTokenGmail(String tokenGmail) {
        this.tokenGmail = tokenGmail;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}

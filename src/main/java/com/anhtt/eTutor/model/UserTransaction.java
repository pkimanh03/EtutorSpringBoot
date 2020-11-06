package com.anhtt.eTutor.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "UserTransaction")
@EnableJpaAuditing
public class UserTransaction extends AuditingEntityListener {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "UNIQUEIDENTIFIER")
    @JsonIgnore
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "transactionTypeId", referencedColumnName = "id", nullable = false)
    private TransactionType transactionType;

    @Column(name = "createAt")
    @CreatedDate
    private Timestamp createAt;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @ManyToOne
    @JoinColumn(name = "accountId", referencedColumnName = "id", nullable = false)
    private Account account;

    @Value(value = "false")
    @Column(name = "isDeleted")
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "courseId", referencedColumnName = "id")
    private Course course;

    public UserTransaction() {
    }

    public UserTransaction(TransactionType transactionType, Timestamp createAt, Long amount, Account account, Course course) {
        this.transactionType = transactionType;
        this.createAt = createAt;
        this.amount = amount;
        this.account = account;
        this.course = course;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

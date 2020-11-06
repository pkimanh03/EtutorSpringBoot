package com.anhtt.eTutor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "TransactionType")
public class TransactionType {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "UNIQUEIDENTIFIER")
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "transactionType")
    private Collection<UserTransaction> transactionCollection;

    @Value(value = "false")
    @Column(name = "isDeleted")
    private boolean isDeleted;

    public TransactionType() {
    }

    public TransactionType(String name) {
        this.name = name;
    }

    public TransactionType(String name, Collection<UserTransaction> transactionCollection) {
        this.name = name;
        this.transactionCollection = transactionCollection;
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

    public Collection<UserTransaction> getTransactionCollection() {
        return transactionCollection;
    }

    public void setTransactionCollection(Collection<UserTransaction> transactionCollection) {
        this.transactionCollection = transactionCollection;
    }
}

package com.anhtt.eTutor.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "Course")
public class Course {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "UNIQUEIDENTIFIER")
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(name = "name", nullable = false, columnDefinition = "NVARCHAR(max)")
    private String name;

    @Column(name = "description", columnDefinition = "NVARCHAR(max)")
    private String description;

    @Column(name = "image", columnDefinition = "VARCHAR(MAX)")
    private String image;

    @ManyToOne
    @JoinColumn(name = "tutorId", referencedColumnName = "id", nullable = false)
    private Tutor tutor;

    @ManyToOne
    @JoinColumn(name = "majorId", referencedColumnName = "id", nullable = false)
    private Majors majors;

    @OneToMany(mappedBy = "course")
    private Collection<Registration> registrationCollection;

    @Value(value = "false")
    @Column(name = "isDeleted")
    private boolean isDeleted;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "minSlot")
    private int minSlot;

    @OneToMany(mappedBy = "course")
    private Collection<UserTransaction> userTransactionCollection;

    public Course() {
    }

    public Course(String name, String description, String image, Tutor tutor, Majors majors, Collection<Registration> registrationCollection, Long price, int minSlot, Collection<UserTransaction> userTransactionCollection) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.tutor = tutor;
        this.majors = majors;
        this.registrationCollection = registrationCollection;
        this.price = price;
        this.minSlot = minSlot;
        this.userTransactionCollection = userTransactionCollection;
    }

    public int getMinSlot() {
        return minSlot;
    }

    public void setMinSlot(int minSlot) {
        this.minSlot = minSlot;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Collection<UserTransaction> getUserTransactionCollection() {
        return userTransactionCollection;
    }

    public void setUserTransactionCollection(Collection<UserTransaction> userTransactionCollection) {
        this.userTransactionCollection = userTransactionCollection;
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

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public Majors getMajors() {
        return majors;
    }

    public void setMajors(Majors majors) {
        this.majors = majors;
    }

    public Collection<Registration> getRegistrationCollection() {
        return registrationCollection;
    }

    public void setRegistrationCollection(Collection<Registration> registrationCollection) {
        this.registrationCollection = registrationCollection;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}

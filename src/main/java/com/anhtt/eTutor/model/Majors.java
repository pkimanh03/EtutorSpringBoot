package com.anhtt.eTutor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "Majors")
public class Majors {

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

    @OneToMany(mappedBy = "majors", fetch = FetchType.EAGER)
    private Collection<Standard> standardCollection;

    @ManyToMany
    @JoinTable(
            name = "TutorMajors",
            joinColumns = @JoinColumn(name = "majorsId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tutorId", referencedColumnName = "id")
    )
    private Collection<Tutor> tutorCollection;

    @OneToMany(mappedBy = "majors")
    private Collection<Course> courseCollection;

    @Value(value = "false")
    @Column(name = "isDeleted")
    private boolean isDeleted;

    public Majors() {
    }

    public Majors(String name, String description, Collection<Standard> standardCollection, Collection<Tutor> tutorCollection, Collection<Course> courseCollection) {
        this.name = name;
        this.description = description;
        this.standardCollection = standardCollection;
        this.tutorCollection = tutorCollection;
        this.courseCollection = courseCollection;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
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

    public Collection<Standard> getStandardCollection() {
        return standardCollection;
    }

    public void setStandardCollection(Collection<Standard> standardCollection) {
        this.standardCollection = standardCollection;
    }

    public Collection<Tutor> getTutorCollection() {
        return tutorCollection;
    }

    public void setTutorCollection(Collection<Tutor> tutorCollection) {
        this.tutorCollection = tutorCollection;
    }

    public Collection<Course> getCourseCollection() {
        return courseCollection;
    }

    public void setCourseCollection(Collection<Course> courseCollection) {
        this.courseCollection = courseCollection;
    }
}

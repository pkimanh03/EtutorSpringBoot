package com.anhtt.eTutor.model;

import com.anhtt.eTutor.notification.model.PushNotificationRequest;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "ACCOUNT", uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
public class Account {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID", columnDefinition = "UNIQUEIDENTIFIER")
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;

    @Size(max = 50)
    @Email
    @Column(name = "email")
    private String email;

    @Size(min = 6, max = 100)
    @Column(name = "password")
    private String password;

    @Column(name = "tokenGmail")
    private String tokenGmail;

    @Value(value = "0")
    @Column(name = "balance")
    private Long balance;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToOne(mappedBy = "tutorAccount")
    private Tutor tutor;

    @OneToOne(mappedBy = "studentAccount")
    private Student student;

    @OneToMany(mappedBy = "account")
    @JsonBackReference
    private Collection<UserTransaction> userTransactionCollection;

    @Value(value = "false")
    @Column(name = "isDeleted")
    private boolean isDeleted;

    @OneToMany(mappedBy = "account")
    private Collection<PushNotificationRequest> pushNotificationRequestCollection;
    public Account() {
    }

    public Account(@Size(max = 50) @Email String email, @Size(min = 6, max = 100) String password, Long balance) {
        this.email = email;
        this.password = password;
        this.balance = balance;
    }
    
    public Account(@Size(max = 50) @Email String email, @Size(min = 6, max = 100) String password) {
        this.email = email;
        this.password = password;
        
    }

    public Account(@Size(max = 50) @Email String email, @Size(min = 6, max = 100) String password, Set<Role> roleSet) {
        this.email = email;
        this.password = password;
        this.balance = new Long(0);
        this.roles = roleSet;
    }

    public Account(@Size(max = 50) @Email String email, @Size(min = 6, max = 100) String password, String tokenGmail) {
        this.email = email;
        this.password = password;
        this.tokenGmail = tokenGmail;
    }

    public Account(@Size(max = 50) @Email String email, @Size(min = 6, max = 100) String password, String tokenGmail, Long balance, Set<Role> roles, Tutor tutor, Student student, Collection<UserTransaction> userTransactionCollection, boolean isDelete) {
        this.email = email;
        this.password = password;
        this.tokenGmail = tokenGmail;
        this.balance = balance;
        this.roles = roles;
        this.tutor = tutor;
        this.student = student;
        this.userTransactionCollection = userTransactionCollection;
        this.isDeleted = isDelete;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Collection<UserTransaction> getUserTransactionCollection() {
        return userTransactionCollection;
    }

    public void setUserTransactionCollection(Collection<UserTransaction> userTransactionCollection) {
        this.userTransactionCollection = userTransactionCollection;
    }

    public boolean isDelete() {
        return isDeleted;
    }

    public void setDelete(boolean delete) {
        isDeleted = delete;
    }
}
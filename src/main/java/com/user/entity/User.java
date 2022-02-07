package com.user.entity;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    Long id;

    @Column(name="username")
    String userName;

    @Column(name="full_name")
    String fullName;

    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "role", referencedColumnName = "id")
    Role role;

    @Column(name="email_address")
    String emailAddress;

    @Column(name="status")
    String status;

    @Column(name="verification_code")
    String verificationCode;

    @Column(name="password")
    String password;

    public User(){}

    public User(String userName, String fullName, Role role, String emailAddress, String status){
        this.userName = userName;
        this.fullName = fullName;
        this.role = role;
        this.emailAddress = emailAddress;
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}

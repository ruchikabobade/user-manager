package com.user.model;

public class SignUp {
    Long Id;
    String userName;
    String password;

    public SignUp(){}

    public SignUp(Long Id,String userName, String password){
        this.Id = Id;
        this.userName = userName;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

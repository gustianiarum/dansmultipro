package com.danspro.danspro.beans;

import java.io.Serializable;

public class AuthReq implements Serializable {
    
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //need default constructor for JSON Parsing
    public AuthReq()
    {

    }

    public AuthReq(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

}

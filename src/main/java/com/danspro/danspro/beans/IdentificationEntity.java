package com.danspro.danspro.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "db_identification")
public class IdentificationEntity {
    
    @Id
    @Column(name = "userName", length = 25)
    private String userName;

    @Column(name = "password")
    private String password;

    public String getUserName() {
        return userName;
    }
    public void setUserName(String password) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.userName = userName;
    }



    
}

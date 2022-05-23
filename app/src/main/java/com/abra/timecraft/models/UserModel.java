package com.abra.timecraft.models;

import androidx.annotation.Nullable;

public class UserModel {
    String user;
    String email;
    String password;


    public UserModel( String email, String password) {
        this.user = user;
        this.email = email;
        this.password = password;
    }
    public UserModel( String user, String email, String password) {
        this.user = user;
        this.email = email;
        this.password = password;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}

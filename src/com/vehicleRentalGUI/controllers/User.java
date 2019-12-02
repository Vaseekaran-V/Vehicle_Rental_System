package com.vehicleRentalGUI.controllers;

public class User {
    private String username;
    private byte[] password;

    public User(String uname, byte[] pwd){
        this.username=uname;
        this.password=pwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }
}

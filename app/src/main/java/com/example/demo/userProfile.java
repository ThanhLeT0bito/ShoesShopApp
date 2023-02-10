package com.example.demo;

public class userProfile {
    String idUser;
    String email;
    String pass;
    String address;
    String numPhone;

    public userProfile() {
    }

    public userProfile(String idUser, String email, String pass, String address, String numPhone) {
        this.idUser = idUser;
        this.email = email;
        this.pass = pass;
        this.address = address;
        this.numPhone = numPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumPhone() {
        return numPhone;
    }

    public void setNumPhone(String numPhone) {
        this.numPhone = numPhone;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}

package com.BiF.BananaSuprema.BananaSuprema.Service;

public class User {
    int id;
    String username;
    String email;
    int privilege;

    public User(int id, String username, String email, int privilege) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.privilege = privilege;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }
}

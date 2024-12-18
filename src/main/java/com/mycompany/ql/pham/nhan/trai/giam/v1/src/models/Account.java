package com.mycompany.ql.pham.nhan.trai.giam.v1.src.models;

public class Account {
    private static int currentId = (int) (Math.random() * 9999) + 1;
    private int id;
    private String username;
    private String password;
    private int role;

    public Account() {
    }

    public Account(String username, String password, int role) {
         this.id = currentId++;
        this.username = username;
        this.password = password;
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
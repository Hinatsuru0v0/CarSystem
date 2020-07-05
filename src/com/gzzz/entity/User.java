package com.gzzz.entity;

/**
 * User Class
 * @author GZZZ
 * @version 1.0.0
 */
public class User {
    private String username;
    private String password;
    private int balance;
    private boolean is_admin;

    public User() {}

    public User(String username, String password, int balance, boolean is_admin) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.is_admin = is_admin;
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

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }
}

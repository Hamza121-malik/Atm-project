package com.javacourse.user;

import com.javacourse.account.Account;

import java.util.Map;

import static utility.Utility.userData;

public class User {
    private String fullName;
    private String userName;
    private String password;
    Account account;

    public User(String fullName, String userName, String password){
        account = new Account(userName);
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void updateUserName(String userName){
        this.userName = userName;
        
    }
    public void updatePassword(String password){
        this.password = password;
    }
}

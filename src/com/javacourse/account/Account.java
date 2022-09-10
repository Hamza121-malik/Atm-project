package com.javacourse.account;

import com.javacourse.transation.Transaction;

import java.util.List;

public class Account {
    private double balance;
    private String accountNumber;
    public List<Transaction> transactions;


    public Account(String accountNumberPrefix) {
        this.accountNumber = accountNumberPrefix;
        this.balance = 0;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public double withdraw(double amount) {
        Transaction t = new Transaction(this.accountNumber, this.accountNumber, amount);
        balance = balance - amount;
      //  this.transactions.add(t)  ;
        return balance;
    }

    public double deposit(double amount) {
        Transaction t = new Transaction(this.accountNumber, this.accountNumber, amount);
        balance = balance + amount;
      //  this.transactions.add(t)  ;
        return balance;
    }

    public void quit() {

    }
}

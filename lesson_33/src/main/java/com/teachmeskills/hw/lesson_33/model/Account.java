package com.teachmeskills.hw.lesson_33.model;

import java.util.Objects;

public class Account {

    private int id;
    private String accountNumber;
    private double balance;
    private String username;

    public Account(int id, String username, String accountNumber, double balance) {
        this.id = id;
        this.username = username;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountNumber, account.accountNumber) && Objects.equals(username, account.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, username);
    }
}

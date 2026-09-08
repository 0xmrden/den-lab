package com.den.lab.case7.before;

public class Wallet {

    private final String id;
    private int balance;

    public Wallet(String id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
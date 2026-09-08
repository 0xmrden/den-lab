package com.den.lab.case7.after;

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

    public boolean withdraw(int amount) {
        if (amount > balance) {
            return false;
        }

        balance -= amount;

        return true;
    }
}
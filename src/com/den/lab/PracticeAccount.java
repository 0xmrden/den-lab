package com.den.lab;

public class PracticeAccount {
    private int balance;

    public PracticeAccount(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be > 0");
        }

        balance = balance + amount;
    }

    public void withdraw(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("amount < 0");
        }

        if (amount == 0) {
            return;
        }

        if (amount > balance) {
            return;
        }

        balance = balance - amount;
    }

    public PracticeAccount copy() {
        return new PracticeAccount(balance);
    }
}

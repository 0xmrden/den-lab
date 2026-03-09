package com.den.lab;

public class Account {

    private int balance;

    public Account(int initialBalance) {
        if (initialBalance <= 0) {
            throw new IllegalArgumentException("Initial balance must be positive");
        }
        this.balance = initialBalance;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
    }

    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be positive");
        }
        if (balance == 0) {
            throw new IllegalArgumentException("Balance is zero");
        }

        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        balance -= amount;
    }
}
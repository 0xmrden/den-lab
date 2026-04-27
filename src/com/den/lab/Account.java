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

    public void deposit ( int amount ) {
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

    public void transfer(Account target, int amount) {
        if (target == null) {
            throw new IllegalArgumentException("Target account cannot be null");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        withdraw(amount);
        target.deposit(amount);
    }
}
package com.den.lab;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void depositShouldIncreaseBalance() {
        Account account = new Account(100);

        account.deposit(50);

        assertEquals(150, account.getBalance());
    }

    @Test
    void withdrawShouldDecreaseBalance() {
        Account account = new Account(100);
        account.withdraw(30);
        assertEquals(70, account.getBalance());
    }

    @Test
    void withdrawShouldTrowExceptionWhenAmountIsZero() {
        Account account = new Account(100);
        assertThrows(IllegalArgumentException.class, () ->
                account.withdraw(0));
    }
}
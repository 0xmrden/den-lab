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
}
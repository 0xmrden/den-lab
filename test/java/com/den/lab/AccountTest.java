package com.den.lab;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    @Test
    void withdrawShouldDecreaseBalance_whenAmountIsValid() {
        Account account = new Account(100);

        account.withdraw(60);

        assertEquals(40, account.getBalance());
    }

    @Test
    void withdrawShouldSetBalanceToZero_whenAmountEqualsBalance() {
        Account account = new Account(100);

        account.withdraw(100);

        assertEquals(0, account.getBalance());
    }

    @Test
    void withdrawShouldThrowException_whenAmountIsNegative() {
        Account account = new Account(100);

        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(-10);
        });

        assertEquals(100, account.getBalance());
    }

    @Test
    void withdrawShouldNotChangeBalance_whenAmountIsZero() {
        Account account = new Account(100);

        account.withdraw(0);

        assertEquals(100, account.getBalance());
    }

    @Test
    void withdrawShouldNotChangeBalance_whenAmountExceedsBalance() {
        Account account = new Account(100);

        account.withdraw(150);

        assertEquals(100, account.getBalance());
    }
    @Test
    void depositShouldIncreaseBalance_whenAmountIsValid() {
        Account account = new Account(100);

        account.deposit(50);

        assertEquals(150, account.getBalance());
    }

    @Test
    void depositShouldThrowException_whenAmountIsZero() {
        Account account = new Account(100);

        assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(0);
        });

        assertEquals(100, account.getBalance());
    }

    @Test
    void depositShouldThrowException_whenAmountIsNegative() {
        Account account = new Account(100);

        assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-10);
        });

        assertEquals(100, account.getBalance());
    }
}
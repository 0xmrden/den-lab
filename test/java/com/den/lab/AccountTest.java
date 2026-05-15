package com.den.lab;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    @Test
    void withdrawShouldDecreaseBalance_whenAmountIsValid() {
        // arrange
        Account account = new Account(100);

        // act
        account.withdraw(60);

        // assert
        assertEquals(40, account.getBalance());
    }

    @Test
    void withdrawShouldSetBalanceToZero_whenAmountEqualsBalance() {
        // arrange
        Account account = new Account(100);

        // act
        account.withdraw(100);

        // assert
        assertEquals(0, account.getBalance());
    }

    @Test
    void withdrawShouldThrowException_whenAmountIsNegative() {
        // arrange
        Account account = new Account(100);

        // act
        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(-10);
        });

        // assert
        assertEquals(100, account.getBalance());
    }

    @Test
    void withdrawShouldNotChangeBalance_whenAmountIsZero() {
        // arrange
        Account account = new Account(100);

        // act
        account.withdraw(0);

        // assert
        assertEquals(100, account.getBalance());
    }

    @Test
    void withdrawShouldNotChangeBalance_whenAmountExceedsBalance() {
        // arrange
        Account account = new Account(100);

        // act
        account.withdraw(150);

        // assert
        assertEquals(100, account.getBalance());
    }

    @Test
    void depositShouldIncreaseBalance_whenAmountIsValid() {
        // arrange
        Account account = new Account(100);

        // act
        account.deposit(50);

        // assert
        assertEquals(150, account.getBalance());
    }

    @Test
    void depositShouldThrowException_whenAmountIsZero() {
        // arrange
        Account account = new Account(100);

        // act
        assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(0);
        });

        // assert
        assertEquals(100, account.getBalance());
    }

    @Test
    void depositShouldThrowException_whenAmountIsNegative() {
        // arrange
        Account account = new Account(100);

        // act
        assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-10);
        });

        // assert
        assertEquals(100, account.getBalance());
    }

    @Test
    void transferShouldMoveAmount_whenAmountIsValid() {
        // arrange
        Account sender = new Account(100);
        Account target = new Account(50);

        // act
        sender.transfer(target, 30);

        // assert
        assertEquals(70, sender.getBalance());
        assertEquals(80, target.getBalance());
    }

    @Test
    void transferShouldSetSenderBalanceToZero_whenAmountEqualsBalance() {
        // arrange
        Account sender = new Account(100);
        Account target = new Account(50);

        // act
        sender.transfer(target, 100);

        // assert
        assertEquals(0, sender.getBalance());
        assertEquals(150, target.getBalance());
    }

    @Test
    void transferShouldThrowException_whenTargetIsNull() {
        // arrange
        Account sender = new Account(100);

        // act
        assertThrows(IllegalArgumentException.class, () -> {
            sender.transfer(null, 30);
        });

        // assert
        assertEquals(100, sender.getBalance());
    }

    @Test
    void transferShouldThrowException_whenAmountIsZero() {
        // arrange
        Account sender = new Account(100);
        Account target = new Account(50);

        // act
        assertThrows(IllegalArgumentException.class, () -> {
            sender.transfer(target, 0);
        });

        // assert
        assertEquals(100, sender.getBalance());
        assertEquals(50, target.getBalance());
    }

    @Test
    void transferShouldThrowException_whenAmountIsNegative() {
        // arrange
        Account sender = new Account(100);
        Account target = new Account(50);

        // act
        assertThrows(IllegalArgumentException.class, () -> {
            sender.transfer(target, -10);
        });

        // assert
        assertEquals(100, sender.getBalance());
        assertEquals(50, target.getBalance());
    }

    @Test
    void transferShouldNotChangeBalances_whenAmountExceedsBalance() {
        // arrange
        Account sender = new Account(100);
        Account target = new Account(50);

        // act
        sender.transfer(target, 150);

        // assert
        assertEquals(100, sender.getBalance());
        assertEquals(50, target.getBalance());
    }
}
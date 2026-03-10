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
    void withdrawShouldThrowExceptionWhenAmountIsZero() {
        Account account = new Account(100);
        assertThrows(IllegalArgumentException.class, () ->
                account.withdraw(0));
    }

    @Test
    void withdrawShouldThrowExceptionWhenAmountExceedsBalance() {
        Account account = new Account(100);
        assertThrows(IllegalArgumentException.class, () ->
                account.withdraw(200));
    }

    @Test
    void depositShouldThrowExceptionWhenAmountIsZero() {
        Account account = new Account(100);
        assertThrows(IllegalArgumentException.class, () ->
                account.deposit(0));
    }

    @Test
    void depositShouldThrowExceptionWhenAmountIsNegative() {
        Account account = new Account(100);
        assertThrows(IllegalArgumentException.class, () ->
                account.deposit(-50));
    }

    @Test
    void withdrawShouldThrowExceptionWhenAmountIsNegative() {
        Account account = new Account(100);
        assertThrows(IllegalArgumentException.class, () ->
                account.withdraw(-20));
    }

    @Test
    void multipleDepositsShouldAccumulateBalance() {
        Account account = new Account(100);
        account.deposit(50);
        account.deposit(25);
        assertEquals(175, account.getBalance());
    }

    @Test
    void withdrawExactBalanceShouldSetBalanceToZero() {
        Account account = new Account(100);
        account.withdraw(100);
        assertEquals(0, account.getBalance());
    }

    @Test
    void multipleWithdrawsShouldDecreaseBalanceCorrectly() {
        Account account = new Account(100);
        account.withdraw(30);
        account.withdraw(20);
        assertEquals(50, account.getBalance());
    }

    @Test
    void constructorShouldThrowExceptionWhenInitialBalanceIsNegative() {
        assertThrows(IllegalArgumentException.class, () ->
                new Account(-100));
    }

    @Test
    void constructorShouldThrowExceptionWhenInitialBalanceZero() {
        assertThrows(IllegalArgumentException.class, () ->
                new Account(0));
    }

    @Test
    void constructorShouldSetInitialBalanceCorrectly() {
        Account account = new Account(100);
        assertEquals(100, account.getBalance());
    }

    @Test
    void constructorShouldCreateAccountWhenInitialBalanceIsOne() {
        Account account = new Account(1);
        assertEquals(1, account.getBalance());
    }

    @Test
    void depositShouldWorkWithAmount() {
        Account account = new Account(100);
        account.deposit(1);
        assertEquals(101, account.getBalance());
    }

    @Test
    void withdrawShouldWorkWithAmountOne() {
        Account account = new Account(100);
        account.withdraw(1);
        assertEquals(99, account.getBalance());
    }

    @Test
    void depositAfterWithdrawShouldUpdateBalanceCorrectly() {
        Account account = new Account(100);
        account.withdraw(30);
        account.deposit(20);
        assertEquals(90, account.getBalance());
    }

    @Test
    void withdrawAfterDepositShouldUpdateBalanceCorrectly() {
        Account account = new Account(100);
        account.deposit(50);
        account.withdraw(20);
        assertEquals(130, account.getBalance());
    }

    @Test
    void multipleOperationsShouldKeepCorrectBalance() {
        Account account = new Account(100);
        account.deposit(50);
        account.withdraw(20);
        account.deposit(10);
        account.withdraw(30);
        assertEquals(110, account.getBalance());
    }

    @Test
    void withdrawExactBalanceAfterDepositShouldSetBalanceToZero() {
        Account account = new Account(100);
        account.deposit(50);
        account.withdraw(150);
        assertEquals(0, account.getBalance());
    }

    @Test
    void constructorShouldThrowExceptionWhenInitialBalanceIsVeryNegative() {
        assertThrows(IllegalArgumentException.class, () ->
                new Account(-1000));
    }
    @Test
    void withdrawShouldFailWhenBalanceIsZero() {
        Account account = new Account(10);
        account.withdraw(10);
        assertThrows(IllegalArgumentException.class, () ->
                account.withdraw(10));
    }
    @Test
    void depositShouldFailWhenAmountTooLarge() {
        Account account = new Account(100);
        assertThrows(IllegalArgumentException.class, () ->
                account.deposit(2_000_000));
    }
    @Test
    void transferShouldMoveMoneyBetweenAccounts() {
        Account a = new Account(100);
        Account b = new Account(50);
        a.transfer(b, 40);
        assertEquals(60, a.getBalance());
        assertEquals(90, b.getBalance());
    }
    @Test
    void transferShouldFalWhenTargetIsNull(){
        Account a = new Account(100);
        assertThrows(IllegalArgumentException.class,() ->
            a.transfer(null, 50));
    }
    @Test
    void transferShouldFailWhenAmountExceedsBalance() {

        Account a = new Account(100);
        Account b = new Account(50);

        assertThrows(IllegalArgumentException.class, () ->
            a.transfer(b, 150));
    }
    @Test
    void transferShouldFailWhenAmountIsZero() {

        Account a = new Account(100);
        Account b = new Account(50);

        assertThrows(IllegalArgumentException.class, () ->
            a.transfer(b, 0));
    }
    @Test
    void transferShouldFailWhenAmountIsNegative() {

        Account a = new Account(100);
        Account b = new Account(50);

        assertThrows(IllegalArgumentException.class, () ->
            a.transfer(b, -20));
    }
    @Test
    void transferShouldNotChangeBalancesWhenTransferFails() {

        Account a = new Account(100);
        Account b = new Account(50);

        assertThrows(IllegalArgumentException.class, () ->
            a.transfer(b, 200));

        assertEquals(100, a.getBalance());
        assertEquals(50, b.getBalance());
    }
}
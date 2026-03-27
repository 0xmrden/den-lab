package com.den.lab;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void depositShouldIncreaseBalance() {
        // arrange
        Account account = new Account(100);
        // act
        account.deposit(50);
        // assert
        assertEquals(150, account.getBalance());
    }
@Test
void depositShouldNotChangeBalanceWhenAmountIsNegative(){
        //arrange
    Account account = new Account(100);
    //act
    account.deposit(-10);
    //assert
    assertEquals(100,account.getBalance());

}
    @Test
    void withdrawShouldDecreaseBalance() {
        // arrange
        Account account = new Account(100);
        // act
        account.withdraw(30);
        // assert
        assertEquals(70, account.getBalance());
    }

    @Test
    void withdrawShouldNotChangeBalanceWhenAmountIsZero() {
        // arrange
        Account account = new Account(100);
        // act + assert
        account.withdraw(0);
       assertEquals(100,account.getBalance());

    }

    @Test
    void withdrawShouldNotChangeBalanceWhenAmountExceedsBalance() {
        // arrange
        Account account = new Account(100);
        // act + assert
                account.withdraw(200);
                assertEquals(100,account.getBalance());
    }
    @Test
    void withdrawShouldNotChangeBalanceWhenAmountIsNegative() {
        // arrange
        Account account = new Account(100);
        // act + assert
                account.withdraw(-20);
                assertEquals(100,account.getBalance());
    }

    @Test
    void multipleDepositsShouldAccumulateBalance() {
        // arrange
        Account account = new Account(100);
        // act
        account.deposit(50);
        account.deposit(25);
        // assert
        assertEquals(175, account.getBalance());
    }

    @Test
    void withdrawExactBalanceShouldSetBalanceToZero() {
        // arrange
        Account account = new Account(100);
        // act
        account.withdraw(100);
        // assert
        assertEquals(0, account.getBalance());
    }

    @Test
    void multipleWithdrawsShouldDecreaseBalanceCorrectly() {
        // arrange
        Account account = new Account(100);
        // act
        account.withdraw(30);
        account.withdraw(20);
        // assert
        assertEquals(50, account.getBalance());
    }

    @Test
    void constructorShouldThrowExceptionWhenInitialBalanceIsNegative() {
        // act + assert
        assertThrows(IllegalArgumentException.class, () ->
                new Account(-100));
    }

    @Test
    void constructorShouldThrowExceptionWhenInitialBalanceIsZero() {
        // act + assert
        assertThrows(IllegalArgumentException.class, () ->
                new Account(0));
    }

    @Test
    void constructorShouldSetInitialBalanceCorrectly() {
        // act
        Account account = new Account(100);
        // assert
        assertEquals(100, account.getBalance());
    }

    @Test
    void constructorShouldCreateAccountWhenInitialBalanceIsOne() {
        // act
        Account account = new Account(1);
        // assert
        assertEquals(1, account.getBalance());
    }

    @Test
    void depositShouldWorkWithAmount() {
        // arrange
        Account account = new Account(100);
        // act
        account.deposit(1);
        // assert
        assertEquals(101, account.getBalance());
    }

    @Test
    void withdrawShouldWorkWithAmountOne() {
        // arrange
        Account account = new Account(100);
        // act
        account.withdraw(1);
        // assert
        assertEquals(99, account.getBalance());
    }

    @Test
    void depositAfterWithdrawShouldUpdateBalanceCorrectly() {
        // arrange
        Account account = new Account(100);
        // act
        account.withdraw(30);
        account.deposit(20);
        // assert
        assertEquals(90, account.getBalance());
    }

    @Test
    void withdrawAfterDepositShouldUpdateBalanceCorrectly() {
        // arrange
        Account account = new Account(100);
        // act
        account.deposit(50);
        account.withdraw(20);
        // assert
        assertEquals(130, account.getBalance());
    }

    @Test
    void multipleOperationsShouldKeepCorrectBalance() {
        // arrange
        Account account = new Account(100);
        // act
        account.deposit(50);
        account.withdraw(20);
        account.deposit(10);
        account.withdraw(30);
        // assert
        assertEquals(110, account.getBalance());
    }

    @Test
    void withdrawExactBalanceAfterDepositShouldSetBalanceToZero() {
        // arrange
        Account account = new Account(100);
        // act
        account.deposit(50);
        account.withdraw(150);
        // assert
        assertEquals(0, account.getBalance());
    }

    @Test
    void constructorShouldThrowExceptionWhenInitialBalanceIsVeryNegative() {
       // act + assert
        assertThrows(IllegalArgumentException.class, () ->
                new Account(-1000));
    }
    @Test
    void withdrawShouldFailWhenBalanceIsZero() {
        // arrange
        Account account = new Account(10);
        // act
        account.withdraw(10);
        // assert
                account.withdraw(10);
                assertEquals(0,account.getBalance());
    }
    @Test
    void depositShouldFailWhenAmountTooLarge() {
        // arrange
        Account account = new Account(100);
        // act + assert
        assertThrows(IllegalArgumentException.class, () ->
                account.deposit(2_000_000));
    }
    @Test
    void transferShouldMoveMoneyBetweenAccounts() {
        // arrange
        Account source = new Account(100);
        Account target = new Account(50);
        // act
        source.transfer(target, 40);
        // assert
        assertEquals(60, source.getBalance());
        assertEquals(90, target.getBalance());
    }
    @Test
    void transferShouldFailWhenTargetIsNull() {
        // arrange
        Account source = new Account(100);
        // act + assert
        assertThrows(IllegalArgumentException.class,() ->
                source.transfer(null, 50));
    }
    @Test
    void transferShouldFailWhenAmountExceedsBalance() {
        // arrange
        Account source = new Account(100);
        Account target = new Account(50);
        // act + assert
        assertThrows(IllegalArgumentException.class, () ->
                source.transfer(target, 150));
    }
    @Test
    void transferShouldFailWhenAmountIsZero() {
        // arrange
        Account source = new Account(100);
        Account target = new Account(50);
        // act + assert
        assertThrows(IllegalArgumentException.class, () ->
                source.transfer(target, 0));
    }
    @Test
    void transferShouldFailWhenAmountIsNegative() {
        // arrange
        Account source = new Account(100);
        Account target = new Account(50);
        // act + assert
        assertThrows(IllegalArgumentException.class, () ->
                source.transfer(target, -20));
    }
    @Test
    void transferShouldNotChangeBalancesWhenTransferFails() {
        // arrange
        Account source = new Account(100);
        Account target = new Account(50);
        // act
        assertThrows(IllegalArgumentException.class, () ->
                source.transfer(target, 200));
        // assert
        assertEquals(100, source.getBalance());
        assertEquals(50, target.getBalance());
    }
}
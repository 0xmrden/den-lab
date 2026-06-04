package com.den.lab;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class WalletSystemTest {

    // ======= addAccount =======

    @Test
    void addAccountShouldIncreaseCount_whenAccountIsValid() {
        WalletSystem walletSystem = new WalletSystem();
        PracticeAccount account = new PracticeAccount(100);

        walletSystem.addAccount(account);

        assertEquals(1, walletSystem.getAccountsCount());
    }

    @Test
    void addAccountShouldThrowException_whenAccountIsNull() {
        WalletSystem walletSystem = new WalletSystem();

        assertThrows(IllegalArgumentException.class, () -> walletSystem.addAccount(null));

        assertEquals(0, walletSystem.getAccountsCount());
    }

    @Test
    void addAccountShouldNotIncreaseCount_whenAccountIsDuplicate() {
        WalletSystem walletSystem = new WalletSystem();
        PracticeAccount account = new PracticeAccount(100);

        walletSystem.addAccount(account);
        walletSystem.addAccount(account);

        assertEquals(1, walletSystem.getAccountsCount());
    }

    // ======= hasAccounts =======

    @Test
    void hasAccountsShouldReturnTrue_whenSystemHasAccount() {
        WalletSystem walletSystem = new WalletSystem();
        PracticeAccount account = new PracticeAccount(100);

        walletSystem.addAccount(account);

        assertTrue(walletSystem.hasAccounts());
    }

    @Test
    void hasAccountsShouldReturnFalse_whenSystemIsEmpty() {
        WalletSystem walletSystem = new WalletSystem();

        assertFalse(walletSystem.hasAccounts());
    }

    // ======= containsAccount =======

    @Test
    void containsAccountShouldReturnTrue_whenAccountExistsInSystem() {
        WalletSystem walletSystem = new WalletSystem();
        PracticeAccount account = new PracticeAccount(100);

        walletSystem.addAccount(account);

        assertTrue(walletSystem.containsAccount(account));
    }

    @Test
    void containsAccountShouldReturnFalse_whenAccountDoesNotExistInSystem() {
        WalletSystem walletSystem = new WalletSystem();
        PracticeAccount account = new PracticeAccount(100);

        assertFalse(walletSystem.containsAccount(account));
    }

    // ======= isEmpty =======

    @Test
    void isEmptyShouldReturnTrue_whenSystemHasNoAccounts() {
        WalletSystem walletSystem = new WalletSystem();

        assertTrue(walletSystem.isEmpty());
    }

    @Test
    void isEmptyShouldReturnFalse_whenSystemHasAccount() {
        WalletSystem walletSystem = new WalletSystem();
        PracticeAccount account = new PracticeAccount(100);

        walletSystem.addAccount(account);

        assertFalse(walletSystem.isEmpty());
    }

    // ======= filterAccountsWithBalanceGreaterThan =======

    @Test
    void filterAccountsWithBalanceGreaterThanShouldReturnMatchingAccounts_whenAccountsExist() {
        WalletSystem walletSystem = new WalletSystem();
        PracticeAccount account1 = new PracticeAccount(50);
        PracticeAccount account2 = new PracticeAccount(150);
        PracticeAccount account3 = new PracticeAccount(200);

        walletSystem.addAccount(account1);
        walletSystem.addAccount(account2);
        walletSystem.addAccount(account3);

        List<PracticeAccount> result = walletSystem.filterAccountsWithBalanceGreaterThan(100);

        assertEquals(2, result.size());
    }

    @Test
    void filterAccountsWithBalanceGreaterThanShouldNotIncludeBoundaryValue_whenBalanceEqualsAmount() {
        WalletSystem walletSystem = new WalletSystem();
        PracticeAccount account = new PracticeAccount(100);

        walletSystem.addAccount(account);

        List<PracticeAccount> result = walletSystem.filterAccountsWithBalanceGreaterThan(100);

        assertTrue(result.isEmpty());
    }

    @Test
    void filterAccountsWithBalanceGreaterThanShouldReturnEmptyList_whenNoAccountsMatch() {
        WalletSystem walletSystem = new WalletSystem();
        PracticeAccount account1 = new PracticeAccount(10);
        PracticeAccount account2 = new PracticeAccount(50);
        PracticeAccount account3 = new PracticeAccount(99);

        walletSystem.addAccount(account1);
        walletSystem.addAccount(account2);
        walletSystem.addAccount(account3);

        List<PracticeAccount> result = walletSystem.filterAccountsWithBalanceGreaterThan(100);

        assertTrue(result.isEmpty());
    }

    // ======= getTotalBalance =======

    @Test
    void getTotalBalanceShouldReturnSumOfBalances_whenAccountsExist() {
        WalletSystem walletSystem = new WalletSystem();
        PracticeAccount account1 = new PracticeAccount(50);
        PracticeAccount account2 = new PracticeAccount(150);
        PracticeAccount account3 = new PracticeAccount(200);

        walletSystem.addAccount(account1);
        walletSystem.addAccount(account2);
        walletSystem.addAccount(account3);

        int total = walletSystem.getTotalBalance();

        assertEquals(400, total);
    }

    @Test
    void getTotalBalanceShouldReturnZero_whenSystemHasNoAccounts() {
        WalletSystem walletSystem = new WalletSystem();

        int total = walletSystem.getTotalBalance();

        assertEquals(0, total);
    }

    // ======= immutable access =======

    @Test
    void getAccountsShouldThrowException_whenExternalCodeTriesToModifyList() {
        WalletSystem walletSystem = new WalletSystem();
        PracticeAccount account = new PracticeAccount(100);

        assertThrows(UnsupportedOperationException.class,
                () -> walletSystem.getAccounts().add(account));

        assertEquals(0, walletSystem.getAccountsCount());
    }

    @Test
    void getAccountsShouldReturnCopyOfAccounts_whenExternalCodeGetsAccounts() {
        WalletSystem walletSystem = new WalletSystem();
        PracticeAccount account = new PracticeAccount(100);

        walletSystem.addAccount(account);

        PracticeAccount externalAccount = walletSystem.getAccounts().get(0);
        externalAccount.deposit(50);

        assertEquals(100, walletSystem.getAccounts().get(0).getBalance());
    }
}


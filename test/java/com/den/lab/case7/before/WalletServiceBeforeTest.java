package com.den.lab.case7.before;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WalletServiceBeforeTest {

    @Test
    void shouldWithdrawSuccessfullyBeforeRefactoring() {
        Wallet wallet = new Wallet("wallet-1", 100);

        WalletRepository repository = new WalletRepository();
        repository.save(wallet);

        WalletService service = new WalletService(repository);

        WithdrawRequest request =
                new WithdrawRequest("wallet-1", 40);

        boolean result = service.withdraw(request);

        assertTrue(result);
        assertEquals(60, wallet.getBalance());
    }

    @Test
    void shouldKeepBalanceWhenWithdrawalIsRefusedBeforeRefactoring() {
        Wallet wallet = new Wallet("wallet-1", 100);

        WalletRepository repository = new WalletRepository();
        repository.save(wallet);

        WalletService service = new WalletService(repository);

        WithdrawRequest request =
                new WithdrawRequest("wallet-1", 150);

        boolean result = service.withdraw(request);

        assertFalse(result);
        assertEquals(100, wallet.getBalance());
    }
}
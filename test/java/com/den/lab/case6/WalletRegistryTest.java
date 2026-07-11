package com.den.lab.case6;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class WalletRegistryTest {

    //======= constructor =======

    @Test
    void constructorShouldSetTransactionCounterToZero_whenRegistryIsCreated() {
        // arrange
        WalletRegistry registry = new WalletRegistry();

        // act
        long result = registry.getTotalTransactions();

        // assert
        assertEquals(0, result);
    }

    @Test
    void constructorShouldThrowException_whenTransactionCounterIsNegative() {
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new WalletRegistry(-1);
        });
    }

    //======= registerTransaction =======

    @Test
    void registerTransactionShouldIncreaseCounterByOne_whenTransactionIsRegistered() {
        // arrange
        WalletRegistry registry = new WalletRegistry();

        // act
        registry.registerTransaction();

        // assert
        assertEquals(1, registry.getTotalTransactions());
    }

    @Test
    void registerTransactionShouldIncreaseCounterSeveralTimes_whenSeveralTransactionsAreRegistered() {
        // arrange
        WalletRegistry registry = new WalletRegistry();

        // act
        registry.registerTransaction();
        registry.registerTransaction();
        registry.registerTransaction();

        // assert
        assertEquals(3, registry.getTotalTransactions());
    }

    @Test
    void registerTransactionShouldIncreaseCounterToLongMaxValue_whenCounterIsOneStepBeforeLimit() {
        // arrange
        WalletRegistry registry =
                new WalletRegistry(Long.MAX_VALUE - 1);

        // act
        registry.registerTransaction();

        // assert
        assertEquals(
                Long.MAX_VALUE,
                registry.getTotalTransactions()
        );
    }

    @Test
    void registerTransactionShouldThrowException_withoutChangingCounter_whenCounterReachedLongMaxValue() {
        // arrange
        WalletRegistry registry =
                new WalletRegistry(Long.MAX_VALUE);

        // act + assert
        assertThrows(IllegalStateException.class, () -> {
            registry.registerTransaction();
        });

        // assert
        assertEquals(
                Long.MAX_VALUE,
                registry.getTotalTransactions()
        );
    }

    //======= addWallet =======

    @Test
    void addWalletShouldAddWallet_whenWalletIsValid() {
        // arrange
        WalletRegistry registry = new WalletRegistry();

        Wallet wallet =
                new Wallet(
                        "wallet-1",
                        LocalDate.of(2026, 7, 11)
                );

        // act
        registry.addWallet(wallet);

        // assert
        Optional<Wallet> result =
                registry.findWalletById("wallet-1");

        assertTrue(result.isPresent());
        assertSame(wallet, result.get());
    }

    @Test
    void addWalletShouldThrowException_whenWalletIsNull() {
        // arrange
        WalletRegistry registry = new WalletRegistry();

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            registry.addWallet(null);
        });
    }

    @Test
    void addWalletShouldThrowException_withoutReplacingExistingWallet_whenWalletIdIsDuplicate() {
        // arrange
        WalletRegistry registry = new WalletRegistry();

        Wallet wallet1 =
                new Wallet(
                        "wallet-1",
                        LocalDate.of(2026, 7, 11)
                );

        Wallet duplicateWallet =
                new Wallet(
                        "wallet-1",
                        LocalDate.of(2026, 7, 12)
                );

        registry.addWallet(wallet1);

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            registry.addWallet(duplicateWallet);
        });

        // assert
        Optional<Wallet> result =
                registry.findWalletById("wallet-1");

        assertTrue(result.isPresent());
        assertSame(wallet1, result.get());
    }

    //======= findWalletById =======

    @Test
    void findWalletByIdShouldReturnWallet_whenWalletExists() {
        // arrange
        WalletRegistry registry = new WalletRegistry();

        Wallet wallet =
                new Wallet(
                        "wallet-1",
                        LocalDate.of(2026, 7, 11)
                );

        registry.addWallet(wallet);

        // act
        Optional<Wallet> result =
                registry.findWalletById("wallet-1");

        // assert
        assertTrue(result.isPresent());
        assertSame(wallet, result.get());
    }

    @Test
    void findWalletByIdShouldReturnEmpty_whenWalletDoesNotExist() {
        // arrange
        WalletRegistry registry = new WalletRegistry();

        // act
        Optional<Wallet> result =
                registry.findWalletById("wallet-999");

        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    void findWalletByIdShouldThrowException_whenIdIsNull() {
        // arrange
        WalletRegistry registry = new WalletRegistry();

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            registry.findWalletById(null);
        });
    }

    @Test
    void findWalletByIdShouldThrowException_whenIdIsBlank() {
        // arrange
        WalletRegistry registry = new WalletRegistry();

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            registry.findWalletById(" ");
        });
    }
}
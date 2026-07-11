package com.den.lab.case6;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class WalletTest {

    //======= constructor =======

    @Test
    void constructorShouldCreateWallet_whenFieldsAreValid() {
        // arrange
        LocalDate createdDate = LocalDate.of(2026, 7, 11);

        // act
        Wallet wallet = new Wallet("wallet-1", createdDate);

        // assert
        assertEquals("wallet-1", wallet.getId());
        assertEquals(BigDecimal.ZERO, wallet.getBalance());
        assertFalse(wallet.isVerified());
        assertEquals(WalletStatus.ACTIVE, wallet.getStatus());
        assertEquals(createdDate, wallet.getCreatedDate());
        assertTrue(wallet.getLastActivityAt().isEmpty());
    }

    @Test
    void constructorShouldThrowException_whenIdIsNull() {
        // arrange
        LocalDate createdDate = LocalDate.of(2026, 7, 11);

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Wallet(null, createdDate);
        });
    }

    @Test
    void constructorShouldThrowException_whenIdIsBlank() {
        // arrange
        LocalDate createdDate = LocalDate.of(2026, 7, 11);

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Wallet(" ", createdDate);
        });
    }

    @Test
    void constructorShouldThrowException_whenCreatedDateIsNull() {
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Wallet("wallet-1", null);
        });
    }

    //======= deposit =======

    @Test
    void depositShouldIncreaseBalance_whenAmountIsValid() {
        // arrange
        Wallet wallet =
                new Wallet("wallet-1", LocalDate.of(2026, 7, 11));

        // act
        wallet.deposit(new BigDecimal("10.25"));

        // assert
        assertEquals(
                new BigDecimal("10.25"),
                wallet.getBalance()
        );
    }

    @Test
    void depositShouldPreserveDecimalPrecision_whenDecimalAmountsAreAdded() {
        // arrange
        Wallet wallet =
                new Wallet("wallet-1", LocalDate.of(2026, 7, 11));

        // act
        wallet.deposit(new BigDecimal("0.10"));
        wallet.deposit(new BigDecimal("0.20"));

        // assert
        assertEquals(
                new BigDecimal("0.30"),
                wallet.getBalance()
        );
    }

    @Test
    void depositShouldThrowException_withoutChangingBalance_whenAmountIsNull() {
        // arrange
        Wallet wallet =
                new Wallet("wallet-1", LocalDate.of(2026, 7, 11));

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            wallet.deposit(null);
        });

        // assert
        assertEquals(BigDecimal.ZERO, wallet.getBalance());
    }

    @Test
    void depositShouldThrowException_withoutChangingBalance_whenAmountIsZero() {
        // arrange
        Wallet wallet =
                new Wallet("wallet-1", LocalDate.of(2026, 7, 11));

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            wallet.deposit(BigDecimal.ZERO);
        });

        // assert
        assertEquals(BigDecimal.ZERO, wallet.getBalance());
    }

    @Test
    void depositShouldThrowException_withoutChangingBalance_whenAmountIsNegative() {
        // arrange
        Wallet wallet =
                new Wallet("wallet-1", LocalDate.of(2026, 7, 11));

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            wallet.deposit(new BigDecimal("-10.00"));
        });

        // assert
        assertEquals(BigDecimal.ZERO, wallet.getBalance());
    }

    //======= verification =======

    @Test
    void verifyShouldSetVerifiedToTrue_whenWalletIsVerified() {
        // arrange
        Wallet wallet =
                new Wallet("wallet-1", LocalDate.of(2026, 7, 11));

        // act
        wallet.verify();

        // assert
        assertTrue(wallet.isVerified());
    }

    //======= lifecycle status =======

    @Test
    void freezeShouldChangeStatusToFrozen_whenWalletIsActive() {
        // arrange
        Wallet wallet =
                new Wallet("wallet-1", LocalDate.of(2026, 7, 11));

        // act
        wallet.freeze();

        // assert
        assertEquals(WalletStatus.FROZEN, wallet.getStatus());
    }

    @Test
    void closeShouldChangeStatusToClosed_whenWalletIsActive() {
        // arrange
        Wallet wallet =
                new Wallet("wallet-1", LocalDate.of(2026, 7, 11));

        // act
        wallet.close();

        // assert
        assertEquals(WalletStatus.CLOSED, wallet.getStatus());
    }

    @Test
    void closeShouldChangeStatusToClosed_whenWalletIsFrozen() {
        // arrange
        Wallet wallet =
                new Wallet("wallet-1", LocalDate.of(2026, 7, 11));

        wallet.freeze();

        // act
        wallet.close();

        // assert
        assertEquals(WalletStatus.CLOSED, wallet.getStatus());
    }

    @Test
    void freezeShouldThrowException_withoutChangingStatus_whenWalletIsClosed() {
        // arrange
        Wallet wallet =
                new Wallet("wallet-1", LocalDate.of(2026, 7, 11));

        wallet.close();

        // act + assert
        assertThrows(IllegalStateException.class, () -> {
            wallet.freeze();
        });

        // assert
        assertEquals(WalletStatus.CLOSED, wallet.getStatus());
    }

    //======= activity =======

    @Test
    void getLastActivityAtShouldReturnEmpty_whenActivityWasNotMarked() {
        // arrange
        Wallet wallet =
                new Wallet("wallet-1", LocalDate.of(2026, 7, 11));

        // act
        Optional<LocalDateTime> result = wallet.getLastActivityAt();

        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    void markActivityShouldStoreActivityTime_whenTimeIsValid() {
        // arrange
        Wallet wallet =
                new Wallet("wallet-1", LocalDate.of(2026, 7, 11));

        LocalDateTime activityTime =
                LocalDateTime.of(2026, 7, 11, 10, 30);

        // act
        wallet.markActivity(activityTime);

        // assert
        assertTrue(wallet.getLastActivityAt().isPresent());
        assertEquals(
                activityTime,
                wallet.getLastActivityAt().get()
        );
    }

    @Test
    void markActivityShouldThrowException_withoutChangingState_whenTimeIsNull() {
        // arrange
        Wallet wallet =
                new Wallet("wallet-1", LocalDate.of(2026, 7, 11));

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            wallet.markActivity(null);
        });

        // assert
        assertTrue(wallet.getLastActivityAt().isEmpty());
    }
}
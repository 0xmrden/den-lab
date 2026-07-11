package com.den.lab.case6;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DepositTest {

    //======= constructor =======

    @Test
    void constructorShouldCreateDeposit_whenFieldsAreValid() {
        // arrange
        LocalDate unlockDate =
                LocalDate.of(2026, 12, 31);

        // act
        Deposit deposit =
                new Deposit(
                        "deposit-1",
                        new BigDecimal("1000.50"),
                        new BigDecimal("12.5"),
                        unlockDate
                );

        // assert
        assertEquals(
                "deposit-1",
                deposit.getDepositId()
        );

        assertEquals(
                new BigDecimal("1000.50"),
                deposit.getAmount()
        );

        assertEquals(
                new BigDecimal("12.5"),
                deposit.getRewardPercent()
        );

        assertEquals(
                unlockDate,
                deposit.getUnlockDate()
        );
    }

    @Test
    void constructorShouldThrowException_whenDepositIdIsNull() {
        // arrange
        LocalDate unlockDate =
                LocalDate.of(2026, 12, 31);

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Deposit(
                    null,
                    new BigDecimal("1000"),
                    new BigDecimal("12"),
                    unlockDate
            );
        });
    }

    @Test
    void constructorShouldThrowException_whenDepositIdIsBlank() {
        // arrange
        LocalDate unlockDate =
                LocalDate.of(2026, 12, 31);

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Deposit(
                    " ",
                    new BigDecimal("1000"),
                    new BigDecimal("12"),
                    unlockDate
            );
        });
    }

    @Test
    void constructorShouldThrowException_whenAmountIsNull() {
        // arrange
        LocalDate unlockDate =
                LocalDate.of(2026, 12, 31);

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Deposit(
                    "deposit-1",
                    null,
                    new BigDecimal("12"),
                    unlockDate
            );
        });
    }

    @Test
    void constructorShouldThrowException_whenAmountIsZero() {
        // arrange
        LocalDate unlockDate =
                LocalDate.of(2026, 12, 31);

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Deposit(
                    "deposit-1",
                    BigDecimal.ZERO,
                    new BigDecimal("12"),
                    unlockDate
            );
        });
    }

    @Test
    void constructorShouldThrowException_whenAmountIsNegative() {
        // arrange
        LocalDate unlockDate =
                LocalDate.of(2026, 12, 31);

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Deposit(
                    "deposit-1",
                    new BigDecimal("-1000"),
                    new BigDecimal("12"),
                    unlockDate
            );
        });
    }

    @Test
    void constructorShouldThrowException_whenRewardPercentIsNull() {
        // arrange
        LocalDate unlockDate =
                LocalDate.of(2026, 12, 31);

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Deposit(
                    "deposit-1",
                    new BigDecimal("1000"),
                    null,
                    unlockDate
            );
        });
    }

    @Test
    void constructorShouldThrowException_whenRewardPercentIsZero() {
        // arrange
        LocalDate unlockDate =
                LocalDate.of(2026, 12, 31);

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Deposit(
                    "deposit-1",
                    new BigDecimal("1000"),
                    BigDecimal.ZERO,
                    unlockDate
            );
        });
    }

    @Test
    void constructorShouldThrowException_whenRewardPercentIsNegative() {
        // arrange
        LocalDate unlockDate =
                LocalDate.of(2026, 12, 31);

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Deposit(
                    "deposit-1",
                    new BigDecimal("1000"),
                    new BigDecimal("-12"),
                    unlockDate
            );
        });
    }

    @Test
    void constructorShouldThrowException_whenUnlockDateIsNull() {
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Deposit(
                    "deposit-1",
                    new BigDecimal("1000"),
                    new BigDecimal("12"),
                    null
            );
        });
    }
}
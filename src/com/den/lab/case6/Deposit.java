package com.den.lab.case6;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Deposit {

    private final String depositId;
    private final BigDecimal amount;
    private final BigDecimal rewardPercent;
    private final LocalDate unlockDate;

    public Deposit(
            String depositId,
            BigDecimal amount,
            BigDecimal rewardPercent,
            LocalDate unlockDate
    ) {
        if (depositId == null || depositId.isBlank()) {
            throw new IllegalArgumentException(
                    "Deposit id cannot be null or blank."
            );
        }

        if (amount == null) {
            throw new IllegalArgumentException(
                    "Amount cannot be null."
            );
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be greater than zero."
            );
        }

        if (rewardPercent == null) {
            throw new IllegalArgumentException(
                    "Reward percent cannot be null."
            );
        }

        if (rewardPercent.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Reward percent must be greater than zero."
            );
        }

        if (unlockDate == null) {
            throw new IllegalArgumentException(
                    "Unlock date cannot be null."
            );
        }

        this.depositId = depositId;
        this.amount = amount;
        this.rewardPercent = rewardPercent;
        this.unlockDate = unlockDate;
    }

    public String getDepositId() {
        return depositId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getRewardPercent() {
        return rewardPercent;
    }

    public LocalDate getUnlockDate() {
        return unlockDate;
    }
}
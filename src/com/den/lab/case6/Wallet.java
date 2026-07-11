package com.den.lab.case6;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class Wallet {

    private final String id;
    private BigDecimal balance;
    private boolean verified;
    private WalletStatus status;
    private final LocalDate createdDate;
    private LocalDateTime lastActivityAt;

    public Wallet(String id, LocalDate createdDate) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Id cannot be null or blank.");
        }

        if (createdDate == null) {
            throw new IllegalArgumentException("Created date cannot be null.");
        }

        this.id = id;
        this.balance = BigDecimal.ZERO;
        this.verified = false;
        this.status = WalletStatus.ACTIVE;
        this.createdDate = createdDate;
        this.lastActivityAt = null;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public boolean isVerified() {
        return verified;
    }

    public WalletStatus getStatus() {
        return status;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public Optional<LocalDateTime> getLastActivityAt() {
        return Optional.ofNullable(lastActivityAt);
    }

    public void deposit(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null.");
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }

        balance = balance.add(amount);
    }

    public void verify() {
        verified = true;
    }

    public void freeze() {
        if (status == WalletStatus.CLOSED) {
            throw new IllegalStateException("Closed wallet cannot be frozen.");
        }

        status = WalletStatus.FROZEN;
    }

    public void close() {
        status = WalletStatus.CLOSED;
    }

    public void markActivity(LocalDateTime activityTime) {
        if (activityTime == null) {
            throw new IllegalArgumentException("Activity time cannot be null.");
        }

        lastActivityAt = activityTime;
    }
}
package com.den.lab.case6;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WalletRegistry {

    private long totalTransactions;
    private final List<Wallet> wallets;

    public WalletRegistry() {
        this(0);
    }

    public WalletRegistry(long totalTransactions) {
        if (totalTransactions < 0) {
            throw new IllegalArgumentException(
                    "Total transactions cannot be negative."
            );
        }

        this.totalTransactions = totalTransactions;
        this.wallets = new ArrayList<>();
    }

    public void registerTransaction() {
        if (totalTransactions == Long.MAX_VALUE) {
            throw new IllegalStateException(
                    "Transaction counter overflow."
            );
        }

        totalTransactions++;
    }

    public long getTotalTransactions() {
        return totalTransactions;
    }

    public void addWallet(Wallet wallet) {
        if (wallet == null) {
            throw new IllegalArgumentException(
                    "Wallet cannot be null."
            );
        }

        if (findWalletById(wallet.getId()).isPresent()) {
            throw new IllegalArgumentException(
                    "Wallet id already exists."
            );
        }

        wallets.add(wallet);
    }

    public Optional<Wallet> findWalletById(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException(
                    "Id cannot be null or blank."
            );
        }

        for (Wallet wallet : wallets) {
            if (wallet.getId().equals(id)) {
                return Optional.of(wallet);
            }
        }

        return Optional.empty();
    }
}
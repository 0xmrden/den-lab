package com.den.lab.case5;

public class Wallet {

    private final int walletId;
    private final String address;
    private final String network;
    private final int balance;

    public Wallet(int walletId, String address, String network, int balance) {
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("address cannot be null or blank");
        }

        if (network == null || network.isBlank()) {
            throw new IllegalArgumentException("network cannot be null or blank");
        }

        if (balance < 0) {
            throw new IllegalArgumentException("balance cannot be negative");
        }

        this.walletId = walletId;
        this.address = address;
        this.network = network;
        this.balance = balance;
    }

    public int getWalletId() {
        return walletId;
    }

    public String getAddress() {
        return address;
    }

    public String getNetwork() {
        return network;
    }

    public int getBalance() {
        return balance;
    }
}
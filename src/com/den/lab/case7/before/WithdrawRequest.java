package com.den.lab.case7.before;

public class WithdrawRequest {

    private final String walletId;
    private final int amount;

    public WithdrawRequest(String walletId, int amount) {
        this.walletId = walletId;
        this.amount = amount;
    }

    public String getWalletId() {
        return walletId;
    }

    public int getAmount() {
        return amount;
    }
}
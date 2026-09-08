package com.den.lab.case7.before;

import java.util.HashMap;
import java.util.Map;

public class WalletRepository {

    private final Map<String, Wallet> wallets = new HashMap<>();

    public void save(Wallet wallet) {
        wallets.put(wallet.getId(), wallet);
    }

    public Wallet findById(String walletId) {
        return wallets.get(walletId);
    }
}
package com.den.lab.case5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WalletRegistry {

    private final List<Wallet> wallets = new ArrayList<>();
    private final Map<Integer, Wallet> walletsById = new HashMap<>();
    private final Set<String> walletAddresses = new HashSet<>();
    private final Map<String, List<Wallet>> walletsByNetwork = new HashMap<>();
    private final List<Wallet> walletsSortedByBalanceDesc = new ArrayList<>();

    public void addWallet(Wallet wallet) {
        if (wallet == null) {
            throw new IllegalArgumentException("wallet cannot be null");
        }

        if (walletsById.containsKey(wallet.getWalletId())) {
            throw new IllegalArgumentException("walletId already exists");
        }

        if (walletAddresses.contains(wallet.getAddress())) {
            throw new IllegalArgumentException("address already exists");
        }

        wallets.add(wallet);
        walletsById.put(wallet.getWalletId(), wallet);
        walletAddresses.add(wallet.getAddress());

        String network = wallet.getNetwork();

        if (!walletsByNetwork.containsKey(network)) {
            walletsByNetwork.put(network, new ArrayList<>());
        }

        walletsByNetwork.get(network).add(wallet);

        walletsSortedByBalanceDesc.add(wallet);
        walletsSortedByBalanceDesc.sort((wallet1, wallet2) ->
                Integer.compare(wallet2.getBalance(), wallet1.getBalance())
        );
    }

    public Wallet findWalletById(int walletId) {
        return walletsById.get(walletId);
    }

    public Wallet findWalletByAddress(String address) {
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("address cannot be null or blank");
        }

        for (Wallet wallet : wallets) {
            if (wallet.getAddress().equals(address)) {
                return wallet;
            }
        }

        return null;
    }

    public Map<String, List<Wallet>> getWalletsByNetwork() {
        Map<String, List<Wallet>> result = new HashMap<>();

        for (Map.Entry<String, List<Wallet>> entry : walletsByNetwork.entrySet()) {
            result.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }

        return result;
    }

    public List<Wallet> getWalletsSortedByBalanceDesc() {
        return new ArrayList<>(walletsSortedByBalanceDesc);
    }
}
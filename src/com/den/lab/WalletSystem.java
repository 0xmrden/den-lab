package com.den.lab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WalletSystem {

    private List<PracticeAccount> accounts = new ArrayList<>();

    public void addAccount(PracticeAccount account) {
        if (account == null) {
            throw new IllegalArgumentException("account cannot be null");
        }

        if (accounts.contains(account)) {
            return;
        }

        accounts.add(account);
    }

    public boolean hasAccounts() {
        return !accounts.isEmpty();
    }

    public int getAccountsCount() {
        return accounts.size();
    }

    public boolean containsAccount(PracticeAccount account) {
        return accounts.contains(account);
    }

    public boolean isEmpty() {
        return accounts.isEmpty();
    }

    public List<PracticeAccount> filterAccountsWithBalanceGreaterThan(int amount) {
        List<PracticeAccount> result = new ArrayList<>();

        for (PracticeAccount account : accounts) {
            if (account.getBalance() > amount) {
                result.add(account.copy());
            }
        }

        return result;
    }

    public int getTotalBalance() {
        int total = 0;

        for (PracticeAccount account : accounts) {
            total = total + account.getBalance();
        }

        return total;
    }

    public List<PracticeAccount> getAccounts() {
        List<PracticeAccount> result = new ArrayList<>();

        for (PracticeAccount account : accounts) {
            result.add(account.copy());
        }

        return Collections.unmodifiableList(result);
    }
}

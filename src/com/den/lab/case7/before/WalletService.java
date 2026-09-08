package com.den.lab.case7.before;

public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public boolean withdraw(WithdrawRequest request) {

        Wallet wallet =
                walletRepository.findById(
                        request.getWalletId()
                );

        if (wallet == null) {
            return false;
        }

        int amount = request.getAmount();

        if (amount > wallet.getBalance()) {
            return false;
        }

        int newBalance =
                wallet.getBalance() - amount;

        wallet.setBalance(newBalance);

        return true;
    }
}
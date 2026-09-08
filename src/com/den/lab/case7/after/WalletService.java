package com.den.lab.case7.after;

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

        return wallet.withdraw(
                request.getAmount()
        );
    }
}
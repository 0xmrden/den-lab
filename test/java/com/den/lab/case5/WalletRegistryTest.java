package com.den.lab.case5;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class WalletRegistryTest {

    //======= addWallet =======

    @Test
    void addWalletShouldUpdateAllDerivedStructures_whenWalletIsValid() {
        // arrange
        WalletRegistry registry = new WalletRegistry();
        Wallet wallet = new Wallet(1, "0xaaa", "ETHEREUM", 1000);

        // act
        registry.addWallet(wallet);

        // assert
        assertSame(wallet, registry.findWalletById(1));
        assertSame(wallet, registry.findWalletByAddress("0xaaa"));

        Map<String, List<Wallet>> walletsByNetwork =
                registry.getWalletsByNetwork();

        assertEquals(1, walletsByNetwork.size());
        assertTrue(walletsByNetwork.containsKey("ETHEREUM"));
        assertEquals(1, walletsByNetwork.get("ETHEREUM").size());
        assertSame(wallet, walletsByNetwork.get("ETHEREUM").get(0));

        List<Wallet> sortedWallets =
                registry.getWalletsSortedByBalanceDesc();

        assertEquals(1, sortedWallets.size());
        assertSame(wallet, sortedWallets.get(0));
    }

    @Test
    void addWalletShouldRejectDuplicateWalletId_withoutChangingSystemState() {
        // arrange
        WalletRegistry registry = new WalletRegistry();

        Wallet wallet1 =
                new Wallet(1, "0xaaa", "ETHEREUM", 1000);

        Wallet duplicateIdWallet =
                new Wallet(1, "0xbbb", "SOLANA", 3000);

        registry.addWallet(wallet1);

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            registry.addWallet(duplicateIdWallet);
        });

        // assert
        assertSame(wallet1, registry.findWalletById(1));
        assertNull(registry.findWalletByAddress("0xbbb"));

        Map<String, List<Wallet>> walletsByNetwork =
                registry.getWalletsByNetwork();

        assertEquals(1, walletsByNetwork.size());
        assertTrue(walletsByNetwork.containsKey("ETHEREUM"));
        assertFalse(walletsByNetwork.containsKey("SOLANA"));

        List<Wallet> sortedWallets =
                registry.getWalletsSortedByBalanceDesc();

        assertEquals(1, sortedWallets.size());
        assertSame(wallet1, sortedWallets.get(0));
    }

    @Test
    void addWalletShouldRejectDuplicateAddress_withoutChangingSystemState() {
        // arrange
        WalletRegistry registry = new WalletRegistry();

        Wallet wallet1 =
                new Wallet(1, "0xaaa", "ETHEREUM", 1000);

        Wallet duplicateAddressWallet =
                new Wallet(2, "0xaaa", "SOLANA", 3000);

        registry.addWallet(wallet1);

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            registry.addWallet(duplicateAddressWallet);
        });

        // assert
        assertNull(registry.findWalletById(2));
        assertSame(wallet1, registry.findWalletByAddress("0xaaa"));

        Map<String, List<Wallet>> walletsByNetwork =
                registry.getWalletsByNetwork();

        assertEquals(1, walletsByNetwork.size());
        assertTrue(walletsByNetwork.containsKey("ETHEREUM"));
        assertFalse(walletsByNetwork.containsKey("SOLANA"));

        List<Wallet> sortedWallets =
                registry.getWalletsSortedByBalanceDesc();

        assertEquals(1, sortedWallets.size());
        assertSame(wallet1, sortedWallets.get(0));
    }

    @Test
    void addWalletShouldRejectNullWallet_withoutChangingSystemState() {
        // arrange
        WalletRegistry registry = new WalletRegistry();

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            registry.addWallet(null);
        });

        // assert
        assertTrue(registry.getWalletsByNetwork().isEmpty());
        assertTrue(registry.getWalletsSortedByBalanceDesc().isEmpty());
    }

    //======= findWalletById =======

    @Test
    void findWalletByIdShouldReturnWallet_whenWalletExists() {
        // arrange
        WalletRegistry registry = new WalletRegistry();
        Wallet wallet = new Wallet(1, "0xaaa", "ETHEREUM", 1000);

        registry.addWallet(wallet);

        // act
        Wallet result = registry.findWalletById(1);

        // assert
        assertSame(wallet, result);
    }

    @Test
    void findWalletByIdShouldReturnNull_whenWalletDoesNotExist() {
        // arrange
        WalletRegistry registry = new WalletRegistry();

        // act
        Wallet result = registry.findWalletById(999);

        // assert
        assertNull(result);
    }

    //======= findWalletByAddress =======

    @Test
    void findWalletByAddressShouldReturnWallet_whenWalletExists() {
        // arrange
        WalletRegistry registry = new WalletRegistry();
        Wallet wallet = new Wallet(1, "0xaaa", "ETHEREUM", 1000);

        registry.addWallet(wallet);

        // act
        Wallet result = registry.findWalletByAddress("0xaaa");

        // assert
        assertSame(wallet, result);
    }

    @Test
    void findWalletByAddressShouldReturnNull_whenWalletDoesNotExist() {
        // arrange
        WalletRegistry registry = new WalletRegistry();

        // act
        Wallet result = registry.findWalletByAddress("0xmissing");

        // assert
        assertNull(result);
    }

    @Test
    void findWalletByAddressShouldThrowException_whenAddressIsNull() {
        // arrange
        WalletRegistry registry = new WalletRegistry();

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            registry.findWalletByAddress(null);
        });
    }

    @Test
    void findWalletByAddressShouldThrowException_whenAddressIsBlank() {
        // arrange
        WalletRegistry registry = new WalletRegistry();

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            registry.findWalletByAddress(" ");
        });
    }

    //======= getWalletsByNetwork =======

    @Test
    void getWalletsByNetworkShouldGroupWalletsByNetwork() {
        // arrange
        WalletRegistry registry = new WalletRegistry();

        Wallet wallet1 =
                new Wallet(1, "0xaaa", "ETHEREUM", 1000);

        Wallet wallet2 =
                new Wallet(2, "0xbbb", "SOLANA", 2000);

        Wallet wallet3 =
                new Wallet(3, "0xccc", "ETHEREUM", 3000);

        registry.addWallet(wallet1);
        registry.addWallet(wallet2);
        registry.addWallet(wallet3);

        // act
        Map<String, List<Wallet>> result =
                registry.getWalletsByNetwork();

        // assert
        assertEquals(2, result.size());

        assertEquals(2, result.get("ETHEREUM").size());
        assertTrue(result.get("ETHEREUM").contains(wallet1));
        assertTrue(result.get("ETHEREUM").contains(wallet3));

        assertEquals(1, result.get("SOLANA").size());
        assertTrue(result.get("SOLANA").contains(wallet2));
    }

    @Test
    void getWalletsByNetworkShouldReturnEmptyMap_whenRegistryIsEmpty() {
        // arrange
        WalletRegistry registry = new WalletRegistry();

        // act
        Map<String, List<Wallet>> result =
                registry.getWalletsByNetwork();

        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    void getWalletsByNetworkShouldNotExposeInternalGroups() {
        // arrange
        WalletRegistry registry = new WalletRegistry();

        Wallet wallet =
                new Wallet(1, "0xaaa", "ETHEREUM", 1000);

        registry.addWallet(wallet);

        Map<String, List<Wallet>> result =
                registry.getWalletsByNetwork();

        // act
        result.get("ETHEREUM").clear();

        Map<String, List<Wallet>> afterExternalChange =
                registry.getWalletsByNetwork();

        // assert
        assertEquals(1, afterExternalChange.get("ETHEREUM").size());
        assertSame(wallet, afterExternalChange.get("ETHEREUM").get(0));
    }

    //======= getWalletsSortedByBalanceDesc =======

    @Test
    void getWalletsSortedByBalanceDescShouldReturnWalletsFromHighestBalanceToLowest() {
        // arrange
        WalletRegistry registry = new WalletRegistry();

        Wallet wallet1 =
                new Wallet(1, "0xaaa", "ETHEREUM", 500);

        Wallet wallet2 =
                new Wallet(2, "0xbbb", "SOLANA", 3000);

        Wallet wallet3 =
                new Wallet(3, "0xccc", "BASE", 1000);

        registry.addWallet(wallet1);
        registry.addWallet(wallet2);
        registry.addWallet(wallet3);

        // act
        List<Wallet> result =
                registry.getWalletsSortedByBalanceDesc();

        // assert
        assertEquals(3, result.size());
        assertSame(wallet2, result.get(0));
        assertSame(wallet3, result.get(1));
        assertSame(wallet1, result.get(2));
    }

    @Test
    void getWalletsSortedByBalanceDescShouldReturnEmptyList_whenRegistryIsEmpty() {
        // arrange
        WalletRegistry registry = new WalletRegistry();

        // act
        List<Wallet> result =
                registry.getWalletsSortedByBalanceDesc();

        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    void getWalletsSortedByBalanceDescShouldNotExposeInternalSortedList() {
        // arrange
        WalletRegistry registry = new WalletRegistry();

        Wallet wallet =
                new Wallet(1, "0xaaa", "ETHEREUM", 1000);

        registry.addWallet(wallet);

        List<Wallet> result =
                registry.getWalletsSortedByBalanceDesc();

        // act
        result.clear();

        List<Wallet> afterExternalChange =
                registry.getWalletsSortedByBalanceDesc();

        // assert
        assertEquals(1, afterExternalChange.size());
        assertSame(wallet, afterExternalChange.get(0));
    }
}
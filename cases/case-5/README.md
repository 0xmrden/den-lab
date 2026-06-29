# Case 5 — Keeping Derived State Synchronized

## Main Idea

This case demonstrates how a system keeps multiple derived structures synchronized with a single source of truth.

The focus is not on Java collections themselves, but on maintaining consistent system state across multiple representations of the same data.

## Model

The case uses two classes:

* Wallet
* WalletRegistry

`WalletRegistry` stores wallets as the source of truth and maintains several derived structures optimized for different tasks.

## Source of Truth

The primary system state is stored in:

* `wallets`

All other collections are derived from this list.

## Derived Structures

The system maintains:

* `walletsById` — fast lookup by wallet ID
* `walletAddresses` — duplicate address protection
* `walletsByNetwork` — prepared grouping by network
* `walletsSortedByBalanceDesc` — prepared balance ordering

Each successful update keeps all derived structures synchronized.

## State Protection

Before modifying the system, the registry validates:

* null wallet
* duplicate wallet ID
* duplicate wallet address

If validation fails, no derived structure is modified.

This prevents partial state updates.

## Tested Scenarios

The tests verify:

* successful synchronization of all derived structures
* lookup by wallet ID
* lookup by wallet address
* grouping by network
* sorting by balance
* duplicate wallet ID rejection
* duplicate address rejection
* null wallet rejection
* state consistency after rejected operations
* protection against external modification of derived collections

## Engineering Conclusion

A collection is not only a data structure.

When multiple derived structures exist, they become part of the system state.

The primary engineering responsibility is to keep every derived structure synchronized with the source of truth while preventing partial state updates.

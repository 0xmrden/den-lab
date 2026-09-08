# Case 7 — Behavior-Preserving Architecture Refactoring

## Overview

This case demonstrates an architectural refactoring
that changes the distribution of responsibilities
without changing already confirmed system behavior.

The system implements a small withdrawal scenario
using four components:

- `WithdrawRequest`
- `WalletService`
- `WalletRepository`
- `Wallet`

The initial implementation works correctly,
but part of the domain responsibility
is located in the wrong component.

The refactoring moves that responsibility
while preserving the same confirmed
successful and refusal scenarios.

---

## Engineering Problem

Before refactoring, `WalletService`
does more than coordinate the withdrawal scenario.

It:

- retrieves the `Wallet`;
- checks whether the withdrawal is allowed;
- calculates the new balance;
- directly changes the `Wallet` state.

This means the service knows both
how the scenario is coordinated
and how the internal state of the domain object
must be changed.

Meanwhile, `Wallet` mainly stores state
and exposes `setBalance()`.

The system works,
but the responsibilities are poorly distributed.

---

## Before Refactoring

The scenario follows this flow:

```text
WithdrawRequest
      ↓
WalletService
      ↓
WalletRepository
      ↓
Wallet
```

Responsibility is distributed as follows:

```text
WalletService
├── coordinates the scenario
├── retrieves Wallet
├── checks the withdrawal rule
├── calculates the new balance
└── mutates Wallet state

Wallet
└── stores state
```

The critical part of the implementation is:

```text
if (amount > wallet.getBalance()) {
    return false;
}

int newBalance =
        wallet.getBalance() - amount;

wallet.setBalance(newBalance);
```

The service therefore depends on knowledge
of how `Wallet` state is validated,
calculated, and changed.

---

## Refactoring Decision

The existing withdrawal responsibility
is moved from `WalletService`
into `Wallet`.

No new business rule is introduced.

The existing withdrawal rule remains:

```text
a withdrawal is refused
when the requested amount exceeds
the current balance.
```

A rejected withdrawal must still leave
the existing balance unchanged.

The purpose of the refactoring
is therefore not to change what the system does,
but to change which component owns
the existing responsibility.

---

## After Refactoring

`Wallet` now owns the withdrawal operation:

```text
public boolean withdraw(int amount) {
    if (amount > balance) {
        return false;
    }

    balance -= amount;

    return true;
}
```

`WalletService` delegates the domain operation:

```text
return wallet.withdraw(
        request.getAmount()
);
```

The resulting responsibility distribution is:

```text
WithdrawRequest
└── carries input data

WalletService
└── coordinates the scenario

WalletRepository
└── stores and retrieves Wallet

Wallet
├── owns its state
├── checks the withdrawal rule
├── calculates the resulting balance
└── changes its own state
```

`WithdrawRequest` and `WalletRepository`
do not require architectural changes
because their responsibilities
were already appropriate for this scenario.

---

## Behavior Evidence

The refactoring is verified
against the same confirmed scenarios.

### Successful Withdrawal

Initial state:

```text
balance = 100
amount = 40
```

Before refactoring:

```text
result  = true
balance = 60
```

After refactoring:

```text
result  = true
balance = 60
```

### Refused Withdrawal

Initial state:

```text
balance = 100
amount = 150
```

Before refactoring:

```text
result  = false
balance = 100
```

After refactoring:

```text
result  = false
balance = 100
```

The refusal scenario is particularly important:
the rejected operation does not damage
the existing `Wallet` state.

---

## Testing

The artifact contains separate tests
for the before and after implementations.

```text
WalletServiceBeforeTest
├── successful withdrawal
└── refused withdrawal preserves balance

WalletServiceAfterTest
├── successful withdrawal
└── refused withdrawal preserves balance
```

Execution result:

```text
BEFORE: 2 tests passed
AFTER:  2 tests passed
```

The tests provide evidence that
the confirmed observable behavior
remains unchanged after refactoring.

---

## What Changed

```text
BEFORE

WalletService
→ coordination
→ business rule
→ balance calculation
→ state mutation


AFTER

WalletService
→ coordination

Wallet
→ business rule
→ balance calculation
→ state mutation
```

The architecture changed.

The confirmed behavior did not.

---

## Scope

This case intentionally does not introduce:

- frameworks;
- databases;
- REST APIs;
- additional architectural layers;
- new business rules;
- additional abstractions;
- complex design patterns.

The purpose is to isolate one engineering decision:

moving an existing responsibility
to the component that owns the state
affected by that responsibility.

---

## Engineering Conclusion

A working system can still have
poorly distributed responsibilities.

Refactoring does not always mean
changing behavior or adding capabilities.

In this case, the important change
is architectural:

`WalletService` stops knowing
how `Wallet` validates, calculates,
and changes its balance.

`Wallet` becomes responsible
for its own state and the existing rule
that controls its modification.

The tests then provide evidence
that this structural change preserved
the already confirmed observable behavior.

**Architecture changed. Behavior did not.**
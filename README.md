# den-lab

# Account Domain Model — Java Backend

## Overview

This project demonstrates a basic domain model for managing account balance with clearly defined behavior and state protection.

Focus:
— invariant protection (balance ≥ 0)
— method contracts
— fail fast / fail safely behavior
— scenario-based testing
— state consistency after errors

---

## Domain Model

Entity:
— `Account`

State:
— `balance`

Invariant:
— balance must never be negative

---

## Implemented Operations

### deposit(amount)

— throws exception when amount ≤ 0
— increases balance only for valid input

### withdraw(amount)

— decreases balance for valid input
— throws exception when amount < 0
— does nothing when:
— amount == 0
— amount > balance

### transfer(target, amount)

— transfers balance between accounts
— throws exception when target is null
— throws exception when amount ≤ 0
— does nothing when amount exceeds sender balance
— preserves invariants on both accounts

---

## Behavior Model

Each method is designed through scenarios:

— normal scenario
— edge scenario
— error scenario

Failed operations must not break object state.

---

## Testing Approach

Tests validate behavior through state:

— `assertEquals` → state verification
— `assertThrows` → exception verification

Each method is covered by:
— valid operation
— boundary behavior
— invalid input
— state consistency after failure

---

## Project Structure

src/
— Account.java
— Main.java

test/
— AccountTest.java

cases/
— portfolio-ready engineering cases

practice/
— local sandbox, not part of final implementation

---

## Engineering Focus

This project demonstrates:

— thinking in terms of domain behavior
— protecting system invariants
— designing predictable method contracts
— choosing fail fast or fail safely intentionally
— writing tests for normal, edge, and error scenarios

Focus is on correctness and clarity, not complexity.
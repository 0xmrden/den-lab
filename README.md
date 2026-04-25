# den-lab

# Account Domain Model — Java Backend

## Overview

This project demonstrates a basic domain model for managing account balance with clearly defined behavior and state protection.

Focus:
— invariant protection (balance ≥ 0)
— method contracts
— fail-safe behavior
— scenario-based testing

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

— increases balance
— ignores invalid input (amount ≤ 0)
— throws exception if amount is too large

### withdraw(amount)

— decreases balance
— does nothing if:
— amount ≤ 0
— amount > balance

### transfer(target, amount)

— transfers balance between accounts
— validates:
— target is not null
— amount is valid
— preserves invariants on both accounts

---

## Behavior Model

Each method is designed through scenarios:

— normal (valid operation)
— edge (boundary conditions)
— error (invalid input, no state change)

---

## Testing Approach

Tests validate behavior through state:

— assertEquals → state verification
— assertThrows → critical failures

Each method is covered by:
— normal scenario
— edge cases
— error scenarios

---

## Project Structure

src/
— Account.java
— Main.java

test/
— AccountTest.java

practice/
— local sandbox (not part of final implementation)

---

## Engineering Focus

This project demonstrates:

— thinking in terms of domain behavior
— protecting system invariants
— designing predictable methods
— writing reliable tests

Focus is on correctness and clarity, not complexity.

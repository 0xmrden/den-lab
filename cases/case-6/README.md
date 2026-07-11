# Case 6 — Protecting Domain Contracts Through Type-Safe Modeling

## Overview

Modern backend systems are protected not only by validation logic but also by correct domain modeling.

This case demonstrates how carefully selected data types become part of the domain contract by expressing business meaning, restricting invalid states, preserving numerical correctness, and reducing incorrect usage before business logic even executes.

---

## Engineering Focus

This artifact is intentionally limited to domain modeling.

It does not include persistence, REST APIs, frameworks, databases, or infrastructure.

The goal is to demonstrate how correct type selection strengthens domain contracts before higher architectural layers are introduced.

---

## Main Idea

The purpose of this case is not to demonstrate individual Java types.

The goal is to show how a domain model becomes safer when each type is chosen according to the meaning of the represented data.

The type system itself communicates business intent, restricts invalid states, and makes contracts explicit before runtime behavior is executed.

---

## Model

The artifact contains four production types:

* `Wallet`
* `WalletStatus`
* `WalletRegistry`
* `Deposit`

`Wallet` is the central domain model.

`WalletRegistry` manages wallet registration, protects wallet identity uniqueness, provides safe lookup through `Optional`, and protects the transaction counter from numeric overflow.

`Deposit` is a separate financial model demonstrating constructor-enforced invariants.

---

## Type-Safe Modeling

| Domain Concept      | Type                      | Engineering Reason                           |
| ------------------- | ------------------------- | -------------------------------------------- |
| Wallet identity     | `String`                  | External business identifier                 |
| Monetary values     | `BigDecimal`              | Exact decimal arithmetic                     |
| Verification        | `boolean`                 | Independent binary property                  |
| Wallet lifecycle    | `WalletStatus`            | Mutually exclusive states                    |
| Creation date       | `LocalDate`               | Calendar date without time                   |
| Last activity       | `Optional<LocalDateTime>` | Activity may legitimately be absent          |
| Transaction counter | `long`                    | Efficient counter protected against overflow |

Each type is selected according to the meaning and risks of the represented business data.

---

## Domain Contracts

### Wallet guarantees

* wallet ID cannot be null or blank
* creation date cannot be null
* balance starts at zero
* deposits must be greater than zero
* decimal operations preserve monetary precision
* new wallets start as **ACTIVE**
* new wallets are not verified
* closed wallets cannot be frozen
* last activity may legitimately be absent
* null activity timestamps are rejected
* rejected operations never modify existing state

### WalletRegistry guarantees

* wallet IDs remain unique
* missing wallets return `Optional.empty()`
* invalid lookup requests are rejected
* transaction counter cannot overflow
* rejected operations never replace existing wallets
* rejected operations never modify the transaction counter

### Deposit guarantees

* deposit identity is mandatory
* monetary values must be positive
* reward percentage must be positive
* unlock date is mandatory
* invalid financial objects cannot be created

---

## State Protection

Validation is always performed before state modification.

If an operation is rejected:

* wallet balance remains unchanged
* wallet lifecycle remains unchanged
* wallet activity remains unchanged
* registered wallets remain unchanged
* transaction counter remains unchanged

Failure behavior is therefore part of the domain contract.

---

## Testing

The artifact contains **40 passing tests**.

* **17** Wallet tests
* **13** WalletRegistry tests
* **10** Deposit tests

Every contract described in this document is verified through executable tests.

The test suite covers:

* normal scenarios
* boundary scenarios
* error scenarios
* refusal scenarios
* constructor validation
* decimal precision
* lifecycle transitions
* optional behavior
* duplicate identity protection
* overflow protection
* state consistency after rejected operations

---

## Engineering Conclusion

A domain model is more than a collection of fields.

Well-chosen types reduce ambiguity, eliminate impossible states, preserve numerical correctness, improve readability, and make business contracts explicit.

Validation protects object creation and state transitions, while automated tests verify that these guarantees remain true over time.

Correct type selection is therefore not only a language decision—it is part of engineering the behavior and reliability of the system.

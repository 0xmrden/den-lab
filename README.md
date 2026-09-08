# Java Backend Engineering Lab

## Overview

This repository contains a sequence of small Java engineering cases.

Each case focuses on a specific problem in system behavior,
state management, domain modeling,
or responsibility distribution.

The repository shows how these problems are analyzed,
implemented, and verified with automated tests.

Earlier cases are intentionally preserved
to make the progression visible over time.

---

## Engineering Focus

The cases currently cover:

- operation and state protection;
- predictable error and refusal behavior;
- collection safety;
- derived state synchronization;
- domain modeling;
- type-safe modeling;
- separation of responsibilities;
- behavior-preserving refactoring;
- scenario-based automated testing.

The emphasis is on clear behavior
and evidence through tests,
not on architectural complexity.

---

## Repository Structure

```text
src/
    production implementation

test/
    automated tests

cases/
    documentation for each engineering case

practice/
    local experiments
    not part of the published cases
```

---

## Engineering Progression

The cases are ordered to show
a gradual progression in engineering scope.

Early cases focus on individual operations
and protecting object state.

Later cases expand toward:

- safe use of collections;
- synchronization of related state;
- stronger domain modeling;
- type-safe contracts;
- responsibility distribution between components;
- refactoring without changing confirmed behavior.

Each case remains independently understandable.

Earlier implementations are intentionally left unchanged
instead of being rewritten to match later approaches.

---

## Engineering Cases

### Case 1 — Withdraw Protection

Protecting account state
during rejected withdrawal operations.

### Case 2 — Transfer Safety

Protecting sender and receiver state
during transfer operations.

### Case 3 — Error Handling Contract

Defining predictable method behavior
for invalid and rejected operations.

### Case 4 — Protecting System State Through Collections

Protecting internal state through:

- controlled collections;
- immutable access;
- duplicate protection;
- object copy isolation.

### Case 5 — Keeping Derived State Synchronized

Keeping multiple derived structures synchronized
with a single source of truth
while preserving consistent state.

### Case 6 — Protecting Domain Contracts Through Type-Safe Modeling

Using data types as part of the domain model to:

- express business meaning;
- restrict invalid states;
- preserve numerical correctness;
- make behavior more explicit.

### Case 7 — Behavior-Preserving Architecture Refactoring

Moving withdrawal responsibility
from `WalletService` into `Wallet`
while preserving the confirmed behavior.

The case demonstrates:

- separation of scenario coordination from domain logic;
- ownership of state changes by the object that holds the state;
- targeted architectural refactoring;
- tests before and after the refactoring.

---

## Testing Approach

The repository uses automated,
scenario-based tests.

Across the cases, tests cover scenarios such as:

- successful operations;
- rejected operations;
- invalid input;
- boundary conditions;
- state preservation after rejection.

The tests focus primarily on observable behavior
rather than internal implementation details.

---

## Repository Evolution

The repository preserves earlier cases
instead of continuously rewriting them.

Beginning with Case 5,
larger artifacts are placed
in dedicated packages.

This keeps newer cases isolated
while preserving earlier implementations
as evidence of the learning progression.

---

## Scope

This repository currently focuses on:

- Java domain logic;
- system behavior;
- state consistency;
- engineering reasoning;
- automated verification.
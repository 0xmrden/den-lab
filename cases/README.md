# Cases

## Engineering Cases — Domain Modeling

## Overview

This section contains practical cases that demonstrate
how domain logic is designed, analyzed, and corrected.

Each case represents a real engineering problem encountered
during development and explains the reasoning behind its solution.

---

## Purpose

The goal of these cases is to demonstrate:

- understanding of domain behavior;
- ability to identify incorrect logic;
- ability to protect system invariants;
- reasoning through cause and effect.

---

## Engineering Progression

The cases are intentionally ordered to demonstrate
the gradual development of engineering thinking.

Early cases focus on protecting individual operations
and object state.

Later cases expand toward system behavior,
state synchronization,
and domain modeling,
where correctness is increasingly enforced through:

- architecture;
- engineering contracts;
- carefully selected data types.

---

## Structure of a Case

Each case follows the same structure:

- Problem → what was incorrect;
- What was wrong → analysis of the issue;
- Solution → how it was fixed;
- Result → what is guaranteed now;
- Tests → how behavior is validated.

---

## Cases

### Case 1 — Withdraw Protection

Preventing invalid withdraw operations
and protecting account state.

### Case 2 — Transfer Safety

Preventing unsafe transfer behavior
and protecting both sender and target accounts.

### Case 3 — Error Handling Contract

Defining method behavior for invalid input,
unsafe operations,
and state consistency after failure.

### Case 4 — Protecting System State Through Collections

Demonstrating how a system protects its internal state through:

- controlled collections;
- immutable access;
- duplicate protection;
- object copy isolation.

### Case 5 — Keeping Derived State Synchronized

Demonstrating how a system keeps multiple derived structures
synchronized with a single source of truth while guaranteeing:

- consistent state after successful updates;
- unchanged state after rejected operations.

### Case 6 — Protecting Domain Contracts Through Type-Safe Modeling

Demonstrating how carefully selected data types
become part of the domain contract by:

- expressing business meaning;
- restricting invalid states;
- preserving numerical correctness;
- making system behavior explicit
  before business logic executes.

### Case 7 — Behavior-Preserving Architecture Refactoring

Demonstrating how architectural responsibility
can be moved to a more appropriate component
without changing already confirmed system behavior.

The case focuses on:

- separating coordination from domain logic;
- moving state mutation into the domain object;
- preserving successful and refusal scenarios;
- using tests as evidence that behavior remains unchanged.
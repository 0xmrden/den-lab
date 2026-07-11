# den-lab

# Java Backend Engineering Lab

## Overview

This repository documents the gradual evolution of backend engineering thinking
through practical engineering artifacts.

Rather than presenting isolated programming exercises,
each artifact demonstrates how business behavior is analyzed,
modeled, implemented, and verified through automated tests.

The focus is on building predictable systems whose behavior is protected by
engineering contracts, validation, and executable tests.

---

## Engineering Focus

The repository demonstrates:

- domain modeling;
- engineering contracts;
- invariant protection;
- fail-fast / fail-safe behavior;
- state consistency;
- collection safety;
- derived state synchronization;
- type-safe domain modeling;
- scenario-based automated testing.

The goal is correctness, predictability,
and clarity rather than architectural complexity.

---

## Repository Structure

```text
src/
    production implementation

test/
    automated tests

cases/
    engineering case documentation

practice/
    local experiments
    not part of the published artifacts
```

---

## Engineering Progression

The repository is intentionally organized
as a sequence of engineering cases.

Early cases focus on protecting
individual operations and object state.

Later cases gradually expand toward:

- collection safety;
- derived state synchronization;
- domain modeling;
- type-safe business contracts.

Each new artifact builds upon ideas introduced
in previous cases while remaining independently understandable.

Earlier artifacts intentionally remain unchanged
to preserve evidence of engineering progression.

---

## Engineering Cases

### Case 1 — Withdraw Protection

Protecting account state during withdrawal operations.

### Case 2 — Transfer Safety

Protecting sender and receiver consistency during transfers.

### Case 3 — Error Handling Contract

Designing predictable method behavior
for invalid operations.

### Case 4 — Protecting System State Through Collections

Protecting internal system state through:

- controlled collections;
- immutable access;
- duplicate protection;
- object copy isolation.

### Case 5 — Keeping Derived State Synchronized

Synchronizing multiple derived structures
with a single source of truth while preserving
consistent system state.

### Case 6 — Protecting Domain Contracts Through Type-Safe Modeling

Strengthening domain contracts through carefully selected data types that:

- express business meaning;
- restrict invalid states;
- preserve numerical correctness;
- make system behavior explicit.

---

## Testing Approach

The repository uses scenario-based testing.

Each engineering contract is verified
through executable tests covering:

- normal scenarios;
- boundary scenarios;
- error scenarios;
- refusal scenarios;
- state consistency after rejected operations.

The tests verify observable behavior
rather than implementation details.

---

## Repository Evolution

Early cases intentionally remain unchanged.

Beginning with Case 5,
larger artifacts are implemented
in isolated packages to improve scalability
while preserving the original learning history.

The repository therefore documents
engineering progression
rather than continuous refactoring.

---

## Scope

The repository intentionally focuses on:

- domain modeling;
- engineering reasoning;
- predictable system behavior.

Frameworks, databases, REST APIs,
and infrastructure are introduced separately
as the learning roadmap progresses.
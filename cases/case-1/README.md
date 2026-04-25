# Case 1 — Withdraw Protection

## Problem

The `withdraw` method allowed invalid operations that could break the account state.

In particular:
— it did not properly prevent withdrawing more than the available balance
— it could lead to incorrect or unpredictable behavior

---

## What was wrong

The method lacked proper validation for input conditions.

There was no clear rule defining:
— which inputs are valid
— how the system should behave when inputs are invalid

As a result, the method did not reliably protect the domain invariant.

---

## Solution

Introduced guard clauses to validate input before applying any state changes:

— reject non-positive amounts (`amount ≤ 0`)
— reject amounts greater than the current balance

The method now follows a fail-safe approach:
it exits early without modifying state when input is invalid.

---

## Result

The account state is now protected:

— balance never becomes negative
— invalid operations do not affect the system
— behavior is predictable and consistent

---

## Tests

Behavior is validated through state-based tests:

— valid withdraw decreases balance
— withdrawing the full balance results in zero
— withdrawing more than balance does not change state
— zero and negative amounts are ignored

---

## Key Takeaway

Protecting the invariant is more important than executing the operation.

A method must define:
— what is allowed
— what is rejected
— what is guaranteed after execution

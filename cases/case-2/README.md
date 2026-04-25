# Case 2 — Transfer Safety

## Problem

The `transfer` operation involves two accounts and must keep both in a consistent state.

Without proper validation and execution order, a transfer could:
— fail partially
— update one account but not the other
— lead to inconsistent system state

---

## What was wrong

A transfer is not a simple operation.

It combines multiple steps:
— withdrawing from one account
— depositing into another

Without clear rules:
— invalid input (null target, invalid amount) could break the logic
— incorrect execution order could lead to partial updates

---

## Solution

Introduced strict validation and controlled execution flow:

— reject null target account
— reject invalid amounts (`amount ≤ 0`)
— ensure withdrawal happens before deposit

The method relies on existing domain operations:
— `withdraw` for source account
— `deposit` for target account

This ensures reuse of validated logic and consistent behavior.

---

## Result

The system now guarantees:

— no partial transfers
— both accounts remain consistent
— invalid operations do not change state
— balance invariants are preserved

---

## Tests

Behavior is validated through state-based testing:

— valid transfer moves balance between accounts
— transfer with null target does nothing
— transfer with invalid amount does nothing
— transfer greater than balance does not change state

---

## Key Takeaway

Operations involving multiple objects must ensure consistency across all participants.

A safe transfer:
— validates all inputs
— executes in the correct order
— reuses existing domain logic

Correct orchestration is as important as correct individual methods.

## Related Code

See implementation in:
— src/com/den/lab/Account.java

See tests in:
— test/java/com/den/lab/AccountTest.java
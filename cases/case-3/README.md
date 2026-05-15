# Case 3 — Error Handling Contract

## Goal

This case demonstrates how method behavior is defined and protected when invalid or unsafe input appears.

Focus:
- fail fast for invalid input
- fail safely for impossible operations
- invariant protection
- negative tests
- state consistency after errors

## Methods shown

- `deposit` — rejects invalid input with `IllegalArgumentException`
- `withdraw` — ignores impossible operations without changing state
- `transfer` — prevents null target and avoids partial state changes

## Error-handling rules

- invalid input breaks the method contract and should fail fast
- impossible business operation should fail safely
- failed operation must not change object state
- money balance must never become negative
- transfer must not partially execute

## Tests included

The tests verify:
- normal scenarios
- edge scenarios
- negative scenarios
- exception handling
- invariant protection
- state consistency after failure

## Source code

Core implementation:
- `src/com/den/lab/Account.java`

Tests:
- `test/java/com/den/lab/AccountTest.java`
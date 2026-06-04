# Case 4 — Protecting System State Through Collections

## Main idea

This case is not about using a `List`.

It is about protecting system state through a collection of objects.

A collection inside a system is not just a container.
It is part of the system state.

If external code can change the collection or the objects inside it,
the system loses control over its own state.

## Model

The case uses two simple classes:

- `WalletSystem`
- `PracticeAccount`

`WalletSystem` stores multiple accounts and controls how they can be added,
read, filtered, and exposed to external code.

## What the system protects

The system protects:

- the collection itself
- objects inside the collection
- duplicate account insertion
- invalid account insertion
- read-only access to the collection
- internal state from external mutation

## Implemented behavior

`WalletSystem` supports:

- adding an account
- rejecting `null` accounts
- ignoring duplicate accounts
- checking whether accounts exist
- checking whether a specific account exists
- counting accounts
- filtering accounts by balance
- calculating total balance
- returning accounts without exposing internal mutable state

## State protection

The system does not return the original internal list.

It also does not return original account objects from the internal collection.

Instead, it returns copies.

This protects the system from external code that could otherwise change account state
without going through the rules of the domain model.

## Tested scenarios

The tests cover:

- normal scenarios
- empty system behavior
- duplicate protection
- null account rejection
- filtering behavior
- boundary filtering
- aggregation
- immutable collection access
- object copy protection

## Engineering conclusion

Protecting a collection is not enough.

If objects inside the collection can still be changed from the outside,
the system state is still exposed.

A system must protect not only the collection,
but also the objects inside it.
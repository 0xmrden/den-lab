# Case 1 — Withdraw Protection

## Problem
Method allowed invalid operations that could break balance logic.

## Solution
Added guard clauses to prevent invalid input and protect invariants.

## What was done
— blocked negative and zero amounts  
— prevented overdraft  
— ensured method fails safely

## Result
Balance remains valid under all scenarios.

## Tests
Covers:
— normal case  
— edge cases  
— invalid input  
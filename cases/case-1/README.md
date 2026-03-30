Case 1 — Withdraw Protection

Problem
Method allowed invalid operations that could break balance logic:
— allows negative balance
— no check for insufficient funds

Solution
Added guard clauses to prevent invalid input and protect invariants.

What was done
— blocked negative and zero amounts
— prevented overdraft
— ensured method fails safely

Result
Balance remains valid under all scenarios.

Tests
Covers:
— normal case
— edge cases
— invalid input

Buggy Code

public void withdraw(int amount) {
if (amount <= 0) return;
balance -= amount;
}

Fixed Code

public void withdraw(int amount) {
if (amount <= 0) return;
if (amount > balance) return;
balance -= amount;
}

Tests (example)

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountTest {
@Test  
void withdrawShouldDecreaseBalance() {  
Account account = new Account(100);

    account.withdraw(30);  

    assertEquals(70, account.getBalance());  
}

@Test  
void withdrawShouldNotChangeBalanceWhenAmountExceedsBalance() {  
Account account = new Account(100);

    account.withdraw(200);  

    assertEquals(100, account.getBalance());  
}

@Test  
void withdrawShouldNotChangeBalanceWhenAmountIsNegative() {  
Account account = new Account(100);

    account.withdraw(-20);  

    assertEquals(100, account.getBalance());  
}
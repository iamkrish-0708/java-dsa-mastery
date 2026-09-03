/*
====================================================================================
📚 JAVA OOPs CHEAT SHEET & LECTURE 18 NOTES: ENCAPSULATION & ABSTRACTION
====================================================================================

1. ENCAPSULATION (1st Pillar of OOP):
 -------------------------------------
- Definition: Bundling data (variables) and behavior (methods) into a single unit
  (Class) while restricting direct outside access to data fields.
- Fully Encapsulated Class: A class where ALL data members are marked 'private'.

2. ACCESS RULES FOR 'private':
 ------------------------------
- 'private' means CLASS-LEVEL access, NOT object-level.
- An object of Class A can access private fields/methods of ANOTHER object of Class A
  inside methods written within Class A (e.g., this.accBalance & targetAccount.accBalance).

3. SPECIAL ENCAPSULATION VARIANTS:
 -----------------------------------
- Read-Only Class:
    * Has ONLY Getter methods (no Setters).
    * Data initialized once via Constructor; immutable thereafter.
    * Example: Student ID Card, Aadhar Card Number.

- Write-Only Class:
    * Has ONLY Setter methods (no Getters).
    * Used for sensitive input where data should not be readable.
    * Example: Password update service (`setPassword("newPass")`).

4. ENCAPSULATION vs. ABSTRACTION:
 ---------------------------------
- Encapsulation = DATA HIDING (Hiding internal variables using 'private' + Setters).
  Analogy: Locking the car engine under the hood.

- Abstraction   = IMPLEMENTATION HIDING (Exposing clean public methods while hiding
  complex step-by-step logic inside).
  Analogy: Pressing the Brake Pedal without worrying about
  hydraulic fluid mechanics.
  Example: `acc1.transferfunds(acc2, 1500);`
  ====================================================================================
  */
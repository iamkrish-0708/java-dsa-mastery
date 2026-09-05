# ⚡ Bitwise Operators in Java — Master Cheatsheet & DSA Hacks

---

## 📌 1. What are Bitwise Operators & Why Do We Use Them?

Bitwise operators perform direct operations on the **individual binary bits (0s and 1s)** of integers.

### 🌟 Why Bitwise Operations are Superior in DSA:
1. **Blazing Fast Speed:** Bitwise instructions run in **1 CPU clock cycle** directly inside the Arithmetic Logic Unit (ALU), making them faster than multiplication (`*`), division (`/`), or modulo (`%`).
2. **Zero Extra Memory:** Solves complex problems in $\mathcal{O}(1)$ auxiliary space without creating hash tables or arrays.
3. **Hardware-Level Precision:** Essential for cryptography, low-level systems, gaming engines, and competitive programming.

---

## 🧮 2. The 7 Core Bitwise Operators

| Operator | Name | Rule / Behavior | Truth Table / Formula |
| :---: | :--- | :--- | :--- |
| **`&`** | **Bitwise AND** | Yields `1` **only if both** bits are `1` | `1 & 1 = 1`, `1 & 0 = 0`, `0 & 0 = 0` |
| **`\|`** | **Bitwise OR** | Yields `1` **if at least one** bit is `1` (Yields `0` only when both are `0`) | `1 \| 0 = 1`, `1 \| 1 = 1`, `0 \| 0 = 0` |
| **`^`** | **Bitwise XOR** | Yields `1` **if inputs differ**, `0` if inputs are the same | `1 ^ 0 = 1`, `0 ^ 1 = 1`, `1 ^ 1 = 0`, `0 ^ 0 = 0` |
| **`~`** | **Bitwise NOT** | Inverts all bits (`0` $\rightarrow$ `1`, `1` $\rightarrow$ `0`) | Formula: `~x = -(x + 1)` (e.g. `~5 = -6`) |
| **`<<`** | **Left Shift** | Shifts bits to the left, fills right with `0`s | Multiplies by powers of 2: $\mathbf{N \times 2^i}$ |
| **`>>`** | **Signed Right Shift** | Shifts bits to the right, preserves sign bit | Divides by powers of 2: $\mathbf{\lfloor N / 2^i \rfloor}$ |
| **`>>>`**| **Unsigned Right Shift**| Shifts bits to the right, always fills left with `0`s | Ignores sign bit; produces large positive numbers for negative inputs |

---

## 🔬 3. Deep Dive: Bit Shift Mechanics in 32-Bit Java

In Java, an `int` occupies **4 bytes (32 bits)** in memory using **2's Complement representation**.

```text
Bit Position:  31  30  29  ...  2   1   0
             ┌───┬───┬───┬───┬───┬───┬───┐
             │ S │   │   │...│   │   │   │
             └───┴───┴───┴───┴───┴───┴───┘
               ▲
               └── Sign Bit (0 = Positive, 1 = Negative)
```

### A. Left Shift (`<< i`) — Multiplication by $2^i$
* `5 << 1` $\rightarrow$ `00000101` shifted left by 1 $\rightarrow$ `00001010` = **10** ($5 \times 2^1$)
* `5 << 2` $\rightarrow$ `00000101` shifted left by 2 $\rightarrow$ `00010100` = **20** ($5 \times 2^2$)
* ⚠️ **Sign-Bit Overflow Note:** If you shift a bit into the 31st position (MSB), the number becomes **negative** because the sign bit becomes `1`. Shifting beyond 32 bits shifts all 1s out, leaving `0`.

### B. Signed Right Shift (`>> i`) — Division by $2^i$
* `20 >> 2` $\rightarrow$ `00010100` shifted right by 2 $\rightarrow$ `00000101` = **5** ($\lfloor 20 / 4 \rfloor$)
* **Sign Preservation:** For positive numbers, it fills the left gaps with `0`. For negative numbers, it fills with `1` to preserve the negative sign.

### C. Unsigned Right Shift (`>>> i`) — Logical Shift
* Shifts bits to the right and **always fills left gaps with `0`**, regardless of whether the original number was positive or negative.
* ` -1 >>> 1 ` turns `11111111...1111` into `01111111...1111` = **`2147483647` (`Integer.MAX_VALUE`)**!

---

## ⚡ 4. The 5 Golden DSA & LeetCode Bit Hacks

### 🔹 Hack 1: Lightning-Fast Even / Odd Check
In binary, every even number ends with `0`, and every odd number ends with `1`.
```java
if ((n & 1) == 0) {
    // Number is EVEN (e.g. 6 -> 110 & 001 = 0)
} else {
    // Number is ODD  (e.g. 7 -> 111 & 001 = 1)
}
```
> 💡 **Why use this:** $10\times$ faster than `n % 2 != 0` in tight loops.

---

### 🔹 Hack 2: Check If a Number is a Power of Two (Brian Kernighan)
Powers of 2 have exactly **one set bit** (`1, 2, 4, 8, 16` $\rightarrow$ `10000`).  
Subtracting 1 flips all bits up to the last set bit (`16 - 1 = 15` $\rightarrow$ `01111`).
```java
// If n is a power of 2, n & (n - 1) is always 0
boolean isPowerOfTwo = (n > 0) && ((n & (n - 1)) == 0);
```

---

### 🔹 Hack 3: Swap Two Numbers Without a Temporary Variable
Using XOR properties: $x \oplus x = 0$ and $x \oplus 0 = x$.
```java
int a = 5, b = 6;

a = a ^ b; // a now holds (a ^ b)
b = a ^ b; // b = (a ^ b) ^ b = a (b is now 5!)
a = a ^ b; // a = (a ^ b) ^ a = b (a is now 6!)
```

---

### 🔹 Hack 4: Isolate the Lowest (Rightmost) Set Bit
```java
int lowestSetBit = n & (-n);
// Example: n = 12 (1100) -> -n in 2's complement is (0100) -> 12 & -12 = 4 (0100)
```

---

### 🔹 Hack 5: Find the Single Non-Repeating Element (LeetCode #136)
When all elements appear twice except one, XORing all numbers cancels out the pairs ($X \oplus X = 0$), leaving only the unique number!
```java
int[] nums = {4, 1, 2, 1, 2};
int unique = 0;
for (int num : nums) {
    unique ^= num;
}
// unique = 4! Time: O(N), Space: O(1)
```

---

## 🧠 5. Bitwise Truth & Property Summary

* **Identity Properties:**
  * $x \ \& \ 1 = x \text{ (LSB)}$, \quad $x \ \& \ 0 = 0$
  * $x \ | \ 0 = x$, \quad $x \ | \ 1 = \text{sets LSB to 1}$
  * $x \oplus 0 = x$, \quad $x \oplus x = 0$
* **Algebraic Laws:**
  * Commutative: $a \oplus b = b \oplus a$
  * Associative: $(a \oplus b) \oplus c = a \oplus (b \oplus c)$

---
*Created for Java DSA & LeetCode Practice Track | Target: Summer 2027 Internships*

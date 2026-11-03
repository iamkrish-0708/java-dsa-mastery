# 🪙 Coin Change II (LeetCode #518) — Pure Recursion Notes

> **Category:** Dynamic Programming / Combinatorics  
> **Core Pattern:** **Include-Exclude with Unlimited Supply (Unbounded Knapsack)**  
> **Status:** Pure recursive formulation completed $\to$ Hits expected TLE on LeetCode $\to$ Will be optimized to $\mathcal{O}(N \times \text{Amount})$ using 2D DP.

---

## 📌 1. Problem Statement

Given an array of coin denominations `coins` and an integer `amount`, return the **number of distinct combinations** that make up that amount. You may assume you have an **infinite supply** of each coin.

### Example:
* `amount = 5`, `coins = [1, 2, 5]`
* Output: **4 combinations**
  1. `5`
  2. `2 + 2 + 1`
  3. `2 + 1 + 1 + 1`
  4. `1 + 1 + 1 + 1 + 1`

*(Note: Order does NOT matter. `[2, 1, 2]` and `[1, 2, 2]` are considered the exact same combination).*

---

## 🧠 2. The Recurrence Relation (The Golden Decision)

At any coin `coins[index]` with remaining `amt`, you have **exactly two choices**:

1. **INCLUDE the coin:**
   * Subtract coin value from `amt`: `amt - coins[index]`.
   * **CRITICAL DETAIL:** Stay at the **same index**!  
     $$\text{solve}(amt - coins[index], coins, index)$$
     *Why?* Because you have an **unlimited supply** of this coin! You can pick it again and again until `amt` runs out.
2. **EXCLUDE the coin:**
   * Do not subtract from `amt`.
   * Move to the next coin: `index + 1`.  
     $$\text{solve}(amt, coins, index + 1)$$
     *Why?* We are officially done using `coins[index]` forever in this branch. Moving to `index + 1` prevents permutations/duplicates!

$$\mathbf{\text{Total Combinations} = \text{include} + \text{exclude}}$$

---

## 🛑 3. Base Conditions (The 3 Guards)

```java
// 1. Success Base Case: We hit the exact target amount!
if (amt == 0) {
    return 1; // Exactly 1 valid combination completed
}

// 2. Overshoot Base Case: We subtracted too much
if (amt < 0) {
    return 0; // Invalid path, 0 combinations
}

// 3. Exhaustion Base Case: Ran out of coins to pick from
if (index >= coins.length) {
    return 0; // Cannot make positive amount with 0 coins
}
```

---

## 🌲 4. Recursion Tree Trace (`amount = 5`, `coins = [1, 2, 5]`)

Watch how keeping `index` on the Include branch allows repeated usage of coin `1` and coin `2`:

```
                                solve(amt=5, idx=0 [coin=1])
                               /                           \
                 [INC 1: amt=4, idx=0]            [EXC 1: amt=5, idx=1 (coin=2)]
                /                     \                 /                     \
       [INC 1: amt=3, idx=0]   [EXC 1: amt=4, idx=1] [INC 2: amt=3, idx=1]  [EXC 2: amt=5, idx=2]
             /        \              ...                    ...                  /             \
            ...       ...                                           [INC 5: amt=0]   [EXC 5: idx=3]
                                                                        (amt=0)          (out of bounds)
                                                                     returns 1! ✅         returns 0 ❌
```

---

## 📊 5. Why Does Pure Recursion Hit TLE? (Overlapping Subproblems)

Look at the subproblem state defined by two variables: `(amt, index)`.

In the tree above:
* Path A: Include 1, then Include 2, then Exclude 1 $\implies$ reaches state `(amt=2, index=1)`.
* Path B: Exclude 1, then Include 2 $\implies$ ALSO reaches state `(amt=2, index=1)`!

Without memoization, the exact same state `(amt, index)` is re-computed **hundreds of thousands of times**.

* **Time Complexity (Pure Recursion):** $\mathcal{O}(2^{\text{Amount} / \text{min\_coin}})$ — Exponential!
* **Space Complexity (Call Stack):** $\mathcal{O}(\text{Amount} / \text{min\_coin})$ — Deepest stack depth is picking coin `1` repeatedly until `amt == 0`.

---

## 💡 6. Preview: How Dynamic Programming Fixes This

In upcoming lectures, you will notice that this function takes **2 changing variables**: `amt` and `index`.
Therefore, a **2D Array (Table / Memo)** `dp[coins.length][amount + 1]` will store results:

```java
if (dp[index][amt] != -1) return dp[index][amt];

int inc = solve(amt - coins[index], coins, index);
int exc = solve(amt, coins, index + 1);

return dp[index][amt] = inc + exc;
```

With this 2-line addition, Time drops from **Exponential $\to \mathcal{O}(N \times \text{Amount})$**, passing all test cases instantly!

---

## 💻 7. Final Java Reference Code

```java
class Solution {
    static int solve(int amt, int[] coins, int index) {
        if (amt == 0) return 1;
        if (amt < 0) return 0;
        if (index >= coins.length) return 0;

        // Include: stay at same coin (infinite supply)
        int inc = solve(amt - coins[index], coins, index);

        // Exclude: move to next coin
        int esc = solve(amt, coins, index + 1);

        return inc + esc;
    }

    public int change(int amount, int[] coins) {
        return solve(amount, coins, 0);
    }
}
```

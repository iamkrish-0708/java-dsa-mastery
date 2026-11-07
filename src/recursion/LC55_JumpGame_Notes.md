# 🦘 LeetCode #55: Jump Game — Greedy Post-Mortem & Dry Run Notes

> **Category:** Greedy / Dynamic Programming  
> **Difficulty:** Medium (Blind 75 / Top Interview 150)  
> **Runtime:** 3 ms (Beats ~75%)  
> **Complexity:** $\mathcal{O}(N)$ Time, $\mathcal{O}(1)$ Auxiliary Space  

---

## 💻 The Final Accepted Code

```java
class Solution {
    public boolean canJump(int[] nums) {
        int jumpVal = nums[0]; // Initial fuel from start point

        for (int i = 1; i < nums.length; i++) {
            // 1. Check if we ran out of fuel before reaching index i
            if (jumpVal == 0) {
                return false; // Stranded!
            }

            // 2. Stepping forward burns 1 unit of fuel
            jumpVal--;

            // 3. At pump i: refill only if the pump offers more than current tank
            jumpVal = Math.max(jumpVal, nums[i]);
        }

        return true; // Reached the destination!
    }
}
```

---

## 🔍 Detailed Dry Runs

---

### ❌ Example 1 (Failing Case — Stranded at Zero)

* **Input Array:** `arr = [3, 2, 1, 0, 4]`
* **Array Size:** `size = 5`
* **Target Destination:** Index `4`
* **Initial State:** `jumpVal = arr[0] = 3`

| Iteration | Current Index `i` | Value at Pump `arr[i]` | Fuel Check (`jumpVal == 0`) | Step Forward (`jumpVal--`) | Refill Decision `Math.max(jumpVal, arr[i])` | New `jumpVal` in Tank | Status |
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
| **Init** | `0` | `3` | — | — | Initial fuel | **`3`** | At Start |
| **Iter 1** | `1` | `2` | `3 != 0` (Pass) | `3 - 1 = 2` | `Math.max(2, 2)` (Equal) | **`2`** | Driving forward |
| **Iter 2** | `2` | `1` | `2 != 0` (Pass) | `2 - 1 = 1` | `Math.max(1, 1)` (Equal) | **`1`** | Driving forward |
| **Iter 3** | `3` | `0` | `1 != 0` (Pass) | `1 - 1 = 0` | `Math.max(0, 0)` (Equal) | **`0`** | Empty tank! |
| **Iter 4** | `4` | `4` | **`0 == 0` (FAIL!)** | — | — | **`0`** | **STRANDED!** |

* **Result:** At `i = 4`, `jumpVal == 0`. Exit loop immediately and return **`false`**!

---

### ✅ Example 2 (Passing Case — Fuel Refills Overcome Dips)

* **Input Array:** `arr = [2, 3, 1, 1, 4]`
* **Array Size:** `size = 5`
* **Target Destination:** Index `4`
* **Initial State:** `jumpVal = arr[0] = 2`

| Iteration | Current Index `i` | Value at Pump `arr[i]` | Fuel Check (`jumpVal == 0`) | Step Forward (`jumpVal--`) | Refill Decision `Math.max(jumpVal, arr[i])` | New `jumpVal` in Tank | Status |
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
| **Init** | `0` | `2` | — | — | Initial fuel | **`2`** | At Start |
| **Iter 1** | `1` | `3` | `2 != 0` (Pass) | `2 - 1 = 1` | `Math.max(1, 3)` (Refill! $3 > 1$) | **`3`** | Big fuel boost |
| **Iter 2** | `2` | `1` | `3 != 0` (Pass) | `3 - 1 = 2` | `Math.max(2, 1)` (Keep tank: $2 > 1$) | **`2`** | Coasting |
| **Iter 3** | `3` | `1` | `2 != 0` (Pass) | `2 - 1 = 1` | `Math.max(1, 1)` (Equal) | **`1`** | Coasting |
| **Iter 4** | `4` | `4` | `1 != 0` (Pass) | `1 - 1 = 0` | `Math.max(0, 4)` | **`4`** | Stepped on Finish Line! |

* **Loop Exit:** Next `i` becomes `5`. `5 < 5` is **false**.
* **Termination:** Loop finishes naturally. Destination reached! Return **`true`**!

---

## 💡 Crucial Edge-Case Insights to Remember:

1. **The Single Element Case `nums = [0]`:**
   * Starts at index `0`. The loop `for (int i = 1; i < 1; i++)` **never runs even once!**
   * Exits immediately and returns **`true`**.
   * *Why?* Because if there's only 1 element, you start at the destination. No jumps required!

2. **The "Empty Tank at Destination" Rule:**
   * You only need fuel to **reach** the last index, not to make another jump *after* landing on it.
   * In Example 2 at `i = 4`, our fuel burned down to `0` as we landed on index 4. That is completely fine because we reached the finish line!

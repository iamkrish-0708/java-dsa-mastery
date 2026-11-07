# 🦘 LeetCode #45: Jump Game II — Greedy Horizons & Two-Pointer Post-Mortem

> **Category:** Greedy / Two-Pointer BFS Window  
> **Difficulty:** Medium (Blind 75 / Top Interview 150)  
> **Status:** 100% Passed  
> **Complexity:** $\mathcal{O}(N)$ Time, $\mathcal{O}(1)$ Space  

---

## 💻 The Working Code (Greedy Horizons)

```java
class Solution {
    public int jump(int[] nums) {
        int currentEnd = 0; // Horizon of current jump wave
        int farthest = 0;   // Maximum reach scouted within current wave
        int minJumps = 0;

        // Loop runs up to nums.length - 2
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);

            // When i reaches the boundary of the current jump:
            if (currentEnd == i) {
                minJumps++;
                currentEnd = farthest;
            }
        }

        return minJumps;
    }
}
```

---

## 🔍 Krish's Complete Dry Run (Step-by-Step)

### Input Array: `nums = [2, 3, 1, 1, 4]`, `size = nums.length = 5`
* **Loop Bound:** `i < size - 1` $\implies i < 4$ (Indices `0, 1, 2, 3`)
* **Initial State:**
  * `currentEnd = 0`
  * `farthest = 0`
  * `minJumps = 0`

---

### Execution Trace:

#### 🔹 Iteration 0 (`i = 0`):
* `curr = 0 + nums[0] = 0 + 2 = 2`
* `farthest = max(0, 2) = 2` *(since curr > farthest)*
* Condition check: `(currentEnd == i)` $\implies (0 == 0)$ **PASSES!**
  * `minJumps++` $\implies$ **`minJumps = 1`**
  * `currentEnd = farthest = 2`

#### 🔹 Iteration 1 (`i = 1`):
* `curr = 1 + nums[1] = 1 + 3 = 4`
* `farthest = max(2, 4) = 4` *(since curr > farthest)*
* Condition check: `(currentEnd == i)` $\implies (2 == 1)$ **FAILS!**
  * No jump triggered, continue scouting within horizon.

#### 🔹 Iteration 2 (`i = 2`):
* `curr = 2 + nums[2] = 2 + 1 = 3`
* `farthest = max(4, 3) = 4` *(since curr < farthest)*
* Condition check: `(currentEnd == i)` $\implies (2 == 2)$ **PASSES!**
  * `minJumps++` $\implies$ **`minJumps = 2`**
  * `currentEnd = farthest = 4`

#### 🔹 Iteration 3 (`i = 3`):
* `curr = 3 + nums[3] = 3 + 1 = 4`
* `farthest = max(4, 4) = 4` *(since curr == farthest)*
* Condition check: `(currentEnd == i)` $\implies (4 == 3)$ **FAILS!**
  * Continue.

#### 🔹 Iteration 4 (`i = 4`):
* Loop condition: `i < size - 1` $\implies (4 < 4)$ **FAILS!**
* **Loop terminates immediately.**

---

### 🏁 Final Result:
* Return **`minJumps = 2`**! ✅

---

## 💡 Key Architectural Insights:
1. **Why `i < size - 1` is Essential:**
   * If the loop ran up to `size - 1` (`i = 4`), at `i = 4` we would have `currentEnd == i` ($4 == 4$), triggering a redundant jump (`minJumps = 3`) even though we were already on the destination square!
2. **Single Element Case `nums = [0]`:**
   * `size = 1`, loop condition `i < 1 - 1` $\implies i < 0$ is immediately false.
   * Directly returns `minJumps = 0` without entering the loop!

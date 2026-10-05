# 🫓 Binary Search on Answer Space: Roti Prata (SPOJ) Pattern

> **Core Concept:** Finding the **Minimum Time** required to produce $N$ items when workers (chefs) have non-linear, arithmetic-progression production speeds ($r \cdot 1, r \cdot 2, r \cdot 3, \dots$).

---

## 🧭 Problem Essence & Real-World Parallel

* **The Problem:** 
  * $N$ pratas are ordered.
  * $C$ chefs are available, where chef $i$ has rank $R_i$.
  * Chef $i$ takes:
    * $R_i \cdot 1$ minutes for the 1st prata
    * $R_i \cdot 2$ minutes for the 2nd prata
    * $R_i \cdot 3$ minutes for the 3rd prata
    * In general, $k$ pratas take $R_i \cdot \frac{k(k + 1)}{2}$ total time.
  * All chefs work **simultaneously / in parallel**.
  * **Goal:** Find the **minimum time** to complete all $N$ pratas.

---

## 🔍 Why Binary Search? (Monotonicity Property)

Let $T$ be the candidate time allowed:
* **If $T$ is VALID (total pratas baked $\ge N$ in time $T$):**
  * Any larger time $> T$ will definitely bake enough pratas.
  * Since we want the **minimum** time, we record $T$ (`ans = mid`) and try to finish even faster: `end = mid - 1;`.
* **If $T$ is INVALID (total pratas baked $< N$):**
  * Less time will bake even fewer pratas.
  * We must give chefs more time: `start = mid + 1;`.

```
Time T:      [ 0,  1,  2, ..., 11,  12,  13,  14, ... ]
Valid?       [ F,  F,  F, ...,  F,   T,   T,   T, ... ]
                                     ^
                             Min Valid Time = 12
```

---

## 📐 Search Space Bounds

* **Lower Bound (`start`):** `0` (or `1`) minute.
* **Upper Bound (`end`):** $\text{worstRank} \times \frac{N(N + 1)}{2}$
  * *Worst-case scenario:* Only the single slowest chef bakes all $N$ pratas alone.

---

## ⚙️ The Algorithm Blueprint

### 1. The Time-Simulation Validator (`isValid`)
For each chef with rank $r$, simulate how many pratas they can bake within `minTime` using a while loop or quadratic equation:

```java
static boolean isValid(int[] ranks, int n, int minTime) {
    int prataCount = 0;
    
    for (int r : ranks) {
        int time = 0;
        int ptr = 1;
        
        // Accumulate time for each subsequent prata
        while (time + r * ptr <= minTime) {
            time += r * ptr;
            ptr++;
        }
        
        prataCount += (ptr - 1); // Add total pratas finished by this chef
        
        // Early exit optimization
        if (prataCount >= n) {
            return true;
        }
    }
    return false;
}
```

### 2. Main Binary Search Function

```java
public static int minTimeToBakePratas(int[] ranks, int n) {
    int start = 0;
    int maxRank = 0;
    for (int r : ranks) {
        maxRank = Math.max(maxRank, r);
    }
    
    // Worst case: slowest chef cooks all n pratas alone
    int end = maxRank * (n * (n + 1) / 2);
    int ans = -1;

    while (start <= end) {
        int mid = start + (end - start) / 2;
        
        if (isValid(ranks, n, mid)) {
            ans = mid;        // Feasible! Try finding an even smaller time
            end = mid - 1;
        } else {
            start = mid + 1;  // Not enough pratas, increase time
        }
    }
    return ans;
}
```

---

## 📊 Complexity Analysis

* **Time Complexity:** $\mathcal{O}(C \cdot \sqrt{\text{Mid}} \cdot \log(\text{WorstTime}))$
  * For each chef, the while loop runs $\approx \sqrt{\frac{2 \cdot \text{Mid}}{r}}$ steps (since time grows quadratically with pratas: $r \cdot \frac{k^2}{2}$).
  * Binary search takes $\mathcal{O}(\log(\text{MaxTime}))$ iterations.
  * For $N = 1000, C = 50$, total operations $\approx 50 \times 1000 \times 30 \approx 1.5 \times 10^6$ (runs in $< 10\text{ ms}$).
* **Space Complexity:** $\mathcal{O}(1)$ auxiliary space.

---

## 💡 Summary Comparison Across All 4 Major Patterns

| Problem Pattern | Nature of Work | Predicate Check | Search on Valid |
| :--- | :--- | :--- | :--- |
| **Book Allocation / Painter / LC 410** | Contiguous subarrays | Split into $K$ chunks $\le \text{mid}$ | `end = mid - 1` (Min of Max) |
| **Aggressive Cows / LC 1552 / LC 2517** | Spatial placement with gap | Place $K$ items with gap $\ge \text{mid}$ | `start = mid + 1` (Max of Min) |
| **EKO SPOJ / LC 875 / LC 2226** | Independent linear output | Sum linear contributions $\ge \text{target}$ | `start = mid + 1` or `end = mid - 1` |
| **Roti Prata SPOJ** | Independent quadratic output | Sum non-linear A.P. times $\ge \text{target}$ | `end = mid - 1` (Min Time) |

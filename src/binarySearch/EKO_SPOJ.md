# 🌲 Binary Search on Answer Space: EKO (SPOJ) Pattern

> **Core Concept:** Finding the **Maximum Threshold** ($H$) such that the total accumulated contribution across independent items satisfies a required quota ($M$).

---

## 🧭 The Pattern Family (Accumulator Binary Search)

| Problem Name | Context | Accumulation Formula | Target | LeetCode / Platform |
| :--- | :--- | :--- | :--- | :--- |
| **EKO (SPOJ)** | Cut trees at saw height $H$ | $\sum \max(0, \text{tree}[i] - H) \ge M$ | **Maximize** saw height $H$ | SPOJ / CodeStudio |
| **Koko Eating Bananas** | Eat piles at speed $K$ | $\sum \lceil \text{pile}[i] / K \rceil \le \text{hours}$ | **Minimize** eating speed $K$ | **[LeetCode #875 (Medium)](https://leetcode.com/problems/koko-eating-bananas/)** |
| **Maximum Candies to K Children** | Divide candy piles | $\sum \lfloor \text{pile}[i] / \text{candies} \rfloor \ge K$ | **Maximize** candies per child | **[LeetCode #2226 (Medium)](https://leetcode.com/problems/maximum-candies-allocated-to-k-children/)** |
| **Minimum Time for Trips** | Bus trip times | $\sum \lfloor \text{time} / \text{bus}[i] \rfloor \ge \text{trips}$ | **Minimize** total time | **[LeetCode #2187 (Medium)](https://leetcode.com/problems/minimum-time-to-complete-trips/)** |

---

## 🔍 Key Distinctions from Other Binary Search Patterns

| Feature | Book Allocation / Painter | Aggressive Cows | EKO (SPOJ) |
| :--- | :--- | :--- | :--- |
| **Goal** | Minimize the Maximum | Maximize the Minimum | **Maximize the Threshold** |
| **Array Sorting Needed?** | ❌ NO (Contiguous subarrays) | ✅ **YES** (Distance between neighbors) | ❌ **NO** (Independent tree contributions) |
| **Evaluation Type** | Partition counter | Greedy element placer | **Sum accumulator** |
| **Monotonicity** | `[F, F, ..., T, T]` | `[T, T, ..., F, F]` | `[T, T, ..., F, F]` |
| **On `isValid == true`** | `ans = mid; end = mid - 1;` | `ans = mid; start = mid + 1;` | `ans = mid; start = mid + 1;` |

---

## 📈 Monotonic Property (Why Binary Search Works)

Let $H$ be the saw blade height:
* **If $H$ is VALID ($\text{wood} \ge M$):**
  * Any lower height $< H$ will cut even more wood (definitely valid).
  * To save as many trees as possible, we test higher blade heights $\rightarrow$ `ans = mid; start = mid + 1;`.
* **If $H$ is INVALID ($\text{wood} < M$):**
  * Any higher blade $> H$ will yield even less wood.
  * We must lower the blade $\rightarrow$ `end = mid - 1;`.

```
Saw Height H: [ 0,  1,  2, ..., 15,  16,  17,  18,  19,  20 ]
Validity:     [ T,  T,  T, ...,  T,   F,   F,   F,   F,   F ]
                                 ^
                        Max Valid Height = 15
```

---

## ⚠️ Critical Gotcha: 64-bit Integer Overflow

In real online judges and competitive programming:
* $N \le 10^6$ trees, with tree heights up to $10^9$.
* Required wood $M \le 2 \times 10^9$.
* Total wood collected can reach $10^6 \times 10^9 = 10^{15}$, which exceeds 32-bit `int` limits ($2 \times 10^9$).
* 👉 **Always use `long` for the accumulated wood sum.**

---

## ⚙️ The Algorithm Blueprint

```java
package binarySearch;

public class EkoSpoj {

    static boolean isValid(int[] arr, long m, int sawHeight) {
        long woodCollection = 0; // Use long to prevent 32-bit overflow
        for (int tree : arr) {
            if (tree > sawHeight) {
                woodCollection += (tree - sawHeight);
            }
        }
        return woodCollection >= m;
    }

    public static int getMaxSawHeight(int[] tree, long m) {
        int start = 0;
        int max = 0;
        for (int h : tree) {
            max = Math.max(max, h);
        }
        int end = max;
        int ans = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (isValid(tree, m, mid)) {
                ans = mid;        // Candidate found!
                start = mid + 1;  // Try a HIGHER blade height
            } else {
                end = mid - 1;    // Not enough wood, cut lower
            }
        }
        return ans;
    }
}
```

---

## 📊 Complexity Analysis

* **Time Complexity:** $\mathcal{O}(N + N \log(\max(\text{tree})))$
  * Finding `max`: $\mathcal{O}(N)$.
  * Binary Search: $\mathcal{O}(\log(\text{MaxHeight}))$ iterations, each taking $\mathcal{O}(N)$ inside `isValid()`.
  * For $N = 10^6$ and $\text{MaxHeight} = 10^9$, $\log_2(10^9) \approx 30 \implies 3 \times 10^7$ operations (runs in $< 0.1\text{ s}$).
* **Space Complexity:** $\mathcal{O}(1)$ auxiliary space.

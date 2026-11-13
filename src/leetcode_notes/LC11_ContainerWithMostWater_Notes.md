# 💧 LeetCode #11: Container With Most Water — Deep Dive & Proof

> **Package:** `leetcode_notes`  
> **Source Code:** [`src/leetcode/LC11_ContainerWithMostWater.java`](file:///C:/Users/Krish/IdeaProjects/Java/src/leetcode/LC11_ContainerWithMostWater.java)  
> **Difficulty:** Medium (Blind 75 / Top Interview 150)  
> **Topic:** Two Pointers Inward Squeeze / Greedy Pruning

---

## 📌 1. Problem Overview
You are given an array of non-negative integers `height` where each element represents the height of a vertical line at that index. Find two lines that, together with the x-axis, trap the **maximum possible water**.

* **Water Formula:**
  $$\text{Area} = \min(\text{height}[i], \text{height}[j]) \times (j - i)$$
* **The Constraint:** Water cannot slant; it spills over the shorter boundary line.

---

## 🧠 2. The Core Mathematical Intuition

### Why start at the two extreme boundaries (`i = 0`, `j = n - 1`)?
* At the beginning, the width $(j - i)$ is at its **global maximum**.
* As we move either pointer inward, the width is **strictly decreasing** ($n-1 \to n-2 \to \dots \to 1$).
* To achieve a larger area with a smaller width, the new container **MUST have a taller bottleneck height**!

---

### The Fundamental Greedy Decision: Which Pointer Moves?

Suppose `height[i] < height[j]` (left line is shorter than right line):

1. **What if we moved the taller line `j` inward (`j--`)?**
   * The new width will be smaller: $(j - 1) - i < j - i$.
   * What about the new height? 
     $$\text{New Height} = \min(\text{height}[i], \text{height}[j - 1])$$
     Since `height[i]` is still the same, the new height can **never exceed `height[i]`**! It can only be $\le \text{height}[i]$.
   * **Result:** Smaller width $\times$ (equal or smaller height) = **Provably smaller area!**
   * Moving the taller line is **mathematically guaranteed to be useless**.

2. **What if we move the shorter line `i` inward (`i++`)?**
   * Yes, the width decreases by 1.
   * BUT, `height[i + 1]` has a chance of being **significantly taller** than `height[i]`!
   * This is the **ONLY** choice that has any possibility of producing a larger area.

> **Golden Rule:** Always advance the pointer pointing to the **shorter wall**!

---

## ⚡ 3. The 2 ms Skip / Hop Optimization

In the basic 5 ms solution:
```java
if (height[i] < height[j]) i++;
else j--;
```
If the new line we encounter is **even shorter or equal** to the wall we just left (`height[i] <= effHeight`), calculating its area is completely wasted work because width shrank AND height didn't grow.

### The 2 ms Hop Loop:
```java
while (i < j && height[i] <= effHeight) i++;
while (i < j && height[j] <= effHeight) j--;
```
This fast-forwards through valleys and plateaus in a single stride, dropping runtime from **5 ms down to 2 ms (Beats 98%)**!

---

## 📊 4. Trace Visualization: `[1, 8, 6, 2, 5, 4, 8, 3, 7]`

```text
Indices:   0   1   2   3   4   5   6   7   8
Heights:  [1,  8,  6,  2,  5,  4,  8,  3,  7]
           ^                               ^
          i=0                             j=8

Step 1:
- i = 0 (h=1), j = 8 (h=7)
- effHeight = min(1, 7) = 1, width = 8 -> Area = 1 * 8 = 8, max = 8
- height[0] < height[8] -> Advance i past all walls <= 1 -> i moves to 1 (h=8).

Step 2:
- i = 1 (h=8), j = 8 (h=7)
- effHeight = min(8, 7) = 7, width = 7 -> Area = 7 * 7 = 49, max = 49 🎯
- height[8] < height[1] -> Advance j past all walls <= 7 -> j moves to 6 (h=8).

Step 3:
- i = 1 (h=8), j = 6 (h=8)
- effHeight = min(8, 8) = 8, width = 5 -> Area = 8 * 5 = 40 (max remains 49).
- Equal heights -> Both advance inward.

... Pointers cross, Max Area = 49!
```

---

## ⏱️ 5. Complexity Analysis

| Metric | Complexity | Explanation |
| :--- | :--- | :--- |
| **Time Complexity** | $\mathcal{O}(N)$ | Pointers $i$ and $j$ start at ends and take at most $N$ single steps towards each other. Each element is visited at most once. |
| **Space Complexity** | $\mathcal{O}(1)$ | Only 4 integer variables on the stack (`i`, `j`, `max`, `effHeight`). Zero heap allocation. |

---

## 🎤 6. Interview Soundbite (How to Explain in 30 Seconds)
> *"We start with the maximum possible width using two pointers at the two ends. Since the water volume is constrained by the shorter wall, moving the taller wall inward will strictly decrease the area (smaller width, same or worse height). Therefore, we greedily advance the shorter pointer inward to seek a taller bottleneck, achieving an optimal $\mathcal{O}(N)$ time and $\mathcal{O}(1)$ space solution."*

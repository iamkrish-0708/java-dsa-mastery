# 🧠 Backtracking & Combination Patterns: Interview Revision Masterclass

> **Context:** Master cheat sheet synthesized from solving **LC 78 (Subsets), LC 90 (Subsets II), LC 39 (Combination Sum I), LC 40 (Combination Sum II), and LC 216 (Combination Sum III)**.  
> Designed for quick recall before technical coding interviews (FAANG / Tier-1).

---

## 🎯 1. The 4 Decision Dials of Backtracking

Whenever an interviewer gives you a problem involving subsets, combinations, or target sums, evaluate these **4 Dials** to instantly determine your recurrence structure:

```
[DIAL 1] Can elements be reused?
   ├── Unlimited times (LC 39)        ──> Include stays at `index`
   └── At most once (LC 40, 216, 90)  ──> Include moves to `index + 1`

[DIAL 2] Does the input contain duplicates?
   ├── Distinct elements (LC 39, 216) ──> No duplicate output risk. Arrays.sort() is optional (used only for pruning).
   └── Contains duplicates (LC 40, 90)──> MUST sort first! In exclude branch, skip twins:
                                          `while (i + 1 < n && nums[i] == nums[i + 1]) i++;`

[DIAL 3] What is the success condition?
   ├── Power Set / All subsets (LC 78, 90) ──> Snapshot `curr` at every step / base case.
   └── Target Sum (LC 39, 40, 216)          ──> Base case: `if (target == 0) ans.add(new ArrayList<>(curr));`

[DIAL 4] Is there a fixed combination length $k$?
   ├── Any length (LC 39, 40)          ──> Let recursion run until target or boundary.
   └── Fixed length k (LC 216)         ──> Add size pruning: `if (curr.size() == k) return;`
```

---

## 📊 2. Master Problem Comparison Table

| LeetCode Problem | Can Reuse? | Has Duplicates? | Length Constraint? | Include Step | Exclude / Duplicate Logic | Runtime |
| :--- | :---: | :---: | :---: | :---: | :--- | :---: |
| **LC 78: Subsets** | ❌ No | ❌ No | None | `solve(i + 1)` | Standard exclude: `solve(i + 1)` | **0 ms** |
| **LC 90: Subsets II** | ❌ No | ⚠️ **YES** | None | `solve(i + 1)` | `while (nums[i] == nums[i+1]) i++;` then `solve(i + 1)` | **1 ms** |
| **LC 39: Combination Sum** | ✅ **YES** | ❌ No | None | `solve(target - nums[i], i)` | Standard exclude: `solve(target, i + 1)` | **3 ms** |
| **LC 40: Combination Sum II** | ❌ No | ⚠️ **YES** | None | `solve(target - nums[i], i + 1)` | `while (nums[i] == nums[i+1]) i++;` then `solve(target, i + 1)` | **2–7 ms** |
| **LC 216: Combination Sum III**| ❌ No | ❌ No | 🎯 **Fixed $k$** | `solve(target - nums[i], i + 1)` | Standard exclude + `if (curr.size() == k) return;` | **0 ms** |

---

## ⚡ 3. The 3 Tiers of Pruning (How to Explain Optimization to an Interviewer)

When an interviewer asks: *"Can we optimize this search tree?"*, articulate these 3 layers of pruning:

### Tier 1: State Boundary Pruning (The Baseline)
```java
// Cuts the branch when sum is exceeded or out of array bounds
if (target < 0 || index >= candidates.length) {
    return;
}
```

### Tier 2: Sorted Array Early-Break Pruning (From 5ms $\to$ 1ms)
* **The Insight:** If the array is sorted in ascending order (`Arrays.sort(candidates)`), the moment `candidates[i] > target`, **every subsequent element will also be $> target$**.
* **In For-Loop Pattern:**
  ```java
  for (int i = index; i < candidates.length; i++) {
      if (candidates[i] > target) break; // Kills all remaining siblings instantly!
      ...
  }
  ```
* **In Binary Choice Pattern:**
  ```java
  if (candidates[index] > target) return; // Neither include nor exclude can ever work!
  ```

### Tier 3: Length / Cardinality Pruning (LC 216 Pattern)
* **The Insight:** If `curr.size() == k` but `target != 0`, adding further elements will violate the size constraint.
```java
if (curr.size() == k) {
    return; // Don't explore deeper children
}
```

---

## ⚠️ 4. The 4 Fatal Interview Traps (Always Double-Check!)

1. **The Reference Trap (Snapshotting):**
   * ❌ `ans.add(curr);` — Stores the *reference*! By the time recursion finishes, `curr` is empty, so `ans` contains `[[], [], []]`.
   * ✅ `ans.add(new ArrayList<>(curr));` — Creates a permanent deep-copy snapshot.

2. **The Base Case Order Trap:**
   * ❌ Putting `if (index >= nums.length) return;` **before** `if (target == 0)`.
   * **Why it fails:** If a valid combination uses the very last element of the array, `index` reaches `nums.length` at the same time `target == 0`. Checking index first prematurely kills valid answers!
   * ✅ Always check `if (target == 0)` **FIRST**.

3. **The Unsorted Duplicate Trap (LC 90 / LC 40):**
   * If you want to skip duplicates using `while (nums[i] == nums[i+1])`, **the array MUST be sorted first**.
   * On unsorted arrays (e.g. `[4, 1, 4]`), identical twins aren't adjacent, causing duplicate branches to slip through.

4. **The Backtrack Restoration Rule:**
   * For every `curr.add(...)`, there must be an exact corresponding `curr.remove(curr.size() - 1)` along the path before the parent frame regains control.

---

## 🎤 5. The Golden Interview Script (How to Speak Like a Senior)

> *"This is a combinatorial search problem where we enumerate valid paths. I will use a backtracking state-space tree with include/exclude decisions.*  
> *Since candidates can be reused, the include step recurses on the same index. To ensure uniqueness and prune dead subtrees, I sort the array upfront so that if any candidate exceeds the remaining target, all subsequent candidates can be pruned early with a break condition, keeping our auxiliary space bounded to $\mathcal{O}(\text{depth})$."*

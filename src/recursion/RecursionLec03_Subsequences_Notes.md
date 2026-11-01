# 🌟 Recursion: Include-Exclude Pattern (Subsequences of a String) — Lecture 59 Notes

> **Why This Pattern is Foundational:**  
> The **Include-Exclude (Pick / Not-Pick)** pattern is the gateway to **Subsets, Combinations, Permutations, Backtracking, and 0/1 Knapsack (Dynamic Programming)**. Mastering this single pattern unlocks dozens of medium and hard interview questions.

---

## 📌 1. What is a Subsequence?

A **subsequence** is a sequence derived from a string or array by deleting zero or more elements **without changing the relative order** of the remaining elements.

* For string `"abc"`:
  * Subsequences (length $N = 3 \implies 2^3 = 8$):
    1. `"abc"` (pick a, pick b, pick c)
    2. `"ab"`  (pick a, pick b, skip c)
    3. `"ac"`  (pick a, skip b, pick c)
    4. `"a"`   (pick a, skip b, skip c)
    5. `"bc"`  (skip a, pick b, pick c)
    6. `"b"`   (skip a, pick b, skip c)
    7. `"c"`   (skip a, skip b, pick c)
    8. `""`    (empty string: skip a, skip b, skip c)

> 💡 **Subsequence vs. Substring:**
> * **Substring:** Contiguous block (e.g., `"ab"`, `"bc"`). `"ac"` is **not** a substring.
> * **Subsequence:** Maintains order, does **not** need to be contiguous. `"ac"` **is** a valid subsequence.

---

## 🧠 2. The Include-Exclude Mental Model

At each index $i$ of the string, you stand at a fork in the road with **only two choices**:

1. **INCLUDE (`ch` is picked):**
   * Append character $s[i]$ to the running output string.
   * Recurse for the next index $(i + 1)$.
2. **BACKTRACK (Undo the choice):**
   * Remove character $s[i]$ from output (`deleteCharAt(output.length() - 1)`).
3. **EXCLUDE (`ch` is skipped):**
   * Without adding $s[i]$, recurse for the next index $(i + 1)$.

### 🛑 Base Condition:
When $i == s.\text{length}()$, you have made decisions for all characters!
* Record the current built string into `ans` list.
* `return;` (start unwinding).

---

## 🌲 3. Complete Recursion Tree for Test Case `s = "abc"`

```
                                  i = 0, output = ""
                                  Decision for 'a'
                                 /                \
                     [INCLUDE 'a']                [EXCLUDE 'a']
                     output = "a"                 output = ""
                     i = 1                        i = 1
                    /             \              /             \
            [INC 'b']             [EXC 'b']    [INC 'b']        [EXC 'b']
            output = "ab"         output = "a" output = "b"     output = ""
            i = 2                 i = 2        i = 2            i = 2
           /         \           /       \    /         \      /         \
       [INC 'c']  [EXC 'c']  [INC 'c'] [EXC] [INC 'c'] [EXC] [INC 'c']  [EXC]
      "abc"       "ab"       "ac"      "a"   "bc"      "b"   "c"        ""
      (i=3)       (i=3)      (i=3)    (i=3)  (i=3)    (i=3)  (i=3)     (i=3)
```

Total leaves = $2^3 = 8$ distinct subsequences!

---

## 📋 4. Step-by-Step Call Trace (Dry Run for `s = "abc"`)

Watch how the recursion unwinds and backtracks:

1. `call(0, "")`: Include `'a'` $\to$ `output = "a"`.
2. `call(1, "a")`: Include `'b'` $\to$ `output = "ab"`.
3. `call(2, "ab")`: Include `'c'` $\to$ `output = "abc"`.
4. `call(3, "abc")`: Base case! Add **`"abc"`** to `ans`. Return.
5. Back in `call(2)`: Backtrack! Remove `'c'` $\to$ `output = "ab"`. Exclude `'c'`.
6. `call(3, "ab")`: Base case! Add **`"ab"`** to `ans`. Return.
7. Back in `call(1)`: Backtrack! Remove `'b'` $\to$ `output = "a"`. Exclude `'b'`.
8. `call(2, "a")`: Include `'c'` $\to$ `output = "ac"`.
9. `call(3, "ac")`: Base case! Add **`"ac"`** to `ans`. Return.
10. Back in `call(2)`: Backtrack! Remove `'c'` $\to$ `output = "a"`. Exclude `'c'`.
11. `call(3, "a")`: Base case! Add **`"a"`** to `ans`. Return.
12. Back in `call(0)`: Backtrack! Remove `'a'` $\to$ `output = ""`. Exclude `'a'`.
13. Now the entire right subtree processes for `'b'` and `'c'`, generating **`"bc"`, `"b"`, `"c"`, `""`**!

---

## 💻 5. Clean Template Code (With Backtracking)

```java
package recursion;

import java.util.*;

public class SubsequencesOfString {

    static void getAllSubsequences(String s, StringBuilder output, int i, List<String> ans) {
        // Base case: all characters evaluated
        if (i >= s.length()) {
            ans.add(output.toString());
            return;
        }

        char ch = s.charAt(i);

        // 1. INCLUDE CHOICE
        output.append(ch);
        getAllSubsequences(s, output, i + 1, ans);

        // 2. BACKTRACK (Undo the include before exploring exclude)
        output.deleteCharAt(output.length() - 1);

        // 3. EXCLUDE CHOICE
        getAllSubsequences(s, output, i + 1, ans);
    }

    public static void main(String[] args) {
        String s = "abc";
        List<String> ans = new ArrayList<>();
        getAllSubsequences(s, new StringBuilder(), 0, ans);

        System.out.println("All Subsequences: " + ans);
        System.out.println("Total Count: " + ans.size()); // 2^N = 8
    }
}
```

---

## ⏱️ 6. Time and Space Complexity Analysis

* **Total Decisions (Tree Depth):** $N$ (the length of the string).
* **Branching Factor:** $2$ at each node (Include or Exclude).
* **Total Leaf Nodes:** $2^N$.
* **Total Nodes in Tree:** $2^0 + 2^1 + 2^2 + \dots + 2^N = 2^{N+1} - 1 \approx 2^N$.
* **Work done at each leaf:** Copying `StringBuilder` to `String` takes $\mathcal{O}(N)$ time.

$$\mathbf{\text{Time Complexity: } \mathcal{O}(N \cdot 2^N)}$$

* **Auxiliary Space (Call Stack):** The maximum depth of the tree from root to leaf is $N$.
* **Heap Space for `output`:** The `StringBuilder` holds at most $N$ characters at any time.

$$\mathbf{\text{Auxiliary Space Complexity: } \mathcal{O}(N)}$$
*(Excluding the $\mathcal{O}(N \cdot 2^N)$ space used to store the output list of $2^N$ strings).*

---

## ⚡ 7. Why is `output.deleteCharAt(...)` Necessary? (Backtracking)

Because `StringBuilder` is an **object passed by reference** across all stack frames!
* If you append `'a'` and don't delete it before making the exclude call, the exclude branch would still have `'a'` in it!
* **Golden Rule of Backtracking:**  
  $$\text{Do Choice} \implies \text{Recurse} \implies \text{Undo Choice (Backtrack)}$$

*(Alternative without backtracking: passing `output + ch` as a fresh `String` on every call. But that creates $\mathcal{O}(N)$ new String copies on every call, causing high memory usage and garbage collection overhead! `StringBuilder` + Backtrack is the industry-standard high-performance way).*

---

## 🎯 8. Where Does This Pattern Lead Next?

1. **Subsets / Power Set:** [LeetCode #78: Subsets](https://leetcode.com/problems/subsets/) (Identical logic with `int[]` instead of `String`).
2. **Combination Sum:** [LeetCode #39: Combination Sum](https://leetcode.com/problems/combination-sum/) (Include same element multiple times or Exclude).
3. **0/1 Knapsack:** Include item in bag or Exclude item.
4. **Target Sum / Subset Sum:** Include number with `+` or Include with `-`.

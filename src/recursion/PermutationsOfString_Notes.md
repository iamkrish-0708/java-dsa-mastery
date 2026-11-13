# 🔀 Permutations of a String: Lecture Notes & Deep Dive

> **Package:** `recursion`  
> **Source Code:** [`PermutationOfString.java`](file:///C:/Users/Krish/IdeaProjects/Java/src/recursion/PermutationOfString.java)  
> **Topic:** String Permutations via Substring Reduction Recursion

---

## 📌 1. Problem Statement
Given a string `s` of length $N$ containing distinct characters (e.g. `"ABC"`), generate all possible permutations (orderings) of the string.

* For a string of length $N$, the total number of unique permutations is:
  $$\text{Total Permutations} = N! = N \times (N - 1) \times (N - 2) \times \dots \times 1$$
* For $N = 3$ (`"ABC"`): $3! = 6$ permutations (`ABC`, `ACB`, `BAC`, `BCA`, `CAB`, `CBA`).

---

## 💡 2. The Core Mechanism: Substring Extraction

At every recursion level, we make a choice for the **current position**:
1. Iterate through every character in the string `s` from `i = 0` to `s.length() - 1`.
2. Select character `ch = s.charAt(i)` to append to our current permutation `curr`.
3. **Exclude `ch` from the remaining string:**
   ```java
   String remainingString = s.substring(0, i) + s.substring(i + 1);
   ```
4. Recurse with the smaller string:
   ```java
   solve(remainingString, curr + ch, ans);
   ```
5. **Base Case:** When `s.isEmpty()` (all characters have been picked), `curr` holds a complete permutation of length $N$. Add it to `ans` and return.

---

## 🌲 3. Complete Recursion Tree for `"ABC"`

```text
                                solve("ABC", "")
               /                       |                       \
     Pick 'A' (i=0)                 Pick 'B' (i=1)           Pick 'C' (i=2)
     rem = "BC", curr = "A"        rem = "AC", curr = "B"    rem = "AB", curr = "C"
         /          \                  /          \              /          \
      Pick 'B'    Pick 'C'          Pick 'A'    Pick 'C'      Pick 'A'    Pick 'B'
      rem="C"     rem="B"           rem="C"     rem="A"       rem="B"     rem="A"
     curr="AB"   curr="AC"         curr="BA"   curr="BC"     curr="CA"   curr="CB"
        |           |                 |           |             |           |
      Pick 'C'    Pick 'B'          Pick 'C'    Pick 'A'      Pick 'B'    Pick 'A'
      rem=""      rem=""            rem=""      rem=""        rem=""      rem=""
     curr="ABC"  curr="ACB"        curr="BAC"  curr="BCA"    curr="CAB"  curr="CBA"
        🎯          🎯                🎯          🎯            🎯          🎯
```

* **Level 0 (Root):** 3 choices (`A`, `B`, `C`)
* **Level 1:** 2 choices remaining for each branch
* **Level 2:** 1 choice remaining
* **Level 3 (Leaves):** Base case reached, all $3! = 6$ permutations recorded.

---

## ⏱️ 4. Complexity Analysis

### Time Complexity: $\mathcal{O}(N! \times N)$
1. **Number of Leaves (Permutations):** Exactly $N!$ permutations are generated.
2. **Work per Node:** 
   * `s.substring(0, i) + s.substring(i + 1)` creates a new string of length up to $N$, taking $\mathcal{O}(N)$ time.
   * `curr + ch` string concatenation takes $\mathcal{O}(N)$ time.
3. **Total Time:** $\mathcal{O}(N! \times N)$.

### Space Complexity: $\mathcal{O}(N)$ Auxiliary Stack Space
* **Call Stack Depth:** The maximum depth of the recursion tree is equal to the length of the string, so auxiliary stack space is $\mathcal{O}(N)$.
* **Heap Space:** Multiple intermediate string objects are allocated at each recursive call on the heap (handled by Java's Garbage Collector).

---

## 🔄 5. Comparison: String Substring vs. Array Backtracking (LC 46)

| Feature | String Substring Pattern (`PermutationOfString.java`) | Array Backtracking Pattern (LeetCode #46) |
| :--- | :--- | :--- |
| **Input Type** | `String s` | `int[] nums` |
| **Exclusion Method** | `s.substring(0, i) + s.substring(i + 1)` | `boolean[] visited` or `swap(nums, i, index)` |
| **State Mutation** | Creates a new String each call (Immutable) | Modifies list/array in-place, then backtracks (`curr.remove()`) |
| **Heap Allocations** | Higher (creates $\mathcal{O}(N!)$ String objects) | Lower (reuses one single `curr` list) |
| **Interview Context** | Classic college/university & string manipulation question | Standard FAANG coding round backtracking pattern |

---

## ⚠️ 6. Key Takeaways for Interviews
1. **Duplicate Characters Note:** If the input string has duplicate characters (e.g. `"AAB"`), this basic approach generates duplicate permutations (e.g. `AAB` twice). To deduplicate, either use a `HashSet<String>` or sort the string and skip adjacent identical characters (`if (i > 0 && s.charAt(i) == s.charAt(i - 1)) continue;`).
2. **Immutability of Strings:** Notice why you didn't need a manual `backtrack` step like `curr.remove()` here — because Java Strings are **immutable**. Passing `curr + ch` creates a new string expression for the child frame without modifying the parent frame's `curr`!

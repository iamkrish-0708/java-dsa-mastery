# 👑 N-Queens Problem & Advanced Constraint Backtracking: Master Lecture Notes

> **Package:** `recursion`  
> **Source Code:** [`src/recursion/NQueens.java`](file:///C:/Users/Krish/IdeaProjects/Java/src/recursion/NQueens.java) | [`src/leetcode/LC51_NQueens.java`](file:///C:/Users/Krish/IdeaProjects/Java/src/leetcode/LC51_NQueens.java)  
> **Topic:** N-Queens (LeetCode #51 - Hard), Constraint Satisfaction, Directional Board Safety & State-Space Pruning

---

## 📌 1. Problem Mechanics & The Core Constraints

The **N-Queens** problem is the holy grail of backtracking:
Place $N$ chess queens on an $N \times N$ chessboard such that **no two queens attack each other**.

### A. How Does a Queen Attack?
In chess, a Queen can attack any square in:
1. The **same row** (horizontally)
2. The **same column** (vertically)
3. Both **diagonals** (diagonally at $45^\circ$ and $135^\circ$)

### B. The Fundamental Mathematical Invariants:
* To place $N$ non-attacking queens on an $N \times N$ board:
  * Exactly **one queen per row**.
  * Exactly **one queen per column**.
* If any column or row has 0 queens, by Pigeonhole Principle, another row/col must have $\ge 2$ queens, causing an instant attack.

---

## 🧭 2. Architectural Strategy: Column-by-Column Placement

Instead of randomly picking any `(r, c)` cell on the board, we enforce a strict **column-by-column traversal**:

```
Level 0 (colIndex = 0) ──► Try placing Queen at Row 0, 1, ..., N-1
Level 1 (colIndex = 1) ──► Try placing Queen at Row 0, 1, ..., N-1
...
Level N-1 (colIndex = N-1) ──► Try placing Queen at Row 0, 1, ..., N-1
Level N (colIndex = N)   ──► BASE CASE REACHED! All N queens placed safely!
```

### Why Place Column-by-Column?
1. **Vertical Safety is 100% Free:** Because the recursion advances `colIndex + 1` at each step, **it is impossible to place two queens in the same column!** We never even need to check vertical safety.
2. **Orderly State-Space Tree:** Each tree depth strictly represents one column.

---

## 🛡️ 3. The Directional Safety Check (`safePosition`)

When deciding whether `board[rowIndex][colIndex]` is safe, which directions do we need to check?

### 💡 The Big Interview Insight: Why ONLY Check the LEFT Side?
Notice: We are placing queens from **left to right** (`col = 0, 1, 2, ...`).
* Columns to the **right** (`c > colIndex`) are **completely empty**—no queens have been placed there yet!
* Therefore, checking right horizontal, right upper diagonal, or right lower diagonal is **completely redundant**.

We only check **3 directions on the left**:
```
        \ (Left Upper Diagonal)
         \ 
  ──────── Q (rowIndex, colIndex)  <── Checking [row][colIndex]
         / 
        / (Left Lower Diagonal)
  (Left Horizontal)
```

### The 3 Safety Checks in Code:
```java
// 1. Left Horizontal (r remains constant, c decreases to 0)
int r = rowIndex, c = colIndex;
while (c >= 0) {
    if (board[r][c] == 'Q') return false;
    c--;
}

// 2. Left Upper Diagonal (r decreases, c decreases)
r = rowIndex; c = colIndex;
while (r >= 0 && c >= 0) {
    if (board[r][c] == 'Q') return false;
    r--;
    c--;
}

// 3. Left Lower Diagonal (r increases, c decreases)
r = rowIndex; c = colIndex;
while (r < n && c >= 0) {
    if (board[r][c] == 'Q') return false;
    r++;
    c--;
}

return true; // All 3 left pathways are clear!
```

---

## 🔄 4. The Choose - Explore - Unchoose Symmetry

At each column `colIndex`, we iterate through all rows `0 <= rowIndex < n`:

```java
for (int rowIndex = 0; rowIndex < n; rowIndex++) {
    if (safePosition(board, n, rowIndex, colIndex)) {
        // 1. CHOOSE: Place Queen
        board[rowIndex][colIndex] = 'Q';

        // 2. EXPLORE: Recurse to place Queen in the next column
        solve(ans, board, n, colIndex + 1);

        // 3. UNCHOOSE (BACKTRACK): Remove Queen to test next row configuration
        board[rowIndex][colIndex] = '.';
    }
}
```

### Why is Backtracking (`board[rowIndex][colIndex] = '.'`) Mandatory?
If placing a queen at `(0, 0)` leads to a dead-end at column 2 or 3, we must remove `'Q'` and restore `'.'` so that `(0, 0)` is clear when exploring placements starting at `(1, 0)`. Without reverting state, the board stays corrupted with phantom queens!

---

## 🌳 5. Full Step-by-Step Visual Dry Run ($N = 4$)

Let's trace how the algorithm solves $N = 4$:

### Board State Progression:
```
Step 1: col = 0
Try row = 0: Place Q at (0, 0)
[Q . . .]
[. . . .]
[. . . .]
[. . . .]

Step 2: col = 1
- row = 0: Attacked by (0, 0) horizontally.
- row = 1: Attacked by (0, 0) diagonally.
- row = 2: SAFE! Place Q at (2, 1)
[Q . . .]
[. . . .]
[. Q . .]
[. . . .]

Step 3: col = 2
- row = 0: Attacked by (0, 0).
- row = 1: Attacked by (2, 1) diagonally.
- row = 2: Attacked by (2, 1) horizontally.
- row = 3: Attacked by (2, 1) diagonally.
DEAD END! No row is safe in col 2.
──► BACKTRACK to col = 1! Remove Q from (2, 1).

Step 4: Back in col = 1
- Try next row: row = 3: SAFE! Place Q at (3, 1)
[Q . . .]
[. . . .]
[. . . .]
[. Q . .]

Step 5: col = 2
- row = 0: Attacked by (0, 0).
- row = 1: SAFE! Place Q at (1, 2)
[Q . . .]
[. . Q .]
[. . . .]
[. Q . .]

Step 6: col = 3
- row = 0: Attacked by (0, 0).
- row = 1: Attacked by (1, 2).
- row = 2: Attacked by (1, 2) diagonally.
- row = 3: Attacked by (3, 1).
DEAD END!
──► BACKTRACK all the way to col = 0! Remove Q from (0, 0).

Step 7: col = 0, try row = 1! Place Q at (1, 0)
[. . . .]
[Q . . .]
[. . . .]
[. . . .]
Following this path successfully reaches col = 4 without dead-ends:
Solution 1 Found:
[. . Q .]
[Q . . .]
[. . . Q]
[. Q . .]
```

---

## ⚡ 6. Complexity Analysis

### Time Complexity: $\mathcal{O}(N!)$
* In column 0, we have $N$ possible row choices.
* In column 1, at most $N - 2$ rows are safe (cannot be same row, cannot be adjacent diagonal).
* In column 2, at most $N - 4$ rows are safe.
* Total states explored $\le N \times (N - 2) \times (N - 4) \times \dots = \mathcal{O}(N!)$.
* At each leaf, copying the $N \times N$ board into `List<String>` takes $\mathcal{O}(N^2)$.
* **Overall Time:** $\mathcal{O}(N! \times N^2)$ upper bound (heavily pruned in practice, passing in **2 ms**).

### Space Complexity: $\mathcal{O}(N^2)$ Auxiliary Space
* Board Matrix: $N \times N$ characters $\implies \mathcal{O}(N^2)$.
* Recursion Call Stack: Maximum depth is $N$ frames $\implies \mathcal{O}(N)$.
* Total Auxiliary Space: $\mathcal{O}(N^2)$ (excluding the returned answer list).

---

## 🚀 7. Advanced Interview Optimization: $\mathcal{O}(1)$ Hashing Check

Notice that in your 2 ms solution, `safePosition()` scans while loops taking $\mathcal{O}(N)$ per placement check.
In a FAANG interview, the interviewer may ask: *"Can we check safety in $\mathcal{O}(1)$ time instead of $\mathcal{O}(N)$?"*

**Yes! Using 3 lookup arrays (or bitmasks):**
1. **Row Check:** `boolean[] rowLookup = new boolean[n];`
2. **Left Lower Diagonal ($r + c$):** For any cell $(r, c)$, $r + c$ is constant along the lower diagonal! Range: $0$ to $2n - 2$.
   `boolean[] lowerDiag = new boolean[2 * n - 1];`
3. **Left Upper Diagonal ($n - 1 + c - r$):** $(n - 1 + c - r)$ is constant along the upper diagonal!
   `boolean[] upperDiag = new boolean[2 * n - 1];`

With these 3 arrays, safety checking becomes a simple $\mathcal{O}(1)$ lookup:
```java
if (!rowLookup[r] && !lowerDiag[r + c] && !upperDiag[n - 1 + c - r]) {
    // Safe in O(1)!
}
```

---

## 🏆 Key Interview Takeaways
1. **Column-wise expansion** eliminates vertical collision checks for free.
2. **Only check the left side** because the right side has no queens yet.
3. **State-Space Tree Pruning:** Backtracking does NOT explore all $N^N$ combinations; it terminates paths immediately when `safePosition()` returns `false`, reducing the search space to $\mathcal{O}(N!)$.

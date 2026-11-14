# 🐭 Rat in a Maze & The Foundations of Backtracking: Master Lecture Notes

> **Package:** `recursion`  
> **Source Code:** [`src/recursion/RatInMaze.java`](file:///C:/Users/Krish/IdeaProjects/Java/src/recursion/RatInMaze.java)  
> **Topic:** Backtracking Foundations, State-Space Trees, Cycle Prevention & 4-Directional Grid Search

---

## 📌 1. What is Backtracking? (Textbook Definition vs. Practical Code)

### A. The Textbook Definition
> **Backtracking** is an algorithmic technique for solving problems recursively by trying to build a solution incrementally, **one piece at a time**, and **removing ("backtracking")** those solutions that fail to satisfy the constraints of the problem at any point in time.

### B. Pure Recursion vs. Backtracking (The Key Distinction)
* **Pure Recursion (Divide & Conquer):** You pass immutable data down (e.g. `n - 1`, `s.substring()`), solve smaller independent subproblems, and combine their returned answers. No shared state is mutated across siblings.
* **Backtracking:** You mutate a **shared, pass-by-reference state** (like a 2D `visited[][]` matrix or a `curr` list) to explore a path. When you hit a dead end, **you must explicitly undo/revert that mutation** before returning to the caller frame so subsequent sibling branches see a pristine, uncorrupted board!

### C. The Golden Backtracking Rhythm:
```
1. CHOOSE   ──> Mark cell as visited: `visited[r][c] = true;`
2. EXPLORE  ──> Recurse into all 4 possible directions (U, D, R, L)
3. UNCHOOSE ──> Backtrack / Reset state: `visited[r][c] = false;` (CRITICAL!)
```

---

## 🧭 2. Problem Mechanics: Rat in a Maze

Given an $N \times N$ grid `maze`:
* **Start:** `(0, 0)`
* **Destination:** `(N - 1, N - 1)`
* **Values:** `1` = Open path, `0` = Blocked wall.
* **Movement Rules:** Can move in 4 cardinal directions:
  * **U** (Up): `(r - 1, c)`
  * **D** (Down): `(r + 1, c)`
  * **R** (Right): `(r, c + 1)`
  * **L** (Left): `(r, c - 1)`

---

## 🛡️ 3. Why `visited[][]` and Why Backtrack?

### Why do we need `visited[][]`? (Cycle Prevention)
If you are at `(0, 0)` and step Right to `(0, 1)`, from `(0, 1)` the rat has a valid Left move back to `(0, 0)`.
* Without `visited`, the rat will bounce infinitely between `(0, 0) <---> (0, 1)` until Java crashes with a **`StackOverflowError`**!
* `visited[r][c] = true` guarantees **no cell is visited more than once in the same active path**.

### Why MUST we backtrack (`visited[r][c] = false`)?
* A cell marked visited in Path A might be the **only valid bridge** for Path B!
* If you leave `visited[r][c] = true` permanently after returning, alternative paths can never cross through that cell, causing valid solutions to be missed.
* **Rule:** Marking a cell `visited = true` is only valid for the *current exploration path*. When the function returns, it frees the cell for other paths.

---

## 🔍 4. The 4 Safety Guard Conditions (`isSafe()`)

Before making any recursive call, we validate whether cell `(r, c)` is safe:

```java
static boolean isSafe(int[][] maze, boolean[][] visited, int r, int c) {
    int n = maze.length;

    // 1. Array Index Out of Bounds Guard
    if (r < 0 || r >= n || c < 0 || c >= n) {
        return false;
    }
    // 2. Obstacle / Wall Guard
    if (maze[r][c] == 0) {
        return false;
    }
    // 3. Cycle Prevention Guard (Already in current path)
    if (visited[r][c]) {
        return false;
    }
    // 4. Safe to proceed
    return true;
}
```

---

## 🌲 5. Visual Step-by-Step Dry Run Example

Consider this $3 \times 3$ maze:
```text
  c=0  c=1  c=2
r=0 [ 1,   1,   0 ]   Start at (0, 0)
r=1 [ 1,   1,   0 ]   Destination at (2, 2)
r=2 [ 0,   1,   1 ]
```

```text
STEP 1: At (0, 0)
- Mark visited[0][0] = true.
- Path = ""
- Try Up: (-1, 0) -> Out of bounds.
- Try Down: (1, 0) -> Open & unvisited! Move Down!

STEP 2: At (1, 0)
- Mark visited[1][0] = true.
- Path = "D"
- Try Up: (0, 0) -> visited! Skip.
- Try Down: (2, 0) -> maze[2][0] == 0 (WALL!). Skip.
- Try Right: (1, 1) -> Open & unvisited! Move Right!

STEP 3: At (1, 1)
- Mark visited[1][1] = true.
- Path = "DR"
- Try Up: (0, 1) -> Open & unvisited! Move Up!

STEP 4: At (0, 1)
- Mark visited[0][1] = true.
- Path = "DRU"
- Try Up: Out of bounds.
- Try Down: (1, 1) visited!
- Try Right: (0, 2) is a wall (0)!
- Try Left: (0, 0) visited!
- 💥 DEAD END! All 4 moves fail!

STEP 5: BACKTRACKING IN ACTION!
- Unmark visited[0][1] = false. Backtrack to (1, 1).
- At (1, 1): Up failed. Now try Down: (2, 1) -> Open & unvisited! Move Down!

STEP 6: At (2, 1)
- Mark visited[2][1] = true.
- Path = "DRD"
- Try Right: (2, 2) -> DESTINATION REACHED! 🎯
- Add "DRDR" to ans!

STEP 7: Backtrack all the way to explore other routes (e.g. starting with Right from (0, 0)).
```

---

## ⚡ 6. The Lexicographical Order Pro-Tip (D-L-R-U)

GFG requires paths in **alphabetical order**:
In your solution, you explored: `Up -> Down -> Right -> Left`, which produced paths in random order, requiring `Collections.sort(ans)`.

> **Pro-Tip:** If you explore in alphabetical order of letters:
> 1. **D**own (`r + 1, c`)
> 2. **L**eft (`r, c - 1`)
> 3. **R**ight (`r, c + 1`)
> 4. **U**p (`r - 1, c`)
> 
> The recursion tree naturally produces the paths in **strictly sorted alphabetical order**! You can completely eliminate `Collections.sort(ans)`.

---

## ⚠️ 7. Critical Edge Cases to Guard Against

1. **Blocked Start:** If `maze[0][0] == 0`, the rat cannot even enter! Immediately return empty list `[]`.
2. **Blocked Destination:** If `maze[n-1][n-1] == 0`, reaching destination is physically impossible! Return `[]`.
3. **Single Cell Maze ($N = 1$):**
   * If `maze[0][0] == 1`, start IS destination! Returns `[""]` (zero moves needed).

---

## ⏱️ 8. Complexity Analysis

| Metric | Complexity | Explanation |
| :--- | :--- | :--- |
| **Time Complexity** | $\mathcal{O}(4^{N^2})$ | Upper bound: From each of the $N^2$ cells, there are at most 4 choices. In practice, obstacles and `visited` pruning reduce this drastically. |
| **Auxiliary Space** | $\mathcal{O}(N^2)$ | $\mathcal{O}(N^2)$ for the 2D boolean `visited` array + $\mathcal{O}(N^2)$ maximum recursion stack depth in the worst-case snake path. |

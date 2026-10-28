# ⏱️ Time & Space Complexity of Recursion (Lecture 55)

> **Core Philosophy:** Never memorize Time & Space Complexity formulas. Build the **recursion tree mental model**, count the nodes and depth, and the complexity reveals itself naturally on paper and in interviews/viva.

---

## 🧠 1. The Universal Mental Model

Whenever you look at a recursive function, ask **only two questions**:

1. **How many total function calls (nodes in recursion tree) are made?** $\implies$ **Time Complexity (TC)**
2. **What is the maximum depth (height of call stack) at any single moment?** $\implies$ **Space Complexity (SC)**

$$\text{Time Complexity} = (\text{Total Recursive Calls}) \times (\text{Work done per call})$$
$$\text{Space Complexity} = (\text{Max Depth of Call Stack}) \times (\text{Memory per stack frame}) + \text{Auxiliary Heap Space}$$

> 💡 **Crucial Interview Insight on Space Complexity:**
> Stack frames do **not** stay in memory forever! Once a base case returns, its stack frame is **popped immediately**. 
> Therefore, Space Complexity is **NEVER** the total number of calls; it is only the **maximum depth reached simultaneously** from root to leaf along any branch.

---

## 🔍 2. Linear Recursion (Single Branch per Call)

In linear recursion, each call spawns **at most 1** child call. The recursion tree is a single straight line.

---

### Example A: Print "Hello" $N$ Times

```java
void printHello(int n) {
    if (n == 0) return;
    System.out.println("Hello"); // O(1) work
    printHello(n - 1);           // 1 call with (n - 1)
}
```

#### 🌲 Recursion Tree & Call Stack:
```
printHello(n)
    └── printHello(n - 1)
            └── printHello(n - 2)
                    └── ...
                         └── printHello(1)
                                └── printHello(0) [Base Case Return]
```

* **Number of calls:** $n + 1$ calls ($n, n-1, \dots, 0$)
* **Work per call:** $\mathcal{O}(1)$ (one print statement)
* **$\text{TC} = (n + 1) \times \mathcal{O}(1) = \mathbf{\mathcal{O}(n)}$**
* **Max stack depth:** At `printHello(0)`, there are $(n + 1)$ frames sitting on top of each other.
* **$\text{SC} = \mathbf{\mathcal{O}(n)}$** (auxiliary stack space)

---

### Example B: Factorial ($N!$)

```java
int fact(int n) {
    if (n == 0 || n == 1) return 1;
    return n * fact(n - 1);
}
```

#### 🌲 Recursion Trace:
```
fact(4) 
  └── 4 * fact(3)
            └── 3 * fact(2)
                      └── 2 * fact(1) [Base Case: returns 1]
```

* **Recurrence Relation:** $T(n) = T(n - 1) + \mathcal{O}(1)$
* **Expansion:**
  $$T(n) = T(n - 1) + 1$$
  $$T(n) = T(n - 2) + 1 + 1$$
  $$T(n) = T(0) + \underbrace{1 + 1 + \dots + 1}_{n \text{ times}} = n + 1$$
* **$\text{TC} = \mathbf{\mathcal{O}(n)}$**
* **$\text{SC} = \mathbf{\mathcal{O}(n)}$** (Stack holds `fact(4)`, `fact(3)`, `fact(2)`, `fact(1)` simultaneously before unwinding).

---

### Example C: Power of Two / Power of Three (Reduction by 1)

```java
// Calculating 2^n = 2 * 2^(n-1)
int power(int n) {
    if (n == 0) return 1;
    return 2 * power(n - 1);
}
```

* Function called $n + 1$ times.
* Work per call: 1 multiplication ($\mathcal{O}(1)$).
* **$\text{TC} = \mathbf{\mathcal{O}(n)}$**
* **$\text{SC} = \mathbf{\mathcal{O}(n)}$**

---

## ⚡ 3. Divide & Conquer Recursion (Problem Size Halves: $N \to N/2$)

What happens when each step reduces the problem size by **dividing** rather than subtracting?

---

### Example D: Binary Search using Recursion

```java
int binarySearch(int[] arr, int target, int low, int high) {
    if (low > high) return -1;
    int mid = low + (high - low) / 2;
    if (arr[mid] == target) return mid;
    
    if (arr[mid] > target) {
        return binarySearch(arr, target, low, mid - 1); // Only 1 branch taken!
    } else {
        return binarySearch(arr, target, mid + 1, high); // Only 1 branch taken!
    }
}
```

> ⚠️ **Key Trap in Interviews:** Binary Search code has *two* recursive calls written, but because of `if-else`, **ONLY ONE is executed per level!**

#### 🌲 Tree Structure:
At each step, array size goes: $N \to \frac{N}{2} \to \frac{N}{4} \to \dots \to 1$

How many times can you divide $N$ by 2 until you reach 1?
$$\frac{N}{2^k} = 1 \implies N = 2^k \implies k = \log_2 N$$

* **Total Calls:** $k + 1 = \log_2 N + 1$
* **Work per Call:** $\mathcal{O}(1)$ (calculating mid and comparisons)
* **$\text{TC} = \mathbf{\mathcal{O}(\log N)}$**
* **$\text{SC} = \mathbf{\mathcal{O}(\log N)}$** (at most $\log_2 N$ stack frames active at any time)

---

## 🌳 4. Tree / Multiple Branch Recursion (Branching Factor > 1)

When a function calls itself **more than once**, the tree branches out exponentially.

---

### Example E: Nth Fibonacci Number (Naive Recursive)

```java
int fib(int n) {
    if (n == 0) return 0;
    if (n == 1) return 1;
    return fib(n - 1) + fib(n - 2); // 2 recursive calls per invocation!
}
```

#### 🌲 Recursion Tree for `fib(4)`:
```
                     fib(4)                     Level 0: 2^0 = 1 node
                   /        \
              fib(3)          fib(2)            Level 1: 2^1 = 2 nodes
             /      \        /      \
         fib(2)    fib(1)  fib(1)   fib(0)      Level 2: 2^2 = 4 nodes
        /      \
     fib(1)   fib(0)                            Level 3: 2^3 = 8 nodes (upper bound)
```

#### Step-by-Step Mathematical Evaluation on Paper:
1. **Branching Factor ($b$):** Each node has up to $2$ children.
2. **Height of Tree ($h$):** Longest branch goes along $n - 1$ all the way to base cases: $\text{height} = n$.
3. **Total Nodes (Total Work):**
   $$\text{Total Calls} \approx 2^0 + 2^1 + 2^2 + \dots + 2^n = \frac{2^{n+1} - 1}{2 - 1} = 2^{n+1} - 1$$
   $$\implies \mathbf{\text{TC} = \mathcal{O}(2^n)}$$ *(Strict tight bound is $\mathcal{O}(\phi^n) \approx 1.618^n$, but $\mathcal{O}(2^n)$ is the standard accepted upper bound in interviews).*

#### Space Complexity of `fib(n)` (The Common Viva Trap!):
* **Trap:** Candidates often say $\text{SC} = \mathcal{O}(2^n)$. **WRONG!**
* **Reality:** Java evaluates **left branch first**.
  `fib(4)` calls `fib(3)` $\to$ `fib(2)` $\to$ `fib(1)`.
  When `fib(1)` returns, that frame is **destroyed** before `fib(0)` is even pushed!
* The stack only holds nodes along a **single path from root to leaf**.
* **Longest path from root to leaf = $n$**.
* **$\text{SC} = \mathbf{\mathcal{O}(n)}$** (Linear Auxiliary Stack Space!)

---

## 📊 5. Summary Cheat Sheet for Viva & Interviews

| Problem Pattern | Recurrence Form | Branching Factor | Tree Depth | Time Complexity (TC) | Space Complexity (SC) |
|---|---|---|---|---|---|
| **Print 1..N / Factorial** | $T(n) = T(n-1) + \mathcal{O}(1)$ | 1 | $n$ | $\mathbf{\mathcal{O}(n)}$ | $\mathbf{\mathcal{O}(n)}$ |
| **Sum / Count of digits** | $T(n) = T(n/10) + \mathcal{O}(1)$ | 1 | $\log_{10} n$ | $\mathbf{\mathcal{O}(\log_{10} n)}$ | $\mathbf{\mathcal{O}(\log_{10} n)}$ |
| **Power of 3 (Division)** | $T(n) = T(n/3) + \mathcal{O}(1)$ | 1 | $\log_3 n$ | $\mathbf{\mathcal{O}(\log_3 n)}$ | $\mathbf{\mathcal{O}(\log_3 n)}$ |
| **Binary Search** | $T(n) = T(n/2) + \mathcal{O}(1)$ | 1 | $\log_2 n$ | $\mathbf{\mathcal{O}(\log_2 n)}$ | $\mathbf{\mathcal{O}(\log_2 n)}$ |
| **Fibonacci (Naive)** | $T(n) = T(n-1) + T(n-2) + \mathcal{O}(1)$ | 2 | $n$ | $\mathbf{\mathcal{O}(2^n)}$ | $\mathbf{\mathcal{O}(n)}$ *(Max depth!)* |

---

## 📝 6. The 3-Step "On Paper" Viva Method

When handed a piece of paper or asked in viva: *"Find TC and SC of this recursive function"*:

1. **Draw the tree for $N = 3$ or $N = 4$:**
   * Count how many calls each function produces ($1, 2, \text{etc.}$).
2. **Find the depth:**
   * If step size decreases by subtraction ($-1, -2$): depth is linear $\implies \mathcal{O}(n)$.
   * If step size divides ($/ 2, / 10$): depth is logarithmic $\implies \mathcal{O}(\log n)$.
3. **Formulate:**
   * $\text{TC} = \text{Total Nodes} \times (\text{Work per node})$.
   * $\text{SC} = \text{Height of tree} \times (\text{Memory per call})$.

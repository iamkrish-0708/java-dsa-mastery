# ⏱️ Time & Space Complexity Cheatsheet for Java DSA

---

## 📌 1. Fundamentals: What is Complexity Analysis?

When evaluating code efficiency, we **do not** measure physical execution time in seconds because runtime depends on CPU speed, RAM, background apps, and compiler optimizations.

Instead, we measure **Asymptotic Complexity**:
* **Time Complexity:** How the execution time (number of operations) grows relative to input size $N$.
* **Space Complexity:** How the extra memory (auxiliary memory) allocated by the algorithm grows relative to input size $N$.

---

## 🏛️ 2. Asymptotic Notations

| Notation | Name | Meaning | Use Case |
| :--- | :--- | :--- | :--- |
| **$O$ (Big-O)** | Upper Bound | **Worst Case** (Maximum operations the code will ever take) | **Primary focus for coding interviews** |
| **$\Omega$ (Big-Omega)** | Lower Bound | **Best Case** (Minimum operations the code can take) | Identifies ideal conditions (e.g. sorted array) |
| **$\Theta$ (Big-Theta)** | Tight Bound | **Average Case** (Upper and lower bound are equal) | Precise growth rate description |

> [!IMPORTANT]
> In coding interviews and Online Assessments (OAs), **Big-O (Worst Case)** is almost always expected unless specified otherwise.

---

## 📈 3. Big-O Complexity Ranking (Fastest to Slowest)

$$O(1) < O(\log N) < O(N) < O(N \log N) < O(N^2) < O(2^N) < O(N!)$$

```
Fastest / Best  ──►  O(1)         : Constant Time (Direct array lookup: arr[i])
                     O(log N)     : Logarithmic Time (Binary Search, n = n / 2)
                     O(N)         : Linear Time (Single loop over array)
                     O(N log N)   : Linearithmic Time (Merge Sort, Quick Sort)
                     O(N²)        : Quadratic Time (Nested loops)
                     O(2ⁿ)        : Exponential Time (Recursive Fibonacci)
Slowest / Worst ──►  O(N!)        : Factorial Time (Generating all permutations)
```

---

## 📏 4. Rules for Calculating Big-O

### Rule 1: Drop Constants
Ignore constant multipliers because we care about growth rate for large $N$.
* $O(2N) \rightarrow O(N)$
* $O(500) \rightarrow O(1)$
* $O(3N^2 + 5N + 100) \rightarrow O(N^2)$

### Rule 2: Drop Non-Dominant Terms
Focus only on the term with the highest growth rate.
* $O(N^2 + N) \rightarrow O(N^2)$
* $O(N + \log N) \rightarrow O(N)$

### Rule 3: Add for Sequential Steps, Multiply for Nested Steps
* **Sequential Loops (Add):**
  ```java
  for (int i = 0; i < N; i++) { ... } // O(N)
  for (int j = 0; j < M; j++) { ... } // O(M)
  // Total: O(N + M)
  ```
* **Nested Loops (Multiply):**
  ```java
  for (int i = 0; i < N; i++) {       // O(N)
      for (int j = 0; j < N; j++) {   // O(N)
          ...
      }
  }
  // Total: O(N * N) = O(N²)
  ```

---

## 💻 5. Common Code Patterns & Their Complexity

### 🔹 Pattern 1: $O(1)$ - Constant Time
Operations execute in fixed time regardless of input size $N$.
```java
int getFirstElement(int[] nums) {
    return nums[0]; // O(1) time, O(1) space
}
```

### 🔹 Pattern 2: $O(N)$ - Linear Time
Single loop running $N$ times.
```java
int sumArray(int[] nums) {
    int total = 0;
    for (int i = 0; i < nums.length; i++) { // Runs N times
        total += nums[i];
    }
    return total; // O(N) time, O(1) space
}
```

### 🔹 Pattern 3: $O(\log N)$ - Logarithmic Time
Input size is halved (or divided/multiplied by a constant) at each step.
```java
void divideByTwo(int n) {
    while (n > 0) {
        n = n / 2; // Reduces input by half each step -> O(log N)
    }
}
```

### 🔹 Pattern 4: $O(N^2)$ - Quadratic Time
Nested loop where inner loop runs $N$ times for each outer loop iteration.
```java
void printPairs(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
        for (int j = 0; j < nums.length; j++) {
            System.out.println(nums[i] + ", " + nums[j]);
        }
    }
}
```

---

## 💾 6. Time vs. Auxiliary Space Complexity

* **Total Space Complexity:** Input Space + Auxiliary (Extra) Space.
* **Auxiliary Space Complexity:** The **extra** memory allocated by the algorithm excluding the input data.

```java
// O(1) Auxiliary Space (modifies input in-place, uses scalar variables)
int sum(int[] arr) {
    int s = 0; // 1 variable
    for (int val : arr) s += val;
    return s;
}

// O(N) Auxiliary Space (creates new array of size N)
int[] copyArray(int[] arr) {
    int[] newArr = new int[arr.length]; // Allocates N memory
    for (int i = 0; i < arr.length; i++) newArr[i] = arr[i];
    return newArr;
}
```

---

## 🧠 7. Quick Reference Rule of Thumb for Java DSA

| Operation Pattern | Time Complexity |
| :--- | :--- |
| `i++` or `i--` in single loop | $O(N)$ |
| `i = i * 2` or `i = i / 2` in loop | $O(\log N)$ |
| Nested loop ($N \times N$) | $O(N^2)$ |
| Nested loop where inner loop does division | $O(N \log N)$ |
| Binary Search | $O(\log N)$ |
| Sorting (`Arrays.sort()` in Java - Dual-Pivot Quicksort / TimSort) | $O(N \log N)$ |

---
*Created for Java DSA & LeetCode Practice Track | Target: Summer 2027 Internships*

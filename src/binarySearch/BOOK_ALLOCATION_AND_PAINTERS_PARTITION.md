# 📚 Binary Search on Answer Space: Book Allocation & Painter's Partition

> **Core Concept:** Minimizing the Maximum contiguous allocation across $K$ partitions using Binary Search over the monotonic answer space.

---

## 🧭 The 4-Way Equivalence (The Story Disguises)

In technical interviews and online platforms, the exact same mathematical problem appears under four different real-world stories:

| Problem Name | Context / Array Elements | Partitions ($K$) | Target to Minimize | LeetCode / Platform |
| :--- | :--- | :--- | :--- | :--- |
| **Book Allocation** | Book pages `pages[i]` | $M$ Students | Maximum pages allocated to any single student | GFG / CodeStudio |
| **Painter's Partition** | Board lengths `boards[i]` | $K$ Painters | Maximum time spent painting by any single painter | GFG / InterviewBit |
| **Split Array Largest Sum** | Array values `nums[i]` | $K$ Subarrays | Maximum subarray sum among $K$ splits | **[LeetCode #410 (Hard)](https://leetcode.com/problems/split-array-largest-sum/)** |
| **Ship Packages in D Days** | Package weights `weights[i]` | $D$ Days | Maximum ship weight capacity required | **[LeetCode #1011 (Medium)](https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/)** |

> **Key Takeaway:** If you understand one of them, you have mastered all four! They share the exact same greedy validation function and binary search bounds.

---

## 🔍 Why Binary Search? (The Monotonicity Property)

Unlike traditional binary search which operates on a sorted index space, here we binary search on the **possible answer values**:

* **If a capacity/limit $X$ is valid:**
  * Any value $> X$ will also be valid (more capacity makes fitting into $K$ partitions even easier).
  * To find the **minimum valid value**, we record $X$ as a candidate and search to the left: `end = mid - 1`.
* **If a capacity/limit $X$ is invalid:**
  * Any value $< X$ will definitely fail (less capacity cannot possibly fit).
  * We must increase capacity and search to the right: `start = mid + 1`.

```
Validity:   [ False, False, False, True, True, True, True ]
                                    ^
                             Target (Minimum Valid Mid)
```

---

## 📐 Search Space Bounds

* **Lower Bound (`start`):** $\max(\text{arr})$
  * *Reason:* If your capacity is smaller than the single largest book/board/package, that individual item can never be allocated to anyone.
  * *Bonus:* Setting `start = max(arr)` guarantees that `mid >= arr[i]` for every element, removing the need for `if (arr[i] > mid) return false;` inside the validation loop.
* **Upper Bound (`end`):** $\sum \text{arr}$
  * *Reason:* If only 1 student/painter/day is available, they must take the entire sum.

---

## ⚙️ The Algorithm Blueprint

### 1. The Greedy Validator (`isValid`)
Iterate through the array greedily accumulating elements into the current partition. When adding an element exceeds `mid`, start a new partition (`currentPartitions++`). If total partitions exceed $K$, return `false`.

```java
static boolean isValid(int[] arr, int mid, int k) {
    int partitions = 1;
    int currentSum = 0;
    
    for (int val : arr) {
        if (currentSum + val <= mid) {
            currentSum += val;
        } else {
            partitions++;
            if (partitions > k) {
                return false;
            }
            currentSum = val;
        }
    }
    return true;
}
```

### 2. The Binary Search Loop

```java
public static int findMinimumCapacity(int[] arr, int k) {
    int n = arr.length;
    if (k > n) return -1; // Edge case: more partitions than items (for book allocation)
    
    int start = 0;
    int sum = 0;
    for (int val : arr) {
        start = Math.max(start, val);
        sum += val;
    }
    int end = sum;
    int ans = -1;
    
    while (start <= end) {
        int mid = start + (end - start) / 2;
        if (isValid(arr, mid, k)) {
            ans = mid;        // Candidate found!
            end = mid - 1;    // Try to find a smaller valid maximum
        } else {
            start = mid + 1;  // Capacity too small, increase limit
        }
    }
    return ans;
}
```

---

## 📊 Complexity Analysis

* **Time Complexity:** $\mathcal{O}(N \cdot \log(\sum \text{arr} - \max(\text{arr})))$
  * In each of the $\mathcal{O}(\log(\text{Range}))$ binary search iterations, we scan all $N$ elements once in $\mathcal{O}(N)$.
  * For $N = 10^5$ and $\sum \text{arr} \approx 10^9$, $\log_2(10^9) \approx 30$ iterations $\implies \approx 3 \times 10^6$ operations (easily executes in $< 10\text{ ms}$).
* **Space Complexity:** $\mathcal{O}(1)$ auxiliary space.

---

## ⚠️ Common Pitfalls & Edge Cases

1. **Integer Overflow:**
   * If $\sum \text{arr} > 2^{31} - 1$, use `long` for `sum`, `start`, `end`, and `mid`.
2. **Boundary Check (`k > n`):**
   * If each person must get at least 1 book and students $M > N$ books, allocation is impossible $\implies$ return `-1`.
3. **Partition Initialization:**
   * Always start with `partitions = 1` (since the first student/day/painter is active from index 0).

---

## 🔄 Dual Pattern Preview: Min-of-Max vs Max-of-Min

| Pattern Type | Examples | Binary Search Direction on `isValid == true` |
| :--- | :--- | :--- |
| **Minimizing the Maximum** | Book Allocation, Painter's Partition, LC 410, LC 1011 | `ans = mid; end = mid - 1;` (Find smaller valid bound) |
| **Maximizing the Minimum** | Aggressive Cows (Lecture 43), LC 1552 (Magnetic Force) | `ans = mid; start = mid + 1;` (Find larger valid distance) |

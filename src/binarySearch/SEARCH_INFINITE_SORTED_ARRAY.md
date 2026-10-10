# ♾️ Search in an Infinite / Unknown Size Sorted Array (Exponential Search)

> **Core Concept:** When searching in a sorted array of infinite or unknown size, we cannot use `arr.length - 1`. Instead, we find the search range **exponentially** by doubling the window size ($\mathcal{O}(\log T)$), followed by standard **Binary Search** ($\mathcal{O}(\log T)$).

---

## 🧭 Problem Statement

Given a sorted array of **infinite length** (or unknown size, accessed via an `ArrayReader` interface where `reader.get(k)` returns the element at index $k$ or `Integer.MAX_VALUE` if out of bounds), find the index of target $K$. If $K$ is not present, return `-1`.

* **Example 1:** `arr = [-1, 0, 3, 5, 9, 12, ...]`, `target = 9` $\implies$ **Index `4`**
* **Example 2:** `arr = [-1, 0, 3, 5, 9, 12, ...]`, `target = 2` $\implies$ **`-1` (Not Found)**

---

## 🔍 The Key Insights & Intuition

### 1. Why Standard Binary Search Fails
* In standard Binary Search, we initialize `start = 0` and `end = arr.length - 1`.
* In an **infinite array** or with **`ArrayReader` (LeetCode 702)**, we do **not know the size**, so we cannot set `end` directly.

### 2. Step 1: Exponential Window Expansion ($\times 2$)
* Start with a small window: `s = 0`, `e = 1` (or pointer `i = 1`).
* As long as the current element is smaller than `target`, double the index:
  $$\text{window size doubles: } 1 \rightarrow 2 \rightarrow 4 \rightarrow 8 \rightarrow 16 \rightarrow \dots \rightarrow 2^k$$
* When `reader.get(e) >= target`, we know the target **must** lie in the range $[s, e]$ (or $[i/2, i]$).

### 3. Step 2: Bounded Binary Search
* Once the range $[s, e]$ is identified, apply standard Binary Search within that bounded window.

### 4. Bounded vs. Unbounded Search (Crucial Interview Trap ⚠️)
* **Unbounded Search (LC 702 / Infinite Array):** $N$ is unknown $\rightarrow$ MUST find range exponentially first.
* **Bounded Search (LC 374 / LC 278):** $N$ is explicitly given $\rightarrow$ directly binary search in $[1, n]$ without exponential loop!

---

## ⚙️ Complete Java Implementation

```java
package binarySearch;

// Mock interface representing LeetCode 702
interface ArrayReader {
    int get(int index);
}

public class SearchInfiniteSortedArray {

    public static int search(ArrayReader reader, int target) {
        // Step 1: Find the search range [s, e] exponentially
        int s = 0;
        int e = 1;

        while (reader.get(e) < target) {
            s = e;
            e = e * 2; // Exponential expansion
        }

        // Step 2: Standard Binary Search within [s, e]
        while (s <= e) {
            int mid = s + (e - s) / 2;
            int val = reader.get(mid);

            if (val == target) {
                return mid;
            } else if (val < target) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }

        return -1; // Target not found
    }
}
```

---

## 📊 Complexity Analysis

* **Time Complexity:** $\mathcal{O}(\log T)$ where $T$ is the index of the target.
  * Finding the range $[s, e]$ takes $\mathcal{O}(\log T)$ steps (doubling index until $\ge T$).
  * Binary searching within range $[s, e]$ of length at most $2T$ takes $\mathcal{O}(\log T)$ steps.
  * **Total Time Complexity:** $\mathcal{O}(\log T) + \mathcal{O}(\log T) = \mathcal{O}(\log T)$.
* **Space Complexity:** $\mathcal{O}(1)$ auxiliary space (in-place iterative pointer manipulation).

---

## 🔗 Related Questions
* **LeetCode #702:** Search in a Sorted Array of Unknown Size (Locked / Premium)
* **LeetCode #374:** Guess Number Higher or Lower (Bounded API-based Binary Search)
* **LeetCode #278:** First Bad Version (Boolean Boundary Binary Search)
* **GeeksforGeeks:** Find position of an element in a sorted array of infinite numbers

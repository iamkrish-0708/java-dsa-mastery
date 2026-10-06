# 🔄 Search in a Nearly Sorted (Modified) Array

> **Core Concept:** Binary Search where each element at sorted index $i$ could be shifted to index $i - 1$, $i$, or $i + 1$.

---

## 🧭 Problem Statement

Given an array where every element is at most **1 position away** from its sorted position (i.e. element that should be at index $i$ is at $i-1$, $i$, or $i+1$), find the index of target $K$. If not found, return `-1`.

* **Example:** `arr = [3, 5, 10, 9, 11]`, `k = 10`
* Sorted version would be `[3, 5, 9, 10, 11]`. The elements `9` and `10` were swapped.
* **Target 10 is at index 2.**

---

## 🔍 The Key Insights

1. **3-Way Target Inspection:**
   * In standard binary search, we only check `arr[mid] == k`.
   * In a nearly sorted array, target could be at:
     1. `arr[mid]`
     2. `arr[mid - 1]` (if `mid - 1 >= 0`)
     3. `arr[mid + 1]` (if `mid + 1 < n`)
2. **Jump by 2 (`mid + 2` and `mid - 2`):**
   * Because you have already inspected `mid - 1`, `mid`, and `mid + 1`:
     * If `arr[mid] < k`: Target must be strictly to the right of `mid + 1` $\implies$ **`start = mid + 2`**.
     * If `arr[mid] > k`: Target must be strictly to the left of `mid - 1` $\implies$ **`end = mid - 2`**.

---

## ⚙️ Complete Implementation

```java
package binarySearch;

public class NearlySortedArray {

    public static int search(int[] arr, int k) {
        int n = arr.length;
        int start = 0;
        int end = n - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            // Check mid
            if (arr[mid] == k) {
                return mid;
            }

            // Check mid - 1 safely
            if (mid - 1 >= 0 && arr[mid - 1] == k) {
                return mid - 1;
            }

            // Check mid + 1 safely
            if (mid + 1 < n && arr[mid + 1] == k) {
                return mid + 1;
            }

            // Update bounds by jumping 2 steps
            if (arr[mid] < k) {
                start = mid + 2;
            } else {
                end = mid - 2;
            }
        }

        return -1; // Element not found
    }
}
```

---

## 📊 Complexity Analysis

* **Time Complexity:** $\mathcal{O}(\log N)$
  * In each iteration, we eliminate at least half the array (stepping by 2).
* **Space Complexity:** $\mathcal{O}(1)$ auxiliary space.

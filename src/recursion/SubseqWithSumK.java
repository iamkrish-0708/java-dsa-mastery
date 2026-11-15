package recursion;

// GeeksforGeeks - Check if there exists a subsequence with sum K (Subset Sum with K)
// Difficulty: Medium
// Problem: Given an array of integers arr and a target value k, return true if there exists
//          a subsequence in arr whose elements sum up to k, else return false.
//
// =================================================================================================
// 💡 TECHNIQUE: BINARY RECURSION (INCLUDE / EXCLUDE) WITH SHORT-CIRCUITING (PASSED ON GFG ⚡)
// =================================================================================================
// - Mechanics:
//     1. Early Pruning: If the total sum of all elements in arr is less than k, impossible -> return false.
//     2. Base Case 1 (Success): k == 0 indicates we successfully picked a subset summing to k -> return true.
//     3. Base Case 2 (Failure): index >= arr.length (exhausted array) or k < 0 (overshot target) -> return false.
//     4. Binary Choice & Short-Circuiting:
//        - Include arr[index]: solve(arr, k - arr[index], index + 1)
//        - Exclude arr[index]: solve(arr, k, index + 1)
//        The || operator guarantees that as soon as the include branch returns true, the exclude
//        branch is skipped completely.
//
// Complexity:
//   - Time Complexity: O(2^N) in worst case where N is arr.length (pruned heavily by short-circuiting & bounds).
//   - Space Complexity: O(N) Auxiliary Space for the recursive call stack.
// =================================================================================================

class SolutionSubseqWithSumK {
    static boolean solve(int[] arr, int k, int index) {
        if (k == 0) {
            return true;
        }
        if (index >= arr.length || k < 0) {
            return false;
        }

        return solve(arr, k - arr[index], index + 1) || solve(arr, k, index + 1);
    }

    public boolean checkSubsequenceSum(int[] arr, int k) {
        int totalSum = 0;
        for (int n : arr) {
            totalSum += n;
        }
        if (totalSum < k) {
            return false;
        }

        return solve(arr, k, 0);
    }
}

public class SubseqWithSumK {
    public static void main(String[] args) {
        SolutionSubseqWithSumK solver = new SolutionSubseqWithSumK();

        // Test 1: arr = [10, 1, 2, 7, 6, 1, 5], k = 8 -> true (e.g., [1, 7] or [2, 6])
        int[] arr1 = {10, 1, 2, 7, 6, 1, 5};
        int k1 = 8;
        System.out.println("Test 1: " + solver.checkSubsequenceSum(arr1, k1) + " (Expected: true)");

        // Test 2: arr = [2, 5], k = 5 -> true ([5])
        int[] arr2 = {2, 5};
        int k2 = 5;
        System.out.println("Test 2: " + solver.checkSubsequenceSum(arr2, k2) + " (Expected: true)");

        // Test 3: arr = [1, 2, 3], k = 10 -> false (total sum 6 < 10)
        int[] arr3 = {1, 2, 3};
        int k3 = 10;
        System.out.println("Test 3: " + solver.checkSubsequenceSum(arr3, k3) + " (Expected: false)");
    }
}

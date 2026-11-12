package leetcode;

import java.util.*;

// LeetCode #216 - Combination Sum III
// Difficulty: Medium
// Problem: Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
//          - Only numbers 1 through 9 are used.
//          - Each number is used at most once.
//          Return a list of all possible valid combinations. The list must not contain the same combination twice,
//          and the combinations may be returned in any order.
//
// =================================================================================================
// 💡 TECHNIQUE: BACKTRACKING WITH SIZE & SUM BOUNDARY PRUNING (0 ms Beats 100% ⚡)
// =================================================================================================
// - Candidates pool: Fixed digits 1 through 9.
// - Binary Include/Exclude Decision Tree:
//     * Base Case 1 (Success): If n == 0 && curr.size() == k -> Add copy to ans and return.
//     * Base Case 2 (Out of Bounds / Negative Sum): If i >= candidates.length || n < 0 -> Return.
//     * Pruning (Size Limit): If curr.size() == k -> Already has k elements but sum != n,
//       adding more elements will violate the k constraint, so return immediately!
//     * Include: Add candidates[i], recurse with n - candidates[i] and i + 1.
//     * Backtrack: Remove last element.
//     * Exclude: Recurse with unchanged n and i + 1.
//
// Complexity:
//   - Time Complexity: O(C(9, k) * k) bounded by at most C(9, k) combinations -> executes in 0 ms!
//   - Space Complexity: O(k) Auxiliary recursion stack depth and combination buffer.
// =================================================================================================

public class LC216_CombinationSumIII {

    static void solve(int k, int n, int[] candidates, List<List<Integer>> ans, List<Integer> curr, int i) {
        if (n == 0 && curr.size() == k) {
            ans.add(new ArrayList<>(curr));
            return;
        }
        if (i >= candidates.length || n < 0) {
            return;
        }
        // Pruning: If size k reached but target sum != 0, further elements will exceed size k
        if (curr.size() == k) {
            return;
        }
        // Include
        curr.add(candidates[i]);
        solve(k, n - candidates[i], candidates, ans, curr, i + 1);

        // Exclude / Backtrack
        curr.remove(curr.size() - 1);
        solve(k, n, candidates, ans, curr, i + 1);
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        int[] candidates = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        solve(k, n, candidates, ans, curr, 0);
        return ans;
    }

    public static void main(String[] args) {
        LC216_CombinationSumIII solver = new LC216_CombinationSumIII();

        System.out.println("Test 1 (k=3, n=7)  Expected: [[1, 2, 4]] | Output: " + solver.combinationSum3(3, 7));
        System.out.println("Test 2 (k=3, n=9)  Expected: [[1, 2, 6], [1, 3, 5], [2, 3, 4]] | Output: " + solver.combinationSum3(3, 9));
        System.out.println("Test 3 (k=4, n=1)  Expected: [] | Output: " + solver.combinationSum3(4, 1));
    }
}

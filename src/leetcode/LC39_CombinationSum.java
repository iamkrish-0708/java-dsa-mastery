package leetcode;

import java.util.*;

// LeetCode #39 - Combination Sum
// Difficulty: Medium (Top Interview 150 / Blind 75)
// Problem: Given an array of distinct integers candidates and a target integer target, return
//          a list of all unique combinations of candidates where the chosen numbers sum to target.
//          You may return the combinations in any order.
//          The same number may be chosen from candidates an unlimited number of times.
//
// =================================================================================================
// 💡 EVOLUTION OF SOLUTIONS (KRISH'S FULL INDEPENDENT PROGRESSION):
// =================================================================================================
//
// Approach 1 (Krish's Independent Solve - Pure Include/Exclude Backtracking - 3 ms ⚡):
//   - Unbounded Choice Insight: When including candidates[index], we do NOT advance index!
//     Instead, we stay at index (allowing the number to be chosen unlimited times).
//   - When excluding, we advance to index + 1.
//   - Base Cases:
//       * target == 0: Found valid combination -> ans.add(new ArrayList<>(curr)).
//       * index >= candidates.length || target < 0: Exceeded boundaries -> prune & return.
//   - Optimization Insight: Since candidates are all distinct and we explore all combinations,
//     Arrays.sort() is NOT required for pure include/exclude; removing it dropped runtime from 5ms to 3ms!
//   - Complexity: O(2^(target/min)) worst-case Time, O(target/min) Auxiliary Stack Space.
//
// Approach 2 (For-Loop Backtracking with Pruning - 1 ms Beats 100% ⚡):
//   - Sort candidates upfront: Arrays.sort(candidates).
//   - Iterate using a for-loop from index to candidates.length - 1.
//   - Pruning Insight: The moment candidates[i] > target, all subsequent numbers are also > target.
//     Executing 'break;' immediately halts the loop and prunes all dead-end branches.
//   - Complexity: O(2^(target/min)) Time, O(target/min) Auxiliary Stack Space.
// =================================================================================================

public class LC39_CombinationSum {

    // Approach 1: Krish's Independent Binary Include/Exclude Solution (3 ms)
    static void solve(int[] candidates, int target, List<List<Integer>> ans, List<Integer> curr, int index) {
        if (target == 0) {
            ans.add(new ArrayList<>(curr));
            return;
        }
        if (index >= candidates.length || target < 0) {
            return;
        }
        // include (same index allowed for unlimited reuse)
        curr.add(candidates[index]);
        solve(candidates, target - candidates[index], ans, curr, index);

        // exclude (backtrack and move to next candidate)
        curr.remove(curr.size() - 1);
        solve(candidates, target, ans, curr, index + 1);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        solve(candidates, target, ans, curr, 0);
        return ans;
    }

    // Approach 2: For-Loop Backtracking with Break Pruning (1 ms Beats 100%)
    static void solveForLoop(int[] candidates, int target, List<List<Integer>> ans, List<Integer> curr, int index) {
        if (target == 0) {
            ans.add(new ArrayList<>(curr));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (candidates[i] > target) {
                break; // Early pruning: sorted array guarantees remaining numbers are also too big
            }
            curr.add(candidates[i]);
            solveForLoop(candidates, target - candidates[i], ans, curr, i);
            curr.remove(curr.size() - 1);
        }
    }

    public List<List<Integer>> combinationSumOptimized(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        solveForLoop(candidates, target, ans, curr, 0);
        return ans;
    }

    public static void main(String[] args) {
        LC39_CombinationSum solver = new LC39_CombinationSum();

        int[] c1 = {2, 3, 6, 7};
        int target1 = 7;
        System.out.println("Test 1 [2,3,6,7], target 7 (Include/Exclude 3ms): " + solver.combinationSum(c1, target1));
        System.out.println("Test 1 [2,3,6,7], target 7 (For-Loop Pruning 1ms):  " + solver.combinationSumOptimized(c1, target1));

        int[] c2 = {2, 3, 5};
        int target2 = 8;
        System.out.println("Test 2 [2,3,5], target 8 (Include/Exclude 3ms):   " + solver.combinationSum(c2, target2));
        System.out.println("Test 2 [2,3,5], target 8 (For-Loop Pruning 1ms):    " + solver.combinationSumOptimized(c2, target2));
    }
}

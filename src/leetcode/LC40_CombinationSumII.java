package leetcode;

import java.util.*;

// LeetCode #40 - Combination Sum II
// Difficulty: Medium (Top Interview 150 / Blind 75)
// Problem: Given a collection of candidate numbers (candidates) and a target number (target),
//          find all unique combinations in candidates where the candidate numbers sum to target.
//          Each number in candidates may only be used ONCE in the combination.
//          Note: The solution set must not contain duplicate combinations.
//
// =================================================================================================
// 💡 EVOLUTION OF SOLUTIONS (KRISH'S FULL INDEPENDENT PROGRESSION):
// =================================================================================================
//
// Approach 1 (Krish's Independent Solve - Binary Backtracking with Duplicate Pruning - 7 ms ⚡):
//   - Synthesized LC 39's target-sum backtracking with LC 90's duplicate skipping!
//   - Each element used at most once: on include, advance index to index + 1.
//   - Duplicate suppression: Sort candidates upfront. In the exclude branch, fast-forward through
//     all identical duplicates using:
//       while (index + 1 < candidates.length && candidates[index] == candidates[index + 1]) index++;
//   - Key Optimization Insight: If candidates[index] > target, because array is sorted, all subsequent
//     elements are also > target; returning early cuts off all oversized branches.
//   - Complexity: O(2^N) worst-case Time, O(N) Auxiliary Stack Space.
//
// Approach 2 (For-Loop Backtracking with Break & Continue - 2 ms Beats 99% ⚡):
//   - Iterate using a for-loop from index to candidates.length - 1.
//   - Pruning: if (candidates[i] > target) break;
//   - Duplicate skipping: if (i > index && candidates[i] == candidates[i - 1]) continue;
//   - Complexity: O(2^N) Time, O(N) Auxiliary Stack Space.
// =================================================================================================

public class LC40_CombinationSumII {

    // Approach 1: Krish's Independent Binary Backtracking Solution (Passed 7 ms)
    static void solve(int[] candidates, int target, List<List<Integer>> ans, List<Integer> curr, int index) {
        // Base case: Valid combination found
        if (target == 0) {
            ans.add(new ArrayList<>(curr));
            return;
        }
        if (index >= candidates.length || target < 0) {
            return;
        }

        // Early pruning: Since array is sorted, if current number exceeds target,
        // no subsequent number can fit either!
        if (candidates[index] > target) {
            return;
        }

        // Include (advance to index + 1 since each number is used at most once)
        curr.add(candidates[index]);
        solve(candidates, target - candidates[index], ans, curr, index + 1);

        // Backtrack
        curr.remove(curr.size() - 1);

        // Exclude: Skip all identical duplicates of the excluded element
        while (index + 1 < candidates.length && candidates[index] == candidates[index + 1]) {
            index++;
        }
        solve(candidates, target, ans, curr, index + 1);
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        solve(candidates, target, ans, curr, 0);
        return ans;
    }

    // Approach 2: For-Loop Backtracking with Pruning (2 ms Beats 99%)
    static void solveForLoop(int[] candidates, int target, List<List<Integer>> ans, List<Integer> curr, int index) {
        if (target == 0) {
            ans.add(new ArrayList<>(curr));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (candidates[i] > target) {
                break; // Oversized element pruning
            }
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue; // Skip duplicates at the same recursion depth
            }
            curr.add(candidates[i]);
            solveForLoop(candidates, target - candidates[i], ans, curr, i + 1);
            curr.remove(curr.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum2ForLoop(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        solveForLoop(candidates, target, ans, curr, 0);
        return ans;
    }

    public static void main(String[] args) {
        LC40_CombinationSumII solver = new LC40_CombinationSumII();

        int[] c1 = {10, 1, 2, 7, 6, 1, 5};
        int target1 = 8;
        System.out.println("Test 1 [10,1,2,7,6,1,5], target 8 (Binary):  " + solver.combinationSum2(c1, target1));
        System.out.println("Test 1 [10,1,2,7,6,1,5], target 8 (ForLoop): " + solver.combinationSum2ForLoop(c1, target1));

        int[] c2 = {2, 5, 2, 1, 2};
        int target2 = 5;
        System.out.println("Test 2 [2,5,2,1,2], target 5 (Binary):  " + solver.combinationSum2(c2, target2));
        System.out.println("Test 2 [2,5,2,1,2], target 5 (ForLoop): " + solver.combinationSum2ForLoop(c2, target2));
    }
}

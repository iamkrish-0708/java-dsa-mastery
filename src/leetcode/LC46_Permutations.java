package leetcode;

import java.util.ArrayList;
import java.util.List;

// LeetCode #46 - Permutations
// Difficulty: Medium (Top Interview 150 / Blind 75)
// Problem: Given an array nums of distinct integers, return all the possible permutations.
//          You can return the answer in any order.
//
// =================================================================================================
// 💡 TECHNIQUE: BACKTRACKING WITH VISITED TRACKER (KRISH'S INDEPENDENT SOLVE - 3 ms ⚡)
// =================================================================================================
// - We want to generate all N! orderings of nums.
// - State Tracking:
//     * Use a boolean[] tracker array where tracker[i] indicates whether nums[i] is already picked.
//     * Use List<Integer> curr to accumulate the current permutation.
// - Base Case:
//     * When curr.size() == nums.length, all elements have been picked in a unique order.
//     * Snapshot: ans.add(new ArrayList<>(curr)); return;
// - Backtracking Loop:
//     * Iterate i from 0 to nums.length - 1:
//         - If !tracker[i]:
//             1. Choose: tracker[i] = true, curr.add(nums[i])
//             2. Explore: solve(nums, ans, curr, tracker)
//             3. Unchoose (Backtrack): curr.remove(curr.size() - 1), tracker[i] = false
//
// Complexity:
//   - Time Complexity: O(N! * N) — Total of N! leaf permutations, each taking O(N) to snapshot.
//   - Space Complexity: O(N) Auxiliary Space — O(N) stack depth + O(N) tracker array + O(N) curr buffer.
// =================================================================================================

public class LC46_Permutations {

    // Krish's Solution (Passed 3 ms on LeetCode)
    static void solve(int[] nums, List<List<Integer>> ans, List<Integer> curr, boolean[] tracker) {
        if (curr.size() == nums.length) {
            ans.add(new ArrayList<>(curr));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!tracker[i]) {
                tracker[i] = true;
                curr.add(nums[i]);
                solve(nums, ans, curr, tracker);

                // backtrack
                curr.remove(curr.size() - 1);
                tracker[i] = false;
            }
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        boolean[] tracker = new boolean[nums.length];
        solve(nums, ans, curr, tracker);
        return ans;
    }

    public static void main(String[] args) {
        LC46_Permutations solver = new LC46_Permutations();

        int[] test1 = {1, 2, 3};
        System.out.println("Test 1 [1,2,3]: " + solver.permute(test1));

        int[] test2 = {0, 1};
        System.out.println("Test 2 [0,1]:   " + solver.permute(test2));

        int[] test3 = {1};
        System.out.println("Test 3 [1]:     " + solver.permute(test3));
    }
}

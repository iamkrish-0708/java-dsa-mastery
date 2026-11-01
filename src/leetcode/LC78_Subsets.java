package leetcode;

import java.util.ArrayList;
import java.util.List;

// LeetCode #78 - Subsets
// Difficulty: Medium
// Problem: Given an integer array nums of unique elements, return all possible subsets (the power set).
//          The solution set must not contain duplicate subsets. Return the solution in any order.
// Approach: Include-Exclude (Pick / Not-Pick) Backtracking Pattern
// Key Mechanics:
//   - At each index, decide whether to INCLUDE nums[index] in curr or EXCLUDE it.
//   - Include: curr.add(nums[index]), recurse on (index + 1).
//   - Backtrack: curr.remove(curr.size() - 1) to undo the inclusion.
//   - Exclude: recurse on (index + 1) without adding nums[index].
//   - Base Case: When index >= nums.length, all elements have been decided.
//     CRUCIAL: Add a snapshot copy `new ArrayList<>(curr)` into `ans` (not curr itself,
//     since curr is mutated during backtracking).
// Complexity:
//   - Time: O(N * 2^N) - 2^N subsets generated, copying each subset takes O(N)
//   - Space: O(N) auxiliary call stack depth (excluding the output container)
// Problem Link: https://leetcode.com/problems/subsets/

public class LC78_Subsets {

    static void getSubsets(int[] nums, List<List<Integer>> ans, List<Integer> curr, int index) {
        // Base case
        if (index >= nums.length) {
            ans.add(new ArrayList<>(curr)); // Snapshot copy
            return;
        }

        // Include
        curr.add(nums[index]);
        getSubsets(nums, ans, curr, index + 1);

        // Exclude / Backtrack
        curr.remove(curr.size() - 1);
        getSubsets(nums, ans, curr, index + 1);
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        getSubsets(nums, ans, curr, 0);
        return ans;
    }

    public static void main(String[] args) {
        LC78_Subsets solver = new LC78_Subsets();
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = solver.subsets(nums);
        System.out.println("Subsets count: " + result.size()); // 2^3 = 8
        System.out.println("Result: " + result);
    }
}

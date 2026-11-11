package leetcode;

import java.util.*;

// LeetCode #90 - Subsets II
// Difficulty: Medium
// Problem: Given an integer array nums that may contain duplicates, return all possible
//          subsets (the power set). The solution set must not contain duplicate subsets.
//          Return the solution in any order.
//
// =================================================================================================
// 💡 EVOLUTION OF SOLUTIONS (KRISH'S FULL PROGRESSION):
// =================================================================================================
//
// Approach 1 (Krish's Initial Solve - Sort + HashSet Post-Filtering):
//   - Key edge case: Must sort nums first! Otherwise, identical subsets in different orders
//     (e.g., [4, 1] vs [1, 4]) are not recognized as equal by ArrayList.equals() in HashSet.
//   - Uses pure include/exclude recursion to generate all 2^N subsets.
//   - Uses Set<List<Integer>> uniqueArraysSet to filter duplicate subsets before returning.
//   - Complexity: O(2^N * N) Time, O(2^N * N) Space (HashSet heap overhead).
//
// Approach 2 (Optimal Backtracking with Duplicate Pruning - 1 ms Beats ~100% ⚡):
//   - After sorting nums, duplicate elements are grouped consecutively.
//   - Include branch: Add nums[index] and recurse to index + 1.
//   - Backtrack: Remove nums[index].
//   - Core Pruning Insight: Once we decide to EXCLUDE nums[index], we must not include any of its
//     identical duplicates in this choice. Use a while loop to skip all adjacent duplicate values:
//       while (index + 1 < nums.length && nums[index] == nums[index + 1]) index++;
//   - Exclude branch: Recurse to index + 1 on the next distinct element.
//   - Complexity: O(2^K * N) Time where 2^K is the count of unique subsets, O(N) auxiliary recursion stack space.
// =================================================================================================

public class LC90_SubsetsII {

    // Approach 2: Optimal Backtracking with Duplicate Pruning (1 ms Beats 100%)
    static void getSubsets(int[] nums, List<List<Integer>> ans, List<Integer> curr, int index) {
        // Base case
        if (index >= nums.length) {
            ans.add(new ArrayList<>(curr));
            return;
        }

        // Include
        curr.add(nums[index]);
        getSubsets(nums, ans, curr, index + 1);

        curr.remove(curr.size() - 1); // backtrack

        // The element being excluded must not be included again for this choice
        while (index + 1 < nums.length && nums[index] == nums[index + 1]) {
            index++;
        }

        // Exclude
        getSubsets(nums, ans, curr, index + 1);
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        getSubsets(nums, ans, curr, 0);
        return ans;
    }

    // Approach 1: Krish's Initial Sort + HashSet Solution
    static void solve(int[] nums, List<Integer> arraysSet, int index, Set<List<Integer>> uniqueArraysSet) {
        if (index >= nums.length) {
            uniqueArraysSet.add(new ArrayList<>(arraysSet));
            return;
        }
        // Include
        arraysSet.add(nums[index]);
        solve(nums, arraysSet, index + 1, uniqueArraysSet);

        // Exclude
        arraysSet.remove(arraysSet.size() - 1);
        solve(nums, arraysSet, index + 1, uniqueArraysSet);
    }

    public List<List<Integer>> subsetsWithDupHashSet(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> uniqueArraysSet = new HashSet<>();
        List<Integer> arraysSet = new ArrayList<>();
        solve(nums, arraysSet, 0, uniqueArraysSet);
        return new ArrayList<>(uniqueArraysSet);
    }

    public static void main(String[] args) {
        LC90_SubsetsII solver = new LC90_SubsetsII();

        int[] test1 = {1, 2, 2};
        System.out.println("Test 1 [1,2,2] (Optimal): " + solver.subsetsWithDup(test1));
        System.out.println("Test 1 [1,2,2] (HashSet): " + solver.subsetsWithDupHashSet(test1));

        int[] test2 = {0};
        System.out.println("Test 2 [0] (Optimal): " + solver.subsetsWithDup(test2));
    }
}

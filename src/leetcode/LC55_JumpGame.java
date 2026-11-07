package leetcode;

// LeetCode #55 - Jump Game
// Difficulty: Medium (Top Interview 150 / Blind 75)
// Problem: You are given an integer array nums. You are initially positioned at the array's
//          first index, and each element in the array represents your maximum jump length at that position.
//          Return true if you can reach the last index, or false otherwise.
//
// =================================================================================================
// 🔍 ARCHITECTURAL COMPARISON & POST-MORTEM:
// =================================================================================================
//
// ❌ Flaws in Raw Initial Attempt:
//   1. Checked only exact jump: (i + nums[i] == size), ignoring that nums[i] represents the
//      MAXIMUM jump (we can take any jump from 1 to nums[i]).
//   2. Simply did solve(nums, size, i + 1), which falsely assumed index i + 1 was reachable
//      without verifying if any prior jump could land there.
//
// ✅ Approach 1 (Recursive Multi-Choice Branching - Lecture 65):
//   - From current index, explore all valid jumps from 1 up to nums[index].
//   - Transition: solve(nums, index + jump).
//   - Base Cases:
//       * index == nums.length - 1 -> return true (reached the end!)
//       * index >= nums.length -> return false (jumped out of bounds)
//       * nums[index] == 0 -> return false (stuck at 0, cannot jump forward)
//   - Short-circuit optimization: If any recursive call returns true, return true IMMEDIATELY
//     without exploring remaining jumps.
//
// Status: Pure recursion hits expected TLE on large arrays (O(K^N) exponential combinations).
//         Optimized in DP to O(N^2), and with Greedy to O(N) time and O(1) space!
// Complexity (Pure Recursion):
//   - Time: O(2^N) / O(K^N) exponential
//   - Space: O(N) auxiliary call stack depth
// Problem Link: https://leetcode.com/problems/jump-game/

public class LC55_JumpGame {

    // Approach 1: Recursive Multi-Choice Jump Exploration with Early Exit
    static boolean solve(int[] nums, int index) {
        // Base cases
        if (index == nums.length - 1) {
            return true;
        }
        if (index >= nums.length || nums[index] == 0) {
            return false;
        }

        int jumpVal = nums[index];

        // Try every legal jump length from 1 to jumpVal
        for (int jump = 1; jump <= jumpVal; jump++) {
            if (solve(nums, index + jump)) {
                return true; // Early exit on first successful path
            }
        }

        return false;
    }

    public boolean canJump(int[] nums) {
        return solve(nums, 0);
    }

    // Raw Attempt (Kept for documentation of learning pitfalls)
    static boolean solveRaw(int[] nums, int size, int i) {
        if (i > size) {
            return false;
        }
        if ((i + nums[i]) == size) {
            return true;
        }
        return solveRaw(nums, size, i + 1);
    }

    public static void main(String[] args) {
        LC55_JumpGame solver = new LC55_JumpGame();
        int[] nums1 = {2, 3, 1, 1, 4};
        System.out.println("Output 1: " + solver.canJump(nums1)); // Expected: true

        int[] nums2 = {3, 2, 1, 0, 4};
        System.out.println("Output 2: " + solver.canJump(nums2)); // Expected: false
    }
}

package leetcode;

// LeetCode #55 - Jump Game
// Difficulty: Medium (Top Interview 150 / Blind 75)
// Problem: You are given an integer array nums. You are initially positioned at the array's
//          first index, and each element in the array represents your maximum jump length at that position.
//          Return true if you can reach the last index, or false otherwise.
//
// =================================================================================================
// 🔍 EVOLUTION OF SOLUTIONS (KRISH'S LEARNING PROGRESSION):
// =================================================================================================
//
// Approach 1 (Lecture 65 - Pure Recursion Multi-Choice Branching):
//   - From current index, explore all valid jumps from 1 up to nums[index].
//   - Transition: solve(nums, index + jump).
//   - Complexity: O(K^N) exponential time -> Hits expected TLE on large arrays.
//
// Approach 2 (Krish's Greedy Petrol-Pump Iteration - 3 ms Beats ~75% ⚡):
//   - Mental Analogy (Car & Petrol Pumps):
//       * Moving one step forward burns 1 unit of fuel (jumpVal--).
//       * At each index i, a petrol pump offers nums[i] fuel.
//       * We refill only if the pump offers more than our current tank:
//         jumpVal = Math.max(jumpVal, nums[i]).
//       * If jumpVal == 0 before moving, the car is stranded and cannot reach index i -> return false!
//   - Naturally handles single-element edge case nums = [0] because loop starts at i = 1,
//     skipping the loop and returning true immediately!
//
// Complexity:
//   - Time: O(N) single forward pass through the array.
//   - Space: O(1) Auxiliary Space (pure primitive variables).
// Problem Link: https://leetcode.com/problems/jump-game/

public class LC55_JumpGame {

    // Approach 2: Greedy Petrol-Pump Forward Scan (3 ms ⚡ - O(N) Time, O(1) Space)
    public boolean canJump(int[] nums) {
        int jumpVal = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // Stranded with 0 fuel: cannot reach or advance past index i
            if (jumpVal == 0) {
                return false;
            }

            // Burn 1 unit of fuel to step forward
            jumpVal--;

            // Refill if current pump has more fuel
            jumpVal = Math.max(jumpVal, nums[i]);
        }

        return true;
    }

    // Approach 1: Recursive Multi-Choice Jump Exploration (Lecture 65 - TLE)
    static boolean solve(int[] nums, int index) {
        if (index == nums.length - 1) {
            return true;
        }
        if (index >= nums.length || nums[index] == 0) {
            return false;
        }

        int jumpVal = nums[index];
        for (int jump = 1; jump <= jumpVal; jump++) {
            if (solve(nums, index + jump)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LC55_JumpGame solver = new LC55_JumpGame();
        int[] nums1 = {2, 3, 1, 1, 4};
        System.out.println("Output 1: " + solver.canJump(nums1)); // Expected: true

        int[] nums2 = {3, 2, 1, 0, 4};
        System.out.println("Output 2: " + solver.canJump(nums2)); // Expected: false

        int[] nums3 = {0};
        System.out.println("Output 3: " + solver.canJump(nums3)); // Expected: true
    }
}

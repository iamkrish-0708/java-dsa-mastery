package leetcode;

// LeetCode #45 - Jump Game II
// Difficulty: Medium (Top Interview 150 / Blind 75)
// Problem: Given a 0-indexed array of integers nums of length n, you are initially positioned at nums[0].
//          Each element nums[i] represents the maximum length of a forward jump from index i.
//          Return the minimum number of jumps to reach nums[n - 1].
//
// Approach: Greedy Jump Horizons / Breadth-First-Search Layers (O(N) Time, O(1) Space ⚡)
// Key Mechanics:
//   - currentEnd: The boundary/horizon of the CURRENT jump wave.
//   - farthest: The maximum reach discovered so far by scouting ahead within the current wave.
//   - When i hits currentEnd: We have exhausted the reach of the previous jump wave.
//     We are forced to take another jump (minJumps++) and expand currentEnd to farthest.
//   - Loop terminates at nums.length - 2 because once we reach or cross the second-to-last index,
//     the last jump needed to land on nums.length - 1 has already been triggered.
//
// Complexity: O(N) Time (single linear pass), O(1) Auxiliary Space
// Problem Link: https://leetcode.com/problems/jump-game-ii/

public class LC45_JumpGameII {

    public int jump(int[] nums) {
        int currentEnd = 0;
        int farthest = 0;
        int minJumps = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            // Scout the farthest index reachable from here
            farthest = Math.max(farthest, i + nums[i]);

            // When we reach the boundary of the current jump horizon:
            if (currentEnd == i) {
                minJumps++;
                currentEnd = farthest;
            }
        }

        return minJumps;
    }

    public static void main(String[] args) {
        LC45_JumpGameII solver = new LC45_JumpGameII();

        int[] nums1 = {2, 3, 1, 1, 4};
        System.out.println("Result 1: " + solver.jump(nums1)); // Expected: 2

        int[] nums2 = {2, 3, 0, 1, 4};
        System.out.println("Result 2: " + solver.jump(nums2)); // Expected: 2

        // Custom Example: [1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9]
        int[] nums3 = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
        System.out.println("Result 3: " + solver.jump(nums3)); // Expected: 3
    }
}

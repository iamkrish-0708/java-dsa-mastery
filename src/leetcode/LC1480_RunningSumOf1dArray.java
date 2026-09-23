package leetcode;

import java.util.Arrays;

/**
 * LeetCode #1480: Running Sum of 1d Array
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/running-sum-of-1d-array/
 * 
 * Approach 1: Nested Iteration (Brute Force Prefix Sum)
 * - For each index i, re-sum all elements from index 0 to i.
 * - Time Complexity: O(N^2)
 * - Space Complexity: O(N) for output array
 * 
 * Approach 2: Linear Prefix Accumulation (O(N) Time)
 * - Use the relation: runningSum[i] = runningSum[i - 1] + nums[i].
 * - Time Complexity: O(N) - Single linear pass (0ms Beats 100%).
 * - Space Complexity: O(N) for output array (or O(1) in-place if modifying input array).
 */
public class LC1480_RunningSumOf1dArray {

    // Approach 1: Nested Iteration / Brute Force (O(N^2) Time)
    public int[] runningSumBruteForce(int[] nums) {
        int[] runningSum = new int[nums.length];
        int iterations = nums.length;
        int i = 0;
        while (iterations != 0) {
            for (int j = 0; j <= i; j++) {
                runningSum[i] = runningSum[i] + nums[j];
            }
            iterations--;
            i++;
        }
        return runningSum;
    }

    // Approach 2: Linear Prefix Accumulation (O(N) Time, 0ms Beats 100%)
    public int[] runningSum(int[] nums) {
        int[] runningSum = new int[nums.length];
        runningSum[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            runningSum[i] = runningSum[i - 1] + nums[i];
        }

        return runningSum;
    }

    // Approach 3: Strictly O(1) Auxiliary Space (In-Place Mutation)
    public int[] runningSumInPlace(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        return nums;
    }

    public static void main(String[] args) {
        LC1480_RunningSumOf1dArray solver = new LC1480_RunningSumOf1dArray();

        // Test 1
        int[] nums1 = {1, 2, 3, 4};
        System.out.println("Test 1: " + Arrays.toString(solver.runningSum(nums1)));
        // Expected: [1, 3, 6, 10]

        // Test 2
        int[] nums2 = {1, 1, 1, 1, 1};
        System.out.println("Test 2: " + Arrays.toString(solver.runningSum(nums2)));
        // Expected: [1, 2, 3, 4, 5]

        // Test 3
        int[] nums3 = {3, 1, 2, 10, 1};
        System.out.println("Test 3: " + Arrays.toString(solver.runningSum(nums3)));
        // Expected: [3, 4, 6, 16, 17]
    }
}

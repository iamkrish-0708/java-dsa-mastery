package leetcode;

import java.util.Arrays;

/**
 * LeetCode #1470: Shuffle the Array
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/shuffle-the-array/
 * 
 * Approach 1: Subarray Splitting & Interleaving (Krish's Solution)
 * - Split into leftHalf and rightHalf subarrays.
 * - Interleave back into nums using bitwise parity check (k & 1).
 * - Time Complexity: O(N)
 * - Space Complexity: O(N) auxiliary space
 * 
 * Approach 2: Direct Single-Pass Construction (Optimized Standard)
 * - Directly allocate result array of size 2 * n.
 * - Place nums[i] at 2 * i and nums[i + n] at 2 * i + 1 in a single loop from 0 to n - 1.
 * - Time Complexity: O(N) (0ms Beats 100%).
 * - Space Complexity: O(N) output array.
 */
public class LC1470_ShuffleTheArray {

    // Approach 1: Krish's Halves Splitting & Bitwise Interleaving
    public int[] shuffle(int[] nums, int n) {
        int mid = nums.length / 2;
        int[] leftHalf = new int[mid];
        int[] rightHalf = new int[mid];

        for (int i = 0; i < mid; i++) {
            leftHalf[i] = nums[i];
        }
        for (int j = 0; j < (nums.length / 2); j++) {
            rightHalf[j] = nums[mid];
            mid++;
        }

        int ptr = 0;
        for (int k = 0; k < nums.length; k++) {
            if ((k & 1) == 0) {
                nums[k] = leftHalf[ptr];
            }
            if ((k & 1) != 0) {
                nums[k] = rightHalf[ptr];
                ptr++;
            }
        }
        return nums;
    }

    // Approach 2: Direct Single Pass Interleave (0ms Beats 100%)
    public int[] shuffleOptimized(int[] nums, int n) {
        int[] result = new int[2 * n];
        for (int i = 0; i < n; i++) {
            result[2 * i] = nums[i];
            result[2 * i + 1] = nums[i + n];
        }
        return result;
    }

    public static void main(String[] args) {
        LC1470_ShuffleTheArray solver = new LC1470_ShuffleTheArray();

        // Test 1: [2,5,1,3,4,7], n = 3
        int[] nums1 = {2, 5, 1, 3, 4, 7};
        System.out.println("Test 1: " + Arrays.toString(solver.shuffle(nums1, 3)));
        // Expected: [2, 3, 5, 4, 1, 7]

        // Test 2: [1,2,3,4,4,3,2,1], n = 4
        int[] nums2 = {1, 2, 3, 4, 4, 3, 2, 1};
        System.out.println("Test 2: " + Arrays.toString(solver.shuffle(nums2, 4)));
        // Expected: [1, 4, 2, 3, 3, 2, 4, 1]

        // Test 3: [1,1,2,2], n = 2
        int[] nums3 = {1, 1, 2, 2};
        System.out.println("Test 3: " + Arrays.toString(solver.shuffle(nums3, 2)));
        // Expected: [1, 2, 1, 2]
    }
}

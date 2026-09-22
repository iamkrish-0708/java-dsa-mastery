package leetcode;

import java.util.Arrays;

/**
 * LeetCode #283: Move Zeroes
 * Difficulty: Easy (Top Interview 100 / Blind 75)
 * Link: https://leetcode.com/problems/move-zeroes/
 * 
 * Approach: In-Place Two Pointers / Non-Zero Compaction & Zero Filling
 * 1. Maintain a pointer `start` for placing non-zero elements and `zeroCount` to count zeroes.
 * 2. Pass 1: Iterate through `nums`. If nums[i] != 0, assign nums[start] = nums[i] and start++.
 *    If nums[i] == 0, increment zeroCount.
 * 3. Pass 2: Fill the trailing elements from the end (endIndex = nums.length - 1) with 0s.
 * 
 * Time Complexity: O(N) - Linear single pass over the array + zero filling pass (1ms Beats 100%).
 * Space Complexity: O(1) - Auxiliary space (Strictly in-place modification).
 */
public class LC283_MoveZeroes {

    public void moveZeroes(int[] nums) {
        int start = 0;
        int zeroCount = 0;
        int endIndex = nums.length - 1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroCount++;
            } else {
                nums[start] = nums[i];
                start++;
            }
        }

        while (zeroCount > 0) {
            nums[endIndex] = 0;
            endIndex--;
            zeroCount--;
        }
    }

    public static void main(String[] args) {
        LC283_MoveZeroes solver = new LC283_MoveZeroes();

        // Test 1: Standard case
        int[] nums1 = {0, 1, 0, 3, 12};
        System.out.println("Original 1: " + Arrays.toString(nums1));
        solver.moveZeroes(nums1);
        System.out.println("Result 1:   " + Arrays.toString(nums1));
        // Expected: [1, 3, 12, 0, 0]

        // Test 2: Single element 0
        int[] nums2 = {0};
        System.out.println("Original 2: " + Arrays.toString(nums2));
        solver.moveZeroes(nums2);
        System.out.println("Result 2:   " + Arrays.toString(nums2));
        // Expected: [0]

        // Test 3: Multiple non-zeroes and leading zeroes
        int[] nums3 = {0, 0, 1};
        System.out.println("Original 3: " + Arrays.toString(nums3));
        solver.moveZeroes(nums3);
        System.out.println("Result 3:   " + Arrays.toString(nums3));
        // Expected: [1, 0, 0]
    }
}

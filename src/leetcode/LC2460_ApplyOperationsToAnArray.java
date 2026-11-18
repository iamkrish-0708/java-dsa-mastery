package leetcode;

import java.util.Arrays;

// LeetCode #2460 - Apply Operations to an Array
// Difficulty: Easy
// Problem: You are given a 0-indexed array nums of size n consisting of non-negative integers.
//          You need to apply n - 1 operations where in the ith operation (0-indexed):
//          if nums[i] == nums[i + 1], then multiply nums[i] by 2 and set nums[i + 1] to 0.
//          After applying all operations, shift all 0's to the end of the array while maintaining
//          the relative order of non-zero elements. Return the resulting array.
//
// =================================================================================================
// 💡 TECHNIQUE: ADJACENT ELEMENT SIMULATION & NON-ZERO EXTRACTION (KRISH'S SOLVE - 1 ms ⚡)
// =================================================================================================
// - Mechanics:
//     1. Pass 1 (Operations): Traverse adjacent pairs (i, j). If nums[i] == nums[j], double nums[i]
//        and zero-out nums[j]. Advance both pointers linearly.
//     2. Pass 2 (Zero-Shift): Allocate ans array of size n. Extract all non-zero elements into ans
//        in their original relative order.
//     3. Fill remaining trailing positions with 0.
//
// Complexity:
//   - Time Complexity: O(N) — Linear scan over array of size N.
//   - Space Complexity: O(N) — for the resulting array ans.
// =================================================================================================

public class LC2460_ApplyOperationsToAnArray {

    public int[] applyOperations(int[] nums) {
        int i = 0;
        int j = 1;

        while (j < nums.length) {
            if (nums[i] == nums[j]) {
                nums[i] = 2 * nums[i];
                nums[j] = 0;
            }
            i++;
            j++;
        }

        int[] ans = new int[nums.length];
        int ptr = 0;
        for (int n : nums) {
            if (n != 0) {
                ans[ptr] = n;
                ptr++;
            }
        }
        while (ptr < ans.length) {
            ans[ptr] = 0;
            ptr++;
        }
        return ans;
    }

    // Local Test Harness
    public static void main(String[] args) {
        LC2460_ApplyOperationsToAnArray solver = new LC2460_ApplyOperationsToAnArray();

        // Test 1: [1, 2, 2, 1, 1, 0] -> Op: [1, 4, 0, 2, 0, 0] -> Shift: [1, 4, 2, 0, 0, 0]
        int[] nums1 = {1, 2, 2, 1, 1, 0};
        System.out.println("Test 1: " + Arrays.toString(solver.applyOperations(nums1)) + " (Expected: [1, 4, 2, 0, 0, 0])");

        // Test 2: [0, 1] -> [1, 0]
        int[] nums2 = {0, 1};
        System.out.println("Test 2: " + Arrays.toString(solver.applyOperations(nums2)) + " (Expected: [1, 0])");
    }
}

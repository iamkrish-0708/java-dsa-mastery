package leetcode;

import java.util.Arrays;

// LeetCode #2574 - Left and Right Sum Differences
// Difficulty: Easy
// Problem: Given a 0-indexed integer array nums, find a 0-indexed integer array answer where:
//          answer.length == nums.length.
//          answer[i] = |leftSum[i] - rightSum[i]|.
//          Where leftSum[i] is the sum of elements to the left of index i, and rightSum[i] is the sum of elements to the right.
//
// =================================================================================================
// 💡 TECHNIQUE: PREFIX & SUFFIX SUM ACCUMULATION (KRISH'S SOLVE - 2 ms ⚡)
// =================================================================================================
// - Mechanics:
//     1. Allocate leftSum and rightSum arrays of size N.
//     2. Forward pass: compute prefix sums leftSum[i] = leftSum[i-1] + nums[i-1].
//     3. Backward pass: compute suffix sums rightSum[i] = rightSum[i+1] + nums[i+1].
//     4. In-place reuse: populate nums[i] with Math.abs(rightSum[i] - leftSum[i]) to save return array allocation.
//
// Complexity:
//   - Time Complexity: O(N) — Three linear passes over the array of size N.
//   - Space Complexity: O(N) Auxiliary Space — for leftSum and rightSum arrays of size N.
// =================================================================================================

public class LC2574_LeftAndRightSumDifferences {

    public int[] leftRightDifference(int[] nums) {
        int n = nums.length;
        int[] leftSum = new int[n];
        int[] rightSum = new int[n];
        leftSum[0] = 0;
        rightSum[n-1] = 0;
        for(int i=1 ; i<n ; i++){
            leftSum[i] = leftSum[i-1] + nums[i-1];
        }
        for(int i=n-2; i>=0 ; i--){
            rightSum[i] = rightSum[i+1] + nums[i+1];
        }
        for(int i=0; i<n; i++){
            nums[i] = Math.abs(rightSum[i]-leftSum[i]);
        }
        return nums;
    }

    // Local Test Harness
    public static void main(String[] args) {
        LC2574_LeftAndRightSumDifferences solver = new LC2574_LeftAndRightSumDifferences();

        // Test 1: [10, 4, 8, 3] -> [15, 1, 11, 22]
        int[] nums1 = {10, 4, 8, 3};
        System.out.println("Test 1: " + Arrays.toString(solver.leftRightDifference(nums1)) + " (Expected: [15, 1, 11, 22])");

        // Test 2: [1] -> [0]
        int[] nums2 = {1};
        System.out.println("Test 2: " + Arrays.toString(solver.leftRightDifference(nums2)) + " (Expected: [0])");
    }
}

package leetcode;

/**
 * LeetCode #53: Maximum Subarray
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/maximum-subarray/
 * 
 * Approach: Kadane's Algorithm
 * - Track running `sum` and global `maximumSum` initialized to Integer.MIN_VALUE.
 * - At each element:
 *   1. Add nums[i] to `sum`.
 *   2. Update `maximumSum = Math.max(maximumSum, sum)`.
 *   3. If `sum < 0`, reset `sum = 0` (discard negative prefix baggage).
 * 
 * Time Complexity: O(N) - Single pass (1ms Beats 99.6%)
 * Space Complexity: O(1) - Constant memory
 */
public class LC53_MaximumSubarray {

    public int maxSubArray(int[] nums) {
        int sum = 0;
        int maximumSum = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];

            if (sum > maximumSum) {
                maximumSum = sum;
            }

            if (sum < 0) {
                sum = 0;
            }
        }

        return maximumSum;
    }

    public static void main(String[] args) {
        LC53_MaximumSubarray solver = new LC53_MaximumSubarray();

        int[] test1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4}; // [4, -1, 2, 1] -> 6
        int[] test2 = {1};
        int[] test3 = {5, 4, -1, 7, 8};                 // 23
        int[] test4 = {-1, -2, -3};                     // -1

        System.out.println("Test 1: [-2, 1, -3, 4, -1, 2, 1, -5, 4] -> " + solver.maxSubArray(test1) + " (Expected: 6)");
        System.out.println("Test 2: [1]                             -> " + solver.maxSubArray(test2) + " (Expected: 1)");
        System.out.println("Test 3: [5, 4, -1, 7, 8]                -> " + solver.maxSubArray(test3) + " (Expected: 23)");
        System.out.println("Test 4: [-1, -2, -3]                    -> " + solver.maxSubArray(test4) + " (Expected: -1)");
    }
}

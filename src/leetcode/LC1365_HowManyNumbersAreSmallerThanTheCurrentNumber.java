package leetcode;

import java.util.Arrays;

/**
 * LeetCode #1365: How Many Numbers Are Smaller Than the Current Number
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/
 * 
 * Approach 1: Nested Loop Brute Force (Krish's Raw First-Sight Solution)
 * - For each index i, compare with all other elements j (where j != i).
 * - If nums[j] < nums[i], increment count.
 * - Store count in ans[i].
 * - Time Complexity: O(N^2)
 * - Space Complexity: O(N) for output array
 * 
 * Approach 2: Frequency Array + Running Prefix Sum (Optimized)
 * - Since 0 <= nums[i] <= 100, count frequencies in count[102].
 * - Compute prefix sum: count[i] += count[i - 1].
 * - For each num, count[num - 1] gives the exact count of strictly smaller numbers (0 if num == 0).
 * - Time Complexity: O(N) (1ms Beats 100%).
 * - Space Complexity: O(1) extra space (fixed 102 array).
 */
public class LC1365_HowManyNumbersAreSmallerThanTheCurrentNumber {

    // Approach 1: Raw First-Sight Brute Force (O(N^2) Time)
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[j] < nums[i]) {
                    count++;
                }
            }
            ans[i] = count;
        }
        return ans;
    }

    // Approach 2: Frequency Bucket & Running Prefix Sum (O(N) Time, 1ms Beats 100%)
    public int[] smallerNumbersThanCurrentOptimized(int[] nums) {
        int[] count = new int[102];
        for (int num : nums) {
            count[num + 1]++;
        }
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = count[nums[i]];
        }
        return ans;
    }

    public static void main(String[] args) {
        LC1365_HowManyNumbersAreSmallerThanTheCurrentNumber solver = new LC1365_HowManyNumbersAreSmallerThanTheCurrentNumber();

        // Test 1: [8, 1, 2, 2, 3]
        int[] nums1 = {8, 1, 2, 2, 3};
        System.out.println("Test 1: " + Arrays.toString(solver.smallerNumbersThanCurrent(nums1)));
        // Expected: [4, 0, 1, 1, 3]

        // Test 2: [6, 5, 4, 8]
        int[] nums2 = {6, 5, 4, 8};
        System.out.println("Test 2: " + Arrays.toString(solver.smallerNumbersThanCurrent(nums2)));
        // Expected: [2, 1, 0, 3]

        // Test 3: [7, 7, 7, 7]
        int[] nums3 = {7, 7, 7, 7};
        System.out.println("Test 3: " + Arrays.toString(solver.smallerNumbersThanCurrent(nums3)));
        // Expected: [0, 0, 0, 0]
    }
}

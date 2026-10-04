package leetcode;

import java.util.Arrays;

/**
 * LeetCode #1929: Concatenation of Array
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/concatenation-of-array/
 * 
 * Approach: Single-Pass Array Duplication
 * 1. Create a new array `ans` of size `2 * n`.
 * 2. Traverse `nums` once and simultaneously fill `ans[i]` and `ans[s2]` where `s2 = n + i`.
 * 
 * Time Complexity: O(N) - 1ms Beats 100%
 * Space Complexity: O(N) to store result array
 */
public class LC1929_ConcatenationOfArray {

    public int[] getConcatenation(int[] nums) {
        int[] ans = new int[nums.length * 2];
        int s2 = nums.length;
        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[i];
            ans[s2] = nums[i];
            s2++;
        }
        return ans;
    }

    public static void main(String[] args) {
        LC1929_ConcatenationOfArray solver = new LC1929_ConcatenationOfArray();

        // Test 1: [1,2,1] -> [1,2,1,1,2,1]
        int[] nums1 = {1, 2, 1};
        System.out.println("Test 1 Result: " + Arrays.toString(solver.getConcatenation(nums1)));

        // Test 2: [1,3,2,1] -> [1,3,2,1,1,3,2,1]
        int[] nums2 = {1, 3, 2, 1};
        System.out.println("Test 2 Result: " + Arrays.toString(solver.getConcatenation(nums2)));
    }
}

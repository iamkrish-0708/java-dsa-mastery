package leetcode;

import java.util.Arrays;

/**
 * LeetCode #1920: Build Array from Permutation
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/build-array-from-permutation/
 * 
 * Approach: Direct Index Mapping
 * 1. Allocate an array `ans` of size `nums.length`.
 * 2. Set `ans[i] = nums[nums[i]]` for each index in a single pass.
 * 
 * Time Complexity: O(N) - 0ms Beats 100%
 * Space Complexity: O(N) to store result array
 */
public class LC1920_BuildArrayFromPermutation {

    public int[] buildArray(int[] nums) {
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[nums[i]];
        }
        return ans;
    }

    public static void main(String[] args) {
        LC1920_BuildArrayFromPermutation solver = new LC1920_BuildArrayFromPermutation();

        // Test 1: [0,2,1,5,3,4] -> [0,1,2,4,5,3]
        int[] nums1 = {0, 2, 1, 5, 3, 4};
        System.out.println("Test 1 Result: " + Arrays.toString(solver.buildArray(nums1)));

        // Test 2: [5,0,1,2,3,4] -> [4,5,0,1,2,3]
        int[] nums2 = {5, 0, 1, 2, 3, 4};
        System.out.println("Test 2 Result: " + Arrays.toString(solver.buildArray(nums2)));
    }
}

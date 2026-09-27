package leetcode;

import java.util.Arrays;

/**
 * LeetCode #34: Find First and Last Position of Element in Sorted Array
 * Difficulty: Medium (Top Interview 150 / Blind 75)
 * Link: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * 
 * Approach: Dual Binary Search using Lower Bound and Upper Bound
 * 1. Calculate Lower Bound (lb):
 *    - First index where nums[mid] >= target.
 *    - If lb == nums.length or nums[lb] != target, target is absent -> return [-1, -1].
 * 2. Calculate Upper Bound (ub):
 *    - First index where nums[mid] > target.
 * 3. Range of target is [lb, ub - 1].
 * 
 * Time Complexity: O(log N) - Dual binary search (0ms Beats 100%).
 * Space Complexity: O(1) - Auxiliary space.
 */
public class LC34_FindFirstAndLastPositionOfElementInSortedArray {

    public int[] searchRange(int[] nums, int target) {
        int s = 0;
        int e = nums.length - 1;
        int lb = nums.length;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (nums[mid] >= target) {
                lb = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        if (lb == nums.length || nums[lb] != target) {
            return new int[]{-1, -1};
        }
        s = 0;
        e = nums.length - 1;
        int ub = nums.length;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (nums[mid] > target) {
                ub = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return new int[]{lb, ub - 1};
    }

    public static void main(String[] args) {
        LC34_FindFirstAndLastPositionOfElementInSortedArray solver = new LC34_FindFirstAndLastPositionOfElementInSortedArray();

        // Test 1: Multiple occurrences
        int[] nums1 = {5, 7, 7, 8, 8, 10};
        int target1 = 8;
        System.out.println("Test 1 Result: " + Arrays.toString(solver.searchRange(nums1, target1)));
        // Expected: [3, 4]

        // Test 2: Target not in array
        int[] nums2 = {5, 7, 7, 8, 8, 10};
        int target2 = 6;
        System.out.println("Test 2 Result: " + Arrays.toString(solver.searchRange(nums2, target2)));
        // Expected: [-1, -1]

        // Test 3: Empty array
        int[] nums3 = {};
        int target3 = 0;
        System.out.println("Test 3 Result: " + Arrays.toString(solver.searchRange(nums3, target3)));
        // Expected: [-1, -1]
    }
}

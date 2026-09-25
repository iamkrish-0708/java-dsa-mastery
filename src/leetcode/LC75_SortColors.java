package leetcode;

import java.util.Arrays;

/**
 * LeetCode #75: Sort Colors
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/sort-colors/
 * 
 * Approach: In-Place Insertion Sort
 * - Iteratively shifts larger elements to the right to place each element at its correct sorted position.
 * 
 * Time Complexity: O(N^2)
 * Space Complexity: O(1)
 */
public class LC75_SortColors {

    public void sortColors(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int currVal = nums[i];
            int prev = i - 1;
            while (prev >= 0 && nums[prev] > currVal) {
                nums[prev + 1] = nums[prev];
                prev--;
            }
            nums[prev + 1] = currVal;
        }
    }

    public static void main(String[] args) {
        LC75_SortColors solver = new LC75_SortColors();

        int[] nums1 = {2, 0, 2, 1, 1, 0};
        System.out.println("Original 1: " + Arrays.toString(nums1));
        solver.sortColors(nums1);
        System.out.println("Sorted 1:   " + Arrays.toString(nums1));

        int[] nums2 = {2, 0, 1};
        System.out.println("Original 2: " + Arrays.toString(nums2));
        solver.sortColors(nums2);
        System.out.println("Sorted 2:   " + Arrays.toString(nums2));
    }
}

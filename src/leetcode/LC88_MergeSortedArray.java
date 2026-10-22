package leetcode;

import java.util.Arrays;

/**
 * LeetCode #88: Merge Sorted Array
 * Difficulty: Easy (Top Interview 150 / Blind 75)
 * Link: https://leetcode.com/problems/merge-sorted-array/
 *
 * Approaches:
 * 1. Krish's Merge Sort Auxiliary Array Approach:
 *    - Uses standard Merge Sort merging logic into an auxiliary array `temp[m + n]`.
 *    - Merges valid elements of nums1 (length m) and nums2 (length n) into `temp`.
 *    - Copies back to nums1.
 *    - Time: O(M + N), Space: O(M + N).
 *
 * 2. Optimal In-Place 3-Pointer (For Future Reference):
 *    - Pointers at ends: p1 = m - 1, p2 = n - 1, p = m + n - 1.
 *    - Compares largest elements and fills nums1 from right to left in-place.
 *    - Time: O(M + N), Space: O(1).
 */
public class LC88_MergeSortedArray {

    // Approach 1: Krish's Merge Sort Logic with Auxiliary Array
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int s1 = nums1.length;
        int s2 = nums2.length;
        int[] temp = new int[s1];
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < (s1 - s2) && j < s2) {
            if (nums1[i] <= nums2[j]) {
                temp[k] = nums1[i];
                i++;
                k++;
            } else {
                temp[k] = nums2[j];
                j++;
                k++;
            }
        }

        while (i < (s1 - s2)) {
            temp[k] = nums1[i];
            i++;
            k++;
        }

        while (j < s2) {
            temp[k] = nums2[j];
            j++;
            k++;
        }

        for (int x = 0; x < s1; x++) {
            nums1[x] = temp[x];
        }
    }

    public static void main(String[] args) {
        LC88_MergeSortedArray solver = new LC88_MergeSortedArray();

        // Test Case 1: Standard merge
        int[] nums1_1 = {1, 2, 3, 0, 0, 0};
        int[] nums2_1 = {2, 5, 6};
        solver.merge(nums1_1, 3, nums2_1, 3);
        System.out.println("Test 1: " + Arrays.toString(nums1_1) + " | Expected: [1, 2, 2, 3, 5, 6]");

        // Test Case 2: nums2 is empty
        int[] nums1_2 = {1};
        int[] nums2_2 = {};
        solver.merge(nums1_2, 1, nums2_2, 0);
        System.out.println("Test 2: " + Arrays.toString(nums1_2) + " | Expected: [1]");

        // Test Case 3: nums1 has 0 valid elements
        int[] nums1_3 = {0};
        int[] nums2_3 = {1};
        solver.merge(nums1_3, 0, nums2_3, 1);
        System.out.println("Test 3: " + Arrays.toString(nums1_3) + " | Expected: [1]");
    }
}

package leetcode;

import java.util.Arrays;

/**
 * LeetCode #75: Sort Colors
 * Difficulty: Medium (Top Interview 150 / Blind 75)
 * Link: https://leetcode.com/problems/sort-colors/
 * 
 * Approach 1: In-Place Insertion Sort (Krish's Solution)
 * - Iteratively shift larger elements rightward to insert each element into its sorted position.
 * - Time Complexity: O(N^2) worst case, O(N) best case (0ms Beats 100% on n <= 300).
 * - Space Complexity: O(1) Auxiliary Space (Strictly In-Place).
 * 
 * Approach 2: Dutch National Flag Algorithm / 3 Pointers (One-Pass O(N) Optimal)
 * - Maintain 3 pointers: low (boundary for 0s), mid (current explorer), high (boundary for 2s).
 * - If nums[mid] == 0: swap(nums[low], nums[mid]), low++, mid++
 * - If nums[mid] == 1: mid++
 * - If nums[mid] == 2: swap(nums[mid], nums[high]), high--
 * - Time Complexity: O(N) Single-Pass (0ms Beats 100%).
 * - Space Complexity: O(1) Auxiliary Space.
 */
public class LC75_SortColors {

    // Approach 1: In-Place Insertion Sort (Krish's Solution)
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

    // Approach 2: Optimal Dutch National Flag 3-Pointer Algorithm (O(N) 1-Pass)
    public void sortColorsDutchFlag(int[] nums) {
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                int temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else { // nums[mid] == 2
                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;
                high--;
            }
        }
    }

    public static void main(String[] args) {
        LC75_SortColors solver = new LC75_SortColors();

        // Test 1: [2, 0, 2, 1, 1, 0]
        int[] nums1 = {2, 0, 2, 1, 1, 0};
        System.out.println("Original 1: " + Arrays.toString(nums1));
        solver.sortColors(nums1);
        System.out.println("Sorted 1:   " + Arrays.toString(nums1));
        // Expected: [0, 0, 1, 1, 2, 2]

        // Test 2: [2, 0, 1]
        int[] nums2 = {2, 0, 1};
        System.out.println("Original 2: " + Arrays.toString(nums2));
        solver.sortColors(nums2);
        System.out.println("Sorted 2:   " + Arrays.toString(nums2));
        // Expected: [0, 1, 2]
    }
}

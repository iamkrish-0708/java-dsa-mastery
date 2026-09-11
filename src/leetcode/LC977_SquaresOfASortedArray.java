package leetcode;

import java.util.Arrays;

/**
 * LeetCode #977: Squares of a Sorted Array
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/squares-of-a-sorted-array/
 * 
 * Approach 1 (Square & Sort via Dual-Pivot Quicksort):
 * - Square every element in-place in a single pass O(N).
 * - Sort the squared elements using Java's built-in Arrays.sort().
 * 
 * Time Complexity: O(N log N) - Dominated by sorting
 * Space Complexity: O(log N) - Space used by Dual-Pivot Quicksort
 * 
 * Note: To be revisited for the O(N) Two-Pointer inward traversal approach
 *       after completing dedicated sorting module.
 */
public class LC977_SquaresOfASortedArray {

    public int[] sortedSquares(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] * nums[i];
        }
        Arrays.sort(nums);
        return nums;
    }

    public static void main(String[] args) {
        LC977_SquaresOfASortedArray solver = new LC977_SquaresOfASortedArray();

        int[] test1 = {-4, -1, 0, 3, 10};
        System.out.println("Test 1: " + Arrays.toString(solver.sortedSquares(test1)) + " (Expected: [0, 1, 9, 16, 100])");

        int[] test2 = {-7, -3, 2, 3, 11};
        System.out.println("Test 2: " + Arrays.toString(solver.sortedSquares(test2)) + " (Expected: [4, 9, 9, 49, 121])");
    }
}

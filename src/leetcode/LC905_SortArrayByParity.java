package leetcode;

import java.util.Arrays;

/**
 * LeetCode #905: Sort Array By Parity
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/sort-array-by-parity/
 * 
 * Approach: In-Place Two Pointers Partitioning + Bitwise Parity Check
 * - Maintain two pointers: `i` at index 0 (left) and `j` at index n-1 (right).
 * - Use the bitwise operator `(nums[i] & 1) == 0` for ultra-fast even detection
 *   and `(nums[i] & 1) != 0` for odd detection.
 * - When left is odd and right is even, swap them and advance both pointers.
 * - If left is already even, move `i++`.
 * - If right is already odd, move `j--`.
 * 
 * Time Complexity: O(N) - Single pass, 0ms Beats 100%
 * Space Complexity: O(1) - In-place mutation with zero extra heap allocations
 */
public class LC905_SortArrayByParity {

    public int[] sortArrayByParity(int[] nums) {
        int i = 0;
        int j = nums.length - 1;

        while (i < j) {
            if (((nums[i] & 1) != 0) && ((nums[j] & 1) == 0)) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j--;
            } else if ((nums[i] & 1) == 0) {
                i++;
            } else if ((nums[j] & 1) != 0) {
                j--;
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        LC905_SortArrayByParity solver = new LC905_SortArrayByParity();

        int[] test1 = {3, 1, 2, 4};
        System.out.println("Test 1: [3, 1, 2, 4] -> " + Arrays.toString(solver.sortArrayByParity(test1)));

        int[] test2 = {0};
        System.out.println("Test 2: [0]          -> " + Arrays.toString(solver.sortArrayByParity(test2)));

        int[] test3 = {1, 3, 5, 2, 4, 6};
        System.out.println("Test 3: [1, 3, 5, 2, 4, 6] -> " + Arrays.toString(solver.sortArrayByParity(test3)));
    }
}

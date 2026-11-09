package leetcode;

import java.util.Arrays;

// LeetCode #66 - Plus One
// Difficulty: Easy (Top Interview 150)
// Problem: You are given a large integer represented as an integer array digits, where each
//          digits[i] is the ith digit of the integer. Increment the large integer by one
//          and return the resulting array of digits.
//
// =================================================================================================
// 💡 TECHNIQUE: BACKWARD CARRY PROPAGATION + IN-PLACE REASSIGNMENT
// =================================================================================================
// - Iterate backwards from the least significant digit (digits.length - 1 down to 0):
//     * If digits[i] < 9: Increment digits[i]++ and return immediately (carry absorbed).
//     * If digits[i] == 9: Set digits[i] = 0 (carry continues leftward).
// - All-9s Boundary Case (e.g. [9, 9, 9] -> [1, 0, 0, 0]):
//     * If loop finishes without returning, all digits were 9 and are now 0.
//     * Allocate a new array of size digits.length + 1.
//     * Exploits Java's JVM zero-initialization: all slots are default 0.
//     * Set digits[0] = 1 and return [1, 0, 0, ... 0].
//
// Complexity:
//   - Time Complexity: O(N) worst-case (all 9s), O(1) average (last digit < 9).
//   - Space Complexity: O(1) auxiliary space (in-place modification), O(N) only on all-9s resize.
// =================================================================================================

public class LC66_PlusOne {

    // Krish's Solution (0 ms Beats 100%)
    public int[] plusOne(int[] digits) {
        for (int i = (digits.length - 1); i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    public static void main(String[] args) {
        LC66_PlusOne solver = new LC66_PlusOne();

        int[] test1 = {1, 2, 3};
        System.out.println("Test 1 [1,2,3]: " + Arrays.toString(solver.plusOne(test1))); // [1, 2, 4]

        int[] test2 = {8, 9, 9};
        System.out.println("Test 2 [8,9,9]: " + Arrays.toString(solver.plusOne(test2))); // [9, 0, 0]

        int[] test3 = {9, 9, 9};
        System.out.println("Test 3 [9,9,9]: " + Arrays.toString(solver.plusOne(test3))); // [1, 0, 0, 0]
    }
}

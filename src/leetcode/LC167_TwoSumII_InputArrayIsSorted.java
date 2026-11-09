package leetcode;

import java.util.Arrays;

// LeetCode #167 - Two Sum II - Input Array Is Sorted
// Difficulty: Medium (Top Interview 150 / Blind 75)
// Problem: Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order,
//          find two numbers such that they add up to a specific target number.
//          Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.
//          You may not use the same element twice. Your solution must use only constant extra space.
//
// =================================================================================================
// 💡 TECHNIQUE: TWO POINTERS (INWARD SQUEEZE)
// =================================================================================================
// - Since the array is already sorted in non-decreasing order:
//     * Place pointer i at 0 (smallest value) and pointer j at length - 1 (largest value).
//     * If numbers[i] + numbers[j] == target: Solution found! Store/return 1-indexed [i + 1, j + 1].
//     * If numbers[i] + numbers[j] < target: Sum is too small -> increment left pointer (i++).
//     * If numbers[i] + numbers[j] > target: Sum is too large -> decrement right pointer (j--).
//
// Complexity:
//   - Time Complexity: O(N) — Pointers i and j move towards each other, visiting at most N elements.
//   - Space Complexity: O(1) Auxiliary Space — Only pointers and the 2-element answer array.
// =================================================================================================

public class LC167_TwoSumII_InputArrayIsSorted {

    // Krish's 2-Pointer Solution (Passed on LeetCode)
    public int[] twoSum(int[] numbers, int target) {
        int[] ans = new int[2];
        int i = 0;
        int j = numbers.length - 1;

        while (i < j) {
            int x = numbers[i];
            int y = numbers[j];
            if ((x + y) == target) {
                ans[0] = i + 1;
                ans[1] = j + 1;
                break;
            } else if ((x + y) < target) {
                i++;
            } else {
                j--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        LC167_TwoSumII_InputArrayIsSorted solver = new LC167_TwoSumII_InputArrayIsSorted();

        int[] test1 = {2, 7, 11, 15};
        System.out.println("Test 1 [2,7,11,15], target 9: " + Arrays.toString(solver.twoSum(test1, 9))); // [1, 2]

        int[] test2 = {2, 3, 4};
        System.out.println("Test 2 [2,3,4], target 6: " + Arrays.toString(solver.twoSum(test2, 6))); // [1, 3]

        int[] test3 = {-1, 0};
        System.out.println("Test 3 [-1,0], target -1: " + Arrays.toString(solver.twoSum(test3, -1))); // [1, 2]
    }
}

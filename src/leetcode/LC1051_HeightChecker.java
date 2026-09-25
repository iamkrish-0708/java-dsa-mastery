package leetcode;

import java.util.Arrays;

/**
 * LeetCode #1051: Height Checker
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/height-checker/
 * 
 * Approach: Clone Array + In-Place Insertion Sort + Mismatch Count
 * 1. Clone original heights array to preserve the initial student order.
 * 2. Sort the heights array in-place using Insertion Sort.
 * 3. Compare sorted heights with the original copy element-by-element.
 * 4. Increment output count for every mismatched index where heights[j] != copy[j].
 * 
 * Time Complexity: O(N^2) for sorting + O(N) for comparison
 * Space Complexity: O(N) for the cloned array
 */
public class LC1051_HeightChecker {

    public int heightChecker(int[] heights) {
        int output = 0;
        int[] copy = heights.clone();

        for (int i = 1; i < heights.length; i++) {
            int currVal = heights[i];
            int prev = i - 1;
            while (prev >= 0 && heights[prev] > currVal) {
                heights[prev + 1] = heights[prev];
                prev--;
            }
            heights[prev + 1] = currVal;
        }

        for (int j = 0; j < heights.length; j++) {
            if (heights[j] != copy[j]) {
                output++;
            }
        }
        return output;
    }

    public static void main(String[] args) {
        LC1051_HeightChecker solver = new LC1051_HeightChecker();

        // Test 1: [1,1,4,2,1,3] -> Expected sorted: [1,1,1,2,3,4] -> Mismatches at indices 2, 4, 5 -> 3
        int[] heights1 = {1, 1, 4, 2, 1, 3};
        System.out.println("Test 1 Result: " + solver.heightChecker(heights1));
        // Expected: 3

        // Test 2: [5,1,2,3,4] -> Expected sorted: [1,2,3,4,5] -> All 5 mismatch -> 5
        int[] heights2 = {5, 1, 2, 3, 4};
        System.out.println("Test 2 Result: " + solver.heightChecker(heights2));
        // Expected: 5

        // Test 3: [1,2,3,4,5] -> Already sorted -> 0
        int[] heights3 = {1, 2, 3, 4, 5};
        System.out.println("Test 3 Result: " + solver.heightChecker(heights3));
        // Expected: 0
    }
}

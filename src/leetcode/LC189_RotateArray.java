package leetcode;

import java.util.Arrays;

// LeetCode #189 - Rotate Array
// Difficulty: Medium (Top Interview 150 / Blind 75)
// Problem: Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
//
// =================================================================================================
// 💡 EVOLUTION OF SOLUTIONS (KRISH'S FULL PROGRESSION):
// =================================================================================================
//
// Approach 1 (Krish's Initial Clone & Two-Pointer Placement - Passed):
//   - Normalize k: k = k % size (handles cases where k > size).
//   - Clone original array: int[] temp = nums.clone().
//   - Copy the last k elements (from size - k to size - 1) to the front of nums.
//   - Copy the remaining prefix elements (from 0 to size - k - 1) right after.
//   - Complexity: O(N) Time, O(N) Auxiliary Space.
//
// Approach 2 (In-Place 3-Step Array Reversal - Passed):
//   - Follow-up requirement: O(1) extra space in-place.
//   - Step 1: Reverse the ENTIRE array (0 to n - 1).
//   - Step 2: Reverse the first k elements (0 to k - 1).
//   - Step 3: Reverse the remaining n - k elements (k to n - 1).
//   - Complexity: O(N) Time, O(1) Auxiliary Space.
// =================================================================================================

public class LC189_RotateArray {

    // Approach 2: Optimal In-Place 3-Step Reversal (O(1) Space)
    static void reverse(int[] nums, int s, int e) {
        while (s < e) {
            int temp = nums[s];
            nums[s] = nums[e];
            nums[e] = temp;
            s++;
            e--;
        }
    }

    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        reverse(nums, 0, n - 1); // rotate completely
        reverse(nums, 0, k - 1); // rotate only first k elements
        reverse(nums, k, n - 1); // rotate the elements after first k elements
    }

    // Approach 1: Krish's Initial Clone Solution (O(N) Space)
    public void rotateWithClone(int[] nums, int k) {
        int[] temp = nums.clone();
        int size = nums.length;
        k = k % size; // saves for cases like k > size
        int x = 0;
        int y = size - k;
        int ptr = 0;
        while (y < size && ptr < size) {
            nums[ptr] = temp[y];
            ptr++;
            y++;
        }
        while (x < (size - k) && ptr < size) {
            nums[ptr] = temp[x];
            ptr++;
            x++;
        }
    }

    public static void main(String[] args) {
        LC189_RotateArray solver = new LC189_RotateArray();

        int[] test1 = {1, 2, 3, 4, 5, 6, 7};
        solver.rotate(test1, 3);
        System.out.println("Test 1 Expected: [5, 6, 7, 1, 2, 3, 4] | Output: " + Arrays.toString(test1));

        int[] test2 = {-1, -100, 3, 99};
        solver.rotate(test2, 2);
        System.out.println("Test 2 Expected: [3, 99, -1, -100] | Output: " + Arrays.toString(test2));

        int[] test3 = {1, 2, 3, 4, 5, 6, 7};
        solver.rotateWithClone(test3, 3);
        System.out.println("Test 3 (Clone) Expected: [5, 6, 7, 1, 2, 3, 4] | Output: " + Arrays.toString(test3));
    }
}

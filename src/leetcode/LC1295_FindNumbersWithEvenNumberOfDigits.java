package leetcode;

// LeetCode #1295 - Find Numbers with Even Number of Digits
// Difficulty: Easy
// Problem: Given an array nums of integers, return how many of them contain an even number of digits.
// Approach: Digit Counting via Iterative Division + Bitwise Parity Check
// Key Mechanics:
//   - evenDigit(n): Counts number of digits by repeatedly dividing by 10 while n >= 1.
//   - Uses bitwise AND (count & 1) == 0 to check if the digit count is even (fast & clean).
//   - Iterates through nums, incrementing output counter for every number satisfying evenDigit(i).
// Complexity: O(N * log_10(max(nums))) Time (at most 6 divisions per number since nums[i] <= 10^5),
//             O(1) Auxiliary Space
// Problem Link: https://leetcode.com/problems/find-numbers-with-even-number-of-digits/

public class LC1295_FindNumbersWithEvenNumberOfDigits {

    static boolean evenDigit(int n) {
        int count = 0;
        while (n >= 1) {
            n = n / 10;
            count++;
        }
        return (count & 1) == 0;
    }

    public int findNumbers(int[] nums) {
        int output = 0;
        for (int i : nums) {
            if (evenDigit(i)) {
                output++;
            }
        }
        return output;
    }

    public static void main(String[] args) {
        LC1295_FindNumbersWithEvenNumberOfDigits solver = new LC1295_FindNumbersWithEvenNumberOfDigits();
        int[] nums1 = {12, 345, 2, 6, 7896};
        System.out.println("Output 1: " + solver.findNumbers(nums1)); // Expected: 2 (12 and 7896)

        int[] nums2 = {555, 901, 482, 1771};
        System.out.println("Output 2: " + solver.findNumbers(nums2)); // Expected: 1 (1771)
    }
}

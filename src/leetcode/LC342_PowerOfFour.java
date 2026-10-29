package leetcode;

// LeetCode #342 - Power of Four
// Difficulty: Easy
// Problem: Given an integer n, return true if it is a power of four (n == 4^x). Otherwise, return false.
// Approach: Top-Down Recursive Division
// Key Mechanics:
//   - Base Case 1: If n <= 0, powers of 4 are strictly positive -> return false.
//   - Base Case 2: If n == 1 (4^0), return true.
//   - Remainder Check: If n % 4 != 0, it cannot be decomposed strictly into factors of 4 -> return false.
//   - Recursive Step: Peel off one factor of 4 via return isPowerOfFour(n / 4).
// Complexity: O(log_4 N) Time, O(log_4 N) Auxiliary Space (recursion stack)
// Note: Completes the classic Power of X trilogy (LC 231, LC 326, LC 342).
// Problem Link: https://leetcode.com/problems/power-of-four/

public class LC342_PowerOfFour {
    public boolean isPowerOfFour(int n) {
        if (n <= 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }
        if (n % 4 != 0) {
            return false;
        }
        return isPowerOfFour(n / 4);
    }
}

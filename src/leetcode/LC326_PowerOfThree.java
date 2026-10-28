package leetcode;

// LeetCode #326 - Power of Three
// Difficulty: Easy
// Problem: Given an integer n, return true if it is a power of three. Otherwise, return false.
// Approach 1 (Krish's Working Approach): Bottom-Up Recursion with Math.pow() (double to avoid int overflow)
//   - Checks 3^0, 3^1, 3^2, ... until ans >= n.
// Approach 2 (Standard Optimal Recursion): Top-Down Reduction (n / 3)
//   - If n <= 0 -> false
//   - If n == 1 -> true (3^0)
//   - If n % 3 != 0 -> false
//   - return isPowerOfThree(n / 3)
// Complexity: O(log_3 N) Time, O(log_3 N) Space (recursion stack)
// Problem Link: https://leetcode.com/problems/power-of-three/

public class LC326_PowerOfThree {

    // Approach 1: Bottom-up recursive power check (Krish's original passing code)
    static boolean power3(int n, int i) {
        double ans = Math.pow(3, i);
        if (ans > n) {
            return false;
        }
        if (ans < n) {
            return power3(n, i + 1);
        }
        return true;
    }

    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        return power3(n, 0);
    }

    // Approach 2: Optimal top-down recursive division (Standard interview way)
    public boolean isPowerOfThreeOptimal(int n) {
        if (n <= 0) return false;
        if (n == 1) return true;
        if (n % 3 != 0) return false;
        return isPowerOfThreeOptimal(n / 3);
    }
}

package leetcode;

import java.util.ArrayList;
import java.util.List;

// LeetCode #1281 - Subtract the Product and Sum of Digits of an Integer
// Difficulty: Easy
// Problem: Given an integer number n, return the difference between the product of its digits and the sum of its digits.
//
// Approach 1 (Initial Solve - 1ms):
//   - Recursive Digit Extraction into List<Integer> + separate iteration to accumulate product & sum.
//
// Approach 2 (Optimized In-Place Accumulation - 0ms ⚡):
//   - Passes running product and running sum directly as accumulator parameters in the recursive call.
//   - Eliminates ArrayList heap allocation and boxing/unboxing overhead.
//   - Reaches base case (num == 0) and stores final product & sum into a fixed int[2] array.
//
// Complexity:
//   - Time: O(log_10 N) (single pass through digits)
//   - Space: O(log_10 N) (recursion stack only; no auxiliary dynamic list)
// Problem Link: https://leetcode.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer/

public class LC1281_SubtractProductAndSumOfDigits {

    // Approach 2: 0ms Optimized Accumulator Passing
    static void digitsEvalOptimized(int num, int product, int sum, int[] arr) {
        if (num == 0) {
            arr[0] = product;
            arr[1] = sum;
            return;
        }
        int digit = num % 10;
        product = product * digit;
        sum = sum + digit;
        num = num / 10;
        digitsEvalOptimized(num, product, sum, arr);
    }

    public int subtractProductAndSum(int n) {
        int[] arr = new int[2];
        digitsEvalOptimized(n, 1, 0, arr);
        return arr[0] - arr[1];
    }

    // Approach 1: Initial 1ms List-based solution (kept for learning progression)
    static void digitsEvalList(int num, List<Integer> d) {
        if (num == 0) {
            return;
        }
        int digit = num % 10;
        num = num / 10;
        d.add(digit);
        digitsEvalList(num, d);
    }
}

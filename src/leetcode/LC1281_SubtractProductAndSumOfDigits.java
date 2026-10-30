package leetcode;

import java.util.ArrayList;
import java.util.List;

// LeetCode #1281 - Subtract the Product and Sum of Digits of an Integer
// Difficulty: Easy
// Problem: Given an integer number n, return the difference between the product of its digits and the sum of its digits.
// Approach: Recursive Digit Extraction into List + Iterative Product & Sum Evaluation
// Key Mechanics:
//   - digitsEval(num, d): Recursively peels off the least significant digit (num % 10)
//     and appends to list d, then recurses with num / 10 until num == 0 (Base Case).
//   - Post-processes the extracted digits list with a loop to compute product and sum.
//   - Returns (product - sum).
// Complexity: O(log_10 N) Time (number of digits in n), O(log_10 N) Auxiliary Space (call stack + list)
// Problem Link: https://leetcode.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer/

public class LC1281_SubtractProductAndSumOfDigits {

    static void digitsEval(int num, List<Integer> d) {
        if (num == 0) {
            return;
        }
        int digit = num % 10;
        num = num / 10;
        d.add(digit);
        digitsEval(num, d);
    }

    public int subtractProductAndSum(int n) {
        List<Integer> digit = new ArrayList<>();
        digitsEval(n, digit);
        int product = 1;
        int sum = 0;
        for (Integer i : digit) {
            product = product * i;
            sum = sum + i;
        }
        return (product - sum);
    }
}

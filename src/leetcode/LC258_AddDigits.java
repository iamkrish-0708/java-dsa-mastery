package leetcode;

// LeetCode #258 - Add Digits
// Difficulty: Easy
// Problem: Given an integer num, repeatedly add all its digits until the result has only one digit, and return it.
// Approach: Iterative Digit Sum + Recursive Single-Digit Reduction (Top-Down)
// Key Mechanics:
//   - Extract each digit using num % 10 and accumulate into ans while num > 0.
//   - Base Case: If accumulated digit sum ans < 10, it is already a single digit; return ans directly.
//   - Recursive Step: If ans >= 10, recurse on addDigits(ans) until the base case is reached.
// Complexity: O(log N) Time, O(log N) Space (recursion stack)
// Note: Can also be solved in O(1) using digital root math: 1 + (num - 1) % 9 for num > 0.
// Problem Link: https://leetcode.com/problems/add-digits/

public class LC258_AddDigits {
    public int addDigits(int num) {
        if (num == 0) {
            return 0;
        }
        int ans = 0;

        while (num > 0) {
            int digit = num % 10;
            ans += digit;
            num = num / 10;
        }

        if (ans < 10) {
            return ans;
        }
        return addDigits(ans);
    }
}

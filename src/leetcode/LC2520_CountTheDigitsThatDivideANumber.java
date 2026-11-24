package leetcode;

// LeetCode #2520 - Count the Digits That Divide a Number
// Difficulty: Easy
// Problem: Given an integer num, return the number of digits in num that divide num.
//          An integer val divides num if num % val == 0.
//          Note: Every digit in num is non-zero according to problem constraints.
//
// =================================================================================================
// 💡 TECHNIQUE: ELEMENTARY DIGIT EXTRACTION & MODULO ARITHMETIC (KRISH'S SOLVE - 0 ms ⚡ BEATS 100%)
// =================================================================================================
// - Mechanics:
//     1. Preserve original number in num, and create a copy temp = num for digit peeling.
//     2. Loop while temp >= 1:
//        - Extract rightmost digit: int digit = temp % 10.
//        - Check divisibility against the original number: if (num % digit == 0) count++.
//        - Truncate last digit: temp = temp / 10.
//     3. Return final accumulated count.
//
// Complexity:
//   - Time Complexity: O(log10(N)) — Proportional to the number of digits in num (at most 9-10 iterations).
//   - Space Complexity: O(1) Auxiliary Space — Purely primitive integers.
// =================================================================================================

public class LC2520_CountTheDigitsThatDivideANumber {

    // Approach 1: Digit-by-Digit Modulo Extraction (Krish's Accepted Solve - 0 ms Beats 100%)
    public int countDigits(int num) {
        int temp = num;
        int count = 0;
        while (temp >= 1) {
            int digit = temp % 10;
            if ((num % digit) == 0) {
                count++;
            }
            temp = temp / 10;
        }
        return count;
    }

    public static void main(String[] args) {
        LC2520_CountTheDigitsThatDivideANumber solver = new LC2520_CountTheDigitsThatDivideANumber();

        // Test Case 1: num = 7 -> 7 divides 7 -> 1
        int res1 = solver.countDigits(7);
        System.out.println("Test 1 (7): " + res1 + " (Expected: 1)");

        // Test Case 2: num = 121 -> 1 divides 121, 2 doesn't, 1 divides 121 -> 2
        int res2 = solver.countDigits(121);
        System.out.println("Test 2 (121): " + res2 + " (Expected: 2)");

        // Test Case 3: num = 1248 -> 1, 2, 4, 8 all divide 1248 -> 4
        int res3 = solver.countDigits(1248);
        System.out.println("Test 3 (1248): " + res3 + " (Expected: 4)");
    }
}

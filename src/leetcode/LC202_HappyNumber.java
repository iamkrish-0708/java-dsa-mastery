package leetcode;

import java.util.HashSet;
import java.util.Set;

// LeetCode #202 - Happy Number
// Difficulty: Easy (Top Interview 150)
// Problem: Write an algorithm to determine if a number n is happy.
//          A happy number is a number defined by the following process:
//          - Starting with any positive integer, replace the number by the sum of the squares of its digits.
//          - Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle
//            which does not include 1.
//          - Those numbers for which this process ends in 1 are happy.
//          Return true if n is a happy number, and false if not.
//
// =================================================================================================
// 💡 TECHNIQUE: CYCLE DETECTION & MATHEMATICAL INVARIANT (KRISH'S EVOLUTION - 2 ms ➔ 1 ms ⚡)
// =================================================================================================
// - Mechanics & Evolution:
//     1. Helper function solve(num): Peels digits using % 10 and / 10, accumulating sum of squares.
//     2. Approach 1 (HashSet - 2 ms):
//        Detects loop repetition by storing visited numbers in a Set<Integer>.
//     3. Approach 2 (Single-Digit Math Invariant - 1 ms / 0 ms ⚡):
//        Mathematical proof shows all numbers eventually reduce to a single digit [1..9].
//        Tracing each single digit reveals:
//        - 1 -> 1 (Happy)
//        - 7 -> 49 -> 97 -> 130 -> 10 -> 1 (Happy)
//        - Every other single digit (2, 3, 4, 5, 6, 8, 9) is trapped in the famous 4-cycle:
//          (4 -> 16 -> 37 -> 58 -> 89 -> 145 -> 42 -> 20 -> 4).
//        Therefore, reduce until n <= 9, then check if n == 1 || n == 7!
//
// Complexity:
//   - Time Complexity: O(log N) — Digits shrink rapidly.
//   - Space Complexity: O(1) Auxiliary Space for the mathematical invariant approach.
// =================================================================================================

public class LC202_HappyNumber {

    // Helper: Sum of squares of digits
    static int solve(int num) {
        int sum = 0;
        while (num >= 1) {
            int digit = num % 10;
            sum += (digit * digit);
            num = num / 10;
        }
        return sum;
    }

    // Approach 1: HashSet Cycle Detection (Krish's Initial Solve - 2 ms)
    public boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();
        while (n != 1) {
            if (seen.contains(n)) {
                return false;
            }
            seen.add(n);
            n = solve(n);
        }
        return true;
    }

    // Approach 2: Single-Digit Mathematical Invariant (Krish's Optimized Solve - 1 ms Beats 100%)
    public boolean isHappyMath(int n) {
        // 1 and 7 are only happy numbers as single digits
        while (n > 9) {
            n = solve(n);
        }
        return n == 1 || n == 7;
    }

    // Approach 3: Floyd's Tortoise & Hare (O(1) Space Pointer Simulation)
    public boolean isHappyFloyd(int n) {
        int slow = n;
        int fast = solve(n);

        while (fast != 1 && slow != fast) {
            slow = solve(slow);
            fast = solve(solve(fast));
        }

        return fast == 1;
    }

    public static void main(String[] args) {
        LC202_HappyNumber solver = new LC202_HappyNumber();

        // Test Case 1: 19 -> True
        System.out.println("Test 1 (19): " + solver.isHappy(19) + " (Expected: true)");
        System.out.println("Math Test (19): " + solver.isHappyMath(19) + " (Expected: true)");

        // Test Case 2: 2 -> False
        System.out.println("Test 2 (2): " + solver.isHappy(2) + " (Expected: false)");
        System.out.println("Math Test (2): " + solver.isHappyMath(2) + " (Expected: false)");

        // Test Case 3: 7 -> True
        System.out.println("Test 3 (7): " + solver.isHappy(7) + " (Expected: true)");
        System.out.println("Math Test (7): " + solver.isHappyMath(7) + " (Expected: true)");
    }
}

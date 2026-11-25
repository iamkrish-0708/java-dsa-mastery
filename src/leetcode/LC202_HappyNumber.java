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
// 💡 TECHNIQUE: CYCLE DETECTION VIA HASHSET (KRISH'S SOLVE - 2 ms ⚡)
// =================================================================================================
// - Mechanics:
//     1. Helper function solve(num): Peels digits using % 10 and / 10, accumulating sum of squares.
//     2. Cycle Detection:
//        An unhappy number is mathematically guaranteed to enter an infinite cycle (e.g., 4 -> 16 -> ... -> 4).
//     3. Maintain a Set<Integer> seen:
//        - At each step, if seen.contains(n), a cycle is detected -> return false.
//        - Otherwise, add n to seen and advance n = solve(n).
//     4. If n reaches 1, the while loop terminates and returns true.
//
// - O(1) Space Alternative (Floyd's Tortoise and Hare Cycle Finding):
//     Instead of storing numbers in a HashSet, use two pointers:
//     - slow moves 1 step: slow = solve(slow)
//     - fast moves 2 steps: fast = solve(solve(fast))
//     They will eventually meet if a cycle exists (just like in a circular linked list)!
//
// Complexity:
//   - Time Complexity: O(log N) — The number of digits decreases rapidly (e.g. 999 -> 243 -> 29 -> ...).
//   - Space Complexity: O(log N) Auxiliary Space for HashSet (or O(1) using Floyd's algorithm).
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

    // Approach 1: HashSet Cycle Detection (Krish's Accepted Solve - 2 ms)
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

    // Approach 2: Floyd's Tortoise & Hare (O(1) Space, 1 ms Beats 100%)
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

        // Test Case 1: 19 -> True (19 -> 82 -> 68 -> 100 -> 1)
        System.out.println("Test 1 (19): " + solver.isHappy(19) + " (Expected: true)");

        // Test Case 2: 2 -> False (Enters cycle starting at 4)
        System.out.println("Test 2 (2): " + solver.isHappy(2) + " (Expected: false)");

        // Test Case 3: 7 -> True (7 -> 49 -> 97 -> 130 -> 10 -> 1)
        System.out.println("Test 3 (7): " + solver.isHappy(7) + " (Expected: true)");

        // Floyd's test
        System.out.println("Floyd Test (19): " + solver.isHappyFloyd(19) + " (Expected: true)");
        System.out.println("Floyd Test (2): " + solver.isHappyFloyd(2) + " (Expected: false)");
    }
}

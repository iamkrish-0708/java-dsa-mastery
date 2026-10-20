package leetcode;

/**
 * LeetCode #1342: Number of Steps to Reduce a Number to Zero
 * Difficulty: Easy (Top Liked)
 * Link: https://leetcode.com/problems/number-of-steps-to-reduce-a-number-to-zero/
 *
 * Approach: Bitwise Parity Inspection & Simulation
 * - While num > 0:
 *   - Check parity using bitwise AND: `(num & 1) == 0`.
 *   - If even, divide by 2 (`num / 2` or `num >>= 1`).
 *   - If odd, subtract 1 (`num - 1`).
 *   - Increment steps count in each operation.
 * - Terminates when num reaches 0.
 *
 * Time Complexity: O(log N) - In binary, each right shift cuts the number in half
 * Space Complexity: O(1) - Constant auxiliary space
 */
public class LC1342_NumberOfStepsToReduceANumberToZero {

    public int numberOfSteps(int num) {
        int steps = 0;
        while (num != 0) {
            if ((num & 1) == 0) {
                num = num / 2;
                steps++;
            } else {
                num = num - 1;
                steps++;
            }
        }
        return steps;
    }

    public static void main(String[] args) {
        LC1342_NumberOfStepsToReduceANumberToZero solver = new LC1342_NumberOfStepsToReduceANumberToZero();

        // Test Case 1: Standard number with alternating steps
        // 14 -> 7 -> 6 -> 3 -> 2 -> 1 -> 0 (6 steps)
        System.out.println("Test 1 (num = 14): " + solver.numberOfSteps(14) + " | Expected: 6");

        // Test Case 2: Power of 2 (Pure division)
        // 8 -> 4 -> 2 -> 1 -> 0 (4 steps)
        System.out.println("Test 2 (num = 8): " + solver.numberOfSteps(8) + " | Expected: 4");

        // Test Case 3: Odd number
        // 123 (12 steps)
        System.out.println("Test 3 (num = 123): " + solver.numberOfSteps(123) + " | Expected: 12");

        // Test Case 4: Zero
        System.out.println("Test 4 (num = 0): " + solver.numberOfSteps(0) + " | Expected: 0");
    }
}

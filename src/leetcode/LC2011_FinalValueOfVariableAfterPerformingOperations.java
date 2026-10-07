package leetcode;

/**
 * LeetCode #2011: Final Value of Variable After Performing Operations
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/final-value-of-variable-after-performing-operations/
 * 
 * Approach: String Inspection / Simulation
 * 1. Initialize `x = 0`.
 * 2. Traverse each operation:
 *    - If the operation contains '-' (or charAt(1) == '-'), decrement `x`.
 *    - Otherwise, increment `x`.
 * 
 * Time Complexity: O(N) - 0ms Beats 100%
 * Space Complexity: O(1) Auxiliary Space
 */
public class LC2011_FinalValueOfVariableAfterPerformingOperations {

    public int finalValueAfterOperations(String[] operations) {
        int X = 0;
        for (String str : operations) {
            if (str.indexOf("-") != -1) {
                X = X - 1;
            } else {
                X = X + 1;
            }
        }
        return X;
    }

    public static void main(String[] args) {
        LC2011_FinalValueOfVariableAfterPerformingOperations solver = new LC2011_FinalValueOfVariableAfterPerformingOperations();

        // Test 1: ["--X","X++","X++"] -> 1
        String[] ops1 = {"--X", "X++", "X++"};
        System.out.println("Test 1 Result: " + solver.finalValueAfterOperations(ops1)); // Expected: 1

        // Test 2: ["++X","++X","X++"] -> 3
        String[] ops2 = {"++X", "++X", "X++"};
        System.out.println("Test 2 Result: " + solver.finalValueAfterOperations(ops2)); // Expected: 3

        // Test 3: ["X++","++X","--X","X--"] -> 0
        String[] ops3 = {"X++", "++X", "--X", "X--"};
        System.out.println("Test 3 Result: " + solver.finalValueAfterOperations(ops3)); // Expected: 0
    }
}

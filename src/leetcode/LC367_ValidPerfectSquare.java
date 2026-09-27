package leetcode;

/**
 * LeetCode #367: Valid Perfect Square
 * Difficulty: Easy (Top Interview 150 / Blind 75)
 * Link: https://leetcode.com/problems/valid-perfect-square/
 * 
 * Approach: Binary Search on Monotonic Answer Range [1, num]
 * - The square root of num must lie in the sorted range [1, num].
 * - Compute mid with overflow-safe formula: mid = s + (e - s) / 2.
 * - Cast multiplication to `long` to prevent 32-bit integer overflow: `long test = (long) mid * mid`.
 * - If test == num: return true.
 * - If test < num: search right half (s = mid + 1).
 * - If test > num: search left half (e = mid - 1).
 * - If loop ends without exact match: return false.
 * 
 * Time Complexity: O(log(num)) - 0ms Beats 100%
 * Space Complexity: O(1) - Auxiliary Space
 */
public class LC367_ValidPerfectSquare {

    public boolean isPerfectSquare(int num) {
        int s = 1;
        int e = num;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            long test = (long) mid * mid;
            if (test == num) {
                return true;
            } else if (test < num) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LC367_ValidPerfectSquare solver = new LC367_ValidPerfectSquare();

        // Test 1: 16 -> true (4 * 4)
        System.out.println("Test 1 (16): " + solver.isPerfectSquare(16));
        // Expected: true

        // Test 2: 14 -> false
        System.out.println("Test 2 (14): " + solver.isPerfectSquare(14));
        // Expected: false

        // Test 3: 1 -> true (1 * 1)
        System.out.println("Test 3 (1): " + solver.isPerfectSquare(1));
        // Expected: true

        // Test 4: Integer.MAX_VALUE (2147483647) -> false
        System.out.println("Test 4 (MAX_VALUE): " + solver.isPerfectSquare(2147483647));
        // Expected: false
    }
}

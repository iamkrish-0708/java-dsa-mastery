package leetcode;

/**
 * LeetCode #3870: Count Commas in Numbers (or Number of Commas in Range [1, n])
 * Difficulty: Easy
 * 
 * Concept:
 * - Numbers in range [1, 999] have 0 commas.
 * - Numbers in range [1000, 999999] have 1 comma (e.g., "1,000", "999,999").
 * 
 * Approach 1 (Iterative as authored by Krish):
 * - Decrement from n down to 1000 and count every number >= 1000.
 * - Time Complexity: O(n)
 * - Space Complexity: O(1)
 * 
 * Approach 2 (O(1) Direct Math):
 * - If n < 1000, returns 0.
 * - If n >= 1000, returns n - 999 (or Math.max(0, n - 999)).
 * - Time Complexity: O(1)
 * - Space Complexity: O(1)
 */
public class LC3870_CountCommas {

    // Approach 1: User's Iterative Solution
    public int countCommas(int n) {
        int c = 0;
        while (n-- >= 1000) {
            c++;
        }
        return c;
    }

    // Approach 2: O(1) Direct Math Optimization
    public int countCommasOptimal(int n) {
        return Math.max(0, n - 999);
    }

    public static void main(String[] args) {
        LC3870_CountCommas solver = new LC3870_CountCommas();

        int test1 = 999;
        int test2 = 1000;
        int test3 = 1050;

        System.out.println("Test 1 (n = 999)  -> " + solver.countCommas(test1) + " (Expected: 0)");
        System.out.println("Test 2 (n = 1000) -> " + solver.countCommas(test2) + " (Expected: 1)");
        System.out.println("Test 3 (n = 1050) -> " + solver.countCommas(test3) + " (Expected: 51)");

        System.out.println("\nOptimal Math Check:");
        System.out.println("Test 1 Optimal -> " + solver.countCommasOptimal(999));
        System.out.println("Test 2 Optimal -> " + solver.countCommasOptimal(1000));
        System.out.println("Test 3 Optimal -> " + solver.countCommasOptimal(1050));
    }
}

package leetcode;

import java.util.ArrayList;

// LeetCode #1492 - The kth Factor of n
// Difficulty: Medium
// Problem: You are given two positive integers n and k. A factor of an integer n is defined as an integer i
//          where n % i == 0. Consider a list of all factors of n in ascending order, return the kth factor
//          in this list or return -1 if n has less than k factors.
//
// =================================================================================================
// 💡 TECHNIQUE: FACTOR COUNTING & ACCUMULATION (KRISH'S SOLVES - 1 ms ⚡)
// =================================================================================================
// - Approach 1: Dynamic ArrayList Collection
//     * Collect all factors in an ArrayList<Integer> in ascending order.
//     * Return factors.get(k - 1) if k <= factors.size(), else -1.
//     * Space Complexity: O(Number of Factors).
//
// - Approach 2: Constant Auxiliary Space with Early Return (Optimal)
//     * Single pass from x = 1 to n.
//     * When n % x == 0, increment count.
//     * As soon as count == k, return x immediately without completing the loop.
//     * Space Complexity: O(1) Auxiliary Space.
//
// Complexity:
//   - Time Complexity: O(N) linear scan over numbers 1 to n.
//   - Space Complexity: O(1) auxiliary space (Approach 2).
// =================================================================================================

public class LC1492_TheKthFactorOfN {

    // Approach 2: Optimal O(1) Space Early-Return (1 ms)
    public int kthFactor(int n, int k) {
        int x = 1;
        int count = 0;
        while (x <= n) {
            if (n % x == 0) {
                count++;
                if (count == k) {
                    return x;
                }
            }
            x++;
        }
        return -1;
    }

    // Approach 1: ArrayList Collection (1 ms)
    public int kthFactorArrayList(int n, int k) {
        int x = 2;
        ArrayList<Integer> factors = new ArrayList<>();
        factors.add(1);
        while (x <= n) {
            if (n % x == 0) {
                factors.add(x);
            }
            x++;
        }
        if (k > factors.size()) {
            return -1;
        }
        return factors.get(k - 1);
    }

    // Local Test Harness
    public static void main(String[] args) {
        LC1492_TheKthFactorOfN solver = new LC1492_TheKthFactorOfN();

        // Test 1: n = 12, k = 3 -> factors = [1, 2, 3, 4, 6, 12] -> 3rd is 3
        System.out.println("Test 1 (n=12, k=3): " + solver.kthFactor(12, 3) + " (Expected: 3)");

        // Test 2: n = 7, k = 2 -> factors = [1, 7] -> 2nd is 7
        System.out.println("Test 2 (n=7, k=2): " + solver.kthFactor(7, 2) + " (Expected: 7)");

        // Test 3: n = 4, k = 4 -> factors = [1, 2, 4] -> -1
        System.out.println("Test 3 (n=4, k=4): " + solver.kthFactor(4, 4) + " (Expected: -1)");
    }
}

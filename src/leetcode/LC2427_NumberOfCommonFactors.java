package leetcode;

import java.util.HashSet;
import java.util.Set;

// LeetCode #2427 - Number of Common Factors
// Difficulty: Easy
// Problem: Given two positive integers a and b, return the number of common factors of a and b.
//          An integer x is a common factor of a and b if x divides both a and b.
//
// =================================================================================================
// 💡 THREE-STAGE EVOLUTION BY KRISH: 2 ms ➔ 1 ms ➔ 0 ms (BEATS 100% ⚡)
// =================================================================================================
// - Approach 1: HashSet Collision Detection (2 ms)
//     * Collect factors of a in a Set<Integer>.
//     * Check factors of b; if !factors.add(p), p is a duplicate (common factor).
//
// - Approach 2: Bounded Math.min Linear Scan (1 ms)
//     * A common factor cannot exceed min(a, b).
//     * Single loop checking (a % p == 0 && b % p == 0) with zero heap allocation.
//
// - Approach 3: Bitwise Parity Pruning (0 ms Beats 100% ⚡)
//     * Use (end & 1) == 0 to inspect parity via fast bitwise AND.
//     * If end is odd, common factors cannot be even -> step by p += 2, halving loop iterations!
//
// Complexity:
//   - Time Complexity: O(min(a, b)) -> O(min(a, b) / 2) with parity skipping.
//   - Space Complexity: O(1) Auxiliary Space (Approaches 2 & 3).
// =================================================================================================

public class LC2427_NumberOfCommonFactors {

    // Approach 3: Optimal Bitwise Parity Pruning (0 ms Beats 100%)
    static int odd(int end, int p, int a, int b, int count) {
        while (p <= end) {
            if ((a % p == 0) && (b % p == 0)) {
                count++;
            }
            p += 2;
        }
        return count;
    }

    static int even(int end, int p, int a, int b, int count) {
        while (p <= end) {
            if ((a % p == 0) && (b % p == 0)) {
                count++;
            }
            p++;
        }
        return count;
    }

    public int commonFactors(int a, int b) {
        int end = Math.min(a, b);
        if ((end & 1) == 0) {
            return even(end, 2, a, b, 1);
        }
        return odd(end, 1, a, b, 0);
    }

    // Approach 2: Bounded Single Scan (1 ms)
    public int commonFactorsLinear(int a, int b) {
        int p = 1;
        int count = 0;
        int end = Math.min(a, b);
        while (p <= end) {
            if ((a % p == 0) && (b % p == 0)) {
                count++;
            }
            p++;
        }
        return count;
    }

    // Approach 1: HashSet Collision (2 ms)
    public int commonFactorsHashSet(int a, int b) {
        Set<Integer> factors = new HashSet<>();
        int p = 1;
        int count = 0;
        while (p <= a) {
            if (a % p == 0) {
                factors.add(p);
            }
            p++;
        }
        p = 1;
        while (p <= b) {
            if (b % p == 0) {
                if (!factors.add(p)) {
                    count++;
                }
            }
            p++;
        }
        return count;
    }

    // Local Test Harness
    public static void main(String[] args) {
        LC2427_NumberOfCommonFactors solver = new LC2427_NumberOfCommonFactors();

        // Test 1: a = 12, b = 6 -> factors: 1, 2, 3, 6 -> 4
        System.out.println("Test 1 (12, 6): " + solver.commonFactors(12, 6) + " (Expected: 4)");

        // Test 2: a = 25, b = 30 -> factors: 1, 5 -> 2
        System.out.println("Test 2 (25, 30): " + solver.commonFactors(25, 30) + " (Expected: 2)");

        // Test 3: a = 1, b = 1 -> factors: 1 -> 1
        System.out.println("Test 3 (1, 1): " + solver.commonFactors(1, 1) + " (Expected: 1)");
    }
}

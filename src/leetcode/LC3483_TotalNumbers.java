package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode #3483 / #2094: Total Unique 3-Digit Even Numbers
 * Difficulty: Easy
 * 
 * Problem Concept:
 * - Generate all unique 3-digit even numbers using 3 distinct indices (i, j, k) from the given digits array.
 * 
 * Approach (3-Pointer Index Permutation with Pruning & HashSet):
 * 1. Constraint 1 (No leading zeros): digits[i] != 0.
 * 2. Constraint 2 (Distinct indices): i != j and k != i and k != j.
 * 3. Constraint 3 (Must be even): digits[k] is even (checked via bitwise (digits[k] & 1) == 0).
 * 4. Automatic Deduplication: Insert into HashSet<Integer> to count unique numbers.
 * 
 * Time Complexity: O(N^3) where N <= 100 (runs well within limits).
 * Space Complexity: O(U) where U is the number of unique 3-digit even numbers.
 */
public class LC3483_TotalNumbers {

    public int totalNumbers(int[] digits) {
        Set<Integer> uniqueNumbers = new HashSet<>();
        int n = digits.length;

        for (int i = 0; i < n; i++) {
            if (digits[i] == 0) {
                continue;
            }
            for (int j = 0; j < n; j++) {
                if (j == i) {
                    continue;
                }
                for (int k = 0; k < n; k++) {
                    if (k == i || k == j) {
                        continue;
                    } else if ((digits[k] & 1) != 0) {
                        continue;
                    }
                    int num = digits[i] * 100 + digits[j] * 10 + digits[k];
                    uniqueNumbers.add(num);
                }
            }
        }

        return uniqueNumbers.size();
    }

    public static void main(String[] args) {
        LC3483_TotalNumbers solver = new LC3483_TotalNumbers();

        int[] digits1 = {2, 1, 3, 0};
        int[] digits2 = {2, 2, 8, 8, 2};
        int[] digits3 = {3, 7, 5};

        System.out.println("Test 1: [2, 1, 3, 0]       -> " + solver.totalNumbers(digits1) + " unique even numbers");
        System.out.println("Test 2: [2, 2, 8, 8, 2]    -> " + solver.totalNumbers(digits2) + " unique even numbers");
        System.out.println("Test 3: [3, 7, 5]          -> " + solver.totalNumbers(digits3) + " unique even numbers (Expected: 0)");
    }
}

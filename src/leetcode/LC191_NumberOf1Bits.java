package leetcode;

/**
 * LeetCode #191: Number of 1 Bits (Hamming Weight)
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/number-of-1-bits/
 * 
 * Approach 1 (Modulo & Integer Division - Authored by Krish):
 * - Extract binary remainder `n % 2` and divide by 2 (`n = n / 2`).
 * - Time Complexity: O(log2 N) = O(32) = O(1)
 * - Space Complexity: O(1)
 * 
 * Approach 2 (Bitwise Right Shift):
 * - Extract LSB using `n & 1` and right shift `n >>>= 1`.
 * - Time Complexity: O(32) = O(1)
 * - Space Complexity: O(1)
 * 
 * Approach 3 (Brian Kernighan's Algorithm - Hardware Optimal):
 * - In each step, `n = n & (n - 1)` clears the lowest set bit.
 * - Loop runs ONLY as many times as there are 1-bits!
 * - Time Complexity: O(k) where k is the number of set bits (0ms Beats 100%)
 * - Space Complexity: O(1)
 */
public class LC191_NumberOf1Bits {

    // Approach 1: Modulo & Division (Krish's Solution)
    public int hammingWeight(int n) {
        int remainder;
        int count = 0;
        while (n > 0) {
            remainder = n % 2;
            count = count + remainder;
            n = n / 2;
        }
        return count;
    }

    // Approach 2: Bitwise Right Shift
    public int hammingWeightShift(int n) {
        int count = 0;
        while (n != 0) {
            count += (n & 1);
            n >>>= 1;
        }
        return count;
    }

    // Approach 3: Brian Kernighan's Algorithm
    public int hammingWeightBrianKernighan(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1); // Clears the lowest set bit in a single CPU cycle
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        LC191_NumberOf1Bits solver = new LC191_NumberOf1Bits();

        int test1 = 11;         // Binary: 1011 -> 3
        int test2 = 128;        // Binary: 10000000 -> 1
        int test3 = 2147483645; // Binary: 1111111111111111111111111111101 -> 30

        System.out.println("Test 1 (n = 11):");
        System.out.println("  Modulo & Div:     " + solver.hammingWeight(test1) + " (Expected: 3)");
        System.out.println("  Bit Shift:        " + solver.hammingWeightShift(test1));
        System.out.println("  Brian Kernighan:  " + solver.hammingWeightBrianKernighan(test1));

        System.out.println("\nTest 2 (n = 128):");
        System.out.println("  Modulo & Div:     " + solver.hammingWeight(test2) + " (Expected: 1)");
        System.out.println("  Brian Kernighan:  " + solver.hammingWeightBrianKernighan(test2));

        System.out.println("\nTest 3 (n = 2147483645):");
        System.out.println("  Brian Kernighan:  " + solver.hammingWeightBrianKernighan(test3) + " (Expected: 30)");
    }
}

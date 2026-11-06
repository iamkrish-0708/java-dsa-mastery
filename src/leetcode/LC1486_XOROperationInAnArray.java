package leetcode;

// LeetCode #1486 - XOR Operation in an Array
// Difficulty: Easy
// Problem: You are given an integer n and an integer start. Define an array nums where
//          nums[i] = start + 2 * i (0-indexed) and n == nums.length. Return the bitwise
//          XOR of all elements of nums.
//
// =================================================================================================
// 🔍 EVOLUTION OF SOLUTIONS & OPTIMIZATION:
// =================================================================================================
//
// Approach 1 (Two-Pass with Array Allocation - 1 ms):
//   - Pass 1: Allocated int[] nums = new int[n] and populated nums[i] = start + 2 * i.
//   - Pass 2: Iterated from 1 to n - 1 accumulating xor = xor ^ nums[i].
//   - Overhead: Heap memory allocation for int[n] + two separate loop passes over memory.
//
// Approach 2 (Single-Pass with Array - 0 ms Beats 100% ⚡):
//   - Krish's optimization: Combined calculation and XOR accumulation into a single forward loop.
//   - nums[i] = start + 2 * i computed and XORed on the fly!
//
// Approach 3 (Pure O(1) Space - Zero Array Allocation ⚡):
//   - Eliminates int[] nums completely!
//   - We never need to retain the array in memory; calculate element directly inside loop:
//       int xor = 0;
//       for (int i = 0; i < n; i++) xor ^= (start + 2 * i);
//
// Complexity:
//   - Time: O(N) linear time (single pass)
//   - Space: O(1) Auxiliary Space (Approach 3 avoids all heap allocations)
// Problem Link: https://leetcode.com/problems/xor-operation-in-an-array/

public class LC1486_XOROperationInAnArray {

    // Approach 2: Krish's 0ms Single-Pass Solution
    public int xorOperation(int n, int start) {
        int[] nums = new int[n];
        nums[0] = start;
        int xor = nums[0];
        for (int i = 1; i < n; i++) {
            nums[i] = start + (2 * i);
            xor = xor ^ nums[i];
        }
        return xor;
    }

    // Approach 3: Space-Optimized O(1) Auxiliary Memory
    public int xorOperationOptimizedSpace(int n, int start) {
        int xor = 0;
        for (int i = 0; i < n; i++) {
            xor ^= (start + 2 * i);
        }
        return xor;
    }

    public static void main(String[] args) {
        LC1486_XOROperationInAnArray solver = new LC1486_XOROperationInAnArray();
        System.out.println("Result 1: " + solver.xorOperation(5, 0)); // Expected: 8 (0^2^4^6^8 = 8)
        System.out.println("Result 2: " + solver.xorOperation(4, 3)); // Expected: 8 (3^5^7^9 = 8)
    }
}

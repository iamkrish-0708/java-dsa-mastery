package leetcode;

// LeetCode #1486 - XOR Operation in an Array
// Difficulty: Easy
// Problem: You are given an integer n and an integer start. Define an array nums where
//          nums[i] = start + 2 * i (0-indexed) and n == nums.length. Return the bitwise
//          XOR of all elements of nums.
//
// =================================================================================================
// 🔍 EVOLUTION OF SOLUTIONS (KRISH'S WORK):
// =================================================================================================
//
// Approach 1 (Initial Two-Pass Solve - 1 ms):
//   - Pass 1: Allocated int[] nums = new int[n] and populated nums[i] = start + 2 * i.
//   - Pass 2: Iterated from 1 to n - 1 accumulating xor = xor ^ nums[i].
//   - Result: Passed, but took 1 ms due to separate loop passes over the array.
//
// Approach 2 (Krish's Single-Pass Optimization - 0 ms Beats 100% ⚡):
//   - Combined element calculation and XOR accumulation into a single forward loop.
//   - nums[0] initialized to start, xor initialized to nums[0].
//   - From i = 1 to n - 1: calculates nums[i] = start + (2 * i) and accumulates xor = xor ^ nums[i] on the fly!
//   - Result: 0 ms runtime!
//
// Complexity:
//   - Time: O(N) linear time (single pass)
//   - Space: O(N) array allocation
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

    // Approach 1: Krish's Initial 1ms Two-Pass Solution
    public int xorOperationTwoPass(int n, int start) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = start + (2 * i);
        }
        int xor = nums[0];
        for (int i = 1; i < n; i++) {
            xor = xor ^ nums[i];
        }
        return xor;
    }

    public static void main(String[] args) {
        LC1486_XOROperationInAnArray solver = new LC1486_XOROperationInAnArray();
        System.out.println("Result 1: " + solver.xorOperation(5, 0)); // Expected: 8
        System.out.println("Result 2: " + solver.xorOperation(4, 3)); // Expected: 8
    }
}

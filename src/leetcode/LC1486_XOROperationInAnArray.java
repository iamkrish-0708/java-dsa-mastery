package leetcode;

// LeetCode #1486 - XOR Operation in an Array
// Difficulty: Easy
// Problem: You are given an integer n and an integer start. Define an array nums where
//          nums[i] = start + 2 * i (0-indexed) and n == nums.length. Return the bitwise
//          XOR of all elements of nums.
//
// =================================================================================================
// 🔍 EVOLUTION OF SOLUTIONS (KRISH'S FULL INDEPENDENT PROGRESSION):
// =================================================================================================
//
// Approach 1 (Initial Two-Pass Solve - 1 ms):
//   - Pass 1: Allocated int[] nums = new int[n] and populated nums[i] = start + 2 * i.
//   - Pass 2: Iterated from 1 to n - 1 accumulating xor = xor ^ nums[i].
//   - Result: 1 ms due to separate loop passes over the array.
//
// Approach 2 (Single-Pass Loop Optimization - 0 ms Beats 100% ⚡):
//   - Combined element calculation and XOR accumulation into a single forward loop.
//   - Populated nums[i] and accumulated xor in the same iteration.
//   - Complexity: O(N) Time, O(N) Space.
//
// Approach 3 (Krish's O(1) Space Optimization - 0 ms ⚡):
//   - Realized that 'nums' array is never actually returned or needed in memory!
//   - Eliminated int[] nums allocation entirely.
//   - Initialized xor = start, then looped from i = 1 to n - 1 computing int num = start + (2 * i)
//     directly on the stack and accumulating into xor.
//   - Complexity: O(N) Time, O(1) Auxiliary Space!
// =================================================================================================

public class LC1486_XOROperationInAnArray {

    // Approach 3: Krish's O(1) Space-Optimized Solution (0 ms Beats 100%)
    public int xorOperation(int n, int start) {
        int xor = start;
        for (int i = 1; i < n; i++) {
            int num = start + (2 * i);
            xor = xor ^ num;
        }
        return xor;
    }

    // Approach 2: Krish's Single-Pass Array Solution (0 ms)
    public int xorOperationSinglePass(int n, int start) {
        int[] nums = new int[n];
        nums[0] = start;
        int xor = nums[0];
        for (int i = 1; i < n; i++) {
            nums[i] = start + (2 * i);
            xor = xor ^ nums[i];
        }
        return xor;
    }

    // Approach 1: Krish's Initial Two-Pass Solution (1 ms)
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

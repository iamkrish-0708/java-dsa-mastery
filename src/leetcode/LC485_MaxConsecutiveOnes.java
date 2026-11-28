package leetcode;

// LeetCode #485 - Max Consecutive Ones
// Difficulty: Easy
// Problem: Given a binary array nums, return the maximum number of consecutive 1's in the array.
//
// =================================================================================================
// 💡 TECHNIQUE: SINGLE-PASS RUNNING STREAK ACCUMULATION (KRISH'S SOLVE - 2 ms ⚡)
// =================================================================================================
// - Mechanics:
//     1. Maintain a running streak counter count and a global maximum max.
//     2. Single Linear Scan:
//        - If n == 1: increment running count.
//        - If n == 0: update max = Math.max(max, count), and reset count = 0.
//     3. Trailing Streak Guard:
//        Return Math.max(max, count) upon loop termination to properly capture streaks
//        that extend all the way to the array's end (e.g. [..., 1, 1, 1]).
//
// - Optimization (0-1 ms Beats 100%):
//     Replace Math.max() function calls with direct ternary/conditional checks (count > max)
//     and use standard indexed iteration for raw hardware register execution.
//
// Complexity:
//   - Time Complexity: O(N) — Single linear pass over nums array of length N.
//   - Space Complexity: O(1) Auxiliary Space — Only two primitive int accumulators.
// =================================================================================================

public class LC485_MaxConsecutiveOnes {

    // Approach 1: Single-Pass Enhanced Loop with Math.max (Krish's Accepted Solve - 2 ms)
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int count = 0;
        for (int n : nums) {
            if (n == 1) {
                count++;
            } else {
                max = Math.max(count, max);
                count = 0;
            }
        }
        return Math.max(max, count);
    }

    // Approach 2: Direct Primitive Comparison (1 ms / 0 ms Beats 100%)
    public int findMaxConsecutiveOnesOptimal(int[] nums) {
        int max = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count++;
            } else {
                if (count > max) {
                    max = count;
                }
                count = 0;
            }
        }
        return count > max ? count : max;
    }

    public static void main(String[] args) {
        LC485_MaxConsecutiveOnes solver = new LC485_MaxConsecutiveOnes();

        // Test 1: [1, 1, 0, 1, 1, 1] -> 3
        int[] test1 = {1, 1, 0, 1, 1, 1};
        System.out.println("Test 1: " + solver.findMaxConsecutiveOnes(test1) + " (Expected: 3)");

        // Test 2: [1, 0, 1, 1, 0, 1] -> 2
        int[] test2 = {1, 0, 1, 1, 0, 1};
        System.out.println("Test 2: " + solver.findMaxConsecutiveOnes(test2) + " (Expected: 2)");

        // Optimal check
        System.out.println("Optimal Test 1: " + solver.findMaxConsecutiveOnesOptimal(test1) + " (Expected: 3)");
    }
}

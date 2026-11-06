package leetcode;

// LeetCode #416 - Partition Equal Subset Sum
// Difficulty: Medium (Classic 0/1 Knapsack / Subset Sum Problem)
// Problem: Given an integer array nums, return true if you can partition the array into two subsets
//          such that the sum of the elements in both subsets is equal.
//
// =================================================================================================
// 🔍 ARCHITECTURAL ANALYSIS & KEY LEARNINGS:
// =================================================================================================
//
// 1. Math Observation (Parity Check):
//    - If the total sum of all elements is ODD, it is impossible to partition into two equal integer halves!
//      Immediate pruning: if ((sum & 1) == 1) return false;
//    - Target for each subset becomes: target = sum / 2.
//
// 2. Reduction to Subset Sum (0/1 Bounded Knapsack):
//    - We just need to find ANY subset of nums that sums up to 'target'.
//    - At each index i, we make an Include / Exclude binary decision:
//        * INCLUDE: solve(nums, target - nums[i], i + 1) -> moves to i + 1 because each element can be used AT MOST ONCE.
//        * EXCLUDE: solve(nums, target, i + 1) -> skip nums[i].
//    - Base Cases:
//        * target == 0 -> return true (Valid subset found!)
//        * target < 0  -> return false (Overshot)
//        * i >= nums.length -> return false (Exhausted elements without reaching target)
//
// 3. Complexity & TLE Status:
//    - Pure Recursion: O(2^N) Time, O(N) Space (Stack depth).
//    - Hits expected TLE on LeetCode for large N due to overlapping subproblems.
//    - In Dynamic Programming, memoizing with Boolean[][] dp = new Boolean[n][target + 1]
//      drops time complexity to O(N * Target) polynomial time.
// =================================================================================================

public class LC416_PartitionEqualSubsetSum {

    static boolean solve(int[] nums, int target, int i) {
        // Base cases
        if (target == 0) {
            return true;
        }
        if (target < 0 || i >= nums.length) {
            return false;
        }

        // Include choice (advance to i + 1 since each element used at most once)
        boolean inc = solve(nums, target - nums[i], i + 1);

        // Exclude choice
        boolean exc = solve(nums, target, i + 1);

        return inc || exc;
    }

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }

        // Odd sum can never be divided into two equal integer partitions
        if ((sum & 1) == 1) {
            return false;
        }

        int target = sum / 2;
        return solve(nums, target, 0);
    }

    public static void main(String[] args) {
        LC416_PartitionEqualSubsetSum solver = new LC416_PartitionEqualSubsetSum();

        int[] nums1 = {1, 5, 11, 5};
        System.out.println("Output 1: " + solver.canPartition(nums1));
        // Expected: true (Subset [1, 5, 5] and [11], each sum = 11)

        int[] nums2 = {1, 2, 3, 5};
        System.out.println("Output 2: " + solver.canPartition(nums2));
        // Expected: false (Sum = 11 is odd -> immediate false)
    }
}

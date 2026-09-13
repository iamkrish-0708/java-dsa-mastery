package leetcode;

/**
 * LeetCode #724: Find Pivot Index
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/find-pivot-index/
 * 
 * Note: Identical problem to LeetCode #1991 (Find the Middle Index in Array).
 * 
 * Approach 1 (Prefix & Suffix Sum Arrays - Authored in ArrayProblemP4.java):
 * - Construct leftToRightSum (prefix) and rightToLeftSum (suffix).
 * - Find index i where leftSum == rightSum.
 * - Time Complexity: O(N)
 * - Space Complexity: O(N)
 * 
 * Approach 2 (O(1) Running Prefix Sum Space Optimization):
 * - Precalculate totalSum of the array.
 * - Maintain running leftSum = 0.
 * - At any index i, rightSum = totalSum - leftSum - nums[i].
 * - If leftSum == rightSum, return i.
 * - Time Complexity: O(N) - Single pass (1ms Beats 100%)
 * - Space Complexity: O(1) - Constant auxiliary space
 */
public class LC724_FindPivotIndex {

    // Approach 1: Prefix & Suffix Arrays
    public int pivotIndexPrefixSuffix(int[] nums) {
        int n = nums.length;
        int[] leftToRightSum = new int[n];
        leftToRightSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            leftToRightSum[i] = leftToRightSum[i - 1] + nums[i];
        }

        int[] rightToLeftSum = new int[n];
        rightToLeftSum[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightToLeftSum[i] = rightToLeftSum[i + 1] + nums[i];
        }

        for (int i = 0; i < n; i++) {
            int leftSum = (i == 0) ? 0 : leftToRightSum[i - 1];
            int rightSum = (i == n - 1) ? 0 : rightToLeftSum[i + 1];
            if (leftSum == rightSum) {
                return i;
            }
        }
        return -1;
    }

    // Approach 2: O(1) Space Optimal
    public int pivotIndex(int[] nums) {
        int totalSum = 0;
        for (int x : nums) {
            totalSum += x;
        }

        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (leftSum == totalSum - leftSum - nums[i]) {
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }

    public static void main(String[] args) {
        LC724_FindPivotIndex solver = new LC724_FindPivotIndex();

        int[] test1 = {1, 7, 3, 6, 5, 6};
        int[] test2 = {1, 2, 3};
        int[] test3 = {2, 1, -1};

        System.out.println("Test 1: [1, 7, 3, 6, 5, 6] -> Pivot: " + solver.pivotIndex(test1) + " (Expected: 3)");
        System.out.println("Test 2: [1, 2, 3]          -> Pivot: " + solver.pivotIndex(test2) + " (Expected: -1)");
        System.out.println("Test 3: [2, 1, -1]         -> Pivot: " + solver.pivotIndex(test3) + " (Expected: 0)");
    }
}

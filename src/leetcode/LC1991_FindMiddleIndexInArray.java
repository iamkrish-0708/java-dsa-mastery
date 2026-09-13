package leetcode;

/**
 * LeetCode #1991: Find the Middle Index in Array
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/find-the-middle-index-in-array/
 * 
 * Note: Exact duplicate/rephrasing of LeetCode #724 (Find Pivot Index).
 * 
 * Approach: Single-Pass Running Prefix Sum O(1) Extra Space
 * - Time Complexity: O(N) (0ms Beats 100%)
 * - Space Complexity: O(1)
 */
public class LC1991_FindMiddleIndexInArray {

    public int findMiddleIndex(int[] nums) {
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
        LC1991_FindMiddleIndexInArray solver = new LC1991_FindMiddleIndexInArray();

        int[] test1 = {2, 3, -1, 8, 4};
        int[] test2 = {1, -1, 4};
        int[] test3 = {2, 5};

        System.out.println("Test 1: [2, 3, -1, 8, 4] -> Middle Index: " + solver.findMiddleIndex(test1) + " (Expected: 3)");
        System.out.println("Test 2: [1, -1, 4]       -> Middle Index: " + solver.findMiddleIndex(test2) + " (Expected: 2)");
        System.out.println("Test 3: [2, 5]          -> Middle Index: " + solver.findMiddleIndex(test3) + " (Expected: -1)");
    }
}

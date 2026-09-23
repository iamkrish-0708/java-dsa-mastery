package leetcode;

/**
 * LeetCode #1512: Number of Good Pairs
 * Difficulty: Easy (Top Liked)
 * Link: https://leetcode.com/problems/number-of-good-pairs/
 * 
 * Approach 1: Nested Loop Brute Force
 * - For each element at index i, check all elements at j = i + 1 to n - 1.
 * - If nums[i] == nums[j], increment goodPairCount.
 * - Time Complexity: O(N^2)
 * - Space Complexity: O(1)
 * 
 * Approach 2: Frequency Array / Combinatorics (Optimized)
 * - If a number has appeared k times before, encountering it again creates k new good pairs.
 * - As nums[i] <= 100, use a fixed frequency array count of size 101.
 * - Time Complexity: O(N) - Single pass (0ms Beats 100%).
 * - Space Complexity: O(1) - Fixed size array of 101 integers.
 */
public class LC1512_NumberOfGoodPairs {

    // Approach 1: Brute Force (O(N^2) Time, O(1) Space)
    public int numIdenticalPairsBruteForce(int[] nums) {
        int goodPairCount = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    goodPairCount++;
                }
            }
        }
        return goodPairCount;
    }

    // Approach 2: Optimized Frequency Count (O(N) Time, O(1) Space - 0ms Beats 100%)
    public int numIdenticalPairs(int[] nums) {
        int[] count = new int[101];
        int goodPairCount = 0;

        for (int num : nums) {
            goodPairCount += count[num];
            count[num]++;
        }

        return goodPairCount;
    }

    public static void main(String[] args) {
        LC1512_NumberOfGoodPairs solver = new LC1512_NumberOfGoodPairs();

        // Test 1: Multiple pairs
        int[] nums1 = {1, 2, 3, 1, 1, 3};
        System.out.println("Test 1 Result: " + solver.numIdenticalPairs(nums1)); 
        // Expected: 4 ((0,3), (0,4), (3,4), (2,5))

        // Test 2: All identical
        int[] nums2 = {1, 1, 1, 1};
        System.out.println("Test 2 Result: " + solver.numIdenticalPairs(nums2)); 
        // Expected: 6

        // Test 3: No good pairs
        int[] nums3 = {1, 2, 3};
        System.out.println("Test 3 Result: " + solver.numIdenticalPairs(nums3)); 
        // Expected: 0
    }
}

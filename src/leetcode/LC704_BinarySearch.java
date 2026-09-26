package leetcode;

/**
 * LeetCode #704: Binary Search
 * Difficulty: Easy (Top Interview 150 / Blind 75)
 * Link: https://leetcode.com/problems/binary-search/
 * 
 * Approach: Iterative Binary Search with Overflow-Safe Midpoint
 * - Maintain two pointers: start and end defining the active search space.
 * - Calculate mid using: mid = start + (end - start) / 2 to avoid integer overflow.
 * - If nums[mid] == target, return mid immediately.
 * - If nums[mid] > target, discard the right half by setting end = mid - 1.
 * - If nums[mid] < target, discard the left half by setting start = mid + 1.
 * - If search space becomes empty (start > end), target is absent; return -1.
 * 
 * Time Complexity: O(log N) - Halves search space each iteration (0ms Beats 100%).
 * Space Complexity: O(1) - Auxiliary space.
 */
public class LC704_BinarySearch {

    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        LC704_BinarySearch solver = new LC704_BinarySearch();

        // Test 1: Target exists in array
        int[] nums1 = {-1, 0, 3, 5, 9, 12};
        int target1 = 9;
        System.out.println("Test 1 Result: " + solver.search(nums1, target1));
        // Expected: 4

        // Test 2: Target does not exist in array
        int[] nums2 = {-1, 0, 3, 5, 9, 12};
        int target2 = 2;
        System.out.println("Test 2 Result: " + solver.search(nums2, target2));
        // Expected: -1
    }
}

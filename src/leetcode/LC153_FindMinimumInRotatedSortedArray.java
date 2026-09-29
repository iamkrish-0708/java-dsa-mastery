package leetcode;

/**
 * LeetCode #153: Find Minimum in Rotated Sorted Array
 * Difficulty: Medium (Top Interview 150 / Blind 75)
 * Link: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 * 
 * Approach: Binary Search via Maximum Pivot Detection
 * 1. If nums[0] <= nums[end], array is not rotated -> return nums[0].
 * 2. Otherwise, binary search for the inflection pivot (maximum element before the drop)
 *    by comparing nums[mid] with nums[size].
 * 3. The minimum element is located immediately after the pivot at nums[pivot + 1].
 * 
 * Time Complexity: O(log N) - 0ms Beats 100%
 * Space Complexity: O(1) - Auxiliary Space
 */
public class LC153_FindMinimumInRotatedSortedArray {

    public int findMin(int[] nums) {
        int start = 0;
        int size = nums.length - 1;
        int end = nums.length - 1;
        int pivot = nums.length - 1;
        if (nums[0] <= nums[end]) {
            return nums[0];
        } else {
            while (start <= end) {
                int mid = start + (end - start) / 2;
                if (nums[mid] <= nums[size]) {
                    end = mid - 1;
                } else {
                    pivot = mid;
                    start = mid + 1;
                }
            }
        }
        return nums[pivot + 1];
    }

    public static void main(String[] args) {
        LC153_FindMinimumInRotatedSortedArray solver = new LC153_FindMinimumInRotatedSortedArray();

        // Test 1: Standard rotated array
        int[] nums1 = {3, 4, 5, 1, 2};
        System.out.println("Test 1 Result: " + solver.findMin(nums1));
        // Expected: 1

        // Test 2: Rotated 4 times
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("Test 2 Result: " + solver.findMin(nums2));
        // Expected: 0

        // Test 3: Unrotated sorted array
        int[] nums3 = {11, 13, 15, 17};
        System.out.println("Test 3 Result: " + solver.findMin(nums3));
        // Expected: 11

        // Test 4: Single element array
        int[] nums4 = {1};
        System.out.println("Test 4 Result: " + solver.findMin(nums4));
        // Expected: 1
    }
}

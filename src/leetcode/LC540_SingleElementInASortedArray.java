package leetcode;

/**
 * LeetCode #540: Single Element in a Sorted Array
 * Difficulty: Medium (Top Interview 150 / Blind 75)
 * Link: https://leetcode.com/problems/single-element-in-a-sorted-array/
 * 
 * Approach: Binary Search using Index Parity Pattern
 * 1. Property of Sorted Array with Pairs:
 *    - Left Half (Before Single Element): Pairs start at EVEN index and end at ODD index:
 *      - If mid is EVEN -> nums[mid] == nums[mid + 1]
 *      - If mid is ODD  -> nums[mid] == nums[mid - 1]
 *    - Right Half (After Single Element): Order is disrupted, pairs start at ODD index and end at EVEN index.
 * 2. Binary Search:
 *    - If isValid(mid) is true -> we are in the left half, so the single element is to the right (start = mid + 1).
 *    - Else -> we are at the single element or in the right half (end = mid).
 * 3. Convergence:
 *    - Loop terminates when start == end, pointing directly to the single unique element.
 * 
 * Time Complexity: O(log N) - 0ms Beats 100%
 * Space Complexity: O(1) Auxiliary Space
 */
public class LC540_SingleElementInASortedArray {

    static boolean isValid(int mid, int[] arr) {
        if (((mid & 1) == 0 && arr[mid] == arr[mid + 1]) || ((mid & 1) != 0 && arr[mid - 1] == arr[mid])) {
            return true;
        }
        return false;
    }

    public int singleNonDuplicate(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        if (end == 0) {
            return nums[end];
        }
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (isValid(mid, nums)) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return nums[start];
    }

    public static void main(String[] args) {
        LC540_SingleElementInASortedArray solver = new LC540_SingleElementInASortedArray();

        // Test 1: [1,1,2,3,3,4,4,8,8] -> 2
        int[] nums1 = {1, 1, 2, 3, 3, 4, 4, 8, 8};
        System.out.println("Test 1 Result: " + solver.singleNonDuplicate(nums1)); // Expected: 2

        // Test 2: [3,3,7,7,10,11,11] -> 10
        int[] nums2 = {3, 3, 7, 7, 10, 11, 11};
        System.out.println("Test 2 Result: " + solver.singleNonDuplicate(nums2)); // Expected: 10

        // Test 3: [1,1,2,2,3] (Last element unique) -> 3
        int[] nums3 = {1, 1, 2, 2, 3};
        System.out.println("Test 3 Result: " + solver.singleNonDuplicate(nums3)); // Expected: 3

        // Test 4: [1,2,2,3,3] (First element unique) -> 1
        int[] nums4 = {1, 2, 2, 3, 3};
        System.out.println("Test 4 Result: " + solver.singleNonDuplicate(nums4)); // Expected: 1
    }
}

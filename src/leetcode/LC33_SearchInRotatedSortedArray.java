package leetcode;

/**
 * LeetCode #33: Search in Rotated Sorted Array
 * Difficulty: Medium (Top Interview 150 / Blind 75)
 * Link: https://leetcode.com/problems/search-in-rotated-sorted-array/
 * 
 * Approach: 2-Step Binary Search (Find Pivot -> Binary Search on Respective Subarray)
 * 1. Find Pivot (Maximum Element Index):
 *    - If nums[0] < nums[size-1], array is unrotated -> pivot = size - 1.
 *    - Otherwise, binary search comparing nums[mid] with nums[size-1].
 *      - If nums[mid] <= nums[size-1]: search left (e = mid - 1).
 *      - Else: record pivot = mid and search right (s = mid + 1).
 * 2. Identify Subarray for Target:
 *    - If target lies in left sorted segment (nums[0] <= target <= nums[pivot]):
 *      Binary search in [0, pivot].
 *    - Else:
 *      Binary search in right sorted segment [pivot + 1, size - 1].
 * 
 * Time Complexity: O(log N) - Two logarithmic passes (0ms Beats 100%).
 * Space Complexity: O(1) - Auxiliary Space.
 */
public class LC33_SearchInRotatedSortedArray {

    public int search(int[] nums, int target) {
        int s = 0;
        int e = nums.length - 1;
        int size = nums.length;
        int pivot = size - 1;

        if (nums[0] < nums[size - 1]) {
            pivot = size - 1;
        } else {
            while (s <= e) {
                int mid = s + (e - s) / 2;
                if (nums[mid] <= nums[size - 1]) {
                    e = mid - 1;
                } else {
                    pivot = mid;
                    s = mid + 1;
                }
            }
        }

        int sL2 = pivot + 1;
        s = 0;
        e = nums.length - 1;

        if (nums[0] <= target && nums[pivot] >= target) {
            e = pivot;
            while (s <= e) {
                int mid = s + (e - s) / 2;
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] < target) {
                    s = mid + 1;
                } else {
                    e = mid - 1;
                }
            }
        } else {
            while (sL2 <= e) {
                int mid = sL2 + (e - sL2) / 2;
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] < target) {
                    sL2 = mid + 1;
                } else {
                    e = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        LC33_SearchInRotatedSortedArray solver = new LC33_SearchInRotatedSortedArray();

        // Test 1: Rotated array, target found in left segment
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        int target1 = 0;
        System.out.println("Test 1 Result: " + solver.search(nums1, target1));
        // Expected: 4

        // Test 2: Rotated array, target not found
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        int target2 = 3;
        System.out.println("Test 2 Result: " + solver.search(nums2, target2));
        // Expected: -1

        // Test 3: Unrotated single-element array
        int[] nums3 = {1};
        int target3 = 0;
        System.out.println("Test 3 Result: " + solver.search(nums3, target3));
        // Expected: -1

        // Test 4: Unrotated sorted array
        int[] nums4 = {1, 3, 5};
        int target4 = 3;
        System.out.println("Test 4 Result: " + solver.search(nums4, target4));
        // Expected: 1
    }
}

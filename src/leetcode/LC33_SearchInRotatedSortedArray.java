package leetcode;

/**
 * LeetCode #33: Search in Rotated Sorted Array
 * Difficulty: Medium (Top Interview 150 / Blind 75)
 * Link: https://leetcode.com/problems/search-in-rotated-sorted-array/
 * 
 * Approach: Modular 2-Step Binary Search (Helper Functions)
 * 1. `pivotIndex()`: Finds the maximum element index in the rotated sorted array.
 * 2. `binarySearch()`: Reusable binary search helper for any specified subsegment [start, end].
 * 3. `search()`: Routes target to segment [0, pivot] or [pivot + 1, end] and invokes binarySearch.
 * 
 * Time Complexity: O(log N) - 0ms Beats 100%
 * Space Complexity: O(1) - Auxiliary Space
 */
public class LC33_SearchInRotatedSortedArray {

    static int binarySearch(int[] arr, int start, int end, int target) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    static int pivotIndex(int[] arr, int start, int end) {
        int pivot = end;
        int size = arr.length - 1;
        if (arr[0] < arr[end]) {
            pivot = end;
            return pivot;
        }
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] <= arr[size]) {
                end = mid - 1;
            } else {
                pivot = mid;
                start = mid + 1;
            }
        }
        return pivot;
    }

    public int search(int[] nums, int target) {
        int pivot = pivotIndex(nums, 0, nums.length - 1);
        int end = nums.length - 1;
        if (nums[0] <= target && nums[pivot] >= target) {
            end = pivot;
            return binarySearch(nums, 0, end, target);
        } else {
            return binarySearch(nums, pivot + 1, end, target);
        }
    }

    public static void main(String[] args) {
        LC33_SearchInRotatedSortedArray solver = new LC33_SearchInRotatedSortedArray();

        // Test 1: Target in left segment
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        int target1 = 0;
        System.out.println("Test 1 Result: " + solver.search(nums1, target1));
        // Expected: 4

        // Test 2: Target not in array
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        int target2 = 3;
        System.out.println("Test 2 Result: " + solver.search(nums2, target2));
        // Expected: -1

        // Test 3: Single element
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

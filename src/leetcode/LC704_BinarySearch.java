package leetcode;

/**
 * LeetCode #704: Binary Search
 * Difficulty: Easy (Top Interview 150 / Blind 75)
 * Link: https://leetcode.com/problems/binary-search/
 * 
 * Approach 1: Iterative Binary Search with Overflow-Safe Midpoint
 * - Maintain two pointers: start and end defining active search space.
 * - mid = start + (end - start) / 2 to avoid integer overflow.
 * - Time: O(log N), Space: O(1)
 * 
 * Approach 2: Recursive Binary Search (Lec 58 Java DSA)
 * - Base condition: if low > high, target not present in array -> return -1.
 * - Compute mid, if arr[mid] == target -> return mid.
 * - If arr[mid] < target, recurse on right subarray: BS(arr, mid + 1, high, target).
 * - Else, recurse on left subarray: BS(arr, low, mid - 1, target).
 * - Time: O(log N), Space: O(log N) auxiliary stack frames.
 */
public class LC704_BinarySearch {

    // Approach 2: Recursive Binary Search
    static int BS(int[] arr, int low, int high, int target) {
        if (low > high) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if (arr[mid] == target) {
            return mid;
        }
        if (arr[mid] < target) {
            return BS(arr, mid + 1, high, target);
        } else {
            return BS(arr, low, mid - 1, target);
        }
    }

    public int search(int[] nums, int target) {
        return BS(nums, 0, nums.length - 1, target);
    }

    // Approach 1: Iterative Binary Search
    public int searchIterative(int[] nums, int target) {
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
        System.out.println("Test 1 Result (Recursive): " + solver.search(nums1, target1));
        // Expected: 4

        // Test 2: Target does not exist in array
        int[] nums2 = {-1, 0, 3, 5, 9, 12};
        int target2 = 2;
        System.out.println("Test 2 Result (Recursive): " + solver.search(nums2, target2));
        // Expected: -1
    }
}

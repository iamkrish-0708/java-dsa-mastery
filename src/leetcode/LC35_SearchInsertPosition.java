package leetcode;

/**
 * LeetCode #35: Search Insert Position
 * Difficulty: Easy (Top Interview 150 / Blind 75)
 * Link: https://leetcode.com/problems/search-insert-position/
 * 
 * Approach: Binary Search (Insertion Index via Ceiling Pointer)
 * - Standard Binary Search with start, end, and overflow-safe midpoint calculation.
 * - If target is found (nums[mid] == target), return mid immediately.
 * - If element is not found, when while loop terminates (start > end), the start pointer
 *   naturally lands on the ceiling index (the exact position where target must be inserted
 *   to preserve sorted order).
 * 
 * Time Complexity: O(log N) - 0ms Beats 100%
 * Space Complexity: O(1) - Auxiliary Space
 */
public class LC35_SearchInsertPosition {

    public int searchInsert(int[] nums, int target) {
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
        return start;
    }

    public static void main(String[] args) {
        LC35_SearchInsertPosition solver = new LC35_SearchInsertPosition();

        // Test 1: Target exists in array -> return existing index
        int[] nums1 = {1, 3, 5, 6};
        int target1 = 5;
        System.out.println("Test 1 Result: " + solver.searchInsert(nums1, target1));
        // Expected: 2

        // Test 2: Target does not exist, fits between elements -> return insertion index
        int[] nums2 = {1, 3, 5, 6};
        int target2 = 2;
        System.out.println("Test 2 Result: " + solver.searchInsert(nums2, target2));
        // Expected: 1

        // Test 3: Target greater than all elements -> return end insertion index
        int[] nums3 = {1, 3, 5, 6};
        int target3 = 7;
        System.out.println("Test 3 Result: " + solver.searchInsert(nums3, target3));
        // Expected: 4

        // Test 4: Target smaller than all elements -> return start insertion index (0)
        int[] nums4 = {1, 3, 5, 6};
        int target4 = 0;
        System.out.println("Test 4 Result: " + solver.searchInsert(nums4, target4));
        // Expected: 0
    }
}

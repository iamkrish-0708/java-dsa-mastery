package leetcode;

/**
 * LeetCode #162: Find Peak Element
 * Difficulty: Medium (Top Interview 150 / Blind 75)
 * Link: https://leetcode.com/problems/find-peak-element/
 * 
 * Approach: Binary Search on Slope / Local Invariant
 * 1. Observation:
 *    - An element is a peak if it is strictly greater than its neighbors.
 *    - Imaginary boundaries: nums[-1] = nums[n] = -infinity.
 * 2. Slope Logic:
 *    - If nums[mid] > nums[mid + 1]: We are on a downward slope (decreasing).
 *      A peak MUST exist at 'mid' or somewhere to the left -> end = mid.
 *    - Else (nums[mid] < nums[mid + 1]): We are on an upward slope (increasing).
 *      A peak MUST exist strictly to the right -> start = mid + 1.
 * 3. Convergence:
 *    - Loop terminates when start == end, pointing directly to a valid peak element.
 * 
 * Time Complexity: O(log N) - 0ms Beats 100%
 * Space Complexity: O(1) Auxiliary Space
 */
public class LC162_FindPeakElement {

    public int findPeakElement(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] > nums[mid + 1]) {
                end = mid;       // Downward slope -> peak is at mid or to the left
            } else {
                start = mid + 1; // Upward slope -> peak is strictly to the right
            }
        }

        return start; // start == end is guaranteed to be a peak
    }

    public static void main(String[] args) {
        LC162_FindPeakElement solver = new LC162_FindPeakElement();

        // Test 1: [1,2,3,1] -> index 2 (val 3)
        int[] nums1 = {1, 2, 3, 1};
        System.out.println("Test 1 Result: " + solver.findPeakElement(nums1)); // Expected: 2

        // Test 2: [1,2,1,3,5,6,4] -> index 1 or 5
        int[] nums2 = {1, 2, 1, 3, 5, 6, 4};
        System.out.println("Test 2 Result: " + solver.findPeakElement(nums2)); // Expected: 1 or 5

        // Test 3: [1] (Single element) -> index 0
        int[] nums3 = {1};
        System.out.println("Test 3 Result: " + solver.findPeakElement(nums3)); // Expected: 0

        // Test 4: [1,2,3,4] (Strictly increasing) -> index 3
        int[] nums4 = {1, 2, 3, 4};
        System.out.println("Test 4 Result: " + solver.findPeakElement(nums4)); // Expected: 3
    }
}

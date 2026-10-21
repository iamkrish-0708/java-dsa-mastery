package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode #2089: Find Target Indices After Sorting Array
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/find-target-indices-after-sorting-array/
 *
 * Earned LeetCode 50 Days Badge 2026 on this milestone submission.
 *
 * Approach: Dual-Phase (Sorting + Binary Search Lower Bound)
 * - Step 1: Sort array in non-decreasing order using Arrays.sort() (O(N log N)).
 * - Step 2: Binary Search to identify the Lower Bound index (first occurrence where nums[mid] >= target).
 * - Step 3: Validate if target exists at lowBound.
 * - Step 4: Linearly collect all contiguous matching indices.
 *
 * Time Complexity: O(N log N) dominated by sorting
 * Space Complexity: O(1) auxiliary space (excluding result list)
 */
public class LC2089_FindTargetIndicesAfterSortingArray {

    public List<Integer> targetIndices(int[] nums, int target) {
        List<Integer> ans = new ArrayList<>();
        Arrays.sort(nums);
        int low = 0;
        int high = nums.length - 1;
        int lowBound = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] >= target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        lowBound = low;

        if (lowBound == nums.length || nums[lowBound] != target) {
            return ans;
        }

        while (lowBound < nums.length && nums[lowBound] == target) {
            ans.add(lowBound);
            lowBound++;
        }
        return ans;
    }

    public static void main(String[] args) {
        LC2089_FindTargetIndicesAfterSortingArray solver = new LC2089_FindTargetIndicesAfterSortingArray();

        // Test Case 1: Target appears multiple times
        int[] nums1 = {1, 2, 5, 2, 3};
        int target1 = 2;
        System.out.println("Test 1: " + solver.targetIndices(nums1, target1) + " | Expected: [1, 2]");

        // Test Case 2: Target appears once
        int[] nums2 = {1, 2, 5, 2, 3};
        int target2 = 3;
        System.out.println("Test 2: " + solver.targetIndices(nums2, target2) + " | Expected: [3]");

        // Test Case 3: Target appears multiple times at end
        int[] nums3 = {1, 2, 5, 2, 3};
        int target3 = 5;
        System.out.println("Test 3: " + solver.targetIndices(nums3, target3) + " | Expected: [4]");

        // Test Case 4: Target not present
        int[] nums4 = {1, 2, 5, 2, 3};
        int target4 = 4;
        System.out.println("Test 4: " + solver.targetIndices(nums4, target4) + " | Expected: []");
    }
}

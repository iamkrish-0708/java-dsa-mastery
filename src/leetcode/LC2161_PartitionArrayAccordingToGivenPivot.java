package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// LeetCode #2161 - Partition Array According to Given Pivot
// Difficulty: Medium
// Problem: You are given a 0-indexed integer array nums and an integer pivot. Rearrange nums such that:
//          1. Every element less than pivot appears before every element greater than pivot.
//          2. Every element equal to pivot appears in between elements less than and greater than pivot.
//          3. The relative order of the elements less than pivot and the elements greater than pivot is maintained.
//          Return nums after the rearrangement.
//
// =================================================================================================
// 💡 TECHNIQUE: THREE-WAY STABLE PARTITIONING (KRISH'S EVOLUTION: 12 ms ➔ 5 ms ⚡)
// =================================================================================================
// - Approach 2 (Optimal - 5 ms): Primitive Array Three-Pass Scan
//     * Directly populates a single flat int[] ans array in 3 linear passes:
//         Pass 1: Elements < pivot
//         Pass 2: Elements == pivot
//         Pass 3: Elements > pivot
//     * Completely bypasses ArrayList autoboxing, dynamic resizing, and unboxing overhead.
//     * Time Complexity: O(N), Space Complexity: O(N).
//
// - Approach 1 (Initial - 12 ms): Dual List Partitioning with Dynamic Middle Calculation
//     * Separates into tempI (< pivot) and tempE (> pivot).
//     * Uses the clever dynamic remaining check: while (nums.length - ptr != tempE.size()), fill pivot.
//     * Time Complexity: O(N), Space Complexity: O(N).
// =================================================================================================

public class LC2161_PartitionArrayAccordingToGivenPivot {

    // Approach 2: Krish's 3-Pass Primitive Array Solution (5 ms)
    public int[] pivotArray(int[] nums, int pivot) {
        int[] ans = new int[nums.length];
        int ptr = 0;

        // less than pivot
        for (int n : nums) {
            if (n < pivot) {
                ans[ptr] = n;
                ptr++;
            }
        }

        // equal to pivot
        for (int n : nums) {
            if (n == pivot) {
                ans[ptr] = n;
                ptr++;
            }
        }

        // greater than pivot
        for (int n : nums) {
            if (n > pivot) {
                ans[ptr] = n;
                ptr++;
            }
        }

        return ans;
    }

    // Approach 1: Krish's Dual List Solution (12 ms)
    public int[] pivotArrayDualList(int[] nums, int pivot) {
        List<Integer> tempI = new ArrayList<>();
        List<Integer> tempE = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == pivot) {
                continue;
            } else if (nums[i] < pivot) {
                tempI.add(nums[i]);
            } else {
                tempE.add(nums[i]);
            }
        }
        int ptr = 0;
        for (int n : tempI) {
            nums[ptr] = n;
            ptr++;
        }
        while (nums.length - ptr != tempE.size()) {
            nums[ptr] = pivot;
            ptr++;
        }
        for (int n : tempE) {
            nums[ptr] = n;
            ptr++;
        }
        return nums;
    }

    // Local Test Harness
    public static void main(String[] args) {
        LC2161_PartitionArrayAccordingToGivenPivot solver = new LC2161_PartitionArrayAccordingToGivenPivot();

        // Test 1: [9,12,5,10,14,3,10], pivot = 10 -> [9,5,3,10,10,12,14]
        int[] nums1 = {9, 12, 5, 10, 14, 3, 10};
        System.out.println("Test 1 (5 ms): " + Arrays.toString(solver.pivotArray(nums1, 10)));

        // Test 2: [-3,4,3,2], pivot = 2 -> [-3,2,4,3]
        int[] nums2 = {-3, 4, 3, 2};
        System.out.println("Test 2 (5 ms): " + Arrays.toString(solver.pivotArray(nums2, 2)));
    }
}

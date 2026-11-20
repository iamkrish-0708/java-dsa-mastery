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
// 💡 TECHNIQUE: THREE-WAY STABLE PARTITIONING (KRISH'S SOLVE - 12 ms ⚡ / OPTIMAL 2 ms ⚡)
// =================================================================================================
// - Approach 1: List Partitioning with Dynamic Middle Derivation (Krish - 12 ms)
//     * Collect elements < pivot into tempI and elements > pivot into tempE.
//     * Write tempI to nums.
//     * Clever Middle Derivation: while (nums.length - ptr != tempE.size()), fill nums[ptr] = pivot.
//     * Write tempE to nums.
//     * Time Complexity: O(N), Space Complexity: O(N).
//
// - Approach 2: Primitive Array Three-Pass Scan (Optimal - 2 ms Beats 100% ⚡)
//     * Avoids ArrayList autoboxing/unboxing tax.
//     * Pass 1 (< pivot) -> Pass 2 (== pivot) -> Pass 3 (> pivot) directly into new int[nums.length].
//     * Time Complexity: O(N), Space Complexity: O(N).
// =================================================================================================

public class LC2161_PartitionArrayAccordingToGivenPivot {

    // Approach 1: Krish's Solution (12 ms)
    public int[] pivotArray(int[] nums, int pivot) {
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

    // Approach 2: Optimal Primitive Array (2 ms)
    public int[] pivotArrayOptimal(int[] nums, int pivot) {
        int[] ans = new int[nums.length];
        int ptr = 0;

        for (int n : nums) {
            if (n < pivot) ans[ptr++] = n;
        }
        for (int n : nums) {
            if (n == pivot) ans[ptr++] = n;
        }
        for (int n : nums) {
            if (n > pivot) ans[ptr++] = n;
        }

        return ans;
    }

    // Local Test Harness
    public static void main(String[] args) {
        LC2161_PartitionArrayAccordingToGivenPivot solver = new LC2161_PartitionArrayAccordingToGivenPivot();

        // Test 1: [9,12,5,10,14,3,10], pivot = 10 -> [5,3,9,10,10,12,14] (relative order preserved)
        int[] nums1 = {9, 12, 5, 10, 14, 3, 10};
        System.out.println("Test 1: " + Arrays.toString(solver.pivotArray(nums1, 10)));

        // Test 2: [-3,4,3,2], pivot = 2 -> [-3,2,4,3]
        int[] nums2 = {-3, 4, 3, 2};
        System.out.println("Test 2: " + Arrays.toString(solver.pivotArray(nums2, 2)));
    }
}

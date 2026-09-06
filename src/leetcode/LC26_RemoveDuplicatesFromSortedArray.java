package leetcode;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * LeetCode #26: Remove Duplicates from Sorted Array
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 */
public class LC26_RemoveDuplicatesFromSortedArray {

    // Approach 1: Using LinkedHashSet (Maintains Insertion Order + Deduplication)
    // Time Complexity: O(N)
    // Space Complexity: O(N) - Extra memory used by LinkedHashSet
    public int removeDuplicatesLinkedHashSet(int[] nums) {
        Set<Integer> ansArr = new LinkedHashSet<>();
        for (int a : nums) {
            ansArr.add(a);
        }
        int i = 0;
        for (int x : ansArr) {
            nums[i] = x;
            i++;
        }
        return ansArr.size();
    }

    // Approach 2: Optimal Two-Pointers (In-Place Array Mutation)
    // Time Complexity: O(N) - 0ms Beats 100%
    // Space Complexity: O(1) Auxiliary Space - Zero Extra Memory!
    public int removeDuplicatesOptimal(int[] nums) {
        if (nums.length == 0) return 0;

        int writeIndex = 1; // Position where the next unique number should be placed

        for (int readIndex = 1; readIndex < nums.length; readIndex++) {
            // Since array is sorted, a new unique number is found when nums[readIndex] != nums[readIndex - 1]
            if (nums[readIndex] != nums[readIndex - 1]) {
                nums[writeIndex] = nums[readIndex];
                writeIndex++;
            }
        }

        return writeIndex; // Total count of unique elements
    }

    public static void main(String[] args) {
        LC26_RemoveDuplicatesFromSortedArray solver = new LC26_RemoveDuplicatesFromSortedArray();

        int[] nums1 = {1, 1, 2};
        int k1 = solver.removeDuplicatesOptimal(nums1);
        System.out.println("Unique count (Expected: 2) -> " + k1);

        int[] nums2 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int k2 = solver.removeDuplicatesOptimal(nums2);
        System.out.println("Unique count (Expected: 5) -> " + k2);
    }
}

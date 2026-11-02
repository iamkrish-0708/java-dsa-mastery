package leetcode;

// LeetCode #27 - Remove Element
// Difficulty: Easy (Top Interview 150)
// Problem: Given an integer array nums and an integer val, remove all occurrences of val in nums in-place.
//          The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.
//
// Evolution of Solutions:
//   - Approach 1 (Initial Solve - 0ms): Two-pointer convergence from both ends (x from start, y from end)
//     with a 4-branch if-else condition checking all permutations of nums[x] and nums[y].
//   - Approach 2 (Refactored Clean Two-Pointer - 0ms Beats 100% ⚡):
//     Uses single 'k' write pointer and 'r' read pointer moving left to right.
//     If nums[r] != val -> copy to nums[k] and increment k.
//     Reduces conditional logic from 4 branches down to 1 single if statement.
//
// Complexity: O(N) Time (single pass), O(1) Auxiliary Space
// Problem Link: https://leetcode.com/problems/remove-element/

public class LC27_RemoveElement {

    // Approach 2: Clean Read/Write Pointer (Gold Standard)
    public int removeElement(int[] nums, int val) {
        int k = 0;
        for (int r = 0; r < nums.length; r++) {
            if (nums[r] != val) {
                nums[k] = nums[r];
                k++;
            }
        }
        return k;
    }

    // Approach 1: Initial Two-Pointer Swap Convergence
    public int removeElementTwoPointers(int[] nums, int val) {
        int k = 0;
        int x = 0;
        int y = nums.length - 1;
        while (x <= y) {
            if (nums[x] == val && nums[y] != val) {
                nums[x] = nums[y];
                nums[y] = val;
                x++;
                k++;
                y--;
            } else if (nums[x] == val && nums[y] == val) {
                y--;
            } else if (nums[x] != val && nums[y] == val) {
                x++;
                k++;
                y--;
            } else {
                x++;
                k++;
            }
        }
        return k;
    }

    public static void main(String[] args) {
        LC27_RemoveElement solver = new LC27_RemoveElement();
        int[] nums1 = {3, 2, 2, 3};
        int val1 = 3;
        int k1 = solver.removeElement(nums1, val1);
        System.out.println("k = " + k1); // Expected: 2

        int[] nums2 = {0, 1, 2, 2, 3, 0, 4, 2};
        int val2 = 2;
        int k2 = solver.removeElement(nums2, val2);
        System.out.println("k = " + k2); // Expected: 5
    }
}

package leetcode;

// LeetCode #27 - Remove Element
// Difficulty: Easy
// Problem: Given an integer array nums and an integer val, remove all occurrences of val in nums in-place.
//          The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.
// Approach: Two-Pointer Swap with Boundary Convergence (0ms Beats 100% ⚡)
// Key Mechanics:
//   - Pointer x starts at 0 (left), pointer y starts at nums.length - 1 (right).
//   - If nums[x] == val and nums[y] != val: swap/overwrite nums[x] with nums[y], increment k, move x right and y left.
//   - If nums[x] == val and nums[y] == val: shrink right boundary (y--).
//   - If nums[x] != val: valid element already in place, increment k and move x right.
//   - When x > y, exactly k elements not equal to val reside in the prefix nums[0 .. k-1].
// Complexity: O(N) Time (each element checked at most once), O(1) Auxiliary Space
// Problem Link: https://leetcode.com/problems/remove-element/

public class LC27_RemoveElement {

    public int removeElement(int[] nums, int val) {
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

package leetcode;

/**
 * LeetCode #1752: Check if Array Is Sorted and Rotated
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/
 * 
 * Approach: Circular Adjacent Comparison via Modulo Operator
 * - A sorted and rotated array can have AT MOST ONE inversion/drop where nums[i] > nums[i+1].
 * - Using the circular index `(i + 1) % nums.length`, we seamlessly check:
 *   1. All internal adjacent pairs: nums[0] vs nums[1], ..., nums[n-2] vs nums[n-1]
 *   2. The circular boundary condition: nums[n-1] vs nums[0]
 * - If the number of inversions (`count`) exceeds 1 at any point, the array is NOT sorted and rotated.
 * 
 * Time Complexity: O(N) - Single pass, 0ms Beats 100%
 * Space Complexity: O(1) - Constant auxiliary memory
 */
public class LC1752_CheckIfArrayIsSortedAndRotated {

    public boolean check(int[] nums) {
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums[(i + 1) % nums.length]) {
                count++;
            }

            if (count > 1) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        LC1752_CheckIfArrayIsSortedAndRotated solver = new LC1752_CheckIfArrayIsSortedAndRotated();

        int[] test1 = {3, 4, 5, 1, 2}; // Sorted and rotated -> true
        int[] test2 = {2, 1, 3, 4};    // 2 drops (2>1 and 4>2) -> false
        int[] test3 = {1, 2, 3};       // Already sorted (1 drop 3>1) -> true
        int[] test4 = {1, 1, 1};       // All equal (0 drops) -> true

        System.out.println("Test 1: [3, 4, 5, 1, 2] -> " + solver.check(test1) + " (Expected: true)");
        System.out.println("Test 2: [2, 1, 3, 4]    -> " + solver.check(test2) + " (Expected: false)");
        System.out.println("Test 3: [1, 2, 3]       -> " + solver.check(test3) + " (Expected: true)");
        System.out.println("Test 4: [1, 1, 1]       -> " + solver.check(test4) + " (Expected: true)");
    }
}

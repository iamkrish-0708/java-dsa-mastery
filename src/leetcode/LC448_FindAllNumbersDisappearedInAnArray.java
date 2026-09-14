package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode #448: Find All Numbers Disappeared in an Array
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
 * 
 * Approach: In-Place Index Sign Negation (Negative Tagging)
 * - Since array elements are in range [1, n], we can map each value `num` to index `num - 1`.
 * - Pass 1: For each element, find target index `pos = Math.abs(nums[i]) - 1`.
 *          If nums[pos] is positive, negate it `nums[pos] = -nums[pos]`.
 * - Pass 2: Any index `i` that remains positive (> 0) means the value `i + 1` was never present in the array.
 * 
 * Time Complexity: O(N) - Two linear passes (5ms Beats 85%)
 * Space Complexity: O(1) Extra Space (ignoring the returned output list)
 */
public class LC448_FindAllNumbersDisappearedInAnArray {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> missingNumbers = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int num = Math.abs(nums[i]);
            int pos = num - 1;
            if (nums[pos] > 0) {
                nums[pos] = nums[pos] * (-1);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                missingNumbers.add(i + 1);
            }
        }

        return missingNumbers;
    }

    public static void main(String[] args) {
        LC448_FindAllNumbersDisappearedInAnArray solver = new LC448_FindAllNumbersDisappearedInAnArray();

        int[] test1 = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println("Test 1: [4, 3, 2, 7, 8, 2, 3, 1] -> " + solver.findDisappearedNumbers(test1) + " (Expected: [5, 6])");

        int[] test2 = {1, 1};
        System.out.println("Test 2: [1, 1]                   -> " + solver.findDisappearedNumbers(test2) + " (Expected: [2])");
    }
}

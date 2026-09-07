package leetcode;

/**
 * LeetCode #268: Missing Number
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/missing-number/
 * 
 * Approach: Single-Pass Bitwise XOR Cancellation
 * - Since array numbers are in the range [0, n] and array has size n,
 *   one number in [0, n] is missing.
 * - We initialize missingNum = nums.length (n).
 * - XOR each index i (0 to n-1) and array element nums[i].
 * - Because a ^ a = 0 and a ^ 0 = a, all numbers appearing both as an index/range value
 *   and in the array cancel out, leaving ONLY the missing number!
 * 
 * Time Complexity: O(N) - Single loop, 0ms Beats 100%
 * Space Complexity: O(1) - Constant auxiliary space
 */
public class LC268_MissingNumber {
    public int missingNumber(int[] nums) {
        int missingNum = nums.length; // Start with n
        
        for (int i = 0; i < nums.length; i++) {
            missingNum ^= i ^ nums[i]; // XOR index i and array value nums[i]
        }
        
        return missingNum;
    }

    public static void main(String[] args) {
        LC268_MissingNumber solver = new LC268_MissingNumber();

        int[] test1 = {3, 0, 1};
        int[] test2 = {0, 1};
        int[] test3 = {9, 6, 4, 2, 3, 5, 7, 0, 1};

        System.out.println("Test 1: [3, 0, 1] -> Missing: " + solver.missingNumber(test1) + " (Expected: 2)");
        System.out.println("Test 2: [0, 1] -> Missing: " + solver.missingNumber(test2) + " (Expected: 2)");
        System.out.println("Test 3: [9, 6, 4, 2, 3, 5, 7, 0, 1] -> Missing: " + solver.missingNumber(test3) + " (Expected: 8)");
    }
}

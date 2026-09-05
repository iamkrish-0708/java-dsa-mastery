package leetcode;

/**
 * LeetCode #136: Single Number
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/single-number/
 * 
 * Concept: Bitwise XOR (X ^ X = 0, X ^ 0 = X)
 * Time Complexity: O(N) - Beats 99.9%
 * Space Complexity: O(1) Extra Space
 */
public class LC136_SingleNumber {

    public int singleNumber(int[] nums) {
        int unique = 0;
        for (int num : nums) {
            unique ^= num;
        }
        return unique;
    }

    public static void main(String[] args) {
        LC136_SingleNumber solver = new LC136_SingleNumber();
        int[] test1 = {2, 2, 1};
        int[] test2 = {4, 1, 2, 1, 2};
        int[] test3 = {1};

        System.out.println("Test 1 (Expected: 1) -> " + solver.singleNumber(test1));
        System.out.println("Test 2 (Expected: 4) -> " + solver.singleNumber(test2));
        System.out.println("Test 3 (Expected: 1) -> " + solver.singleNumber(test3));
    }
}

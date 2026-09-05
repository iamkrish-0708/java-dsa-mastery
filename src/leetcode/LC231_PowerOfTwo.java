package leetcode;

/**
 * LeetCode #231: Power of Two
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/power-of-two/
 * 
 * Concept: Brian Kernighan's Bitwise Hack (n & (n - 1) == 0)
 * Time Complexity: O(1) - 0ms Beats 100%
 * Space Complexity: O(1) Extra Space
 */
public class LC231_PowerOfTwo {

    public boolean isPowerOfTwo(int n) {
        return (n > 0) && ((n & (n - 1)) == 0);
    }

    public static void main(String[] args) {
        LC231_PowerOfTwo solver = new LC231_PowerOfTwo();

        System.out.println("1 (Expected: true)   -> " + solver.isPowerOfTwo(1));
        System.out.println("16 (Expected: true)  -> " + solver.isPowerOfTwo(16));
        System.out.println("3 (Expected: false)  -> " + solver.isPowerOfTwo(3));
        System.out.println("0 (Expected: false)  -> " + solver.isPowerOfTwo(0));
        System.out.println("-16 (Expected: false)-> " + solver.isPowerOfTwo(-16));
    }
}

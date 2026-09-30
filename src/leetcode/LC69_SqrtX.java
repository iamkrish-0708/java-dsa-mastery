package leetcode;

/**
 * LeetCode #69: Sqrt(x)
 * Difficulty: Easy (Top Interview 150 / Blind 75)
 * Link: https://leetcode.com/problems/sqrtx/
 * 
 * Approach: Binary Search with Division Math Trick (Zero 64-bit Long Allocation)
 * 1. Early guard: If x == 0, return 0 (avoids division by zero).
 * 2. Range: [1, x], floor candidate tracking with `roundOff`.
 * 3. Use `mid == x / mid` instead of `mid * mid == x` to completely avoid 32-bit integer overflow.
 * 4. If mid == x / mid: exact root -> return mid.
 * 5. If mid > x / mid: mid is too large -> search left (e = mid - 1).
 * 6. If mid < x / mid: valid floor candidate -> record roundOff = mid and search right (s = mid + 1).
 * 
 * Time Complexity: O(log x) - 1ms Beats 100%
 * Space Complexity: O(1) - Pure 32-bit integer primitives
 */
public class LC69_SqrtX {

    public int mySqrt(int x) {
        int s = 1;
        int e = x;
        int roundOff = 0;
        if (x == 0) {
            return 0;
        }
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (mid == x / mid) {
                return mid;
            } else if (mid > x / mid) {
                e = mid - 1;
            } else {
                roundOff = mid;
                s = mid + 1;
            }
        }
        return roundOff;
    }

    public static void main(String[] args) {
        LC69_SqrtX solver = new LC69_SqrtX();

        // Test 1: x = 4 -> 2
        System.out.println("Test 1 (x = 4): " + solver.mySqrt(4));
        // Expected: 2

        // Test 2: x = 8 -> 2
        System.out.println("Test 2 (x = 8): " + solver.mySqrt(8));
        // Expected: 2

        // Test 3: x = 0 -> 0
        System.out.println("Test 3 (x = 0): " + solver.mySqrt(0));
        // Expected: 0

        // Test 4: Large value x = 2147395599 -> 46339
        System.out.println("Test 4 (x = 2147395599): " + solver.mySqrt(2147395599));
        // Expected: 46339
    }
}

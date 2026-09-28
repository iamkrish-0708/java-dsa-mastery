package leetcode;

/**
 * LeetCode #69: Sqrt(x)
 * Difficulty: Easy (Top Interview 150 / Blind 75)
 * Link: https://leetcode.com/problems/sqrtx/
 * 
 * Approach: Binary Search on Monotonic Answer Range with Floor Tracking
 * 1. Search space for integer square root is [1, x] (with roundOff initialized to 0 for x = 0).
 * 2. Calculate mid safely: mid = s + (e - s) / 2.
 * 3. Cast to long: long check = (long) mid * mid to avoid 32-bit integer overflow.
 * 4. If check == x: exact square root found -> return mid.
 * 5. If check > x: mid is too large -> search left (e = mid - 1).
 * 6. If check < x: mid is a valid candidate for floor(sqrt(x)) -> record roundOff = mid and search right (s = mid + 1).
 * 7. Return roundOff when search space is exhausted.
 * 
 * Time Complexity: O(log x) - 1ms Beats 100%
 * Space Complexity: O(1) - Auxiliary space
 */
public class LC69_SqrtX {

    public int mySqrt(int x) {
        int s = 1;
        int e = x;
        int roundOff = 0;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            long check = (long) mid * mid;
            if (check == x) {
                return mid;
            } else if (check > x) {
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

        // Test 1: Exact square root (4 -> 2)
        System.out.println("Test 1 (x = 4): " + solver.mySqrt(4));
        // Expected: 2

        // Test 2: Floor square root (8 -> 2 since 2*2=4 <= 8 < 3*3=9)
        System.out.println("Test 2 (x = 8): " + solver.mySqrt(8));
        // Expected: 2

        // Test 3: Edge case x = 0 (0 -> 0)
        System.out.println("Test 3 (x = 0): " + solver.mySqrt(0));
        // Expected: 0

        // Test 4: Large value x = 2147395599 -> 46339
        System.out.println("Test 4 (x = 2147395599): " + solver.mySqrt(2147395599));
        // Expected: 46339
    }
}

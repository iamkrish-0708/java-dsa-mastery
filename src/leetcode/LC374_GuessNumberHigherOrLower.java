package leetcode;

/**
 * Mock class to simulate LeetCode's GuessGame parent class API.
 */
class GuessGame {
    static int pickedNumber = 6;

    /**
     * @param num your guess
     * @return -1 if num is higher than the picked number,
     *          1 if num is lower than the picked number,
     *          0 if num is equal to the picked number
     */
    int guess(int num) {
        if (num > pickedNumber) {
            return -1;
        } else if (num < pickedNumber) {
            return 1;
        } else {
            return 0;
        }
    }
}

/**
 * LeetCode #374: Guess Number Higher or Lower
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/guess-number-higher-or-lower/
 *
 * Approach: Binary Search on Monotonic Bounded Range [1, n]
 * - Direct classic binary search over range 1 to n.
 * - Call the guess(mid) API:
 *     - result == 0 : Target found, return mid.
 *     - result == 1 : My guess was lower than target (target is higher), so s = mid + 1.
 *     - result == -1: My guess was higher than target (target is lower), so e = mid - 1.
 * - Overflow prevention: mid = s + (e - s) / 2 avoids 32-bit integer overflow when n is large.
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */
public class LC374_GuessNumberHigherOrLower extends GuessGame {

    public int guessNumber(int n) {
        int s = 1;
        int e = n;

        while (s <= e) {
            int mid = s + (e - s) / 2;

            int result = guess(mid);

            if (result == 0) {
                return mid;
            } else if (result == 1) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        LC374_GuessNumberHigherOrLower solver = new LC374_GuessNumberHigherOrLower();

        // Test Case 1: n = 10, pick = 6
        GuessGame.pickedNumber = 6;
        int result1 = solver.guessNumber(10);
        System.out.println("Test Case 1 | n = 10, pick = 6 | Result: " + result1 + " | Expected: 6");

        // Test Case 2: n = 1, pick = 1
        GuessGame.pickedNumber = 1;
        int result2 = solver.guessNumber(1);
        System.out.println("Test Case 2 | n = 1, pick = 1 | Result: " + result2 + " | Expected: 1");

        // Test Case 3: n = 2, pick = 1
        GuessGame.pickedNumber = 1;
        int result3 = solver.guessNumber(2);
        System.out.println("Test Case 3 | n = 2, pick = 1 | Result: " + result3 + " | Expected: 1");
    }
}

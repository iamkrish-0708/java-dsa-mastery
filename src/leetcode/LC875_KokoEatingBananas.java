package leetcode;

/**
 * LeetCode #875: Koko Eating Bananas
 * Difficulty: Medium (Top Interview 150 / Blind 75)
 * Link: https://leetcode.com/problems/koko-eating-bananas/
 * 
 * Approach: Binary Search on Answer Space (Independent Rate Accumulator)
 * 1. Search Space: [1, max(piles)]
 *    - Lower bound: 1 banana/hour.
 *    - Upper bound: Eating at max(piles) bananas/hour means each pile takes exactly 1 hour.
 * 2. `isValid()`:
 *    - For each pile, calculate hours required at eating speed `k`: ceil(pile / speed).
 *    - Formula: `(pile + speed - 1) / speed` or `pile / speed + (pile % speed != 0 ? 1 : 0)`.
 *    - Accumulate total hours using `long` to prevent overflow.
 *    - Return `true` if `totalHours <= h`.
 * 3. Binary Search:
 *    - If valid: record candidate `ans = mid`, try for a SLOWER valid eating speed (end = mid - 1).
 *    - Else: eating too slow (exceeds h hours), increase speed (start = mid + 1).
 * 
 * Time Complexity: O(N * log(max(piles))) - 7ms Beats 98%
 * Space Complexity: O(1) Auxiliary Space
 */
public class LC875_KokoEatingBananas {

    static boolean isValid(int[] piles, int speed, int h) {
        long totalHours = 0;
        for (int pile : piles) {
            // Integer ceiling division: ceil(pile / speed)
            totalHours += (pile + speed - 1) / speed;
        }
        return totalHours <= h;
    }

    public int minEatingSpeed(int[] piles, int h) {
        int start = 1;
        int max = 0;
        for (int pile : piles) {
            max = Math.max(max, pile);
        }
        int end = max;
        int ans = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (isValid(piles, mid, h)) {
                ans = mid;        // Feasible speed found! Try a slower speed.
                end = mid - 1;
            } else {
                start = mid + 1;  // Too slow, increase eating speed.
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        LC875_KokoEatingBananas solver = new LC875_KokoEatingBananas();

        // Test 1: piles = [3,6,7,11], h = 8 -> 4
        int[] piles1 = {3, 6, 7, 11};
        int h1 = 8;
        System.out.println("Test 1 Result: " + solver.minEatingSpeed(piles1, h1)); // Expected: 4

        // Test 2: piles = [30,11,23,4,20], h = 5 -> 30
        int[] piles2 = {30, 11, 23, 4, 20};
        int h2 = 5;
        System.out.println("Test 2 Result: " + solver.minEatingSpeed(piles2, h2)); // Expected: 30

        // Test 3: piles = [30,11,23,4,20], h = 6 -> 23
        int[] piles3 = {30, 11, 23, 4, 20};
        int h3 = 6;
        System.out.println("Test 3 Result: " + solver.minEatingSpeed(piles3, h3)); // Expected: 23
    }
}

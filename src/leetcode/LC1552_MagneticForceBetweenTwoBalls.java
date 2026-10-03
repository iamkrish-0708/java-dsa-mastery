package leetcode;

import java.util.Arrays;

/**
 * LeetCode #1552: Magnetic Force Between Two Balls
 * Difficulty: Medium (Top Interview 150 / Blind 75)
 * Link: https://leetcode.com/problems/magnetic-force-between-two-balls/
 * 
 * Approach: Binary Search on Answer Space (Aggressive Cows Dual Pattern - Max of Min)
 * 1. Sorting: Position array must be sorted to enable greedy sequential placement of balls.
 * 2. Search Space: [1, (max(position) - min(position)) / (m - 1)] or [0, max - min].
 * 3. `isValid()`: Greedily place the 1st ball at position[0]. For subsequent baskets, if
 *    position[i] - position[lastPos] >= minDistance, place next ball (ballCount++).
 *    If ballCount == m, return true.
 * 4. Binary Search:
 *    - If valid: record ans = mid, search right for a LARGER minimum force (start = mid + 1).
 *    - Else: force too large, search left (end = mid - 1).
 * 
 * Time Complexity: O(N log N + N * log(Max - Min)) - Optimal O(N log N)
 * Space Complexity: O(1) - Auxiliary Space
 */
public class LC1552_MagneticForceBetweenTwoBalls {

    static boolean isValid(int[] arr, int m, int minDistance) {
        int ballCount = 1;
        int lastPosition = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[lastPosition] >= minDistance) {
                ballCount++;
                lastPosition = i;
                if (ballCount == m) {
                    return true;
                }
            }
        }
        return false;
    }

    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int start = 0;
        int end = position[position.length - 1] - position[0];
        int ans = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (isValid(position, m, mid)) {
                ans = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        LC1552_MagneticForceBetweenTwoBalls solver = new LC1552_MagneticForceBetweenTwoBalls();

        // Test 1: position = [1,2,3,4,7], m = 3 -> 3
        int[] pos1 = {1, 2, 3, 4, 7};
        int m1 = 3;
        System.out.println("Test 1 Result: " + solver.maxDistance(pos1, m1)); // Expected: 3

        // Test 2: position = [5,4,3,2,1,1000000000], m = 2 -> 999999999
        int[] pos2 = {5, 4, 3, 2, 1, 1000000000};
        int m2 = 2;
        System.out.println("Test 2 Result: " + solver.maxDistance(pos2, m2)); // Expected: 999999999
    }
}

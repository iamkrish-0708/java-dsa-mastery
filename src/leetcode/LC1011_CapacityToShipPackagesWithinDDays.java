package leetcode;

/**
 * LeetCode #1011: Capacity To Ship Packages Within D Days
 * Difficulty: Medium (Top Interview 150 / Blind 75)
 * Link: https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
 * 
 * Approach: Binary Search on Answer Space with Lower Bound Optimization
 * 1. Search Space: [max(weights), sum(weights)].
 *    - Setting start = max(weights) guarantees the ship can at least carry the single heaviest package,
 *      eliminating the need for an extra `arr[i] > mid` check inside isValid().
 * 2. `isValid()`: Greedily loads packages onto the ship day by day. If adding current package
 *    exceeds ship capacity `mid`, increments day count (`current++`) and starts fresh day.
 * 3. Binary Search:
 *    - If valid: record ans = mid, shrink capacity to find smaller valid capacity (end = mid - 1).
 *    - Else: capacity too small, expand capacity (start = mid + 1).
 * 
 * Time Complexity: O(N * log(Sum - Max)) - 6ms Beats 100%
 * Space Complexity: O(1) - Auxiliary Space
 */
public class LC1011_CapacityToShipPackagesWithinDDays {

    static boolean isValid(int[] arr, int mid, int size, int days) {
        int current = 1;
        int weightSum = 0;
        for (int i = 0; i < size; i++) {
            /* THIS CAN BE AVOIDED BY USING LOWER BOUND BS ON START INDEX IN MAIN */
            // if (arr[i] > mid) {
            //     return false;
            // }
            if (weightSum + arr[i] <= mid) {
                weightSum += arr[i];
            } else {
                current++;
                if (current > days) {
                    return false;
                }
                weightSum = arr[i];
            }
        }
        return true;
    }

    public int shipWithinDays(int[] weights, int days) {
        int size = weights.length;
        int start = 0; // INSTEAD OF 0 WE WILL START WITH MAX INDIVIDUAL WEIGHT AVAILABLE IN WEIGHTS
        int sum = 0;
        int ans = -1;
        /* BELOW CHECK IS UNNECESSARY AS CONSTRAINTS OF QUESTION TELLS BELOW CASE WILL NEVER HAPPEN */
        // if (days > size) {
        //     return -1;
        // }
        for (int i = 0; i < size; i++) {
            start = Math.max(start, weights[i]);
            sum += weights[i];
        }
        int end = sum;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (isValid(weights, mid, size, days)) {
                ans = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        LC1011_CapacityToShipPackagesWithinDDays solver = new LC1011_CapacityToShipPackagesWithinDDays();

        // Test 1: [1,2,3,4,5,6,7,8,9,10], days = 5 -> 15
        int[] weights1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int days1 = 5;
        System.out.println("Test 1 Result: " + solver.shipWithinDays(weights1, days1));
        // Expected: 15

        // Test 2: [3,2,2,4,1,4], days = 3 -> 6
        int[] weights2 = {3, 2, 2, 4, 1, 4};
        int days2 = 3;
        System.out.println("Test 2 Result: " + solver.shipWithinDays(weights2, days2));
        // Expected: 6

        // Test 3: [1,2,3,1,1], days = 4 -> 3
        int[] weights3 = {1, 2, 3, 1, 1};
        int days3 = 4;
        System.out.println("Test 3 Result: " + solver.shipWithinDays(weights3, days3));
        // Expected: 3
    }
}

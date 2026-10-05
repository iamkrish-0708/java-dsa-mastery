package leetcode;

/**
 * LeetCode #2594: Minimum Time to Repair Cars
 * Difficulty: Medium (Top Interview 150 / Blind 75)
 * Link: https://leetcode.com/problems/minimum-time-to-repair-cars/
 * 
 * Approach: Binary Search on Answer Space (Roti Prata Dual Pattern - Non-linear Production)
 * 1. Relationship: Time = r * n^2 -> n = sqrt(Time / r) cars repaired by one mechanic.
 * 2. Search Space: [1, maxRank * cars^2]
 *    - Lower bound: 1 minute.
 *    - Upper bound: The slowest mechanic repairs all cars alone: maxRank * cars * cars.
 * 3. `isValid()`:
 *    - For each mechanic with rank `r`, calculate cars repaired in `timeLimit`: (long) sqrt(timeLimit / r).
 *    - If accumulated cars >= `cars`, return true.
 * 4. Binary Search:
 *    - If valid: record candidate `ans = mid`, try for a SMALLER time (end = mid - 1).
 *    - Else: cannot repair all cars in time, increase time limit (start = mid + 1).
 * 
 * Time Complexity: O(N * log(WorstTime)) - 23ms Beats 92%
 * Space Complexity: O(1) Auxiliary Space
 */
public class LC2594_MinimumTimeToRepairCars {

    static boolean isValid(int[] ranks, int cars, long timeLimit) {
        long carCount = 0;
        for (int r : ranks) {
            carCount += (long) Math.sqrt((double) timeLimit / r);
            if (carCount >= cars) {
                return true;
            }
        }
        return false;
    }

    public long repairCars(int[] ranks, int cars) {
        long start = 1;
        long maxRank = ranks[0];
        long ans = -1;

        for (int i = 1; i < ranks.length; i++) {
            maxRank = Math.max(maxRank, ranks[i]);
        }

        long end = maxRank * (long) cars * cars;

        while (start <= end) {
            long mid = start + (end - start) / 2;
            if (isValid(ranks, cars, mid)) {
                ans = mid;        // Feasible time found! Try smaller time.
                end = mid - 1;
            } else {
                start = mid + 1;  // Not enough cars repaired, need more time.
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        LC2594_MinimumTimeToRepairCars solver = new LC2594_MinimumTimeToRepairCars();

        // Test 1: ranks = [4,2,3,1], cars = 10 -> 16
        int[] ranks1 = {4, 2, 3, 1};
        int cars1 = 10;
        System.out.println("Test 1 Result: " + solver.repairCars(ranks1, cars1)); // Expected: 16

        // Test 2: ranks = [5,1,8], cars = 6 -> 16
        int[] ranks2 = {5, 1, 8};
        int cars2 = 6;
        System.out.println("Test 2 Result: " + solver.repairCars(ranks2, cars2)); // Expected: 16
    }
}

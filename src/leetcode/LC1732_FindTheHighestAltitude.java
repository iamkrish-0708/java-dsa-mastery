package leetcode;

// LeetCode #1732 - Find the Highest Altitude
// Difficulty: Easy
// Problem: There is a biker going on a road trip. The road trip consists of n + 1 points at different altitudes.
//          The biker starts at point 0 with altitude equal 0.
//          You are given an integer array gain of length n where gain[i] is the net gain in altitude between points
//          i and i + 1 for all (0 <= i < n). Return the highest altitude of a point.
//
// =================================================================================================
// 💡 TECHNIQUE: PREFIX RUNNING SUM / SIMULATION (KRISH'S SOLVE - 0 ms ⚡ BEATS 100%)
// =================================================================================================
// - Mechanics:
//     1. Initialize current altitude = 0 and peak altitude max = 0.
//     2. In a single pass, add gain[i] to altitude and update max = Math.max(altitude, max).
//     3. Return max.
//
// Complexity:
//   - Time Complexity: O(N) — Single linear scan over gain of size N.
//   - Space Complexity: O(1) Auxiliary Space — Constant extra space.
// =================================================================================================

public class LC1732_FindTheHighestAltitude {

    public int largestAltitude(int[] gain) {
        int altitude = 0;
        int max = 0;
        for (int i = 0; i < gain.length; i++) {
            altitude = altitude + gain[i];
            max = Math.max(altitude, max);
        }
        return max;
    }

    // Local Test Harness
    public static void main(String[] args) {
        LC1732_FindTheHighestAltitude solver = new LC1732_FindTheHighestAltitude();

        // Test 1: [-5, 1, 5, 0, -7] -> Altitudes: [0, -5, -4, 1, 1, -6] -> Max: 1
        int[] gain1 = {-5, 1, 5, 0, -7};
        System.out.println("Test 1: " + solver.largestAltitude(gain1) + " (Expected: 1)");

        // Test 2: [-4, -3, -2, -1, 4, 3, 2] -> Max: 0
        int[] gain2 = {-4, -3, -2, -1, 4, 3, 2};
        System.out.println("Test 2: " + solver.largestAltitude(gain2) + " (Expected: 0)");
    }
}

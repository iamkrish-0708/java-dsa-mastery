package leetcode;

/**
 * Mock class to simulate LeetCode's VersionControl parent class API.
 */
class VersionControl {
    static int badVersionTarget = 4;

    boolean isBadVersion(int version) {
        return version >= badVersionTarget;
    }
}

/**
 * LeetCode #278: First Bad Version
 * Difficulty: Easy (Top Interview 150 / Blind 75)
 * Link: https://leetcode.com/problems/first-bad-version/
 * 
 * Approach: Binary Search (Lower Bound on Boolean Monotonic Space)
 * - The version space is monotonic: [false, false, ..., true, true].
 * - Finding the first bad version is finding the lower bound of 'true'.
 * - Use safe midpoint formula `mid = s + (e - s) / 2` to avoid 32-bit integer overflow when n is up to 2^31 - 1.
 * - When isBadVersion(mid) is true, record `lb = mid` and search left `e = mid - 1`.
 * - Otherwise, search right `s = mid + 1`.
 * 
 * Time Complexity: O(log N) - Minimizes API calls
 * Space Complexity: O(1) - Auxiliary Space
 */
public class LC278_FirstBadVersion extends VersionControl {

    public int firstBadVersion(int n) {
        int s = 1;
        int e = n;
        int lb = n;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (isBadVersion(mid)) {
                lb = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return lb;
    }

    public static void main(String[] args) {
        LC278_FirstBadVersion solver = new LC278_FirstBadVersion();

        // Test 1: n = 5, first bad version = 4
        VersionControl.badVersionTarget = 4;
        System.out.println("Test 1 Result (n = 5, bad = 4): " + solver.firstBadVersion(5));
        // Expected: 4

        // Test 2: n = 1, first bad version = 1
        VersionControl.badVersionTarget = 1;
        System.out.println("Test 2 Result (n = 1, bad = 1): " + solver.firstBadVersion(1));
        // Expected: 1
    }
}

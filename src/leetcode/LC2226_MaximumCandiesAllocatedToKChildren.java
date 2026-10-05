package leetcode;

/**
 * LeetCode #2226: Maximum Candies Allocated to K Children
 * Difficulty: Medium (Top Interview 150 / Blind 75)
 * Link: https://leetcode.com/problems/maximum-candies-allocated-to-k-children/
 * 
 * Approach: Binary Search on Answer Space (EKO Accumulator / Max-of-Min Pattern)
 * 1. Search Space: [1, max(candies)]
 *    - Lower bound: 1 candy per child.
 *    - Upper bound: A child can never receive more candies than the single largest pile
 *      (since piles cannot be merged).
 * 2. `isValid()`:
 *    - For each pile, calculate how many children it can feed: `pile / mid`.
 *    - Sum across all piles using `long` to prevent 32-bit overflow.
 *    - If `totalChildren >= k`, return true.
 * 3. Binary Search:
 *    - If valid: record candidate `ans = mid`, try for a LARGER portion (start = mid + 1).
 *    - Else: not enough candies to feed k children, shrink portion (end = mid - 1).
 * 4. Default: If impossible to give even 1 candy to each child, returns 0.
 * 
 * Time Complexity: O(N * log(max(candies))) - Optimal O(N log(Max))
 * Space Complexity: O(1) Auxiliary Space
 */
public class LC2226_MaximumCandiesAllocatedToKChildren {

    static boolean isValid(int[] arr, long childTotalCount, int candyCount) {
        long currChildCount = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= candyCount) {
                // Each pile can feed (arr[i] / candyCount) children
                currChildCount += (long) arr[i] / candyCount;
            }
        }
        return currChildCount >= childTotalCount;
    }

    public int maximumCandies(int[] candies, long k) {
        // Step 1: Find the maximum candy pile for the upper bound of binary search
        int max = 0;
        for (int i = 0; i < candies.length; i++) {
            if (max < candies[i]) {
                max = candies[i];
            }
        }

        int start = 1;
        int end = max;
        int ans = 0; // Default 0 if no child can get even 1 candy

        // Step 2: Binary Search on the maximum candies per child
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (isValid(candies, k, mid)) {
                ans = mid;        // Candidate found!
                start = mid + 1;  // Try to give MORE candies to each child
            } else {
                end = mid - 1;    // Cannot feed k children, reduce candies per child
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        LC2226_MaximumCandiesAllocatedToKChildren solver = new LC2226_MaximumCandiesAllocatedToKChildren();

        // Test 1: candies = [5,8,6], k = 3 -> 5
        int[] candies1 = {5, 8, 6};
        long k1 = 3;
        System.out.println("Test 1 Result: " + solver.maximumCandies(candies1, k1)); // Expected: 5

        // Test 2: candies = [2,5], k = 11 -> 0
        int[] candies2 = {2, 5};
        long k2 = 11;
        System.out.println("Test 2 Result: " + solver.maximumCandies(candies2, k2)); // Expected: 0

        // Test 3: candies = [4,7,5], k = 4 -> 3
        int[] candies3 = {4, 7, 5};
        long k3 = 4;
        System.out.println("Test 3 Result: " + solver.maximumCandies(candies3, k3)); // Expected: 3
    }
}

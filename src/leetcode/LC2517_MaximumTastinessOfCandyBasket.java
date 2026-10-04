package leetcode;

import java.util.Arrays;

/**
 * LeetCode #2517: Maximum Tastiness of Candy Basket
 * Difficulty: Medium (Top Interview 150 / Blind 75)
 * Link: https://leetcode.com/problems/maximum-tastiness-of-candy-basket/
 * 
 * Approach: Binary Search on Answer Space (Aggressive Cows / LC 1552 Dual Pattern - Max of Min)
 * 1. Sorting: Sort the candy prices to greedily pick candies in increasing order of price.
 * 2. Search Space: [0, max(price) - min(price)]
 * 3. `isValid()`: Greedily pick the first candy at price[0]. For each next candy, if
 *    price[i] - price[lastPos] >= minTastiness, pick it (candyCount++).
 *    If candyCount == k, return true.
 * 4. Binary Search:
 *    - If valid: record ans = mid, try for larger tastiness (start = mid + 1).
 *    - Else: tastiness too large, try smaller tastiness (end = mid - 1).
 * 
 * Time Complexity: O(N log N + N * log(Max - Min)) - Optimal O(N log N) (37ms Beats 95%)
 * Space Complexity: O(1) - Auxiliary Space
 */
public class LC2517_MaximumTastinessOfCandyBasket {

    static boolean isValid(int[] arr, int k, int minTastiness) {
        int candyCount = 1;
        int lastPos = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[lastPos] >= minTastiness) {
                candyCount++;
                lastPos = i;
                if (candyCount == k) {
                    return true;
                }
            }
        }
        return false;
    }

    public int maximumTastiness(int[] price, int k) {
        Arrays.sort(price);
        int start = 0;
        int end = price[price.length - 1] - price[0];
        int ans = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (isValid(price, k, mid)) {
                ans = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        LC2517_MaximumTastinessOfCandyBasket solver = new LC2517_MaximumTastinessOfCandyBasket();

        // Test 1: price = [13,5,1,8,21,2], k = 3 -> 8 (Candies chosen: [1, 13, 21] or [1, 8, 21])
        int[] price1 = {13, 5, 1, 8, 21, 2};
        int k1 = 3;
        System.out.println("Test 1 Result: " + solver.maximumTastiness(price1, k1)); // Expected: 8

        // Test 2: price = [1,3,1], k = 2 -> 2
        int[] price2 = {1, 3, 1};
        int k2 = 2;
        System.out.println("Test 2 Result: " + solver.maximumTastiness(price2, k2)); // Expected: 2

        // Test 3: price = [7,7,7,7], k = 2 -> 0
        int[] price3 = {7, 7, 7, 7};
        int k3 = 2;
        System.out.println("Test 3 Result: " + solver.maximumTastiness(price3, k3)); // Expected: 0
    }
}

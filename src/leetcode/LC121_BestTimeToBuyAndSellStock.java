package leetcode;

/**
 * LeetCode #121: Best Time to Buy and Sell Stock
 * Difficulty: Easy (Top 100 Liked / Blind 75 / Top Interview 150)
 * Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 *
 * NOTE: [Flagged for Re-solve]: Krish understood the logic and committed to re-writing
 * this from a blank slate without references on a multi-problem practice day.
 *
 * Approach: Single-Pass Running Minimum & Max Profit Tracking (Kadane's Variant)
 * - Track the lowest buying price seen so far (`minPrice`).
 * - For each day, calculate profit if sold today (`prices[i] - minPrice`).
 * - Maintain the maximum profit seen across all days.
 * - Solves the "two-peak" and "min at the end" trap in O(N) time with O(1) space.
 *
 * Time Complexity: O(N) - Single linear pass
 * Space Complexity: O(1) - Constant auxiliary space
 */
public class LC121_BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        LC121_BestTimeToBuyAndSellStock solver = new LC121_BestTimeToBuyAndSellStock();

        // Test Case 1: Standard peak profit
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        System.out.println("Test 1: " + solver.maxProfit(prices1) + " | Expected: 5");

        // Test Case 2: Constantly decreasing
        int[] prices2 = {7, 6, 4, 3, 1};
        System.out.println("Test 2: " + solver.maxProfit(prices2) + " | Expected: 0");

        // Test Case 3: The Multi-Peak Trap (Min occurs after a peak profit)
        int[] prices3 = {3, 2, 6, 5, 0, 3};
        System.out.println("Test 3: " + solver.maxProfit(prices3) + " | Expected: 4");

        // Test Case 4: Min at the end
        int[] prices4 = {2, 4, 1};
        System.out.println("Test 4: " + solver.maxProfit(prices4) + " | Expected: 2");
    }
}

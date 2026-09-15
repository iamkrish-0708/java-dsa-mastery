package leetcode;

/**
 * LeetCode #1672: Richest Customer Wealth
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/richest-customer-wealth/
 * 
 * Approach: Row-Wise Sum Matrix Accumulation
 * - Each row represents a customer's bank accounts across multiple banks.
 * - Calculate the sum of each row (wealth) and track the maximum wealth.
 * 
 * Time Complexity: O(R * C) - Single traversal (0ms Beats 100%)
 * Space Complexity: O(1) - Constant auxiliary space
 */
public class LC1672_RichestCustomerWealth {

    public int maximumWealth(int[][] accounts) {
        int wealth = 0;
        int maxWealth = -1;
        int r = accounts.length;
        int c = accounts[0].length;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                wealth = wealth + accounts[i][j];
            }
            if (wealth > maxWealth) {
                maxWealth = wealth;
            }
            wealth = 0;
        }

        return maxWealth;
    }

    public static void main(String[] args) {
        LC1672_RichestCustomerWealth solver = new LC1672_RichestCustomerWealth();

        int[][] acc1 = {{1, 2, 3}, {3, 2, 1}};
        System.out.println("Test 1: " + solver.maximumWealth(acc1) + " (Expected: 6)");

        int[][] acc2 = {{1, 5}, {7, 3}, {3, 5}};
        System.out.println("Test 2: " + solver.maximumWealth(acc2) + " (Expected: 10)");

        int[][] acc3 = {{2, 8, 7}, {7, 1, 3}, {1, 9, 5}};
        System.out.println("Test 3: " + solver.maximumWealth(acc3) + " (Expected: 17)");
    }
}

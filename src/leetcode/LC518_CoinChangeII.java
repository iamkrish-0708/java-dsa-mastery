package leetcode;

// LeetCode #518 - Coin Change II
// Difficulty: Medium
// Problem: You are given an integer array coins representing coins of different denominations
//          and an integer amount representing a total amount of money. Return the number of
//          combinations that make up that amount. You may use an infinite number of each coin.
//
// Approach: Include-Exclude Recursion (Unbounded Knapsack Pattern)
// Key Mechanics:
//   - At each index, two choices:
//     1. INCLUDE coins[index]: Subtract coins[index] from amount, stay at the same index
//        (index does NOT increment because coins can be reused infinitely).
//     2. EXCLUDE coins[index]: Keep amount intact, move to next coin (index + 1).
//   - Base Cases:
//     - if amt == 0 -> return 1 (Found 1 valid combination)
//     - if amt < 0  -> return 0 (Over-shot the target amount)
//     - if index >= coins.length -> return 0 (Out of coins to pick)
//   - Total Combinations: return inc + esc
//
// Status: Hits TLE on large inputs (Expected on pure recursion).
//         Optimized via 2D Dynamic Programming (Memoization / Tabulation) to O(N * Amount) in upcoming lectures.
// Complexity (Pure Recursion):
//   - Time: O(2^(Amount / min_coin)) exponential
//   - Space: O(Amount / min_coin) auxiliary call stack depth
// Problem Link: https://leetcode.com/problems/coin-change-ii/

public class LC518_CoinChangeII {

    static int solve(int amt, int[] coins, int index) {
        if (amt == 0) {
            return 1;
        }
        if (amt < 0) {
            return 0;
        }
        if (index >= coins.length) {
            return 0;
        }

        // Include: stay at same index (unlimited reuse)
        int inc = solve(amt - coins[index], coins, index);

        // Exclude: move to next coin
        int esc = solve(amt, coins, index + 1);

        return (inc + esc);
    }

    public int change(int amount, int[] coins) {
        int index = 0;
        return solve(amount, coins, index);
    }

    public static void main(String[] args) {
        LC518_CoinChangeII solver = new LC518_CoinChangeII();
        int amount = 5;
        int[] coins = {1, 2, 5};
        System.out.println("Combinations to make 5: " + solver.change(amount, coins));
        // Expected: 4 -> [5], [2,2,1], [2,1,1,1], [1,1,1,1,1]
    }
}

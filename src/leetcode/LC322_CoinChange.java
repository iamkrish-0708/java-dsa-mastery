package leetcode;

// LeetCode #322 - Coin Change
// Difficulty: Medium
// Problem: You are given an integer array coins representing coins of different denominations
//          and an integer amount representing a total amount of money. Return the fewest number
//          of coins that you need to make up that amount. If that amount cannot be made up by
//          any combination of the coins, return -1. You may use an infinite number of each coin.
//
// Approach 1 (Krish's Pre-Lecture Solve): Include-Exclude with Guard against Integer Overflow
//   - At each index i:
//       * INCLUDE: Take coin coins[i], stay at index i (infinite supply).
//         CRUCIAL: Only compute (1 + incCall) if incCall != Integer.MAX_VALUE to avoid
//         32-bit integer overflow (Integer.MAX_VALUE + 1 wraps to negative number!).
//       * EXCLUDE: Skip coin coins[i], move to index i + 1.
//       * Return Math.min(inc, esc).
//   - Base Cases:
//       * amt == 0 -> return 0 (0 coins needed to make amount 0)
//       * amt < 0 || i >= coins.length -> return Integer.MAX_VALUE (impossible path)
//   - If final answer == Integer.MAX_VALUE -> return -1.
//
// Status: Pure recursion hits expected TLE on LeetCode due to overlapping subproblems.
//         Optimized via 2D/1D Dynamic Programming (Memoization / Tabulation) to O(N * Amount) in DP.
//
// Approach 2 (Preview - For-Loop over all coins):
//   - solve(amt) = 1 + min(solve(amt - coin) for each coin in coins)
//   - Discussed in Lecture 62.
//
// Complexity (Pure Recursion):
//   - Time: O(2^(Amount / min_coin)) exponential
//   - Space: O(Amount / min_coin) recursion call stack depth
// Problem Link: https://leetcode.com/problems/coin-change/

public class LC322_CoinChange {

    // Approach 1: Include-Exclude Recursion (Formulated by Krish before Lecture)
    static int solve(int[] coins, int amt, int i) {
        // Base cases
        if (amt == 0) {
            return 0;
        }
        if (amt < 0 || i >= coins.length) {
            return Integer.MAX_VALUE;
        }

        // Include choice: stay at index i (unlimited supply)
        int incCall = solve(coins, amt - coins[i], i);
        int inc = Integer.MAX_VALUE;
        if (incCall != Integer.MAX_VALUE) {
            inc = 1 + incCall; // Safe from 32-bit integer overflow
        }

        // Exclude choice: move to index i + 1
        int esc = solve(coins, amt, i + 1);

        // Best choice between including or excluding
        return Math.min(inc, esc);
    }

    public int coinChange(int[] coins, int amount) {
        int ans = solve(coins, amount, 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
        LC322_CoinChange solver = new LC322_CoinChange();
        int[] coins1 = {1, 2, 5};
        int amount1 = 11;
        System.out.println("Fewest coins for 11: " + solver.coinChange(coins1, amount1));
        // Expected: 3 (5 + 5 + 1)

        int[] coins2 = {2};
        int amount2 = 3;
        System.out.println("Fewest coins for 3: " + solver.coinChange(coins2, amount2));
        // Expected: -1 (impossible)

        int[] coins3 = {1};
        int amount3 = 0;
        System.out.println("Fewest coins for 0: " + solver.coinChange(coins3, amount3));
        // Expected: 0
    }
}

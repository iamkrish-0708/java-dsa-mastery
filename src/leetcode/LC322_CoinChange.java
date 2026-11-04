package leetcode;

// LeetCode #322 - Coin Change
// Difficulty: Medium (Blind 75 / Top Interview 150)
// Problem: Given an integer array coins and an integer amount, return the fewest number of coins
//          needed to make up that amount. If impossible, return -1.
//
// =================================================================================================
// 🔍 ARCHITECTURAL COMPARISON: APPROACH 1 vs APPROACH 2
// =================================================================================================
//
// Approach 1 (Include-Exclude Decision Tree):
//   - State: (amt, index)
//   - At each step: Branch 2 ways -> Include coins[i] (stay at i) OR Exclude coins[i] (advance to i+1).
//   - Key Benefit: Order is enforced! It explores combinations (never permutes coin order).
//
// Approach 2 (For-Loop / Multi-Way Branching — As taught in Lecture 62):
//   - State: (amt)
//   - At each step: Runs a loop over ALL coins, branching len(coins) ways at every single node!
//   - Critical Insight: This explores PERMUTATIONS of coin choices (e.g. 1 then 2 vs 2 then 1).
//     In pure recursion, it spawns vastly more redundant recursive calls than Include-Exclude!
//   - Why teachers show Approach 2: Because in Dynamic Programming (Tabulation), Approach 2 maps
//     directly into a 1D DP array dp[amount + 1], whereas Approach 1 maps into a 2D DP table!
//
// Complexity (Pure Recursion):
//   - Approach 1 Time: O(2^(Amount / min_coin))
//   - Approach 2 Time: O((coins.length)^(Amount / min_coin)) — Much wider branching factor!
//   - Both hit TLE without memoization.
// =================================================================================================

public class LC322_CoinChange {

    // Approach 2: For-Loop / Multi-Choice Recursion (Lecture 62)
    static int solveLoop(int[] coins, int amt) {
        if (amt == 0) {
            return 0;
        }
        if (amt < 0) {
            return Integer.MAX_VALUE;
        }

        int min = Integer.MAX_VALUE;

        for (int coin : coins) {
            int recursionAns = solveLoop(coins, amt - coin);
            if (recursionAns != Integer.MAX_VALUE) {
                int totalAns = 1 + recursionAns; // +1 to account for the coin chosen in this step
                min = Math.min(totalAns, min);
            }
        }
        return min;
    }

    // Approach 1: Include-Exclude Recursion (Formulated by Krish)
    static int solveIncludeExclude(int[] coins, int amt, int i) {
        if (amt == 0) {
            return 0;
        }
        if (amt < 0 || i >= coins.length) {
            return Integer.MAX_VALUE;
        }

        // Include: stay at index i (unlimited supply)
        int incCall = solveIncludeExclude(coins, amt - coins[i], i);
        int inc = Integer.MAX_VALUE;
        if (incCall != Integer.MAX_VALUE) {
            inc = 1 + incCall;
        }

        // Exclude: move to index i + 1
        int esc = solveIncludeExclude(coins, amt, i + 1);

        return Math.min(inc, esc);
    }

    public int coinChange(int[] coins, int amount) {
        int ans = solveLoop(coins, amount);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
        LC322_CoinChange solver = new LC322_CoinChange();
        int[] coins = {1, 2, 5};
        System.out.println("Fewest coins for 11 (Loop): " + solver.coinChange(coins, 11)); // Expected: 3
    }
}

package leetcode;

// LeetCode #1981 - Minimize the Difference Between Target and Chosen Elements
// Difficulty: Medium
// Problem: You are given an m x n integer matrix mat and an integer target. Choose one integer
//          from each row in the matrix such that the absolute difference between the sum of
//          these elements and target is minimized. Return the minimum absolute difference.
//
// Approach: Pure Recursion (Row-by-Row Choice via For-Loop)
// Key Mechanics:
//   - At each row, iterate over all elements in mat[row] and recursively explore picking each one.
//   - Transition: solve(mat, target, sum + n, row + 1)
//   - Base Case: When row >= mat.length, exactly one element has been chosen from every row.
//     Return Math.abs(sum - target).
//   - Take Math.min() across all possible choices for the current row.
//
// Complexity Analysis (Pure Recursion):
//   - At each row, there are 'cols' choices.
//   - Total rows = 'rows' (height of the tree).
//   - Total Leaves / Combinations = (cols)^(rows).
//   - Time Complexity: O(cols^rows) — Exponential! Hits expected TLE on LeetCode.
//   - Space Complexity: O(rows) — Recursion stack depth.
//
// Optimization Note (Dynamic Programming):
//   - State is defined by (row, sum).
//   - In DP, memoizing with Integer[][] dp = new Integer[rows][max_possible_sum] reduces
//     time to O(rows * cols * max_sum). Sorting rows and pruning when sum > target further optimizes it.
//
// Problem Link: https://leetcode.com/problems/minimize-the-difference-between-target-and-chosen-elements/

public class LC1981_MinimizeTheDifferenceBetweenTargetAndChosenElements {

    static int solve(int[][] mat, int target, int sum, int row) {
        // Base case: made one choice from every row
        if (row >= mat.length) {
            return Math.abs(sum - target);
        }

        int min = Integer.MAX_VALUE;

        // Pick each number in current row and explore
        for (int n : mat[row]) {
            int ans = solve(mat, target, sum + n, row + 1);
            min = Math.min(ans, min);
        }

        return min;
    }

    public int minimizeTheDifference(int[][] mat, int target) {
        int sum = 0;
        int row = 0;
        return solve(mat, target, sum, row);
    }

    public static void main(String[] args) {
        LC1981_MinimizeTheDifferenceBetweenTargetAndChosenElements solver =
                new LC1981_MinimizeTheDifferenceBetweenTargetAndChosenElements();

        int[][] mat1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int target1 = 13;
        System.out.println("Output 1: " + solver.minimizeTheDifference(mat1, target1));
        // Expected: 0 (e.g. 1 + 5 + 7 = 13)

        int[][] mat2 = {{1}, {2}, {3}};
        int target2 = 100;
        System.out.println("Output 2: " + solver.minimizeTheDifference(mat2, target2));
        // Expected: 94 (|6 - 100| = 94)
    }
}

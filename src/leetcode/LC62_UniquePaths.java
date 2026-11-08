package leetcode;

// LeetCode #62 - Unique Paths
// Difficulty: Medium (Blind 75 / Top Interview 150)
// Problem: There is a robot on an m x n grid. The robot is initially located at the top-left corner
//          (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
//          The robot can only move either down or right at any point in time.
//          Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
//
// =================================================================================================
// 🔍 EVOLUTION OF SOLUTIONS & COMPLEXITY ANALYSIS:
// =================================================================================================
//
// Approach 1 (Pure Recursion - TLE):
//   - At each cell (r, c), two choices: Down (r + 1, c) or Right (r, c + 1).
//   - Transition: ways(r, c) = ways(r + 1, c) + ways(r, c + 1)
//   - Base Cases:
//       * r == m - 1 && c == n - 1 -> return 1 (Found 1 unique valid path to target)
//       * r >= m || c >= n -> return 0 (Out of bounds)
//   - Complexity: O(2^(m + n)) exponential -> Hits TLE on large grids!
//
// Approach 2 (Krish's Top-Down DP with 2D Memoization - 0 ms Beats 100% ⚡):
//   - Key Insight: Multiple paths intersect at the exact same cell (r, c).
//     Instead of re-exploring the grid below (r, c) repeatedly, store the computed paths in grid[r][c].
//   - If grid[r][c] != 0, return the cached result in O(1) time!
//   - Solves each subproblem exactly once.
//
// Complexity:
//   - Time: O(M * N) - Every cell in the m x n grid is evaluated at most once.
//   - Space: O(M * N) auxiliary 2D memo grid + O(M + N) recursion call stack depth.
// Problem Link: https://leetcode.com/problems/unique-paths/

public class LC62_UniquePaths {

    // Approach 2: Top-Down Recursion + Memoization (0 ms Beats 100% ⚡)
    static int ways(int m, int n, int r, int c, int[][] grid) {
        // Base case: Reached destination
        if ((r == m - 1) && (c == n - 1)) {
            return 1;
        }
        // Base case: Out of bounds
        if (r >= m || c >= n) {
            return 0;
        }

        // Cache hit: If already computed for (r, c), return stored result immediately!
        if (grid[r][c] != 0) {
            return grid[r][c];
        }

        // Compute Down + Right, store into grid[r][c], and return
        return grid[r][c] = ways(m, n, r + 1, c, grid) + ways(m, n, r, c + 1, grid);
    }

    public int uniquePaths(int m, int n) {
        int[][] grid = new int[m][n];
        return ways(m, n, 0, 0, grid);
    }

    public static void main(String[] args) {
        LC62_UniquePaths solver = new LC62_UniquePaths();
        System.out.println("Paths for 3x7: " + solver.uniquePaths(3, 7)); // Expected: 28
        System.out.println("Paths for 3x2: " + solver.uniquePaths(3, 2)); // Expected: 3
    }
}

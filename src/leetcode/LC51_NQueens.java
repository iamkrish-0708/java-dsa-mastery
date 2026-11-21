package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// LeetCode #51 - N-Queens
// Difficulty: Hard (Blind 75 / Top Interview 150)
// Problem: The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that
//          no two queens attack each other. Given an integer n, return all distinct solutions
//          to the n-queens puzzle. You may return the answer in any order.
//
// =================================================================================================
// 💡 TECHNIQUE: 2D GRID BACKTRACKING VIA COLUMN-BY-COLUMN PLACEMENT (KRISH'S SOLVE - 2 ms ⚡)
// =================================================================================================
// - Directional Invariants:
//     1. Vertical Safety: Since each recursive call places exactly one queen and advances to
//        colIndex + 1, vertical attacks are mathematically impossible by design!
//     2. Left-Side Safety: Since queens are placed from left to right (0 to n - 1), columns to the
//        right (c > colIndex) are completely empty. We ONLY check the 3 left attack paths:
//        - Left Horizontal: (r, c - 1) down to c = 0
//        - Left Upper Diagonal: (r - 1, c - 1)
//        - Left Lower Diagonal: (r + 1, c - 1)
//     3. Backtracking Symmetry:
//        - Choose: board[rowIndex][colIndex] = 'Q'
//        - Explore: solve(ans, board, n, colIndex + 1)
//        - Unchoose: board[rowIndex][colIndex] = '.'
//
// Complexity:
//   - Time Complexity: O(N!) upper bound, pruned heavily by safePosition().
//   - Space Complexity: O(N^2) Auxiliary Space — O(N^2) char[][] board + O(N) recursion stack.
// =================================================================================================

public class LC51_NQueens {

    static boolean safePosition(char[][] board, int n, int rowIndex, int colIndex) {
        // left horizontal check
        int r = rowIndex;
        int c = colIndex;
        while (c >= 0) {
            if (board[r][c] == 'Q') {
                return false;
            }
            c--;
        }

        // left upper diagonal check
        r = rowIndex;
        c = colIndex;
        while (c >= 0 && r >= 0) {
            if (board[r][c] == 'Q') {
                return false;
            }
            c--;
            r--;
        }

        // left lower diagonal check
        r = rowIndex;
        c = colIndex;
        while (c >= 0 && r < n) {
            if (board[r][c] == 'Q') {
                return false;
            }
            c--;
            r++;
        }

        // safe position
        return true;
    }

    static void solve(List<List<String>> ans, char[][] board, int n, int colIndex) {
        // base case: all N columns safely placed
        if (colIndex >= n) {
            List<String> temp = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                temp.add(new String(board[i]));
            }
            ans.add(temp);
            return;
        }

        for (int rowIndex = 0; rowIndex < n; rowIndex++) {
            if (safePosition(board, n, rowIndex, colIndex)) {
                // place queen
                board[rowIndex][colIndex] = 'Q';
                // recursive call
                solve(ans, board, n, colIndex + 1);
                // backtrack step
                board[rowIndex][colIndex] = '.';
            }
        }
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        solve(ans, board, n, 0);
        return ans;
    }

    // Local Test Harness
    public static void main(String[] args) {
        LC51_NQueens solver = new LC51_NQueens();

        // Test 1: n = 4 -> 2 distinct solutions
        List<List<String>> result4 = solver.solveNQueens(4);
        System.out.println("Test 1 (n = 4) -> Total Solutions: " + result4.size() + " (Expected: 2)");
        for (int i = 0; i < result4.size(); i++) {
            System.out.println("Solution " + (i + 1) + ":");
            for (String row : result4.get(i)) {
                System.out.println("  " + row);
            }
        }

        // Test 2: n = 1 -> 1 solution: [["Q"]]
        List<List<String>> result1 = solver.solveNQueens(1);
        System.out.println("\nTest 2 (n = 1) -> Total Solutions: " + result1.size() + " (Expected: 1)");
    }
}

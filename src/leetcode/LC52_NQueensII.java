package leetcode;

import java.util.Arrays;

// LeetCode #52 - N-Queens II
// Difficulty: Hard (Top Interview 150)
// Problem: The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that
//          no two queens attack each other. Given an integer n, return the number of distinct
//          solutions to the n-queens puzzle.
//
// =================================================================================================
// 💡 TECHNIQUE: BACKTRACKING SOLUTION COUNTING (KRISH'S SOLVES - 2 ms ⚡)
// =================================================================================================
// - Approach 1: 1-Element Array Reference Trick (Krish - 2 ms ⚡)
//     * In Java, primitive ints are passed by value. By wrapping the counter in an array of size 1
//       (int[] count = new int[1]), count[0] can be mutated by reference across recursive frames!
//
// - Approach 2: Mutable Buffer Counter (Krish's Inventive StringBuilder - 2 ms ⚡)
//     * Leveraged pass-by-reference on a StringBuilder token accumulator: solutions.append('+').
//       Final answer is solutions.length().
//
// - Approach 3: Subtree Accumulation / Functional Return (Optimal - 1 ms Beats 100% ⚡)
//     * solve() returns int: leaf returns 1, and parent frames accumulate count += solve(...).
//
// Complexity:
//   - Time Complexity: O(N!) upper bound, pruned heavily by safePosition().
//   - Space Complexity: O(N^2) Auxiliary Space for char[][] board + O(N) recursion stack.
// =================================================================================================

public class LC52_NQueensII {

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

        return true;
    }

    // Approach 1: Krish's 1-Element Array Reference Solution (2 ms)
    static void solveArray(int[] count, char[][] board, int n, int colIndex) {
        if (colIndex >= n) {
            count[0] += 1;
            return;
        }

        for (int rowIndex = 0; rowIndex < n; rowIndex++) {
            if (safePosition(board, n, rowIndex, colIndex)) {
                board[rowIndex][colIndex] = 'Q';
                solveArray(count, board, n, colIndex + 1);
                board[rowIndex][colIndex] = '.';
            }
        }
    }

    public int totalNQueens(int n) {
        int[] count = new int[1];
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        solveArray(count, board, n, 0);
        return count[0];
    }

    // Approach 2: Krish's StringBuilder Counter Solution (2 ms)
    static void solveSB(StringBuilder solutions, char[][] board, int n, int colIndex) {
        if (colIndex >= n) {
            solutions.append('+');
            return;
        }

        for (int rowIndex = 0; rowIndex < n; rowIndex++) {
            if (safePosition(board, n, rowIndex, colIndex)) {
                board[rowIndex][colIndex] = 'Q';
                solveSB(solutions, board, n, colIndex + 1);
                board[rowIndex][colIndex] = '.';
            }
        }
    }

    public int totalNQueensSB(int n) {
        StringBuilder solutions = new StringBuilder();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        solveSB(solutions, board, n, 0);
        return solutions.length();
    }

    // Approach 3: Optimal Functional Subtree Accumulation (1 ms Beats 100%)
    static int solveFunctional(char[][] board, int n, int colIndex) {
        if (colIndex >= n) {
            return 1;
        }

        int count = 0;
        for (int rowIndex = 0; rowIndex < n; rowIndex++) {
            if (safePosition(board, n, rowIndex, colIndex)) {
                board[rowIndex][colIndex] = 'Q';
                count += solveFunctional(board, n, colIndex + 1);
                board[rowIndex][colIndex] = '.';
            }
        }
        return count;
    }

    public int totalNQueensOptimal(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        return solveFunctional(board, n, 0);
    }

    // Local Test Harness
    public static void main(String[] args) {
        LC52_NQueensII solver = new LC52_NQueensII();

        // Test 1: n = 4 -> 2 (Array Reference Approach)
        System.out.println("Test 1 Array Ref (n = 4): " + solver.totalNQueens(4) + " (Expected: 2)");

        // Test 2: n = 4 -> 2 (StringBuilder Approach)
        System.out.println("Test 2 StringBuilder (n = 4): " + solver.totalNQueensSB(4) + " (Expected: 2)");

        // Test 3: n = 8 -> 92 (Functional Approach)
        System.out.println("Test 3 Functional (n = 8): " + solver.totalNQueensOptimal(8) + " (Expected: 92)");
    }
}

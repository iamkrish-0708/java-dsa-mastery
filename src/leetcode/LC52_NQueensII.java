package leetcode;

import java.util.Arrays;

// LeetCode #52 - N-Queens II
// Difficulty: Hard (Top Interview 150)
// Problem: The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that
//          no two queens attack each other. Given an integer n, return the number of distinct
//          solutions to the n-queens puzzle.
//
// =================================================================================================
// 💡 TECHNIQUE: BACKTRACKING SOLUTION COUNTING (KRISH'S SOLVES - 2 ms / OPTIMAL 1 ms ⚡)
// =================================================================================================
// - Approach 1: Mutable Buffer Counter (Krish's Inventive "Jugaad" - 2 ms ⚡)
//     * Since Java primitives (int) are passed by value, Krish leveraged pass-by-reference of
//       StringBuilder by appending a token ('+') at each leaf: solutions.append('+').
//       Final answer is simply solutions.length()!
//
// - Approach 2: The 1-Element Array Reference Trick (2 ms)
//     * Pass int[] count = new int[1] so count[0] can be incremented by reference.
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

    // Approach 1: Krish's StringBuilder Counter (2 ms)
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

    public int totalNQueens(int n) {
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

        // Test 1: n = 4 -> 2
        System.out.println("Test 1 (n = 4): " + solver.totalNQueens(4) + " (Expected: 2)");

        // Test 2: n = 1 -> 1
        System.out.println("Test 2 (n = 1): " + solver.totalNQueens(1) + " (Expected: 1)");

        // Test 3: n = 8 -> 92 (Optimal Functional check)
        System.out.println("Test 3 (n = 8): " + solver.totalNQueensOptimal(8) + " (Expected: 92)");
    }
}

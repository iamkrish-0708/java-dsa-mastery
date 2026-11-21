package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Lecture 73: N-Queens Problem (Backtracking on Chessboard)
// Topic: Constraint Satisfaction & State-Space Tree Exploration
// LeetCode Equivalent: #51 - N-Queens (Hard)

public class NQueens {

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
        // base case: all N queens successfully placed across columns 0 to n-1
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
                // 1. Choose: place queen
                board[rowIndex][colIndex] = 'Q';
                // 2. Explore: move to the next column
                solve(ans, board, n, colIndex + 1);
                // 3. Unchoose (Backtrack): remove queen for sibling branches
                board[rowIndex][colIndex] = '.';
            }
        }
    }

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        solve(ans, board, n, 0);
        return ans;
    }

    public static void main(String[] args) {
        int n = 4;
        List<List<String>> solutions = solveNQueens(n);
        System.out.println("Total solutions for " + n + "-Queens: " + solutions.size());
        for (int i = 0; i < solutions.size(); i++) {
            System.out.println("\nConfiguration " + (i + 1) + ":");
            for (String row : solutions.get(i)) {
                System.out.println(row);
            }
        }
    }
}

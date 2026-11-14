package leetcode;

// LeetCode #79 - Word Search
// Difficulty: Medium (Top Interview 150 / Blind 75)
// Problem: Given an m x n grid of characters board and a string word, return true if word exists in the grid.
//          The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are
//          horizontally or vertically neighboring. The same letter cell may not be used more than once.
//
// =================================================================================================
// 💡 TECHNIQUE: 2D GRID BACKTRACKING WITH VISITED MATRIX (KRISH'S SOLVE - 200 ms ⚡)
// =================================================================================================
// - Pattern Bridge: Directly adapts the 2D Grid Backtracking template from Rat in a Maze (Lec 71).
// - Key Architectural Mechanics:
//     1. Dynamic Starting Cell:
//        Unlike Rat in a Maze (fixed at (0, 0)), a word can start at ANY grid cell (i, j).
//        We iterate over all cells and launch solve(...) whenever board[i][j] == word.charAt(0).
//     2. Base Case:
//        If ptr == word.length() - 1, we have successfully verified the final character -> return true.
//     3. 4-Directional Search & Short-Circuiting:
//        Check Up, Down, Left, Right in sequence. If ANY direction returns true, short-circuit
//        subsequent searches immediately to prune redundant recursive branches.
//     4. Backtracking (Unchoose):
//        visited[r][c] = false restores the cell state so that alternate starting positions
//        or sibling paths can reuse it.
//
// Complexity:
//   - Time Complexity: O(M * N * 3^L) where M x N is board size and L is word length.
//     (From each cell we branch in at most 3 directions because the cell we came from is visited).
//   - Space Complexity: O(M * N + L) Auxiliary Space — O(M * N) for boolean[][] visited + O(L) recursion stack.
// =================================================================================================

public class LC79_WordSearch {

    // Helper function: Backtracking search
    static boolean solve(char[][] board, boolean[][] visited, int ptr, String word, int r, int c) {
        if (ptr == word.length() - 1) {
            return true;
        }

        visited[r][c] = true;
        boolean found = false;
        char ch = word.charAt(ptr + 1);

        // Up
        if (isSafe(board, visited, r - 1, c, ch)) {
            found = solve(board, visited, ptr + 1, word, r - 1, c);
        }
        // Down
        if (!found && isSafe(board, visited, r + 1, c, ch)) {
            found = solve(board, visited, ptr + 1, word, r + 1, c);
        }
        // Left
        if (!found && isSafe(board, visited, r, c - 1, ch)) {
            found = solve(board, visited, ptr + 1, word, r, c - 1);
        }
        // Right
        if (!found && isSafe(board, visited, r, c + 1, ch)) {
            found = solve(board, visited, ptr + 1, word, r, c + 1);
        }

        // Backtrack: unmark visited
        visited[r][c] = false;

        return found;
    }

    // Safety checks: Bounds, Visited, Character Match
    static boolean isSafe(char[][] board, boolean[][] visited, int r, int c, char ch) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) {
            return false;
        }
        if (visited[r][c]) {
            return false;
        }
        if (board[r][c] != ch) {
            return false;
        }
        return true;
    }

    // Main API
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Only start DFS if the first letter matches
                if (board[i][j] == word.charAt(0)) {
                    if (solve(board, visited, 0, word, i, j)) {
                        return true; // Found the word!
                    }
                }
            }
        }
        return false;
    }

    // Local Test Harness
    public static void main(String[] args) {
        LC79_WordSearch solver = new LC79_WordSearch();

        char[][] board1 = {
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
        };

        // Test 1: "ABCCED" -> true
        System.out.println("Test 1 (ABCCED): " + solver.exist(board1, "ABCCED") + " (Expected: true)");

        // Test 2: "SEE" -> true
        System.out.println("Test 2 (SEE): " + solver.exist(board1, "SEE") + " (Expected: true)");

        // Test 3: "ABCB" -> false (cannot reuse cell 'B')
        System.out.println("Test 3 (ABCB): " + solver.exist(board1, "ABCB") + " (Expected: false)");
    }
}

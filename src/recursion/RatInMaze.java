package recursion;

import java.util.ArrayList;
import java.util.Collections;

// GFG - Rat in a Maze Problem - I
// Difficulty: Medium
// Problem: Consider a rat placed at (0, 0) in a square matrix maze of order n*n. It has to reach
//          the destination at (n - 1, n - 1). The rat can move in 4 directions: Up, Down, Left, Right.
//          Value 0 in a cell means it is blocked; Value 1 means it is open.
//          Return all possible paths that the rat can take to reach the destination in alphabetical order.

public class RatInMaze {

    static void solve(ArrayList<String> ans, int[][] maze, boolean[][] visited, int r, int c, String path) {
        // Base case: Destination reached
        if (r == (maze.length - 1) && c == (maze.length - 1)) {
            ans.add(path);
            return;
        }

        visited[r][c] = true;

        // Up
        int newR = r - 1;
        int newC = c;
        if (isSafe(maze, visited, newR, newC)) {
            solve(ans, maze, visited, newR, newC, path + "U");
        }
        // Down
        newR = r + 1;
        newC = c;
        if (isSafe(maze, visited, newR, newC)) {
            solve(ans, maze, visited, newR, newC, path + "D");
        }
        // Right
        newR = r;
        newC = c + 1;
        if (isSafe(maze, visited, newR, newC)) {
            solve(ans, maze, visited, newR, newC, path + "R");
        }
        // Left
        newR = r;
        newC = c - 1;
        if (isSafe(maze, visited, newR, newC)) {
            solve(ans, maze, visited, newR, newC, path + "L");
        }

        // Backtrack (Restore state for other paths)
        visited[r][c] = false;
    }

    static boolean isSafe(int[][] maze, boolean[][] visited, int r, int c) {
        int n = maze.length;

        // Out of bounds case
        if (r < 0 || r >= n || c < 0 || c >= n) {
            return false;
        }
        // Blocked cell case
        else if (maze[r][c] == 0) {
            return false;
        }
        // Repeating a cell in single path case
        else if (visited[r][c]) {
            return false;
        }
        // Cell safe to move case
        return true;
    }

    public ArrayList<String> ratInMaze(int[][] maze) {
        ArrayList<String> ans = new ArrayList<>();
        if (maze[0][0] == 0 || maze[maze.length - 1][maze.length - 1] == 0) {
            return ans;
        }
        boolean[][] visited = new boolean[maze.length][maze.length];
        solve(ans, maze, visited, 0, 0, "");
        Collections.sort(ans);
        return ans;
    }

    public static void main(String[] args) {
        RatInMaze solver = new RatInMaze();

        int[][] maze1 = {
            {1, 0, 0, 0},
            {1, 1, 0, 1},
            {1, 1, 0, 0},
            {0, 1, 1, 1}
        };

        System.out.println("Maze 1 Output: " + solver.ratInMaze(maze1)); // Expected: [DDRDRR, DRDDRR]

        int[][] maze2 = {
            {1, 0},
            {1, 0}
        };
        System.out.println("Maze 2 Output: " + solver.ratInMaze(maze2)); // Expected: [] (destination blocked)
    }
}

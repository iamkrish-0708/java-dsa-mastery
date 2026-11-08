package leetcode;

import java.util.List;

// LeetCode #120 - Triangle
// Difficulty: Medium (Classic 2D Grid Dynamic Programming)
// Problem: Given a triangle array, return the minimum path sum from top to bottom.
//          For each step, you may move to an adjacent number of the row below. More formally,
//          if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.
//
// Approach 1 (Lecture 66 - Pure Top-Down Recursion):
//   - State: (rowIndex, colIndex)
//   - From (r, c), two choices:
//       1. Directly down: (r + 1, c)
//       2. Diagonally right: (r + 1, c + 1)
//   - Recurrence: solve(r, c) = triangle[r][c] + min(solve(r + 1, c), solve(r + 1, c + 1))
//   - Base Case: When rowIndex reaches the bottom row (triangle.size() - 1), return triangle.get(r).get(c).
//
// Complexity Analysis (Pure Recursion):
//   - At each level, the call branches into 2 choices.
//   - Height of triangle = N.
//   - Total paths / combinations = O(2^N) exponential!
//   - Status: Hits expected TLE on large triangles due to overlapping subproblems.
//   - DP Memoization Preview: Caching solve(r, c) in Integer[][] dp drops time to O(N^2) polynomial!
//
// Problem Link: https://leetcode.com/problems/triangle/

public class LC120_Triangle {

    // Approach 1: Pure Recursion (As covered in Lecture 66)
    static int solve(List<List<Integer>> triangle, int rowIndex, int colIndex) {
        // Base case: Reached bottom row
        if (rowIndex == triangle.size() - 1) {
            return triangle.get(rowIndex).get(colIndex);
        }

        // Recursive choices
        int downWay = solve(triangle, rowIndex + 1, colIndex);
        int diagonalWay = solve(triangle, rowIndex + 1, colIndex + 1);

        // Optimal path sum from current position
        return triangle.get(rowIndex).get(colIndex) + Math.min(downWay, diagonalWay);
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        return solve(triangle, 0, 0);
    }

    public static void main(String[] args) {
        LC120_Triangle solver = new LC120_Triangle();

        // Test triangle: [[2], [3,4], [6,5,7], [4,1,8,3]]
        List<List<Integer>> triangle = List.of(
                List.of(2),
                List.of(3, 4),
                List.of(6, 5, 7),
                List.of(4, 1, 8, 3)
        );

        System.out.println("Minimum path sum: " + solver.minimumTotal(triangle));
        // Expected: 11 (2 + 3 + 5 + 1 = 11)
    }
}

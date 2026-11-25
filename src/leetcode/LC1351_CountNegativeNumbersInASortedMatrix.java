package leetcode;

// LeetCode #1351 - Count Negative Numbers in a Sorted Matrix
// Difficulty: Easy
// Problem: Given an m x n matrix grid which is sorted in non-increasing order both row-wise
//          and column-wise, return the number of negative numbers in grid.
//
// =================================================================================================
// 💡 TECHNIQUE: REVERSE ROW SCAN WITH EARLY BREAK (KRISH'S SOLVE - 1 ms ⚡)
// =================================================================================================
// - Mechanics:
//     1. Each row is sorted non-increasingly (descending), meaning negatives are clustered at the right.
//     2. Traverse each row backwards from j = n - 1 down to 0:
//        - If grid[i][j] < 0: increment negatives counter.
//        - Else: non-negative encountered -> immediately break out of the inner loop since
//          all remaining elements to the left are guaranteed >= 0!
//     3. Return final accumulated negative count.
//
// - O(m + n) Optimal Staircase Walk (0 ms Beats 100%):
//     Start at bottom-left (r = m - 1, c = 0).
//     - If grid[r][c] < 0: Since rows are sorted descending, all elements to the right (from c to n - 1)
//       are also negative! Add (n - c) to count and move up (r--).
//     - Else (grid[r][c] >= 0): This element is non-negative, move right (c++).
//
// Complexity:
//   - Krish's Scan: O(m * n) worst case (all negative), but in practice O(m * k) where k is avg negatives per row.
//   - Staircase Walk: O(m + n) Time — Single traversal touching at most m rows and n columns.
//   - Space Complexity: O(1) Auxiliary Space across both approaches.
// =================================================================================================

public class LC1351_CountNegativeNumbersInASortedMatrix {

    // Approach 1: Reverse Row Scan with Early Break (Krish's Accepted Solve - 1 ms)
    public int countNegatives(int[][] grid) {
        int negatives = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = grid[i].length - 1; j >= 0; j--) {
                if (grid[i][j] < 0) {
                    negatives++;
                } else {
                    break;
                }
            }
        }
        return negatives;
    }

    // Approach 2: Optimal Staircase Walk (0 ms Beats 100% — O(m + n) Time)
    public int countNegativesStaircase(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        int r = m - 1;
        int c = 0;

        while (r >= 0 && c < n) {
            if (grid[r][c] < 0) {
                // If grid[r][c] is negative, everything to its right in row r is also negative!
                count += (n - c);
                r--; // Move to previous row
            } else {
                c++; // Move to next column
            }
        }
        return count;
    }

    public static void main(String[] args) {
        LC1351_CountNegativeNumbersInASortedMatrix solver = new LC1351_CountNegativeNumbersInASortedMatrix();

        int[][] test1 = {
                {4, 3, 2, -1},
                {3, 2, 1, -1},
                {1, 1, -1, -2},
                {-1, -1, -2, -3}
        };
        System.out.println("Test 1: " + solver.countNegatives(test1) + " (Expected: 8)");
        System.out.println("Test 1 Staircase: " + solver.countNegativesStaircase(test1) + " (Expected: 8)");

        int[][] test2 = {
                {3, 2},
                {1, 0}
        };
        System.out.println("Test 2: " + solver.countNegatives(test2) + " (Expected: 0)");
        System.out.println("Test 2 Staircase: " + solver.countNegativesStaircase(test2) + " (Expected: 0)");
    }
}

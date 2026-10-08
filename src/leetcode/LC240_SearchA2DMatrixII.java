package leetcode;

/**
 * LeetCode #240: Search a 2D Matrix II
 * Difficulty: Medium (Top Interview 150 / Blind 75)
 * Link: https://leetcode.com/problems/search-a-2d-matrix-ii/
 * 
 * Approach: Staircase Search from Top-Right Corner
 * 1. Difference from LC 74:
 *    - In LC 74, first element of row > last element of previous row (strictly 1D flattening O(log(M*N))).
 *    - In LC 240, rows and columns are sorted independently. 1D flattening does NOT apply.
 * 2. Why Top-Right Corner (r = 0, c = colTotal - 1)?
 *    - Elements to the left are smaller.
 *    - Elements downward are larger.
 *    - This gives an unambiguous binary decision at every step:
 *      - If matrix[r][c] == target: return true.
 *      - If matrix[r][c] < target: eliminate current row -> r++.
 *      - If matrix[r][c] > target: eliminate current column -> c--.
 * 
 * Time Complexity: O(M + N) - 5ms Beats 100%
 * Space Complexity: O(1) Auxiliary Space
 */
public class LC240_SearchA2DMatrixII {

    public boolean searchMatrix(int[][] matrix, int target) {
        int r = 0;
        int c = matrix[0].length - 1;

        while (r < matrix.length && c >= 0) {
            if (matrix[r][c] == target) {
                return true;
            }
            if (matrix[r][c] < target) {
                r++; // Move down
            } else {
                c--; // Move left
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LC240_SearchA2DMatrixII solver = new LC240_SearchA2DMatrixII();

        int[][] matrix = {
            {1, 4, 7, 11, 15},
            {2, 5, 8, 12, 19},
            {3, 6, 9, 16, 22},
            {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30}
        };

        // Test 1: target = 5 -> true
        System.out.println("Test 1 Result: " + solver.searchMatrix(matrix, 5)); // Expected: true

        // Test 2: target = 20 -> false
        System.out.println("Test 2 Result: " + solver.searchMatrix(matrix, 20)); // Expected: false
    }
}

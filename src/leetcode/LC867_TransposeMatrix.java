package leetcode;

import java.util.Arrays;

/**
 * LeetCode #867: Transpose Matrix
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/transpose-matrix/
 * 
 * Approach: Matrix Transposition (Swapping Dimensions & Coordinates)
 * - For an R x C matrix, the transpose has dimensions C x R.
 * - matrixT[i][j] = matrix[j][i].
 * 
 * Time Complexity: O(R * C) - Visits every cell once (0ms Beats 100%)
 * Space Complexity: O(R * C) - Output matrix
 */
public class LC867_TransposeMatrix {

    public int[][] transpose(int[][] matrix) {
        int r = matrix[0].length; // Number of columns in original -> rows in transpose
        int c = matrix.length;    // Number of rows in original -> columns in transpose
        int[][] matrixT = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                matrixT[i][j] = matrix[j][i];
            }
        }
        return matrixT;
    }

    public static void main(String[] args) {
        LC867_TransposeMatrix solver = new LC867_TransposeMatrix();

        int[][] mat1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] res1 = solver.transpose(mat1);
        System.out.println("Test 1: " + Arrays.deepToString(res1));

        int[][] mat2 = {{1, 2, 3}, {4, 5, 6}};
        int[][] res2 = solver.transpose(mat2);
        System.out.println("Test 2: " + Arrays.deepToString(res2));
    }
}

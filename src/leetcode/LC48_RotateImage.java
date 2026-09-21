package leetcode;

import java.util.Arrays;

/**
 * LeetCode #48: Rotate Image
 * Difficulty: Medium (Top Interview 150 / Blind 75)
 * Link: https://leetcode.com/problems/rotate-image/
 * 
 * Approach: In-Place Matrix Rotation (Transpose + Reverse Each Row)
 * 1. Transpose the n x n matrix in-place:
 *    - Swap elements across the main diagonal: matrix[i][j] <-> matrix[j][i] for j < i (or j > i).
 * 2. Reverse each row using Two Pointers:
 *    - Swap matrix[i][left] and matrix[i][right] while moving inwards (left++, right--).
 * 
 * Time Complexity: O(N^2) - One pass for transpose + one pass for row reversal.
 * Space Complexity: O(1) - Mutates input matrix directly in-place without extra space.
 */
public class LC48_RotateImage {

    public void rotate(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        // Step 1: In-Place Transpose (Swap elements across main diagonal)
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Step 2: Reverse each row (Two Pointers)
        for (int i = 0; i < row; i++) {
            int left = 0, right = col - 1;
            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }
    }

    public static void main(String[] args) {
        LC48_RotateImage solver = new LC48_RotateImage();

        // Test 1: 3x3 Matrix
        int[][] matrix1 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("Original Matrix 1:");
        printMatrix(matrix1);
        solver.rotate(matrix1);
        System.out.println("Rotated 90° Clockwise Matrix 1:");
        printMatrix(matrix1);

        // Test 2: 4x4 Matrix
        int[][] matrix2 = {
            { 5,  1,  9, 11},
            { 2,  4,  8, 10},
            {13,  3,  6,  7},
            {15, 14, 12, 16}
        };
        System.out.println("Original Matrix 2:");
        printMatrix(matrix2);
        solver.rotate(matrix2);
        System.out.println("Rotated 90° Clockwise Matrix 2:");
        printMatrix(matrix2);
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}

package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode #54: Spiral Matrix
 * Difficulty: Medium (Top Interview 150 / Blind 75)
 * Link: https://leetcode.com/problems/spiral-matrix/
 * 
 * Approach: 4-Boundary Shrinking Simulation (Layer-by-Layer Traversal)
 * - Maintain 4 pointers: startRow, endRow, startCol, endCol.
 * - Traverse Right across top boundary (startRow), then shrink top (startRow++).
 * - Traverse Down along right boundary (endCol), then shrink right (endCol--).
 * - If startRow <= endRow, traverse Left across bottom boundary (endRow), then shrink bottom (endRow--).
 * - If startCol <= endCol, traverse Up along left boundary (startCol), then shrink left (startCol++).
 * - Repeat until boundaries cross (startRow > endRow or startCol > endCol).
 * 
 * Time Complexity: O(M * N) - Every cell is visited exactly once (0ms Beats 100%).
 * Space Complexity: O(1) - Auxiliary space (excluding output list).
 */
public class LC54_SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int startRow = 0;
        int endRow = row - 1;
        int startCol = 0;
        int endCol = col - 1;
        List<Integer> output = new ArrayList<>();

        while (startRow <= endRow && startCol <= endCol) {
            // Traverse Right (Top Row)
            for (int c = startCol; c <= endCol; c++) {
                output.add(matrix[startRow][c]);
            }
            startRow++;

            // Traverse Down (Right Column)
            for (int r = startRow; r <= endRow; r++) {
                output.add(matrix[r][endCol]);
            }
            endCol--;

            // Traverse Left (Bottom Row) - Guard against single remaining row
            if (startRow <= endRow) {
                for (int ce = endCol; ce >= startCol; ce--) {
                    output.add(matrix[endRow][ce]);
                }
                endRow--;
            }

            // Traverse Up (Left Column) - Guard against single remaining column
            if (startCol <= endCol) {
                for (int re = endRow; re >= startRow; re--) {
                    output.add(matrix[re][startCol]);
                }
                startCol++;
            }
        }
        return output;
    }

    public static void main(String[] args) {
        LC54_SpiralMatrix solver = new LC54_SpiralMatrix();

        // Test 1: 3x3 Matrix
        int[][] matrix1 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("Test 1 (3x3 Matrix): " + solver.spiralOrder(matrix1));
        // Expected: [1, 2, 3, 6, 9, 8, 7, 4, 5]

        // Test 2: 3x4 Rectangular Matrix
        int[][] matrix2 = {
            {1,  2,  3,  4},
            {5,  6,  7,  8},
            {9, 10, 11, 12}
        };
        System.out.println("Test 2 (3x4 Matrix): " + solver.spiralOrder(matrix2));
        // Expected: [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]
    }
}

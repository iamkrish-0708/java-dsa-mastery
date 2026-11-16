package leetcode;

// LeetCode #1572 - Matrix Diagonal Sum
// Difficulty: Easy
// Problem: Given a square matrix mat, return the sum of the matrix diagonals.
//          Only include the sum of all the elements on the primary diagonal and all the elements
//          on the secondary diagonal that are not part of the primary diagonal.
//
// =================================================================================================
// 💡 TECHNIQUE: TWO-POINTER DIAGONAL TRAVERSAL WITH IN-PLACE ZEROING (KRISH'S SOLVE - 0 ms ⚡ BEATS 100%)
// =================================================================================================
// - Clever Insight:
//     When traversing the primary diagonal (top-left to bottom-right), setting mat[r][c] = 0 in-place
//     guarantees that when the secondary diagonal (top-right to bottom-left) visits the intersection
//     center element in odd-dimension matrices, it adds 0—completely eliminating duplicate counting
//     without needing any separate odd/even checks!
//
// Complexity:
//   - Time Complexity: O(N) where N is mat.length (each diagonal is traversed in N steps).
//   - Space Complexity: O(1) Auxiliary Space — In-place traversal.
// =================================================================================================

public class LC1572_MatrixDiagonalSum {

    static int leftDiagonalSum(int[][] mat, int r, int c, int sum) {
        while (r < mat.length && c < mat[0].length) {
            sum += mat[r][c];
            mat[r][c] = 0;
            r++;
            c++;
        }
        return sum;
    }

    static int rightDiagonalSum(int[][] mat, int r, int c, int sum) {
        while (r < mat.length && c >= 0) {
            sum += mat[r][c];
            mat[r][c] = 0;
            r++;
            c--;
        }
        return sum;
    }

    public int diagonalSum(int[][] mat) {
        if (mat.length == 1) {
            return mat[0][0];
        }

        int left = leftDiagonalSum(mat, 0, 0, 0);
        int right = rightDiagonalSum(mat, 0, mat[0].length - 1, 0);
        int sum = left + right;
        return sum;
    }

    // Local Test Harness
    public static void main(String[] args) {
        LC1572_MatrixDiagonalSum solver = new LC1572_MatrixDiagonalSum();

        // Test 1: 3x3 matrix (odd n = 3) -> 1+5+9 + 3+7 = 25 (5 counted once)
        int[][] mat1 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("Test 1 (3x3): " + solver.diagonalSum(mat1) + " (Expected: 25)");

        // Test 2: 4x4 matrix (even n = 4) -> 1+1+1+1 + 1+1+1+1 = 8
        int[][] mat2 = {
            {1, 1, 1, 1},
            {1, 1, 1, 1},
            {1, 1, 1, 1},
            {1, 1, 1, 1}
        };
        System.out.println("Test 2 (4x4): " + solver.diagonalSum(mat2) + " (Expected: 8)");

        // Test 3: 1x1 matrix -> 5
        int[][] mat3 = {{5}};
        System.out.println("Test 3 (1x1): " + solver.diagonalSum(mat3) + " (Expected: 5)");
    }
}

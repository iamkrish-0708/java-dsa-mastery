package leetcode;

/**
 * LeetCode #74: Search a 2D Matrix
 * Difficulty: Medium (Top Interview 150 / Blind 75)
 * Link: https://leetcode.com/problems/search-a-2d-matrix/
 * 
 * Approach: Virtual 1D Flattening Binary Search
 * 1. Observation:
 *    - Each row is sorted.
 *    - The first integer of each row is greater than the last integer of the previous row.
 *    - Therefore, the entire M x N matrix can be treated as a single sorted 1D array of size M * N.
 * 2. 1D to 2D Coordinate Mapping:
 *    - rowIndex = mid / colTotal
 *    - colIndex = mid % colTotal
 * 3. Binary Search:
 *    - Standard O(log(M * N)) binary search over range [0, M * N - 1].
 * 
 * Time Complexity: O(log(M * N)) - 0ms Beats 100%
 * Space Complexity: O(1) Auxiliary Space
 */
public class LC74_SearchA2DMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        int start = 0;
        int colTotal = matrix[0].length;
        int rowTotal = matrix.length;
        int end = (rowTotal * colTotal) - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            int rowIndex = mid / colTotal;
            int colIndex = mid % colTotal;

            if (matrix[rowIndex][colIndex] == target) {
                return true;
            }
            if (matrix[rowIndex][colIndex] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LC74_SearchA2DMatrix solver = new LC74_SearchA2DMatrix();

        // Test 1: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3 -> true
        int[][] matrix1 = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };
        int target1 = 3;
        System.out.println("Test 1 Result: " + solver.searchMatrix(matrix1, target1)); // Expected: true

        // Test 2: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13 -> false
        int[][] matrix2 = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };
        int target2 = 13;
        System.out.println("Test 2 Result: " + solver.searchMatrix(matrix2, target2)); // Expected: false
    }
}

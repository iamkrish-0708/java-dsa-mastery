package leetcode;

/**
 * LeetCode #852: Peak Index in a Mountain Array
 * Difficulty: Medium (Top 100 / Blind 75)
 * Link: https://leetcode.com/problems/peak-index-in-a-mountain-array/
 * 
 * Approach: Binary Search on Mountain Slope
 * - Mountain array has an increasing slope followed by a decreasing slope.
 * - If arr[mid] >= arr[mid + 1]:
 *   We are at the peak or on the descending slope -> record ans = mid and search left (e = mid - 1).
 * - Else (arr[mid] < arr[mid + 1]):
 *   We are on the ascending slope -> peak must be strictly to the right -> search right (s = mid + 1).
 * - Safe from ArrayIndexOutOfBoundsException on mid + 1 because mountain guarantees peak is not at last index.
 * 
 * Time Complexity: O(log N) - 0ms Beats 100%
 * Space Complexity: O(1) - Auxiliary Space
 */
public class LC852_PeakIndexInAMountainArray {

    public int peakIndexInMountainArray(int[] arr) {
        int s = 0;
        int e = arr.length - 1;
        int ans = -1;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (arr[mid] >= arr[mid + 1]) {
                ans = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        LC852_PeakIndexInAMountainArray solver = new LC852_PeakIndexInAMountainArray();

        // Test 1: [0, 1, 0] -> Peak at index 1
        int[] arr1 = {0, 1, 0};
        System.out.println("Test 1 Result: " + solver.peakIndexInMountainArray(arr1));
        // Expected: 1

        // Test 2: [0, 2, 1, 0] -> Peak at index 1
        int[] arr2 = {0, 2, 1, 0};
        System.out.println("Test 2 Result: " + solver.peakIndexInMountainArray(arr2));
        // Expected: 1

        // Test 3: [0, 10, 5, 2] -> Peak at index 1
        int[] arr3 = {0, 10, 5, 2};
        System.out.println("Test 3 Result: " + solver.peakIndexInMountainArray(arr3));
        // Expected: 1

        // Test 4: [1, 2, 3, 5, 4, 2, 1] -> Peak at index 3
        int[] arr4 = {1, 2, 3, 5, 4, 2, 1};
        System.out.println("Test 4 Result: " + solver.peakIndexInMountainArray(arr4));
        // Expected: 3
    }
}

package binarySearch;
/*
You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.

Return the single element that appears only once.

Your solution must run in O(log n) time and O(1) space.



Example 1:

Input: nums = [1,1,2,3,3,4,4,8,8]
Output: 2

Example 2:

Input: nums = [3,3,7,7,10,11,11]
Output: 10
*/
public class SingleElementSortedArray {
    static boolean isLeftHalf(int[] arr, int mid) {
        if (((mid & 1) == 0 && mid + 1 < arr.length && arr[mid] == arr[mid + 1]) || 
            ((mid & 1) != 0 && mid - 1 >= 0 && arr[mid - 1] == arr[mid])) {
            return true; // We are in the left half (normal pairs)
        }
        return false;
    }

    public static int singleNonDuplicate(int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        if (end == 0) {
            return arr[0];
        }

        while (start < end) {
            int mid = start + (end - start) / 2;
            if (isLeftHalf(arr, mid)) {
                start = mid + 1; // Left side valid -> unique element is on the right
            } else {
                end = mid;       // Disrupted side -> search left (keep mid as candidate)
            }
        }
        return arr[start];
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 3, 3, 4, 4, 8, 8};
        System.out.println("Single Element: " + singleNonDuplicate(arr));
    }
}

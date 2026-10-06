package binarySearch;
/*1
C
In a nearly sorted array, elements are expected to be in sorted order except that each element can be swapped with its adjacent elements. This means that any element at index in a sorted array could appear at index 1-1, 1, or 1+1 in a nearly sorted array. Your task is to determine the index of a given target element K within this array. If the element K is not present, return-1.
*/
public class NearlySortedArray {
    public static int search(int[] arr, int k) {
        int n = arr.length;
        int start = 0;
        int end = n - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (mid - 1 >= 0 && arr[mid - 1] == k) {
                return mid - 1;
            }
            if (mid + 1 < n && arr[mid + 1] == k) {
                return mid + 1;
            }
            if (arr[mid] == k) {
                return mid;
            }
            if (arr[mid] < k) {
                start = mid + 2;
            } else {
                end = mid - 2;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 10, 9, 11};
        int k = 10;
        System.out.println("Index of " + k + ": " + search(arr, k));
    }
}

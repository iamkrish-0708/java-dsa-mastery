package binarySearch;

/**
 * LeetCode 702 / Search in an Infinite Sorted Array
 * Problem: Search in a Sorted Array of Unknown Size
 * 
 * An ArrayReader interface is provided where reader.get(k) returns:
 * - the value at index k (0-indexed)
 * - Integer.MAX_VALUE (2147483647) if k is out of the bounds of the array.
 * 
 * Target Time Complexity: O(log T) where T is the index of target
 * Target Space Complexity: O(1)
 */

// Interface matching LeetCode 702
interface ArrayReader {
    int get(int index);
}

// Mock implementation of ArrayReader for local testing
class MockArrayReader implements ArrayReader {
    private final int[] arr;

    public MockArrayReader(int[] arr) {
        this.arr = arr;
    }

    @Override
    public int get(int index) {
        if (index < 0 || index >= arr.length) {
            return Integer.MAX_VALUE; // 2^31 - 1, as defined by LeetCode
        }
        return arr[index];
    }
}

public class SearchInfiniteSortedArray {

    // ==========================================
    // WRITE YOUR LEETCODE SOLUTION HERE
    // ==========================================
    public static int search(ArrayReader reader, int target) {
        // TODO: Write your logic here
        if(reader.get(0)==target){
            return 0;
        }
        int i=1;
        while(reader.get(i)<=target){
            i=i*2;
        }
        if(reader.get(i)>target){
            int s=i/2;
            int e=i;
            while(s<=e){
                int mid=s+(e-s)/2;
                if(reader.get(mid)==target){
                    return mid;
                }
                if(reader.get(mid)<target){
                    s=mid+1;
                }
                else{
                    e=mid-1;
                }
            }
        }
        return -1;
    }

    // ==========================================
    // TEST CASES RUNNER
    // ==========================================
    public static void main(String[] args) {
        // Test Case 1: Target exists in array
        int[] arr1 = {-1, 0, 3, 5, 9, 12};
        ArrayReader reader1 = new MockArrayReader(arr1);
        int target1 = 9;
        int result1 = search(reader1, target1);
        System.out.println("Test Case 1 | Target: " + target1 + " | Expected: 4 | Got: " + result1);

        // Test Case 2: Target does not exist
        int[] arr2 = {-1, 0, 3, 5, 9, 12};
        ArrayReader reader2 = new MockArrayReader(arr2);
        int target2 = 2;
        int result2 = search(reader2, target2);
        System.out.println("Test Case 2 | Target: " + target2 + " | Expected: -1 | Got: " + result2);

        // Test Case 3: Single element target
        int[] arr3 = {5};
        ArrayReader reader3 = new MockArrayReader(arr3);
        int target3 = 5;
        int result3 = search(reader3, target3);
        System.out.println("Test Case 3 | Target: " + target3 + " | Expected: 0 | Got: " + result3);

        // Test Case 4: Larger simulated infinite array
        int[] arr4 = {1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095};
        ArrayReader reader4 = new MockArrayReader(arr4);
        int target4 = 511;
        int result4 = search(reader4, target4);
        System.out.println("Test Case 4 | Target: " + target4 + " | Expected: 8 | Got: " + result4);
    }
}

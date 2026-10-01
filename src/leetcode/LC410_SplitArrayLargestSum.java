package leetcode;

/**
 * LeetCode #410: Split Array Largest Sum
 * Difficulty: Hard (Top Interview / Google & Meta Favorite)
 * Link: https://leetcode.com/problems/split-array-largest-sum/
 * 
 * Approach: Binary Search on Answer Range + Greedy Subarray Partitioning (Book Allocation Model)
 * 1. Search Space: [0, sum of elements].
 * 2. `isValid()`: Greedily packs elements into subarrays. If current subarray sum + arr[i] > mid,
 *    start a new subarray. If needed subarrays > k or single element > mid, return false.
 * 3. If valid: record ans = mid and search left (end = mid - 1) to minimize the largest sum.
 * 4. Else: search right (start = mid + 1) to allow larger subarray capacity.
 * 
 * Time Complexity: O(N * log(Sum)) - 1ms Beats 100%
 * Space Complexity: O(1) - Auxiliary Space
 */
public class LC410_SplitArrayLargestSum {

    static boolean isValid(int[] arr, int mid, int splitCount, int size) {
        int current = 1;
        int allocation = 0;
        for (int i = 0; i < size; i++) {
            if (arr[i] > mid) {
                return false;
            }
            if (allocation + arr[i] <= mid) {
                allocation += arr[i];
            } else {
                current++;
                if (current > splitCount) {
                    return false;
                }
                allocation = arr[i];
            }
        }
        return true;
    }

    public int splitArray(int[] nums, int k) {
        int size = nums.length;
        if (size < k) {
            return -1;
        }
        int start = 0;
        int sum = 0;
        int ans = -1;
        for (int i = 0; i < size; i++) {
            sum += nums[i];
        }
        int end = sum;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (isValid(nums, mid, k, size)) {
                ans = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        LC410_SplitArrayLargestSum solver = new LC410_SplitArrayLargestSum();

        // Test 1: [7,2,5,10,8], k = 2 -> 18 ([7,2,5] and [10,8])
        int[] nums1 = {7, 2, 5, 10, 8};
        System.out.println("Test 1 Result: " + solver.splitArray(nums1, 2));
        // Expected: 18

        // Test 2: [1,2,3,4,5], k = 2 -> 9
        int[] nums2 = {1, 2, 3, 4, 5};
        System.out.println("Test 2 Result: " + solver.splitArray(nums2, 2));
        // Expected: 9
    }
}

package leetcode;

import java.util.Arrays;

// LeetCode #238 - Product of Array Except Self
// Difficulty: Medium (Blind 75 / Top Interview 150)
// Problem: Given an integer array nums, return an array answer such that answer[i] is equal
//          to the product of all the elements of nums except nums[i].
//          The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
//          You must write an algorithm that runs in O(n) time and without using the division operation.
//
// =================================================================================================
// 💡 TECHNIQUE: PREFIX & SUFFIX PRODUCT ACCUMULATION (KRISH'S SOLVE - 3 ms ⚡)
// =================================================================================================
// - Intuition & Breakthrough:
//     1. Division is forbidden, and brute force nested multiplication takes O(N^2) (Time Limit Exceeded).
//     2. Any answer[i] is strictly:
//            (Product of all elements to the left of i) * (Product of all elements to the right of i)
//     3. Multiplicative Identity:
//        - When there is nothing to the left of index 0: left[0] = 1.
//        - When there is nothing to the right of index n - 1: right[n - 1] = 1.
//     4. Left accumulation pass:
//        left[i] = left[i - 1] * nums[i - 1]
//     5. Right accumulation pass:
//        right[i] = right[i + 1] * nums[i + 1]
//     6. In-place result:
//        nums[i] = left[i] * right[i]
//
// Complexity:
//   - Time Complexity: O(N) — Three independent linear passes.
//   - Space Complexity: O(N) Auxiliary Space — for prefix (left) and suffix (right) arrays.
// =================================================================================================

public class LC238_ProductOfArrayExceptSelf {

    // Approach 1: Three-Pass Prefix & Suffix Arrays (Krish's Accepted Solve - 3 ms)
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = 1; // nothing at left from 0 index
        right[n - 1] = 1; // nothing at right from n-1 index
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }
        for (int i = 0; i < n; i++) {
            nums[i] = left[i] * right[i];
        }
        return nums;
    }

    // Approach 2: O(1) Extra Space Follow-Up (Single Output Array + Running Right Product)
    public int[] productExceptSelfOptimalSpace(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        // 1. Build left products directly inside ans
        ans[0] = 1;
        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }

        // 2. Multiply by running right product backwards
        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            ans[i] = ans[i] * rightProduct;
            rightProduct *= nums[i];
        }

        return ans;
    }

    public static void main(String[] args) {
        LC238_ProductOfArrayExceptSelf solver = new LC238_ProductOfArrayExceptSelf();

        int[] test1 = {1, 2, 3, 4};
        int[] res1 = solver.productExceptSelf(test1.clone());
        System.out.println("Test 1 Result: " + Arrays.toString(res1)); // Expected: [24, 12, 8, 6]

        int[] test2 = {-1, 1, 0, -3, 3};
        int[] res2 = solver.productExceptSelf(test2.clone());
        System.out.println("Test 2 Result: " + Arrays.toString(res2)); // Expected: [0, 0, 9, 0, 0]

        int[] optRes = solver.productExceptSelfOptimalSpace(new int[]{1, 2, 3, 4});
        System.out.println("O(1) Space Result: " + Arrays.toString(optRes)); // Expected: [24, 12, 8, 6]
    }
}

package leetcode;

import java.util.HashMap;
import java.util.Map;

// LeetCode #1748 - Sum of Unique Elements
// Difficulty: Easy
// Problem: You are given an integer array nums. The unique elements of an array are the
//          elements that appear exactly once in the array.
//          Return the sum of all the unique elements of nums.
//
// =================================================================================================
// 💡 EVOLUTION OF SOLUTIONS:
// =================================================================================================
//
// Approach 1 (Krish's Initial HashMap Solve):
//   - Pass 1: Build frequency map using map.put(n, map.getOrDefault(n, 0) + 1).
//   - Pass 2: Iterate over map.entrySet(), if entry.getValue() == 1, add entry.getKey() to sum.
//   - Complexity: O(N) Time, O(U) Auxiliary Space (where U <= N is number of unique keys).
//   - Trade-off: Autoboxing (int -> Integer) and HashMap overhead.
//
// Approach 2 (Direct Frequency Array Optimization - 0 ms Beats 100% ⚡):
//   - Constraints: 1 <= nums[i] <= 100.
//   - Use a direct int[] freq = new int[101] to count occurrences.
//   - Pass 2: Iterate nums, if freq[n] == 1, sum += n and reset freq[n] = 0 (prevents re-adding).
//   - Complexity: O(N) Time, O(1) Auxiliary Space (fixed 101 ints, zero GC pressure).
//
// Approach 3 (Single-Pass State Transition):
//   - Maintain sum dynamically in a single pass using state flags in freq[]:
//       * freq[n] == 0 (first sight) -> sum += n, freq[n] = 1
//       * freq[n] == 1 (second sight) -> sum -= n, freq[n] = 2 (revoke uniqueness)
//       * freq[n] >= 2 (already revoked) -> ignore
//   - Complexity: O(N) Time (single pass), O(1) Auxiliary Space.
// =================================================================================================

public class LC1748_SumOfUniqueElements {

    // Approach 2: Direct Frequency Array (0 ms Beats 100%)
    public int sumOfUnique(int[] nums) {
        int[] freq = new int[101];
        int sum = 0;
        for (int n : nums) {
            freq[n]++;
        }
        for (int n : nums) {
            if (freq[n] == 1) {
                sum += n;
                freq[n] = 0; // Guard against duplicate addition
            }
        }
        return sum;
    }

    // Approach 1: Krish's Initial HashMap Solve
    public int sumOfUniqueHashMap(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                sum += entry.getKey();
            }
        }
        return sum;
    }

    // Approach 3: Single-Pass State Transition
    public int sumOfUniqueSinglePass(int[] nums) {
        int[] freq = new int[101];
        int sum = 0;
        for (int n : nums) {
            if (freq[n] == 0) {
                sum += n;
                freq[n] = 1;
            } else if (freq[n] == 1) {
                sum -= n;
                freq[n] = 2;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        LC1748_SumOfUniqueElements solver = new LC1748_SumOfUniqueElements();

        int[] test1 = {1, 2, 3, 2};
        System.out.println("Test 1 [1,2,3,2] Expected: 4 | Output: " + solver.sumOfUnique(test1));

        int[] test2 = {1, 1, 1, 1, 1};
        System.out.println("Test 2 [1,1,1,1,1] Expected: 0 | Output: " + solver.sumOfUnique(test2));

        int[] test3 = {1, 2, 3, 4, 5};
        System.out.println("Test 3 [1,2,3,4,5] Expected: 15 | Output: " + solver.sumOfUnique(test3));
    }
}

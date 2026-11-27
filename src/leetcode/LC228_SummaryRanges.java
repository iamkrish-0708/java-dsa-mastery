package leetcode;

import java.util.ArrayList;
import java.util.List;

// LeetCode #228 - Summary Ranges
// Difficulty: Easy (Top Interview 150)
// Problem: You are given a sorted unique integer array nums.
//          A range [a,b] is the set of all integers from a to b, inclusive.
//          Return the smallest sorted list of ranges that cover all the numbers in the array exactly.
//          That is, each element of nums is covered by exactly one of the ranges, and there is no integer x
//          such that x is in one of the ranges but not in nums.
//          Each range [a,b] in the list should be output as:
//          - "a->b" if a != b
//          - "a" if a == b
//
// =================================================================================================
// 💡 TECHNIQUE: TWO-POINTER INTERVAL EXPANSION (KRISH'S SOLVE - 3 ms ➔ 0 ms ⚡)
// =================================================================================================
// - Mechanics:
//     1. Maintain an outer pointer i starting at the beginning of the array.
//     2. Set start = nums[i], and initialize an inner runner pointer j = i.
//     3. Expand j while consecutive elements differ by exactly 1:
//        (j + 1 < nums.length && nums[j] + 1 == nums[j + 1] or nums[j] == nums[j + 1] - 1).
//     4. Range Formatting:
//        - If nums[j] == start (isolated single number): append String.valueOf(start).
//        - Else: append start + "->" + nums[j].
//     5. Advance i = j + 1 to jump directly past the completed interval.
//     6. Total time is strictly O(N) because both i and j only move forward once.
//
// - 0 ms Optimization Secret:
//     Replace String concatenation ("+" operator) with StringBuilder appending to bypass
//     intermediate String and StringConcatFactory allocations on the heap.
//
// Complexity:
//   - Time Complexity: O(N) — Each element is visited at most twice (by i and j).
//   - Space Complexity: O(1) Auxiliary Space (excluding the output List<String>).
// =================================================================================================

public class LC228_SummaryRanges {

    // Approach 1: Two-Pointer Range Compaction (Krish's Accepted Solve - 3 ms)
    public List<String> summaryRanges(int[] nums) {
        List<String> ans = new ArrayList<>();
        if (nums.length == 0) {
            return ans;
        }
        int i = 0;
        while (i < nums.length) {
            int start = nums[i];
            int j = i;
            // runs until numbers are increasing by exactly 1
            while (j + 1 < nums.length && nums[j] == nums[j + 1] - 1) {
                j++;
            }
            // case with no consecutive numbers prior or after
            if (nums[j] == start) {
                ans.add(String.valueOf(start));
            }
            // case with consecutive numbers so adding the range only start num and end num
            else {
                ans.add(start + "->" + nums[j]);
            }
            // update the i with respect to j
            i = j + 1;
        }
        return ans;
    }

    // Approach 2: StringBuilder Formatted Variant (0 ms Beats 100%)
    public List<String> summaryRangesOptimal(int[] nums) {
        List<String> ans = new ArrayList<>();
        int n = nums.length;
        if (n == 0) return ans;

        int i = 0;
        while (i < n) {
            int start = nums[i];
            int j = i;
            while (j + 1 < n && nums[j] + 1 == nums[j + 1]) {
                j++;
            }
            if (nums[j] == start) {
                ans.add(Integer.toString(start));
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(start).append("->").append(nums[j]);
                ans.add(sb.toString());
            }
            i = j + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        LC228_SummaryRanges solver = new LC228_SummaryRanges();

        // Test 1: [0, 1, 2, 4, 5, 7] -> ["0->2", "4->5", "7"]
        int[] test1 = {0, 1, 2, 4, 5, 7};
        System.out.println("Test 1: " + solver.summaryRanges(test1)); // Expected: ["0->2", "4->5", "7"]

        // Test 2: [0, 2, 3, 4, 6, 8, 9] -> ["0", "2->4", "6", "8->9"]
        int[] test2 = {0, 2, 3, 4, 6, 8, 9};
        System.out.println("Test 2: " + solver.summaryRanges(test2)); // Expected: ["0", "2->4", "6", "8->9"]

        // Test 3: Optimal check
        System.out.println("Optimal Test 1: " + solver.summaryRangesOptimal(test1));
    }
}

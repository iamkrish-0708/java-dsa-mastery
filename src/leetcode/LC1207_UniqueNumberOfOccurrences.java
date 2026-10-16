package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * LeetCode #1207: Unique Number of Occurrences
 * Difficulty: Easy (Top Interview 150 / LeetCode 75)
 * Link: https://leetcode.com/problems/unique-number-of-occurrences/
 *
 * Approach: HashMap Frequency Counter + HashSet Uniqueness Guard
 * - Pass 1: Count frequency of each integer in `arr` using a HashMap.
 * - Pass 2: Iterate through map frequencies (`entry.getValue()`) and insert into a HashSet.
 * - Key Idiom: `!set.add(val)` returns false immediately if the frequency was already seen.
 * - If any duplicate frequency is detected, return false; otherwise return true.
 *
 * Time Complexity: O(N) - Linear pass through array + unique keys
 * Space Complexity: O(N) - Storage for frequency map and set
 */
public class LC1207_UniqueNumberOfOccurrences {

    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> occurenceCount = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (occurenceCount.containsKey(arr[i])) {
                occurenceCount.put(arr[i], occurenceCount.get(arr[i]) + 1);
            } else {
                occurenceCount.put(arr[i], 1);
            }
        }
        Set<Integer> temp = new HashSet<>();
        for (Map.Entry<Integer, Integer> entry : occurenceCount.entrySet()) {
            if (!temp.add(entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LC1207_UniqueNumberOfOccurrences solver = new LC1207_UniqueNumberOfOccurrences();

        // Test Case 1: All unique occurrence counts
        // 1 occurs 3 times, 2 occurs 2 times, 3 occurs 1 time -> counts: [3, 2, 1] (all unique)
        int[] arr1 = {1, 2, 2, 1, 1, 3};
        System.out.println("Test 1: " + solver.uniqueOccurrences(arr1) + " | Expected: true");

        // Test Case 2: Duplicate occurrence counts
        // 1 occurs 1 time, 2 occurs 1 time -> counts: [1, 1] (not unique)
        int[] arr2 = {1, 2};
        System.out.println("Test 2: " + solver.uniqueOccurrences(arr2) + " | Expected: false");

        // Test Case 3: Negative numbers
        // -3 occurs 3 times, 0 occurs 2 times, 1 occurs 4 times, ...
        int[] arr3 = {-3, 0, 1, -3, 1, 1, 1, -3, 10, 0};
        System.out.println("Test 3: " + solver.uniqueOccurrences(arr3) + " | Expected: true");
    }
}

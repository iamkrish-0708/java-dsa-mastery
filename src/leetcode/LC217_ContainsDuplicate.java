package leetcode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * LeetCode #217: Contains Duplicate
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/contains-duplicate/
 */
public class LC217_ContainsDuplicate {

    // Approach 1: Using HashMap (Frequency / Existence Check)
    // Time Complexity: O(N)
    // Space Complexity: O(N)
    public boolean containsDuplicateHashMap(int[] nums) {
        HashMap<Integer, Integer> duplicateChecker = new HashMap<>();
        for (int num : nums) {
            if (duplicateChecker.containsKey(num)) {
                return true;
            }
            duplicateChecker.put(num, 0);
        }
        return false;
    }

    // Approach 2: Using HashSet (Optimal & Clean)
    // Time Complexity: O(N)
    // Space Complexity: O(N)
    public boolean containsDuplicateHashSet(int[] nums) {
        HashSet<Integer> duplicateChecker = new HashSet<>();
        for (int num : nums) {
            // .add() returns false if element already exists in the set
            if (!duplicateChecker.add(num)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LC217_ContainsDuplicate solver = new LC217_ContainsDuplicate();

        int[] test1 = {1, 2, 3, 1};
        int[] test2 = {1, 2, 3, 4};
        int[] test3 = {1, 1, 1, 3, 3, 4, 3, 2, 4, 2};

        System.out.println("Test 1 (Expected: true)  -> " + solver.containsDuplicateHashSet(test1));
        System.out.println("Test 2 (Expected: false) -> " + solver.containsDuplicateHashSet(test2));
        System.out.println("Test 3 (Expected: true)  -> " + solver.containsDuplicateHashSet(test3));
    }
}

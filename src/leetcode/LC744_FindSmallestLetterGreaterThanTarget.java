package leetcode;

// LeetCode #744 - Find Smallest Letter Greater Than Target
// Difficulty: Easy
// Problem: Given an array of characters letters that is sorted in non-decreasing order,
//          and a character target, return the smallest character in letters that is
//          strictly greater than target. If no such character exists, return letters[0].
// Approach: Binary Search (Upper Bound Pattern)
// Key Mechanics:
//   - Initialize ans = letters[0] to handle wrap-around by default if no strictly greater char is found.
//   - If letters[mid] > target, this could be our answer: record ans = letters[mid] and search left (high = mid - 1).
//   - Otherwise (letters[mid] <= target), search right (low = mid + 1).
// Complexity: O(log N) Time, O(1) Auxiliary Space
// Problem Link: https://leetcode.com/problems/find-smallest-letter-greater-than-target/

public class LC744_FindSmallestLetterGreaterThanTarget {
    public char nextGreatestLetter(char[] letters, char target) {
        int low = 0;
        int high = letters.length - 1;
        char ans = letters[0];
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (letters[mid] > target) {
                ans = letters[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
}

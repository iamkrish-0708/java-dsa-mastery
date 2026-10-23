package leetcode;

// LeetCode #1910 - Remove All Occurrences of a Substring
// Difficulty: Medium
// Problem: Given two strings s and part, repeatedly find the leftmost occurrence of part
//          in s and remove it. Return s after all occurrences of part have been removed.
// Approach: Simulate with while loop using contains() + indexOf() + substring()
// Complexity: O(N * (N/M)) Time (worst case re-scan after each removal), O(N) Space
// Problem Link: https://leetcode.com/problems/remove-all-occurrences-of-a-substring/

public class LC1910_RemoveAllOccurrencesOfASubstring {
    public String removeOccurrences(String s, String part) {
        while (s.contains(part)) {
            int index = s.indexOf(part);
            s = s.substring(0, index) + s.substring(index + part.length());
        }
        return s;
    }
}

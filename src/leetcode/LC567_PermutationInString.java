package leetcode;

import java.util.Arrays;

// LeetCode #567 - Permutation in String
// Difficulty: Medium
// Problem: Given two strings s1 and s2, return true if s2 contains a permutation of s1.
//          In other words, return true if one of s1's permutations is a substring of s2.
// Approach: Sliding Window of fixed size (s1.length()) with frequency arrays
// Key Insight: A permutation of s1 = same char frequencies as s1 in any window of size a.
//              Slide window across s2: add incoming char freq, remove outgoing char freq.
//              Compare 26-length freq arrays using Arrays.equals() after each slide.
// Complexity: O(N) Time (26-element comparison is O(1)), O(1) Space (fixed 26-size arrays)
// Problem Link: https://leetcode.com/problems/permutation-in-string/

public class LC567_PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        int a = s1.length();
        int b = s2.length();
        if (a > b) {
            return false;
        }
        int[] freq1 = new int[26];
        int[] freq2 = new int[26];
        for (int i = 0; i < a; i++) {
            freq1[s1.charAt(i) - 'a']++;
            // first window of size a in s2
            freq2[s2.charAt(i) - 'a']++;
        }
        // check first window
        if (Arrays.equals(freq1, freq2)) {
            return true;
        }
        for (int i = a; i < b; i++) {
            freq2[s2.charAt(i) - 'a']++;           // add new char (right edge)
            freq2[s2.charAt(i - a) - 'a']--;       // remove old char (left edge slides out)
            if (Arrays.equals(freq1, freq2)) {
                return true;
            }
        }
        return false;
    }
}

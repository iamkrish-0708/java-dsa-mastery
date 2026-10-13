package leetcode;

/**
 * LeetCode #392: Is Subsequence
 * Difficulty: Easy (Top Interview 150 / Blind 75)
 * Link: https://leetcode.com/problems/is-subsequence/
 *
 * Problem Statement:
 * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
 * A subsequence is formed by deleting some (or no) characters without changing the relative order.
 *
 * Approaches Authored by Krish:
 * - Approach 1: Early-Exit Two-Pointer with s.isEmpty() upfront guard.
 * - Approach 2: Clean Idiomatic Two-Pointer checking (i == s.length()) at the end.
 *
 * Time Complexity: O(|t|) - Single linear pass through string t
 * Space Complexity: O(1) - Pure pointer primitives
 */
public class LC392_IsSubsequence {

    // Approach 1: Early-Exit Two-Pointer Traversal
    public boolean isSubsequenceEarlyExit(String s, String t) {
        if (s.isEmpty()) {
            return true;
        }
        int i = 0;
        int j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                if (i > s.length() - 1) {
                    return true;
                }
            }
            j++;
        }
        return false;
    }

    // Approach 2: Clean Idiomatic Two-Pointer Traversal (Recommended)
    public boolean isSubsequence(String s, String t) {
        int i = 0;
        int j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == s.length();
    }

    public static void main(String[] args) {
        LC392_IsSubsequence solver = new LC392_IsSubsequence();

        // Test Case 1: Standard valid subsequence
        String s1 = "abc", t1 = "ahbgdc";
        System.out.println("Test 1: " + solver.isSubsequence(s1, t1) + " | Expected: true");

        // Test Case 2: Invalid subsequence
        String s2 = "axc", t2 = "ahbgdc";
        System.out.println("Test 2: " + solver.isSubsequence(s2, t2) + " | Expected: false");

        // Test Case 3: Empty string s
        String s3 = "", t3 = "ahbgdc";
        System.out.println("Test 3: " + solver.isSubsequence(s3, t3) + " | Expected: true");

        // Test Case 4: Both empty strings
        String s4 = "", t4 = "";
        System.out.println("Test 4: " + solver.isSubsequence(s4, t4) + " | Expected: true");
    }
}

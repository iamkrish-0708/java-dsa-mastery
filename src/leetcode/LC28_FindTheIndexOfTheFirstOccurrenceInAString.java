package leetcode;

/**
 * LeetCode #28: Find the Index of the First Occurrence in a String
 * Difficulty: Easy (Top Interview 150 / Blind 75)
 * Link: https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/
 *
 * Also maps directly to:
 * - Mumbai University AOA Lab Experiment 9: Naive String Matching Algorithm
 *
 * Approach: Two-Pointer Traversal with Explicit Backtracking on Mismatch
 * - Maintain pointer `ptrH` for haystack and `ptrN` for needle.
 * - If characters match, increment both pointers.
 * - On mismatch, backtrack `ptrH` to `(ptrH - ptrN + 1)` (the next starting character in haystack)
 *   and reset `ptrN = 0` to prevent skipping overlapping partial matches (e.g. "mississippi" & "issip").
 * - If `ptrN == needle.length()`, the match began at `ptrH - needle.length()`.
 *
 * Time Complexity: O((N - M + 1) * M) worst-case, O(N) average
 * Space Complexity: O(1) - Pure pointer primitives
 */
public class LC28_FindTheIndexOfTheFirstOccurrenceInAString {

    public int strStr(String haystack, String needle) {
        int ptrN = 0;
        int ptrH = 0;
        while (ptrN < needle.length() && ptrH < haystack.length()) {
            if (needle.charAt(ptrN) == haystack.charAt(ptrH)) {
                ptrN++;
                ptrH++;
            } else {
                ptrH = ptrH - ptrN + 1; // Backtrack to next starting character
                ptrN = 0;
            }
        }
        if (ptrN == needle.length()) {
            return ptrH - needle.length();
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        LC28_FindTheIndexOfTheFirstOccurrenceInAString solver = new LC28_FindTheIndexOfTheFirstOccurrenceInAString();

        // Test Case 1: Standard match at start
        String h1 = "sadbutsad", n1 = "sad";
        System.out.println("Test 1: " + solver.strStr(h1, n1) + " | Expected: 0");

        // Test Case 2: No match
        String h2 = "leetcode", n2 = "leeto";
        System.out.println("Test 2: " + solver.strStr(h2, n2) + " | Expected: -1");

        // Test Case 3: Overlapping partial match (The Backtracking Test)
        String h3 = "mississippi", n3 = "issip";
        System.out.println("Test 3: " + solver.strStr(h3, n3) + " | Expected: 4");

        // Test Case 4: Single char match
        String h4 = "a", n4 = "a";
        System.out.println("Test 4: " + solver.strStr(h4, n4) + " | Expected: 0");
    }
}

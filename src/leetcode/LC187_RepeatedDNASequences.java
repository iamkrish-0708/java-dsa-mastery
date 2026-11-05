package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// LeetCode #187 - Repeated DNA Sequences
// Difficulty: Medium
// Problem: The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.
//          Given a string s that represents a DNA sequence, return all the 10-letter-long sequences (substrings)
//          that occur more than once in a DNA molecule. You may return the answer in any order.
//
// =================================================================================================
// 🔍 EVOLUTION OF SOLUTIONS & COMPLEXITY ANALYSIS:
// =================================================================================================
//
// Approach 1 (Krish's First Independent Solve - 2301 ms):
//   - Sliced 10-length substrings using s.substring(i, i + 10).
//   - Stored first-time seen strings in Set<String> test.
//   - When duplicate found (!test.add(str)), checked if already in List<String> ans via ans.contains(str).
//   - Performance Bottleneck: ans.contains(str) on an ArrayList is O(K) linear scan per iteration,
//     causing an O(N * K) quadratic slowdown (2301 ms)!
//
// Approach 2 (Two-HashSet Optimization - ~18 ms Beats ~80% ⚡):
//   - Replaces the ArrayList duplicate check with a second HashSet: Set<String> repeated.
//   - Set.add() operates in O(1) average time and naturally rejects duplicates.
//   - If !seen.add(str) -> repeated.add(str). Both operations are O(1)!
//   - Converts repeated set to new ArrayList<>(repeated) at the very end in O(K) time.
//
// Complexity:
//   - Time: O(N * L) where L = 10 (length of substring) -> O(N) linear time!
//   - Space: O(N * L) to store unique 10-letter substrings in HashSets.
// Problem Link: https://leetcode.com/problems/repeated-dna-sequences/

public class LC187_RepeatedDNASequences {

    // Approach 2: Two-HashSet O(1) Lookup (18 ms ⚡)
    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> seen = new HashSet<>();
        Set<String> repeated = new HashSet<>();

        for (int i = 0; i + 10 <= s.length(); i++) {
            String str = s.substring(i, i + 10);
            if (!seen.add(str)) {
                repeated.add(str);
            }
        }

        return new ArrayList<>(repeated);
    }

    // Approach 1: Krish's Initial Independent Solve (2301 ms)
    public List<String> findRepeatedDnaSequencesInitial(String s) {
        List<String> ans = new ArrayList<>();
        Set<String> test = new HashSet<>();
        for (int i = 0; (i + 10) <= s.length(); i++) {
            String str = s.substring(i, i + 10);
            if (!test.add(str)) {
                if (!ans.contains(str)) {
                    ans.add(str);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        LC187_RepeatedDNASequences solver = new LC187_RepeatedDNASequences();
        String s1 = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        System.out.println("Result 1: " + solver.findRepeatedDnaSequences(s1));
        // Expected: ["AAAAACCCCC", "CCCCCAAAAA"]

        String s2 = "AAAAAAAAAAAAA";
        System.out.println("Result 2: " + solver.findRepeatedDnaSequences(s2));
        // Expected: ["AAAAAAAAAA"]
    }
}

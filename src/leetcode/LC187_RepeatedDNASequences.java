package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// LeetCode #187 - Repeated DNA Sequences
// Difficulty: Medium
// Problem: Given a string s that represents a DNA sequence, return all the 10-letter-long sequences (substrings)
//          that occur more than once in a DNA molecule. You may return the answer in any order.
//
// =================================================================================================
// 🚨 CRITICAL MISTAKE & PERFORMANCE LESSON DOCUMENTED:
// =================================================================================================
//
// ❌ MISTAKE 1: Using ArrayList.contains() Inside a Loop (2301 ms - Near TLE)
//   Code written:
//     if (!test.add(str)) {
//         if (!ans.contains(str)) {  // <-- DANGEROUS BOTTLENECK!
//             ans.add(str);
//         }
//     }
//   WHY THIS IS A CATASTROPHIC MISTAKE:
//   - 'ans' is an ArrayList.
//   - ArrayList.contains(str) performs an O(K) linear scan through all collected duplicates!
//   - When the string has length 100,000, calling an O(K) linear scan on every single iteration
//     turns what should be an O(N) algorithm into an O(N * K) quadratic disaster!
//   - Result: 2301 ms runtime and massive CPU spinning.
//
// ✅ FIX: Replace List duplicate check with a second HashSet (Set<String> ans) (527 ms)
//   - HashSet.add() operates in O(1) average time and naturally rejects duplicates.
//   - Drops runtime from 2301 ms -> 527 ms (over 4.3x speedup!).
//
// ⚠️ MISTAKE 2 / LEARNING: String Heap Allocations (Why it is 527 ms & 272 MB instead of 18 ms)
//   - In Java 7+, s.substring(i, i + 10) allocates a BRAND NEW String object on the heap on EVERY step.
//   - A 100,000-char string creates ~99,991 separate String objects!
//   - 100,000 heap objects consume ~272 MB of RAM and force the Java Garbage Collector (GC)
//     to constantly pause execution to clean up unreferenced strings.
//   - (Note: Top 1% 18ms solutions encode 10-char DNA into a 20-bit integer since each char
//     A, C, G, T needs only 2 bits, completely eliminating String heap allocations).
//   - For SDE-1 / Internship interviews, the Two-HashSet O(N) solution below is 100% expected & standard!
// =================================================================================================

public class LC187_RepeatedDNASequences {

    // Approach 2: Clean Two-HashSet O(1) Lookup (527 ms - Standard SDE Solution)
    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> ans = new HashSet<>();
        Set<String> test = new HashSet<>();

        for (int i = 0; (i + 10) <= s.length(); i++) {
            String str = s.substring(i, i + 10);
            // If test.add(str) returns false, it has been seen before!
            if (!test.add(str)) {
                ans.add(str); // O(1) insertion, naturally ignores existing duplicates
            }
        }

        return new ArrayList<>(ans);
    }

    // Approach 1: Initial Solve (2301 ms - Contains the ArrayList.contains() Mistake)
    public List<String> findRepeatedDnaSequencesInitial(String s) {
        List<String> ans = new ArrayList<>();
        Set<String> test = new HashSet<>();
        for (int i = 0; (i + 10) <= s.length(); i++) {
            String str = s.substring(i, i + 10);
            if (!test.add(str)) {
                if (!ans.contains(str)) { // The O(K) linear search mistake!
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

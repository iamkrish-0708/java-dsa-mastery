package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode #242: Valid Anagram
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/valid-anagram/
 */
public class LC242_ValidAnagram {

    // Approach 1: Using Two HashMaps (Manual Entry Comparison)
    // Time Complexity: O(N)
    // Space Complexity: O(1) - At most 26 unique characters
    public boolean isAnagramHashMap(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Integer> anagramChecker1 = new HashMap<>();
        for (char ch : s.toCharArray()) {
            anagramChecker1.put(ch, anagramChecker1.getOrDefault(ch, 0) + 1);
        }

        HashMap<Character, Integer> anagramChecker2 = new HashMap<>();
        for (char ch : t.toCharArray()) {
            anagramChecker2.put(ch, anagramChecker2.getOrDefault(ch, 0) + 1);
        }

        for (Map.Entry<Character, Integer> entry : anagramChecker2.entrySet()) {
            Character key = entry.getKey();
            Integer count = entry.getValue();

            if (!anagramChecker1.containsKey(key) || !anagramChecker1.get(key).equals(count)) {
                return false;
            }
        }

        return true;
    }

    // Approach 2: Optimal Fixed Frequency Array (int[26])
    // Time Complexity: O(N) - 1ms Beats ~100%
    // Space Complexity: O(1) - Exactly 26 integers
    public boolean isAnagramOptimal(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] charCounts = new int[26];

        for (int i = 0; i < s.length(); i++) {
            charCounts[s.charAt(i) - 'a']++;
            charCounts[t.charAt(i) - 'a']--;
        }

        for (int count : charCounts) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        LC242_ValidAnagram solver = new LC242_ValidAnagram();

        String s1 = "anagram", t1 = "nagaram";
        String s2 = "rat", t2 = "car";

        System.out.println("Test 1 (Expected: true)  -> " + solver.isAnagramOptimal(s1, t1));
        System.out.println("Test 2 (Expected: false) -> " + solver.isAnagramOptimal(s2, t2));
    }
}

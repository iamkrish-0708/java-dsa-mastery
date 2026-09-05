package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode #242: Valid Anagram
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/valid-anagram/
 */
public class LC242_ValidAnagram {

    // Approach 1: Using Two HashMaps (EntrySet Comparison)
    // Time: O(N) | Space: O(1)
    public boolean isAnagramTwoMaps(String s, String t) {
        if (s.length() != t.length()) return false;

        HashMap<Character, Integer> map1 = new HashMap<>();
        for (char ch : s.toCharArray()) {
            map1.put(ch, map1.getOrDefault(ch, 0) + 1);
        }

        HashMap<Character, Integer> map2 = new HashMap<>();
        for (char ch : t.toCharArray()) {
            map2.put(ch, map2.getOrDefault(ch, 0) + 1);
        }

        return map1.equals(map2);
    }

    // Approach 2: Single-Loop Balanced HashMap (+1 for s, -1 for t)
    // Time: O(N) | Space: O(1)
    public boolean isAnagramSingleMap(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Integer> checkMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            checkMap.put(sChar, checkMap.getOrDefault(sChar, 0) + 1);
            checkMap.put(tChar, checkMap.getOrDefault(tChar, 0) - 1);
        }

        for (int count : checkMap.values()) {
            if (count != 0) return false;
        }

        return true;
    }

    // Approach 3: Optimal Fixed Frequency Array (int[26])
    // Time: O(N) (1ms - Beats ~100%) | Space: O(1)
    public boolean isAnagramOptimal(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] charCounts = new int[26];

        for (int i = 0; i < s.length(); i++) {
            charCounts[s.charAt(i) - 'a']++;
            charCounts[t.charAt(i) - 'a']--;
        }

        for (int count : charCounts) {
            if (count != 0) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        LC242_ValidAnagram solver = new LC242_ValidAnagram();

        String s1 = "anagram", t1 = "nagaram";
        String s2 = "rat", t2 = "car";

        System.out.println("Test 1 (Expected: true)  -> " + solver.isAnagramSingleMap(s1, t1));
        System.out.println("Test 2 (Expected: false) -> " + solver.isAnagramSingleMap(s2, t2));
    }
}

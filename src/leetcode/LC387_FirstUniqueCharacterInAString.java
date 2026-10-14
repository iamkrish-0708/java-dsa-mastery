package leetcode;

/**
 * LeetCode #387: First Unique Character in a String
 * Difficulty: Easy (Top Interview 150)
 * Link: https://leetcode.com/problems/first-unique-character-in-a-string/
 *
 * Approach: Two-Pass Fixed Frequency Array
 * - Pass 1: Count frequency of each character using fixed array `charFreq[26]` (ASCII offset `currChar - 'a'`).
 * - Pass 2: Traverse string from left to right; return index of first character with frequency == 1.
 * - If no character has frequency 1, return -1.
 *
 * Time Complexity: O(N) - Two linear passes over string of length N
 * Space Complexity: O(1) - Constant auxiliary space (26 integers)
 */
public class LC387_FirstUniqueCharacterInAString {

    public int firstUniqChar(String s) {
        int[] charFreq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char currChar = s.charAt(i);
            charFreq[currChar - 'a']++;
        }
        for (int j = 0; j < s.length(); j++) {
            char currChar = s.charAt(j);
            if (charFreq[currChar - 'a'] == 1) {
                return j;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        LC387_FirstUniqueCharacterInAString solver = new LC387_FirstUniqueCharacterInAString();

        // Test Case 1: First unique character at start
        String s1 = "leetcode";
        System.out.println("Test 1: \"" + s1 + "\" | Got: " + solver.firstUniqChar(s1) + " | Expected: 0");

        // Test Case 2: First unique character in middle
        String s2 = "loveleetcode";
        System.out.println("Test 2: \"" + s2 + "\" | Got: " + solver.firstUniqChar(s2) + " | Expected: 2");

        // Test Case 3: No unique character
        String s3 = "aabb";
        System.out.println("Test 3: \"" + s3 + "\" | Got: " + solver.firstUniqChar(s3) + " | Expected: -1");

        // Test Case 4: Single character string
        String s4 = "z";
        System.out.println("Test 4: \"" + s4 + "\" | Got: " + solver.firstUniqChar(s4) + " | Expected: 0");
    }
}

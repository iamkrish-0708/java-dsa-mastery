package leetcode;

/**
 * LeetCode #383: Ransom Note
 * Difficulty: Easy (Top Interview 150)
 * Link: https://leetcode.com/problems/ransom-note/
 *
 * Approach: Fixed-Size Inventory Array (int[26])
 * - Pass 1: Count character stock available in `magazine` using `alphabetCount[26]`.
 * - Pass 2: Consume characters required by `ransomNote`.
 *   - If any character has 0 remaining in stock, return false.
 *   - Otherwise, decrement stock.
 * - If all characters are successfully consumed, return true.
 *
 * Time Complexity: O(M + N) where M is magazine.length() and N is ransomNote.length()
 * Space Complexity: O(1) - Constant auxiliary storage (26 integers)
 */
public class LC383_RansomNote {

    public boolean canConstruct(String ransomNote, String magazine) {
        int[] alphabetCount = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            alphabetCount[magazine.charAt(i) - 'a']++;
        }
        for (int j = 0; j < ransomNote.length(); j++) {
            if (alphabetCount[ransomNote.charAt(j) - 'a'] == 0) {
                return false;
            } else {
                alphabetCount[ransomNote.charAt(j) - 'a']--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LC383_RansomNote solver = new LC383_RansomNote();

        // Test Case 1: Cannot construct (missing letter)
        System.out.println("Test 1: " + solver.canConstruct("a", "b") + " | Expected: false");

        // Test Case 2: Cannot construct (insufficient count)
        System.out.println("Test 2: " + solver.canConstruct("aa", "ab") + " | Expected: false");

        // Test Case 3: Can construct
        System.out.println("Test 3: " + solver.canConstruct("aa", "aab") + " | Expected: true");

        // Test Case 4: Any order works
        System.out.println("Test 4: " + solver.canConstruct("bg", "efjbdfg") + " | Expected: true");
    }
}

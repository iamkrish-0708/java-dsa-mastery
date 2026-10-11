package leetcode;

/**
 * LeetCode #151: Reverse Words in a String
 * Difficulty: Medium (Blind 75 / Top Interview 150)
 * Link: https://leetcode.com/problems/reverse-words-in-a-string/
 *
 * Approach: Two-Pointer Backwards Traversal (Right-to-Left)
 * - Traverse string backwards starting at i = s.length() - 1.
 * - Skip trailing spaces using pointer i.
 * - Find word boundaries using pointer j scanning left until a space is found.
 * - Append the extracted word `s.substring(j + 1, i + 1)` to StringBuilder.
 * - Skip intermediate spaces using pointer j.
 * - If there are more words remaining (j >= 0), append a single space ' '.
 * - Update i = j and repeat until the whole string is processed.
 *
 * Time Complexity: O(N) - Single pass over characters
 * Space Complexity: O(N) - Result StringBuilder (no extra String[] arrays allocated)
 */
public class LC151_ReverseWordsInAString {

    public String reverseWords(String s) {
        StringBuilder ans = new StringBuilder();
        int i = s.length() - 1;
        while (i >= 0) {
            while (i >= 0 && s.charAt(i) == ' ') {
                i--;
            }
            if (i < 0) {
                break;
            }
            int j = i;
            while (j >= 0 && s.charAt(j) != ' ') {
                j--;
            }
            ans.append(s.substring(j + 1, i + 1));
            while (j >= 0 && s.charAt(j) == ' ') {
                j--;
            }
            if (j >= 0) {
                ans.append(' ');
            }
            i = j;
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        LC151_ReverseWordsInAString solver = new LC151_ReverseWordsInAString();

        // Test Case 1: Standard sentence
        String s1 = "the sky is blue";
        System.out.println("Test 1: \"" + solver.reverseWords(s1) + "\" | Expected: \"blue is sky the\"");

        // Test Case 2: Leading and trailing spaces
        String s2 = "  hello world  ";
        System.out.println("Test 2: \"" + solver.reverseWords(s2) + "\" | Expected: \"world hello\"");

        // Test Case 3: Multiple spaces between words
        String s3 = "a good   example";
        System.out.println("Test 3: \"" + solver.reverseWords(s3) + "\" | Expected: \"example good a\"");
    }
}

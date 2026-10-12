package leetcode;

/**
 * LeetCode #58: Length of Last Word
 * Difficulty: Easy (Top Interview 150)
 * Link: https://leetcode.com/problems/length-of-last-word/
 *
 * Approach: Backwards Two-Pointer Traversal with Short-Circuit Boundary Guards
 * - Start pointer j at s.length() - 1.
 * - Skip trailing spaces using `while (j >= 0 && s.charAt(j) == ' ') j--;`.
 * - Count characters of the final word using `while (j >= 0 && s.charAt(j) != ' ') { j--; length++; }`.
 * - Guard `j >= 0` prevents StringIndexOutOfBoundsException when word reaches index 0.
 *
 * Time Complexity: O(N) - At most one pass over the final word
 * Space Complexity: O(1) - Auxiliary Space
 */
public class LC58_LengthOfLastWord {

    public int lengthOfLastWord(String s) {
        int j = s.length() - 1;
        int length = 0;
        while (j >= 0 && s.charAt(j) == ' ') {
            j--;
        }
        while (j >= 0 && s.charAt(j) != ' ') {
            j--;
            length++;
        }
        return length;
    }

    public static void main(String[] args) {
        LC58_LengthOfLastWord solver = new LC58_LengthOfLastWord();

        // Test Case 1: Standard string
        String s1 = "Hello World";
        System.out.println("Test 1: \"" + s1 + "\" | Got: " + solver.lengthOfLastWord(s1) + " | Expected: 5");

        // Test Case 2: Trailing and multiple spaces
        String s2 = "   fly me   to   the moon  ";
        System.out.println("Test 2: \"" + s2 + "\" | Got: " + solver.lengthOfLastWord(s2) + " | Expected: 4");

        // Test Case 3: Single word with no spaces
        String s3 = "day";
        System.out.println("Test 3: \"" + s3 + "\" | Got: " + solver.lengthOfLastWord(s3) + " | Expected: 3");

        // Test Case 4: Single character
        String s4 = "a";
        System.out.println("Test 4: \"" + s4 + "\" | Got: " + solver.lengthOfLastWord(s4) + " | Expected: 1");
    }
}

package leetcode;

import java.util.Set;

/**
 * LeetCode #345: Reverse Vowels of a String
 * Difficulty: Easy (Top Interview 150)
 * Link: https://leetcode.com/problems/reverse-vowels-of-a-string/
 *
 * Approach: Extract, Reverse, and Replace (Two-Pass with StringBuilder)
 * - Pass 1: Extract all vowels ('a', 'e', 'i', 'o', 'u' both cases) into a separate StringBuilder `temp`.
 * - Reverse the extracted vowels in `temp`.
 * - Pass 2: Iterate through the original string; replace vowels in-place using `sb.setCharAt()` from `temp`.
 *
 * Time Complexity: O(N) - Linear passes over string of length N
 * Space Complexity: O(N) - Auxiliary StringBuilder storage
 */
public class LC345_ReverseVowelsOfAString {

    public String reverseVowels(String s) {
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
        StringBuilder sb = new StringBuilder(s);
        StringBuilder temp = new StringBuilder();

        // Pass 1: Extract all vowels
        for (int i = 0; i < s.length(); i++) {
            char frontChar = s.charAt(i);
            if (vowels.contains(frontChar)) {
                temp.append(frontChar);
            }
        }

        temp.reverse();

        // Pass 2: Overwrite vowels in reverse order
        int ptr = 0;
        for (int i = 0; i < s.length(); i++) {
            if (vowels.contains(s.charAt(i))) {
                sb.setCharAt(i, temp.charAt(ptr));
                ptr++;
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        LC345_ReverseVowelsOfAString solver = new LC345_ReverseVowelsOfAString();

        // Test Case 1: Standard string with uppercase and lowercase vowels
        String s1 = "IceCreAm";
        System.out.println("Test 1: \"" + s1 + "\" | Got: \"" + solver.reverseVowels(s1) + "\" | Expected: \"AceCreIm\"");

        // Test Case 2: Lowercase vowels
        String s2 = "leetcode";
        System.out.println("Test 2: \"" + s2 + "\" | Got: \"" + solver.reverseVowels(s2) + "\" | Expected: \"leotcede\"");

        // Test Case 3: No vowels
        String s3 = "bcdfg";
        System.out.println("Test 3: \"" + s3 + "\" | Got: \"" + solver.reverseVowels(s3) + "\" | Expected: \"bcdfg\"");

        // Test Case 4: Single vowel
        String s4 = "a.";
        System.out.println("Test 4: \"" + s4 + "\" | Got: \"" + solver.reverseVowels(s4) + "\" | Expected: \"a.\"");
    }
}

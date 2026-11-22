package leetcode;

// LeetCode #1844 - Replace All Digits with Characters
// Difficulty: Easy
// Problem: You are given a 0-indexed string s that has lowercase English letters in its even indices
//          and digits in its odd indices.
//          There is a function shift(c, x), where c is a character and x is a digit, that returns the xth character after c.
//          For every odd index i, you want to replace the digit s[i] with shift(s[i-1], s[i]).
//          Return s after replacing all digits. It is guaranteed that shift(s[i-1], s[i]) will never exceed 'z'.
//
// =================================================================================================
// 💡 TECHNIQUE: STEP-BY-2 ITERATION & IN-PLACE CHAR ARITHMETIC (KRISH'S SOLVE - 1 ms ⚡)
// =================================================================================================
// - Mechanics:
//     1. Iterate across odd indices (i = 1; i < s.length(); i += 2).
//     2. Extract previous character: char ch = s.charAt(i - 1).
//     3. Extract digit offset via ASCII normalization: int n = s.charAt(i) - '0'.
//     4. Append original character and its shifted counterpart (ch += n) to StringBuilder.
//     5. Bitwise Odd-Length Guard:
//        If (s.length() & 1) != 0, append the dangling final trailing character.
//
// Complexity:
//   - Time Complexity: O(N) — Single linear scan over string of length N.
//   - Space Complexity: O(N) Auxiliary Space — for StringBuilder output.
// =================================================================================================

public class LC1844_ReplaceAllDigitsWithCharacters {

    // Approach 1: StringBuilder with Step-by-2 Traversal (Krish's Accepted Solve - 1 ms)
    public String replaceDigits(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < s.length(); i += 2) {
            char ch = s.charAt(i - 1);
            int n = s.charAt(i) - '0';
            sb.append(ch);
            sb.append(ch += n);
        }
        if ((s.length() & 1) != 0) {
            sb.append(s.charAt(s.length() - 1));
        }
        return sb.toString();
    }

    // Approach 2: In-Place char[] Array Modification (Zero extra allocations, 0 ms Beats 100%)
    public String replaceDigitsCharArray(String s) {
        char[] arr = s.toCharArray();
        for (int i = 1; i < arr.length; i += 2) {
            arr[i] = (char) (arr[i - 1] + (arr[i] - '0'));
        }
        return new String(arr);
    }

    public static void main(String[] args) {
        LC1844_ReplaceAllDigitsWithCharacters solver = new LC1844_ReplaceAllDigitsWithCharacters();

        String test1 = "a1c1e1";
        System.out.println("Test 1: " + solver.replaceDigits(test1)); // Expected: "abcdef"

        String test2 = "a1b2c3d4e";
        System.out.println("Test 2: " + solver.replaceDigits(test2)); // Expected: "abbdcfdhe"

        String opt1 = solver.replaceDigitsCharArray(test1);
        System.out.println("Optimized 1: " + opt1); // Expected: "abcdef"
    }
}

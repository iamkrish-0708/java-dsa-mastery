package leetcode;

// LeetCode #2000 - Reverse Prefix of Word
// Difficulty: Easy
// Problem: Given a 0-indexed string word and a character ch, reverse the segment of word that starts
//          at index 0 and ends at the index of the first occurrence of ch (inclusive). If the character
//          ch does not exist in word, do nothing. Return the resulting string.
//
// =================================================================================================
// 💡 TECHNIQUE: DUAL STRINGBUILDER PREFIX REVERSAL (KRISH'S SOLVE - 2 ms ⚡)
// =================================================================================================
// - Mechanics:
//     1. Use sb1 to accumulate characters up to and including the first occurrence of ch.
//     2. When ch is encountered, append it, reverse sb1 in-place, and break.
//     3. If ch is not found, sb1 never reverses, correctly preserving the original word order.
//     4. Use sb2 to accumulate the remaining suffix of word.
//     5. Concatenate and return sb1.toString() + sb2.toString().
//
// Complexity:
//   - Time Complexity: O(N) — Linear traversal over word of length N.
//   - Space Complexity: O(N) Auxiliary Space — for sb1 and sb2 buffers.
// =================================================================================================

public class LC2000_ReversePrefixOfWord {

    public String reversePrefix(String word, char ch) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        String ans = "";
        int i = 0;
        while (i < word.length()) {
            if (word.charAt(i) == ch) {
                sb1.append(ch);
                sb1.reverse();
                i++;
                break;
            }
            sb1.append(word.charAt(i));
            i++;
        }
        while (i < word.length()) {
            sb2.append(word.charAt(i));
            i++;
        }
        ans = sb1.toString() + sb2.toString();
        return ans;
    }

    // Local Test Harness
    public static void main(String[] args) {
        LC2000_ReversePrefixOfWord solver = new LC2000_ReversePrefixOfWord();

        // Test 1: "abcdefd", ch = 'd' -> reverse "abcd" -> "dcba" + "efd" -> "dcbaefd"
        System.out.println("Test 1 (\"abcdefd\", 'd'): " + solver.reversePrefix("abcdefd", 'd') + " (Expected: dcbaefd)");

        // Test 2: "xyxzxe", ch = 'z' -> reverse "xyxz" -> "zxyx" + "xe" -> "zxyxxe"
        System.out.println("Test 2 (\"xyxzxe\", 'z'): " + solver.reversePrefix("xyxzxe", 'z') + " (Expected: zxyxxe)");

        // Test 3: "abcd", ch = 'z' -> not found -> "abcd"
        System.out.println("Test 3 (\"abcd\", 'z'): " + solver.reversePrefix("abcd", 'z') + " (Expected: abcd)");
    }
}

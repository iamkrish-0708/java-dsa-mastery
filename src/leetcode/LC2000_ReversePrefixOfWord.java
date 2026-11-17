package leetcode;

// LeetCode #2000 - Reverse Prefix of Word
// Difficulty: Easy
// Problem: Given a 0-indexed string word and a character ch, reverse the segment of word that starts
//          at index 0 and ends at the index of the first occurrence of ch (inclusive). If the character
//          ch does not exist in word, do nothing. Return the resulting string.
//
// =================================================================================================
// 💡 TECHNIQUE: SINGLE STRINGBUILDER PREFIX REVERSAL & IN-PLACE SUFFIX APPEND (KRISH - 1 ms ⚡)
// =================================================================================================
// - Optimization Insight:
//     Instead of allocating two separate StringBuilders and concatenating them, we accumulate
//     the prefix into sb1, reverse it upon finding ch, and then continue appending the rest
//     of the word directly into the same sb1 buffer!
// - If ch is not found, sb1 never reverses, cleanly preserving the original string.
//
// Complexity:
//   - Time Complexity: O(N) — Single linear scan over word of length N.
//   - Space Complexity: O(N) Auxiliary Space — Only a single StringBuilder buffer.
// =================================================================================================

public class LC2000_ReversePrefixOfWord {

    // Krish's Optimized 1 ms Solution (Single StringBuilder)
    public String reversePrefix(String word, char ch) {
        StringBuilder sb1 = new StringBuilder();
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
            sb1.append(word.charAt(i));
            i++;
        }
        return sb1.toString();
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

package leetcode;

// LeetCode #443 - String Compression
// Difficulty: Medium
// Problem: Compress char[] in-place. Replace consecutive duplicates with char + count.
//          Count omitted if 1. Multi-digit counts written digit by digit. Return new length.
// Approach: Two-Pointer (Read pointer r, Write pointer w) in-place
// Key Mechanics:
//   - r scans and counts consecutive identical chars (inner while loop)
//   - w writes the char, then writes each digit of count (if count > 1)
//     via String.valueOf(count).toCharArray() to handle multi-digit counts naturally
//   - w acts as the new length at the end
// Complexity: O(N) Time, O(1) Auxiliary Space (in-place, only small String for count digits)
// Status: ⚠️ Referred teacher's solution after 45 min — to redo tomorrow
// Problem Link: https://leetcode.com/problems/string-compression/

public class LC443_StringCompression {
    public int compress(char[] chars) {
        int r = 0; // read pointer
        int w = 0; // write pointer
        while (r < chars.length) {
            char ch = chars[r];
            int count = 0;
            // count consecutive same chars
            while (r < chars.length && chars[r] == ch) {
                r++;
                count++;
            }
            // write the char
            chars[w] = ch;
            w++;
            // write count digits only if count > 1
            if (count > 1) {
                String countStr = String.valueOf(count);
                for (char c : countStr.toCharArray()) {
                    chars[w] = c;
                    w++;
                }
            }
        }
        return w;
    }
}

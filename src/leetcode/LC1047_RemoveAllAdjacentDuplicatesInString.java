package leetcode;

// LeetCode #1047 - Remove All Adjacent Duplicates In String
// Difficulty: Easy
// Problem: Given a string s, repeatedly remove adjacent duplicate characters
//          until no adjacent duplicates remain. Return the final string.
// Approach: StringBuilder as a Stack
// Key Insight: Treat StringBuilder as a stack. For each char:
//              - If stack is empty OR top != current char → push (append).
//              - If top == current char → pop (deleteCharAt last) — they cancel out.
//              This naturally handles chain reactions since after a removal,
//              the new top may match the next char.
// Complexity: O(N) Time, O(N) Space
// Problem Link: https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/

public class LC1047_RemoveAllAdjacentDuplicatesInString {
    public String removeDuplicates(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (sb.isEmpty()) {
                sb.append(ch);
            } else if (sb.charAt(sb.length() - 1) == ch) {
                sb.deleteCharAt(sb.length() - 1); // top == ch → pop (cancel pair)
            } else {
                sb.append(ch); // top != ch → push
            }
        }
        return sb.toString();
    }
}

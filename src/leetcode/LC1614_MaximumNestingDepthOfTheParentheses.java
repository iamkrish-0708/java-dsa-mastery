package leetcode;

// LeetCode #1614 - Maximum Nesting Depth of the Parentheses
// Difficulty: Easy
// Problem: Given a valid parenthesized string s, return the maximum nesting depth of parentheses.
// Approach: Single pass with a running counter
// Key Insight: Track current open-paren depth with a counter.
//              On '(' → increment, update max if new peak.
//              On ')' → decrement.
//              Running max of counter = maximum nesting depth.
// Complexity: O(N) Time, O(1) Space
// Problem Link: https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/

public class LC1614_MaximumNestingDepthOfTheParentheses {
    public int maxDepth(String s) {
        int depth = 0;  // max depth seen so far
        int count = 0;  // current open depth
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                count++;
                if (count > depth) {
                    depth = count;
                }
            } else if (ch == ')') {
                count--;
            }
        }
        return depth;
    }
}

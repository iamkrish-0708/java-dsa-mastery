package leetcode;

import java.util.ArrayList;
import java.util.List;

// LeetCode #22 - Generate Parentheses
// Difficulty: Medium (Top Interview 150 / Blind 75)
// Problem: Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
//
// =================================================================================================
// 💡 TECHNIQUE: BOUNDED BACKTRACKING STATE-SPACE TREE (KRISH'S SOLVE - 1 ms ⚡ MILESTONE #120 🏆)
// =================================================================================================
// - Invariants for Validity:
//     1. Open Parenthesis Budget: You can place '(' whenever open < n.
//     2. Close Parenthesis Validity: You can only place ')' when close < open (must match an unmatched '(').
//     3. Base Case: When open == n && close == n, all 2n characters are placed -> snapshot curr.toString().
//
// - State-Space Pruning:
//     Instead of generating all 2^(2n) binary strings and validating, pruning with (open < n) and
//     (close < open) only visits valid prefixes, exploring exactly C_n paths (n-th Catalan Number).
//
// Complexity:
//   - Time Complexity: O(4^n / sqrt(n)) bounded by the n-th Catalan number C_n = (1 / (n + 1)) * (2n choose n).
//   - Space Complexity: O(n) Auxiliary Space — O(2n) stack depth + O(2n) StringBuilder buffer.
// =================================================================================================

public class LC22_GenerateParentheses {

    static void solve(StringBuilder curr, List<String> ans, int n, int open, int close) {
        if (open == n && close == n) {
            ans.add(curr.toString());
            return;
        }

        if (open < n) {
            curr.append('(');                           // choose
            solve(curr, ans, n, open + 1, close);       // recursive call
            curr.deleteCharAt(curr.length() - 1);       // backtrack
        }

        if (close < open) {
            curr.append(')');                           // choose
            solve(curr, ans, n, open, close + 1);       // recursive call
            curr.deleteCharAt(curr.length() - 1);       // backtrack
        }
    }

    public List<String> generateParenthesis(int n) {
        StringBuilder curr = new StringBuilder();
        List<String> ans = new ArrayList<>();
        solve(curr, ans, n, 0, 0);
        return ans;
    }

    // Local Test Harness
    public static void main(String[] args) {
        LC22_GenerateParentheses solver = new LC22_GenerateParentheses();

        // Test 1: n = 3 -> 5 combinations (C_3 = 5):
        // ["((()))","(()())","(())()","()(())","()()()"]
        System.out.println("Test 1 (n=3): " + solver.generateParenthesis(3));

        // Test 2: n = 1 -> ["()"]
        System.out.println("Test 2 (n=1): " + solver.generateParenthesis(1));
    }
}

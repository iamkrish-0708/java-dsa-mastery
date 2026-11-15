package leetcode;

import java.util.ArrayList;
import java.util.List;

// LeetCode #17 - Letter Combinations of a Phone Number
// Difficulty: Medium (Top Interview 150 / Blind 75)
// Problem: Given a string containing digits from 2-9 inclusive, return all possible letter combinations
//          that the number could represent. Return the answer in any order.
//
// =================================================================================================
// 💡 TECHNIQUE: BACKTRACKING / RECURSION TREE WITH STRINGBUILDER (KRISH'S SOLVE - 0 ms ⚡ BEATS 100%)
// =================================================================================================
// - Pattern: State-space tree exploration based on phone keypad mapping.
// - Mechanics:
//     1. Lookup Array: mapping[value] stores the characters mapped to that digit.
//     2. Base Case: When index >= digits.length(), curr contains a full combination.
//        Snapshot curr.toString() into ans and return.
//     3. Backtracking Symmetry (Choose - Explore - Unchoose):
//        - Choose: curr.append(mappedString.charAt(i))
//        - Explore: solve(digits, index + 1, ans, mapping, curr)
//        - Unchoose: curr.deleteCharAt(curr.length() - 1)
//     4. Performance: Using a mutable StringBuilder avoids String allocations on each recursive frame,
//        achieving optimal 0 ms runtime.
//
// Complexity:
//   - Time Complexity: O(4^N * N) where N is digits.length(). In worst case (digits 7 or 9),
//     each has 4 choices, giving 4^N leaf combinations, each taking O(N) to convert to String.
//   - Space Complexity: O(N) Auxiliary Space — O(N) recursion stack depth + O(N) StringBuilder buffer.
// =================================================================================================

public class LC17_LetterCombinationsOfAPhoneNumber {

    static void solve(String digits, int index, List<String> ans, String[] mapping, StringBuilder curr) {
        // base case 
        if (index >= digits.length()) {
            ans.add(curr.toString());
            return;
        }
        // getting the digit from a string
        int value = digits.charAt(index) - '0';
        // getting the characters available for particular digit
        String mappedString = mapping[value];
        for (int i = 0; i < mappedString.length(); i++) {
            // initial character selection
            curr.append(mappedString.charAt(i));

            // recursive call
            solve(digits, index + 1, ans, mapping, curr);

            // backtracking
            curr.deleteCharAt(curr.length() - 1);
        }
    }

    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        String[] mapping = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        StringBuilder curr = new StringBuilder();
        solve(digits, 0, ans, mapping, curr);
        return ans;
    }

    // Local Test Harness
    public static void main(String[] args) {
        LC17_LetterCombinationsOfAPhoneNumber solver = new LC17_LetterCombinationsOfAPhoneNumber();

        // Test 1: "23" -> ["ad","ae","af","bd","be","bf","cd","ce","cf"]
        System.out.println("Test 1 (\"23\"): " + solver.letterCombinations("23"));

        // Test 2: "2" -> ["a","b","c"]
        System.out.println("Test 2 (\"2\"): " + solver.letterCombinations("2"));
    }
}

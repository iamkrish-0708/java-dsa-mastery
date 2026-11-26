package leetcode;

import java.util.Map;

// LeetCode #13 - Roman to Integer
// Difficulty: Easy (Top Interview 150)
// Problem: Given a roman numeral, convert it to an integer.
//          Roman numerals are represented by seven different symbols: I, V, X, L, C, D, and M.
//          Symbol       Value
//          I             1
//          V             5
//          X             10
//          L             50
//          C             100
//          D             500
//          M             1000
//          Subtraction Rule: If a smaller value appears before a larger value (e.g. IV, IX, XL, XC, CD, CM),
//          the smaller value is subtracted rather than added.
//
// =================================================================================================
// 💡 TECHNIQUE: VALUE COMPARISON & TWO-STAGE OPTIMIZATION (KRISH'S SOLVE: 9 ms ➔ 6 ms ➔ 2 ms ⚡)
// =================================================================================================
// - Mechanics & Evolution:
//     1. Universal Roman Invariant:
//        Compare the current numeral value with the next numeral value:
//        - If curr < next: smaller precedes larger -> subtract curr (ans -= curr).
//        - Else: curr >= next (or last numeral) -> add curr (ans += curr).
//     2. Approach 1 (Map.of — 9 ms):
//        Clean and readable using Java Map, but incurs heap autoboxing and hash bucket lookup taxes.
//     3. Approach 2 (Switch Statement — 6 ms):
//        Replaced Map with a static switch function that maps char to primitive int at native speed.
//     4. Approach 3 (Backward Traversal with prev Tracker — 2 ms Beats 100%):
//        Traversing right-to-left requires only ONE switch call per character, completely
//        eliminating lookahead boundary checks (i + 1 < s.length()).
//
// Complexity:
//   - Time Complexity: O(N) — Single linear scan over string of length N.
//   - Space Complexity: O(1) Auxiliary Space across all approaches.
// =================================================================================================

public class LC13_RomanToInteger {

    // Helper: Primitive char to int mapping via fast bytecode switch
    static int value(char c) {
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }

    // Approach 1: Map-based lookup (Krish's Initial Solve - 9 ms)
    public int romanToIntMap(String s) {
        Map<Character, Integer> romanToInt = Map.of('I', 1, 'V', 5, 'X', 10, 'L', 50, 'C', 100, 'D', 500, 'M', 1000);
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if ((i + 1) < s.length() && romanToInt.get(ch) < romanToInt.get(s.charAt(i + 1))) {
                ans -= romanToInt.get(ch);
            } else {
                ans += romanToInt.get(ch);
            }
        }
        return ans;
    }

    // Approach 2: Switch-based forward lookup (Krish's Optimized Solve - 6 ms)
    public int romanToInt(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int curr = value(s.charAt(i));
            if ((i + 1) < s.length() && curr < value(s.charAt(i + 1))) {
                ans -= curr;
            } else {
                ans += curr;
            }
        }
        return ans;
    }

    // Approach 3: Backward traversal with single-pass prev tracker (2 ms Beats 100%)
    public int romanToIntBackward(String s) {
        int ans = 0;
        int prev = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            int curr = value(s.charAt(i));
            if (curr < prev) {
                ans -= curr;
            } else {
                ans += curr;
            }
            prev = curr;
        }
        return ans;
    }

    public static void main(String[] args) {
        LC13_RomanToInteger solver = new LC13_RomanToInteger();

        // Test 1: "III" -> 3
        System.out.println("Test 1 (\"III\"): " + solver.romanToInt("III") + " (Expected: 3)");

        // Test 2: "LVIII" -> 58 (L = 50, V = 5, III = 3)
        System.out.println("Test 2 (\"LVIII\"): " + solver.romanToInt("LVIII") + " (Expected: 58)");

        // Test 3: "MCMXCIV" -> 1994 (M = 1000, CM = 900, XC = 90, IV = 4)
        System.out.println("Test 3 (\"MCMXCIV\"): " + solver.romanToInt("MCMXCIV") + " (Expected: 1994)");
        System.out.println("Backward Test (\"MCMXCIV\"): " + solver.romanToIntBackward("MCMXCIV") + " (Expected: 1994)");
    }
}

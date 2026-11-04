package leetcode;

import java.util.ArrayList;
import java.util.List;

// LeetCode #412 - Fizz Buzz
// Difficulty: Easy (Classic Interview Screening Problem)
// Problem: Given an integer n, return a string array answer (1-indexed).
//
// =================================================================================================
// 🔍 EVOLUTION OF SOLUTIONS & INTERVIEW EXTENSIBILITY:
// =================================================================================================
//
// Approach 1 (Standard If-Else Chain):
//   - Checks (i % 3 == 0 && i % 5 == 0) first, then (i % 3 == 0), then (i % 5 == 0).
//   - Limitation: Hard to scale! Adding more divisors (e.g., 7 -> "Jazz", 11 -> "Razz") causes an
//     exponential combinatorial explosion of 2^K else-if branches.
//
// Approach 2 (String Concatenation / Modular Extensibility — Senior Interview Standard ⚡):
//   - Uses StringBuilder to compose the string incrementally:
//       * if (i % 3 == 0) sb.append("Fizz");
//       * if (i % 5 == 0) sb.append("Buzz");
//   - If i = 15, both conditions trigger automatically yielding "FizzBuzz" with ZERO combined checks!
//   - If sb.isEmpty(), neither matched -> append i directly.
//   - Scalable: Adding "Jazz" (7) or "Razz" (11) requires only 1 additional line, not 10 new else-if blocks!
//
// Complexity: O(N) Time, O(1) Auxiliary Space
// Problem Link: https://leetcode.com/problems/fizz-buzz/

public class LC412_FizzBuzz {

    // Approach 2: Extensible String Concatenation Pattern
    public List<String> fizzBuzz(int n) {
        List<String> ans = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            if ((i % 3) == 0) {
                sb.append("Fizz");
            }
            if ((i % 5) == 0) {
                sb.append("Buzz");
            }
            if (sb.isEmpty()) {
                sb.append(i);
            }
            ans.add(sb.toString());
        }
        return ans;
    }

    // Approach 1: Initial Priority Divisibility Checks
    public List<String> fizzBuzzIfElse(int n) {
        List<String> ans = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if ((i % 3) == 0 && (i % 5) == 0) {
                ans.add("FizzBuzz");
            } else if ((i % 3) == 0) {
                ans.add("Fizz");
            } else if ((i % 5) == 0) {
                ans.add("Buzz");
            } else {
                ans.add(String.valueOf(i));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        LC412_FizzBuzz solver = new LC412_FizzBuzz();
        System.out.println("n = 3: " + solver.fizzBuzz(3));   // ["1", "2", "Fizz"]
        System.out.println("n = 5: " + solver.fizzBuzz(5));   // ["1", "2", "Fizz", "4", "Buzz"]
        System.out.println("n = 15: " + solver.fizzBuzz(15)); // ends with "FizzBuzz"
    }
}

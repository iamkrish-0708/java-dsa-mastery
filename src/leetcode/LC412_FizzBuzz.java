package leetcode;

import java.util.ArrayList;
import java.util.List;

// LeetCode #412 - Fizz Buzz
// Difficulty: Easy (Classic Interview Screening Problem)
// Problem: Given an integer n, return a string array answer (1-indexed) where:
//          - answer[i] == "FizzBuzz" if i is divisible by 3 and 5.
//          - answer[i] == "Fizz" if i is divisible by 3.
//          - answer[i] == "Buzz" if i is divisible by 5.
//          - answer[i] == i (as a string) if none of the above conditions are true.
// Approach: Linear Simulation with Priority Divisibility Checks
// Key Mechanics:
//   - Checks the combined condition (i % 3 == 0 && i % 5 == 0) first to prevent
//     prematurely matching single divisibility branches.
//   - Uses String.valueOf(i) for safe, efficient integer-to-string conversion.
// Complexity: O(N) Time (single pass from 1 to n), O(1) Auxiliary Space (excluding output list)
// Problem Link: https://leetcode.com/problems/fizz-buzz/

public class LC412_FizzBuzz {

    public List<String> fizzBuzz(int n) {
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

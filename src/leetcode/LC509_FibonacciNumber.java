package leetcode;

// LeetCode #509 - Fibonacci Number
// Difficulty: Easy
// Problem: Return the nth Fibonacci number. F(0)=0, F(1)=1, F(n)=F(n-1)+F(n-2)
// Approach: Plain Recursion (two base cases, direct definition)
// Key Mechanics: Directly encodes the mathematical definition as code.
//               Base cases: n==0 → 0, n==1 → 1
//               Recursive case: fib(n-1) + fib(n-2)
// Complexity: O(2^N) Time (each call branches into 2 sub-calls — exponential tree),
//             O(N) Space (max call stack depth = N)
// Note: ⚠️ Not optimized — overlapping subproblems are recomputed.
//       fib(5) computes fib(3) twice, fib(2) three times, etc.
//       Optimization path: Memoization (top-down DP) → O(N) time
//                          Tabulation (bottom-up DP) → O(N) time, O(1) space
//       This version is the correct starting point for understanding recursion first.
// Problem Link: https://leetcode.com/problems/fibonacci-number/

public class LC509_FibonacciNumber {
    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int ans = fib(n - 1) + fib(n - 2);
        return ans;
    }
}

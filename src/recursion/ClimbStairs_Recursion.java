package recursion;

// LeetCode #70 - Climbing Stairs (Recursion Lecture Concept)
// Problem: You are climbing a staircase. It takes n steps to reach the top.
//          Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
//
// Approach 1 (Teacher's Pure Recursive Formulation):
//   To reach step n, you must have come from either:
//     - step (n - 1) taking a 1-step leap, OR
//     - step (n - 2) taking a 2-step leap.
//   Total distinct ways to reach n = ways(n - 1) + ways(n - 2).
//   Base cases:
//     - n == 1 -> 1 way  ( [1] )
//     - n == 2 -> 2 ways ( [1,1], [2] )
//
// Note: This naive recursive approach has O(2^N) time complexity and hits TLE on LeetCode for large n
//       due to overlapping subproblems (identical to naive Fibonacci).
//       Dynamic Programming (Memoization / Tabulation) optimizes this to O(N) time and O(1) space.
//
// Complexity (Pure Recursion):
//   - Time Complexity: O(2^N)
//   - Space Complexity: O(N) (maximum depth of call stack)

public class ClimbStairs_Recursion {

    // Pure Recursive Solution (Conceptual Foundation)
    public static int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int ans = climbStairs(n - 1) + climbStairs(n - 2);
        return ans;
    }

    public static void main(String[] args) {
        System.out.println("Ways to climb 1 stair: " + climbStairs(1)); // 1
        System.out.println("Ways to climb 2 stairs: " + climbStairs(2)); // 2
        System.out.println("Ways to climb 3 stairs: " + climbStairs(3)); // 3
        System.out.println("Ways to climb 4 stairs: " + climbStairs(4)); // 5
        System.out.println("Ways to climb 5 stairs: " + climbStairs(5)); // 8
    }
}

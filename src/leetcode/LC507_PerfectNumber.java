package leetcode;

// LeetCode #507 - Perfect Number
// Difficulty: Easy
// Problem: A perfect number is a positive integer that is equal to the sum of its positive
//          divisors, excluding the number itself. Given an integer num, return true if num is
//          a perfect number, otherwise return false.
//
// =================================================================================================
// 🔍 EVOLUTION OF SOLUTIONS & COMPLEXITY ANALYSIS:
// =================================================================================================
//
// Approach 1 (Krish's Initial Raw Solve - 1500 ms):
//   - Linearly decremented div from (num - 1) down to 1.
//   - Checked (num % div == 0) on every step.
//   - Separate branches for even and odd numbers (even checks every number, odd steps by 2).
//   - Complexity: O(N) linear time -> 1500 ms on LeetCode test suite!
//
// Approach 2 (Optimal O(sqrt(N)) Divisor Pairing - 1 ms Beats ~98% ⚡):
//   - Mathematical Insight: Divisors always come in pairs (i and num / i).
//     If i divides num, then (num / i) is also a divisor!
//   - Therefore, we only need to check up to sqrt(num), i.e., i * i <= num.
//   - 1 is universally a divisor (excluding num itself, initialized sum = 1).
//   - Edge Case 1: If num <= 1, return false immediately (sum of proper divisors of 1 is 0 != 1).
//   - Edge Case 2: When num is a perfect square (e.g. i = 6 for num = 36), i == num / i.
//     Guarded with if (i != num / i) to avoid adding the square root twice!
//
// Complexity:
//   - Time: O(sqrt(N)) -> At most 10,000 operations for 10^8 (1 ms) vs 100,000,000 in O(N).
//   - Space: O(1) Auxiliary Space.
// Problem Link: https://leetcode.com/problems/perfect-number/

public class LC507_PerfectNumber {

    // Approach 2: Optimal O(sqrt(N)) Divisor Pairing (1 ms ⚡)
    public boolean checkPerfectNumber(int num) {
        if (num <= 1) {
            return false;
        }

        int sum = 1; // 1 is always a proper divisor (excluding num itself)

        // Loop only up to sqrt(num)
        for (int i = 2; i * i <= num; i++) {
            if ((num % i) == 0) {
                sum += i;
                // Add partner divisor, avoiding duplicates on perfect squares (e.g. 6 * 6 = 36)
                if (i != num / i) {
                    sum += num / i;
                }
            }
        }

        return sum == num;
    }

    // Approach 1: Krish's Initial Raw O(N) Linear Search (1500 ms)
    static boolean even(int num, int sum, int div) {
        while (div >= 1) {
            if ((num % div) == 0) {
                sum += div;
            }
            div--;
        }
        return sum == num;
    }

    static boolean odd(int num, int sum, int div) {
        while (div >= 1) {
            if ((num % div) == 0) {
                sum += div;
            }
            div = div - 2;
        }
        return sum == num;
    }

    public boolean checkPerfectNumberInitial(int num) {
        if ((num & 1) == 1) {
            return odd(num, 0, num - 2);
        }
        return even(num, 0, num - 1);
    }

    public static void main(String[] args) {
        LC507_PerfectNumber solver = new LC507_PerfectNumber();
        System.out.println("28 is perfect? " + solver.checkPerfectNumber(28)); // Expected: true (1+2+4+7+14 = 28)
        System.out.println("7 is perfect? " + solver.checkPerfectNumber(7));   // Expected: false (1 != 7)
        System.out.println("6 is perfect? " + solver.checkPerfectNumber(6));   // Expected: true (1+2+3 = 6)
        System.out.println("1 is perfect? " + solver.checkPerfectNumber(1));   // Expected: false
    }
}

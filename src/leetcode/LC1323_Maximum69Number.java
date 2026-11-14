package leetcode;

import java.util.ArrayList;
import java.util.Collections;

// LeetCode #1323 - Maximum 69 Number
// Difficulty: Easy
// Problem: You are given a positive integer num consisting only of digits 6 and 9.
//          Return the maximum number you can get by changing at most one digit (6 becomes 9, and 9 becomes 6).
//
// =================================================================================================
// 💡 TECHNIQUE: DIGIT EXTRACTION & GREEDY LEFT-TO-RIGHT SCAN (KRISH'S SOLVE - 1 ms ⚡)
// =================================================================================================
// - Greedy Principle:
//     To maximize the resulting number, we must change the MOST SIGNIFICANT '6' into a '9'.
//     Changing any subsequent '6' or changing a '9' to '6' would yield a strictly smaller number.
// - Implementation Flow:
//     1. Extract digits using modulo (% 10) and integer division (/ 10) into an ArrayList<Integer>.
//     2. Reverse the list so index 0 represents the most significant digit (leftmost).
//     3. Scan from left to right. When the first 6 is found, flip it to 9 and break immediately.
//     4. Reconstruct and return the resulting integer.
//
// Complexity:
//   - Time Complexity: O(D) where D is the number of digits in num (D <= 4 since num <= 10^4). O(1) time.
//   - Space Complexity: O(D) Auxiliary Space for the ArrayList<Integer>.
// =================================================================================================

public class LC1323_Maximum69Number {

    public int maximum69Number(int num) {
        ArrayList<Integer> n = new ArrayList<>();
        int temp = num;
        while (temp > 0) {
            int d = temp % 10;
            temp = temp / 10;
            n.add(d);
        }
        Collections.reverse(n);
        int ptr = 0;
        while (ptr < n.size()) {
            if (n.get(ptr) == 6) {
                n.set(ptr, 9);
                break;
            }
            ptr++;
        }
        int result = 0;
        for (int digit : n) {
            result = (result * 10) + digit;
        }
        return result;
    }

    // Local Test Harness
    public static void main(String[] args) {
        LC1323_Maximum69Number solver = new LC1323_Maximum69Number();

        // Test 1: 9669 -> 9969
        System.out.println("Test 1 (9669): " + solver.maximum69Number(9669) + " (Expected: 9969)");

        // Test 2: 9996 -> 9999
        System.out.println("Test 2 (9996): " + solver.maximum69Number(9996) + " (Expected: 9999)");

        // Test 3: 9999 -> 9999 (no change needed)
        System.out.println("Test 3 (9999): " + solver.maximum69Number(9999) + " (Expected: 9999)");
    }
}

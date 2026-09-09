package leetcode;

/**
 * LeetCode #344: Reverse String
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/reverse-string/
 * 
 * Approach: In-Place Two Pointers Swapping
 * - Initialize two pointers: start = 0, end = s.length - 1.
 * - Swap characters at start and end, then increment start and decrement end.
 * - Terminates when start >= end.
 * 
 * Time Complexity: O(N) - Single pass through half the array (0ms Beats 100%)
 * Space Complexity: O(1) - In-place modification with zero extra auxiliary memory
 */
public class LC344_ReverseString {

    public void reverseString(char[] s) {
        int start = 0;
        int end = s.length - 1;

        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;

            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        LC344_ReverseString solver = new LC344_ReverseString();

        char[] test1 = {'h', 'e', 'l', 'l', 'o'};
        solver.reverseString(test1);
        System.out.println("Test 1 Result: " + new String(test1) + " (Expected: 'olleh')");

        char[] test2 = {'H', 'a', 'n', 'n', 'a', 'h'};
        solver.reverseString(test2);
        System.out.println("Test 2 Result: " + new String(test2) + " (Expected: 'hannaH')");
    }
}

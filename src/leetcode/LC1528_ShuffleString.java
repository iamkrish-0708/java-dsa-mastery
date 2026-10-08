package leetcode;

/**
 * LeetCode #1528: Shuffle String
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/shuffle-string/
 * 
 * Approaches:
 * 1. Brute Force (O(N^2) Time):
 *    - For each target index i from 0 to N-1, scan indices array to find matching index j where indices[j] == i.
 *    - Appends character using String concatenation.
 * 
 * 2. Optimal Direct Placement (O(N) Time - 0ms Beats 100%):
 *    - Allocate a `char[] ans` of size N.
 *    - In a single pass, place each character directly into its target destination: `ans[indices[i]] = s.charAt(i)`.
 *    - Construct final string using `new String(ans)`.
 * 
 * Time Complexity: O(N) - 0ms Beats 100%
 * Space Complexity: O(N) auxiliary space for char array
 */
public class LC1528_ShuffleString {

    // Approach 1: Optimal O(N) Single-Pass with char array buffer
    public String restoreString(String s, int[] indices) {
        char[] ans = new char[s.length()];
        for (int i = 0; i < indices.length; i++) {
            ans[indices[i]] = s.charAt(i);
        }
        return new String(ans);
    }

    // Approach 2: Brute Force O(N^2)
    public String restoreStringBruteForce(String s, int[] indices) {
        String ans = "";
        for (int i = 0; i < indices.length; i++) {
            for (int j = 0; j < indices.length; j++) {
                if (i == indices[j]) {
                    ans += s.charAt(j);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        LC1528_ShuffleString solver = new LC1528_ShuffleString();

        // Test 1: s = "codeleet", indices = [4,5,6,7,0,2,1,3] -> "leetcode"
        String s1 = "codeleet";
        int[] indices1 = {4, 5, 6, 7, 0, 2, 1, 3};
        System.out.println("Test 1 Result: " + solver.restoreString(s1, indices1)); // Expected: "leetcode"

        // Test 2: s = "abc", indices = [0,1,2] -> "abc"
        String s2 = "abc";
        int[] indices2 = {0, 1, 2};
        System.out.println("Test 2 Result: " + solver.restoreString(s2, indices2)); // Expected: "abc"
    }
}

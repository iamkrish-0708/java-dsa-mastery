package leetcode;

// LeetCode #1456 - Maximum Number of Vowels in a Substring of Given Length
// Difficulty: Medium
// Problem: Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.
// Approach: Fixed-Size Sliding Window (O(N) Time, O(1) Space)
// Key Mechanics:
//   - Phase 1: Compute vowel count for the initial window of size k (indices 0 to k-1).
//   - Phase 2: Slide the window from index k to s.length() - 1:
//       - Add incoming character at right edge: s.charAt(i)
//       - Remove outgoing character at left edge: s.charAt(i - k)
//       - Update running max: maxCount = Math.max(currCount, maxCount)
//   - Uses O(1) helper isVowel() avoiding collection overhead.
// Complexity: O(N) Time (single linear pass), O(1) Auxiliary Space
// Problem Link: https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/

public class LC1456_MaximumNumberOfVowelsInASubstringOfGivenLength {

    static boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    public int maxVowels(String s, int k) {
        int currCount = 0;
        // First sliding window of size k
        for (int i = 0; i < k; i++) {
            if (isVowel(s.charAt(i))) {
                currCount++;
            }
        }
        int maxCount = currCount;

        // Slide the window across the rest of the string
        for (int i = k; i < s.length(); i++) {
            // Add new char entering from right
            if (isVowel(s.charAt(i))) {
                currCount++;
            }
            // Remove old char exiting from left
            if (isVowel(s.charAt(i - k))) {
                currCount--;
            }
            maxCount = Math.max(currCount, maxCount);
        }
        return maxCount;
    }

    public static void main(String[] args) {
        LC1456_MaximumNumberOfVowelsInASubstringOfGivenLength solver = new LC1456_MaximumNumberOfVowelsInASubstringOfGivenLength();
        System.out.println("Output 1: " + solver.maxVowels("abciiidef", 3)); // Expected: 3 ("iii")
        System.out.println("Output 2: " + solver.maxVowels("aeiou", 2));     // Expected: 2 ("ae")
        System.out.println("Output 3: " + solver.maxVowels("leetcode", 3));  // Expected: 2 ("lee")
    }
}

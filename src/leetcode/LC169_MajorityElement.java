package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode #169: Majority Element
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/majority-element/
 * 
 * Problem:
 * - Find the element that appears more than floor(n / 2) times in the array.
 * 
 * Approach 1 (HashMap Frequency Counter - Authored by Krish):
 * - Build frequency map using getOrDefault(n, 0) + 1.
 * - Find max frequency key by iterating keySet.
 * - Time Complexity: O(N)
 * - Space Complexity: O(N)
 * 
 * Approach 2 (Boyer-Moore Voting Algorithm - O(1) Space Pro Tip):
 * - Maintain a candidate and count = 0.
 * - If count == 0, candidate = current element.
 * - If current element == candidate, count++ else count--.
 * - Since majority element appears > n/2 times, it will always remain as candidate!
 * - Time Complexity: O(N) - Single pass (1ms Beats 99.8%)
 * - Space Complexity: O(1) - Constant memory
 */
public class LC169_MajorityElement {

    // Approach 1: HashMap Frequency (Krish's Solution)
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> ElementFreq = new HashMap<>();
        for (int n : nums) {
            ElementFreq.put(n, ElementFreq.getOrDefault(n, 0) + 1);
        }
        int maxFreq = -1;
        int maxKey = -1;
        for (int key : ElementFreq.keySet()) {
            int currFreq = ElementFreq.get(key);
            if (currFreq > maxFreq) {
                maxFreq = currFreq;
                maxKey = key;
            }
        }
        return maxKey;
    }

    // Approach 2: Boyer-Moore Voting Algorithm (O(1) Space)
    public int majorityElementBoyerMoore(int[] nums) {
        int candidate = 0;
        int count = 0;

        for (int n : nums) {
            if (count == 0) {
                candidate = n;
            }
            count += (n == candidate) ? 1 : -1;
        }

        return candidate;
    }

    public static void main(String[] args) {
        LC169_MajorityElement solver = new LC169_MajorityElement();

        int[] test1 = {3, 2, 3};
        int[] test2 = {2, 2, 1, 1, 1, 2, 2};

        System.out.println("Test 1 (HashMap)     -> " + solver.majorityElement(test1) + " (Expected: 3)");
        System.out.println("Test 1 (Boyer-Moore) -> " + solver.majorityElementBoyerMoore(test1) + " (Expected: 3)");

        System.out.println("Test 2 (HashMap)     -> " + solver.majorityElement(test2) + " (Expected: 2)");
        System.out.println("Test 2 (Boyer-Moore) -> " + solver.majorityElementBoyerMoore(test2) + " (Expected: 2)");
    }
}

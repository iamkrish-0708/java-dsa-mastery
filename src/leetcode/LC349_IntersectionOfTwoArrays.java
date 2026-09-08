package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode #349: Intersection of Two Arrays
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/intersection-of-two-arrays/
 *
 * Approaches Authored & Iterated by Krish:
 * 1. Approach 1: Brute Force (Nested Loops + ArrayList Deduplication)
 *    - Time Complexity: O(N * M * K)
 *    - Space Complexity: O(K)
 *
 * 2. Approach 2: HashSet + ArrayList (Set Lookup for nums1 + ArrayList Deduplication)
 *    - Time Complexity: O(N + M * K)
 *    - Space Complexity: O(N + K)
 *
 * 3. Approach 3: Two HashSets (Optimal Hash-Based Lookup & Deduplication)
 *    - Time Complexity: O(N + M)
 *    - Space Complexity: O(N + M)
 */
public class LC349_IntersectionOfTwoArrays {

    // --- APPROACH 1: Brute Force Nested Loops ---
    public int[] intersectionApproach1(int[] nums1, int[] nums2) {
        ArrayList<Integer> holder = new ArrayList<>();

        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j] && !holder.contains(nums1[i])) {
                    holder.add(nums1[i]);
                    break;
                }
            }
        }

        int[] result = new int[holder.size()];
        for (int i = 0; i < holder.size(); i++) {
            result[i] = holder.get(i);
        }

        return result;
    }

    // --- APPROACH 2: HashSet (nums1) + ArrayList (common) ---
    public int[] intersectionApproach2(int[] nums1, int[] nums2) {
        Set<Integer> holder = new HashSet<>();

        for (int n : nums1) {
            holder.add(n);
        }

        ArrayList<Integer> common = new ArrayList<>();
        for (int i = 0; i < nums2.length; i++) {
            if (holder.contains(nums2[i]) && !common.contains(nums2[i])) {
                common.add(nums2[i]);
            }
        }

        int[] result = new int[common.size()];
        for (int j = 0; j < common.size(); j++) {
            result[j] = common.get(j);
        }

        return result;
    }

    // --- APPROACH 3: Two HashSets (Optimal Set Operations) ---
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> holder = new HashSet<>();
        Set<Integer> common = new HashSet<>();

        for (int n : nums1) {
            holder.add(n);
        }

        for (int a : nums2) {
            if (holder.contains(a) && !common.contains(a)) {
                common.add(a);
            }
        }

        int[] result = new int[common.size()];
        int i = 0;
        for (int n : common) {
            result[i] = n;
            i++;
        }

        return result;
    }

    public static void main(String[] args) {
        LC349_IntersectionOfTwoArrays solver = new LC349_IntersectionOfTwoArrays();

        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};

        int[] nums3 = {4, 9, 5};
        int[] nums4 = {9, 4, 9, 8, 4};

        System.out.println("Approach 1 Test 1: " + Arrays.toString(solver.intersectionApproach1(nums1, nums2)));
        System.out.println("Approach 2 Test 1: " + Arrays.toString(solver.intersectionApproach2(nums1, nums2)));
        System.out.println("Approach 3 Test 1: " + Arrays.toString(solver.intersection(nums1, nums2)));

        System.out.println("Approach 3 Test 2: " + Arrays.toString(solver.intersection(nums3, nums4)));
    }
}

package leetcode;

import java.util.*;

/**
 * LeetCode #2094: Finding 3-Digit Even Numbers
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/finding-3-digit-even-numbers/
 * 
 * Approach: Pruned 3-Loop Permutation + HashSet Deduplication + Collection Sort
 * - Loop over all distinct triplets (i, j, k).
 * - Guard 1: digits[i] != 0 (No leading zeroes).
 * - Guard 2: j != i (Distinct index).
 * - Guard 3: k != i && k != j (Distinct index).
 * - Guard 4: (digits[k] & 1) == 0 (Units digit must be even).
 * - Store generated numbers in HashSet<Integer> for automatic deduplication.
 * - Sort the resulting collection and convert to primitive int[] array.
 * 
 * Time Complexity: O(N^3 + U log U) where N <= 100, U <= 450 (runs in a few ms).
 * Space Complexity: O(U) auxiliary space.
 */
public class LC2094_FindingThreeDigitEvenNumbers {

    public int[] findEvenNumbers(int[] digits) {
        Set<Integer> uniqueNum = new HashSet<>();
        int n = digits.length;

        for (int i = 0; i < n; i++) {
            if (digits[i] == 0) {
                continue;
            }
            for (int j = 0; j < n; j++) {
                if (j == i) {
                    continue;
                }
                for (int k = 0; k < n; k++) {
                    if (k == i || k == j) {
                        continue;
                    } else if ((digits[k] & 1) != 0) {
                        continue;
                    }
                    int num = digits[i] * 100 + digits[j] * 10 + digits[k];
                    uniqueNum.add(num);
                }
            }
        }

        List<Integer> sortedList = new ArrayList<>(uniqueNum);
        Collections.sort(sortedList);

        int[] result = new int[sortedList.size()];
        for (int x = 0; x < sortedList.size(); x++) {
            result[x] = sortedList.get(x);
        }
        return result;
    }

    public static void main(String[] args) {
        LC2094_FindingThreeDigitEvenNumbers solver = new LC2094_FindingThreeDigitEvenNumbers();

        int[] digits1 = {2, 1, 3, 0};
        System.out.println("Test 1: [2, 1, 3, 0]    -> " + Arrays.toString(solver.findEvenNumbers(digits1)));

        int[] digits2 = {2, 2, 8, 8, 2};
        System.out.println("Test 2: [2, 2, 8, 8, 2] -> " + Arrays.toString(solver.findEvenNumbers(digits2)));

        int[] digits3 = {3, 7, 5};
        System.out.println("Test 3: [3, 7, 5]       -> " + Arrays.toString(solver.findEvenNumbers(digits3)));
    }
}

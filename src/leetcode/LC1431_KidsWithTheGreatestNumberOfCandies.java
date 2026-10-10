package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode #1431: Kids With the Greatest Number of Candies
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/
 * 
 * Approach: Two-Pass Greedy Comparison
 * 1. Pass 1: Find the maximum number of candies any child currently has.
 * 2. Pass 2: For each child, check if (candies[i] + extraCandies >= maxCandy).
 * 
 * Time Complexity: O(N) - 1ms Beats 99%
 * Space Complexity: O(1) Auxiliary Space (ignoring output List)
 */
public class LC1431_KidsWithTheGreatestNumberOfCandies {

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> ansList = new ArrayList<>();
        int maxCandy = -1;

        // Pass 1: Find the maximum candy count
        for (int j = 0; j < candies.length; j++) {
            if (candies[j] > maxCandy) {
                maxCandy = candies[j];
            }
        }

        // Pass 2: Compare each child's potential candy count against maxCandy
        for (int i = 0; i < candies.length; i++) {
            int candyCount = candies[i] + extraCandies;
            if (candyCount >= maxCandy) {
                ansList.add(true);
            } else {
                ansList.add(false);
            }
        }

        return ansList;
    }

    public static void main(String[] args) {
        LC1431_KidsWithTheGreatestNumberOfCandies solver = new LC1431_KidsWithTheGreatestNumberOfCandies();

        // Test 1: candies = [2,3,5,1,3], extraCandies = 3 -> [true,true,true,false,true]
        int[] candies1 = {2, 3, 5, 1, 3};
        int extra1 = 3;
        System.out.println("Test 1 Result: " + solver.kidsWithCandies(candies1, extra1));

        // Test 2: candies = [4,2,1,1,2], extraCandies = 1 -> [true,false,false,false,false]
        int[] candies2 = {4, 2, 1, 1, 2};
        int extra2 = 1;
        System.out.println("Test 2 Result: " + solver.kidsWithCandies(candies2, extra2));

        // Test 3: candies = [12,1,12], extraCandies = 10 -> [true,false,true]
        int[] candies3 = {12, 1, 12};
        int extra3 = 10;
        System.out.println("Test 3 Result: " + solver.kidsWithCandies(candies3, extra3));
    }
}

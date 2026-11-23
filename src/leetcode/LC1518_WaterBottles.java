package leetcode;

// LeetCode #1518 - Water Bottles
// Difficulty: Easy
// Problem: There are numBottles water bottles that are initially full of water.
//          You can exchange numExchange empty water bottles from the market with one full water bottle.
//          The operation of drinking a full water bottle turns it into an empty bottle.
//          Given the two integers numBottles and numExchange, return the maximum number of water bottles
//          you can drink.
//
// =================================================================================================
// 💡 TECHNIQUE: GREEDY SIMULATION WITH REMAINDER TRACKING (KRISH'S SOLVE - 0 ms ⚡ BEATS 100%)
// =================================================================================================
// - Mechanics:
//     1. Maintain total bottles drank (bottles) and leftover empty bottles from previous exchanges (extra).
//     2. In each iteration of the loop while numBottles >= 1:
//        - Drink all currently available full bottles: bottles += numBottles.
//        - Add the leftover empty bottles from prior round: numBottles += extra.
//        - Check exchange division:
//          * If cleanly divisible (numBottles % numExchange == 0):
//            numBottles = numBottles / numExchange, extra = 0.
//          * Otherwise:
//            extra = numBottles % numExchange.
//            numBottles = (numBottles - extra) / numExchange.
//     3. Loop terminates when newly obtained bottles drop to 0.
//
// - O(1) Mathematical Insight (Alternative):
//     Each exchange effectively consumes (numExchange - 1) empty bottles to yield 1 new drink,
//     giving a closed-form formula: numBottles + (numBottles - 1) / (numExchange - 1).
//
// Complexity:
//   - Time Complexity: O(log_{numExchange}(N)) — In each round, bottles decrease by a factor of numExchange.
//   - Space Complexity: O(1) Auxiliary Space — Only primitive integer accumulators.
// =================================================================================================

public class LC1518_WaterBottles {

    // Approach 1: Greedy Simulation with Remainder Tracking (Krish's Accepted Solve - 0 ms Beats 100%)
    public int numWaterBottles(int numBottles, int numExchange) {
        int bottles = 0;
        int extra = 0;
        while (numBottles >= 1) {
            bottles += numBottles;
            numBottles += extra;
            if ((numBottles % numExchange) == 0) {
                numBottles = numBottles / numExchange;
                extra = 0;
            } else {
                extra = numBottles % numExchange;
                numBottles = (numBottles - extra) / numExchange;
            }
        }
        return bottles;
    }

    // Approach 2: Direct O(1) Math Closed-Form Formula
    public int numWaterBottlesMath(int numBottles, int numExchange) {
        return numBottles + (numBottles - 1) / (numExchange - 1);
    }

    public static void main(String[] args) {
        LC1518_WaterBottles solver = new LC1518_WaterBottles();

        // Test Case 1: 9 bottles, 3 exchange -> 9 + 3 + 1 = 13
        int res1 = solver.numWaterBottles(9, 3);
        System.out.println("Test 1 (9, 3): " + res1 + " (Expected: 13)");

        // Test Case 2: 15 bottles, 4 exchange -> 15 + 3 + 1 = 19
        int res2 = solver.numWaterBottles(15, 4);
        System.out.println("Test 2 (15, 4): " + res2 + " (Expected: 19)");

        // Test Case 3: Math formula verification
        int mathRes = solver.numWaterBottlesMath(15, 4);
        System.out.println("Math Formula (15, 4): " + mathRes + " (Expected: 19)");
    }
}

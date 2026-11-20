package leetcode;

// LeetCode #1688 - Count of Matches in Tournament
// Difficulty: Easy
// Problem: You are given an integer n, the number of teams in a tournament that has strange rules:
//          - If the current number of teams is even, each team gets paired with another team.
//            A total of n / 2 matches are played, and n / 2 teams advance to the next round.
//          - If the current number of teams is odd, one team randomly advances in the tournament,
//            and the rest gets paired. A total of (n - 1) / 2 matches are played, and (n - 1) / 2 + 1
//            teams advance to the next round.
//          Return the number of matches played in the tournament until a winner is decided.
//
// =================================================================================================
// 💡 TECHNIQUE: SIMULATION VIA BITWISE PARITY & O(1) DEDUCTION (KRISH'S SOLVE - 1 ms / 0 ms ⚡)
// =================================================================================================
// - Approach 1: Simulation via Bitwise Parity (1 ms)
//     * While teams > 1, check parity using (teams & 1) == 0.
//     * Accumulate matches played and update surviving teams accordingly.
//     * Time Complexity: O(log N) as the number of teams halves each round.
//     * Space Complexity: O(1) Auxiliary Space.
//
// - Approach 2: Constant Time Deduction (0 ms Beats 100% ⚡)
//     * In any single-elimination tournament, every match played eliminates exactly 1 team.
//     * To find 1 champion from n teams, exactly n - 1 teams must be eliminated.
//     * Hence, total matches played is always strictly n - 1.
//     * Time Complexity: O(1), Space Complexity: O(1).
// =================================================================================================

public class LC1688_CountOfMatchesInTournament {

    // Approach 1: Simulation via Bitwise Parity (Krish - 1 ms)
    public int numberOfMatches(int n) {
        int teams = n;
        int matches = 0;
        while (teams != 1) {
            if ((teams & 1) == 0) {
                matches += teams / 2;
                teams = teams / 2;
            } else {
                matches += (teams - 1) / 2;
                teams = 1 + (teams - 1) / 2;
            }
        }
        return matches;
    }

    // Approach 2: Direct Mathematical Deduction (0 ms)
    public int numberOfMatchesMath(int n) {
        return n - 1;
    }

    // Local Test Harness
    public static void main(String[] args) {
        LC1688_CountOfMatchesInTournament solver = new LC1688_CountOfMatchesInTournament();

        // Test 1: n = 7 -> matches = 3 + 2 + 1 = 6
        System.out.println("Test 1 (n=7): " + solver.numberOfMatches(7) + " (Expected: 6)");

        // Test 2: n = 14 -> matches = 7 + 3 + 2 + 1 = 13
        System.out.println("Test 2 (n=14): " + solver.numberOfMatches(14) + " (Expected: 13)");

        // Test 3: Math Approach check
        System.out.println("Test 3 Math (n=14): " + solver.numberOfMatchesMath(14) + " (Expected: 13)");
    }
}

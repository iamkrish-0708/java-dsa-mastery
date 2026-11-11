package leetcode;

import java.util.HashSet;
import java.util.Set;

// LeetCode #771 - Jewels and Stones
// Difficulty: Easy
// Problem: You're given strings jewels representing the types of stones that are jewels,
//          and stones representing the stones you have. Each character in stones is a type
//          of stone you have. You want to know how many of the stones you have are also jewels.
//          Letters are case sensitive, so "a" is considered a different type of stone from "A".
//
// =================================================================================================
// 💡 EVOLUTION OF SOLUTIONS (KRISH'S FULL PROGRESSION):
// =================================================================================================
//
// Approach 1 (Krish's Initial Solve - HashSet Lookup - Passed 1 ms):
//   - Add all jewel characters to a Set<Character> tracker.
//   - Iterate through stones, if tracker.contains(stone), increment ans.
//   - Complexity: O(J + S) Time, O(J) Space (Character object autoboxing & hash bucket overhead).
//
// Approach 2 (Direct ASCII boolean[128] Lookup Table - Passed 1 ms / O(1) Space ⚡):
//   - Constraint insight: Characters consist of English letters ('A'-'Z' = 65-90, 'a'-'z' = 97-122).
//   - The highest character 'z' has ASCII value 122.
//   - A standard boolean[128] array safely covers all ASCII characters with zero arithmetic/branching.
//   - tracker[jewels.charAt(i)] = true provides O(1) direct memory indexing.
//   - Eliminates autoboxing, hash computations, and heap object allocations.
//   - Complexity: O(J + S) Time, O(1) Auxiliary Space (fixed 128 bytes).
// =================================================================================================

public class LC771_JewelsAndStones {

    // Approach 2: Direct ASCII boolean[128] Lookup Table (O(1) Space)
    public int numJewelsInStones(String jewels, String stones) {
        boolean[] tracker = new boolean[128];
        int ans = 0;
        for (int i = 0; i < jewels.length(); i++) {
            tracker[jewels.charAt(i)] = true;
        }
        for (int i = 0; i < stones.length(); i++) {
            if (tracker[stones.charAt(i)]) {
                ans++;
            }
        }
        return ans;
    }

    // Approach 1: Krish's Initial HashSet Solution
    public int numJewelsInStonesHashSet(String jewels, String stones) {
        Set<Character> tracker = new HashSet<>();
        int ans = 0;
        for (int i = 0; i < jewels.length(); i++) {
            tracker.add(jewels.charAt(i));
        }
        for (int x = 0; x < stones.length(); x++) {
            if (tracker.contains(stones.charAt(x))) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        LC771_JewelsAndStones solver = new LC771_JewelsAndStones();

        String jewels1 = "aA", stones1 = "aAAbbbb";
        System.out.println("Test 1 Expected: 3 | Output (ASCII): " + solver.numJewelsInStones(jewels1, stones1));
        System.out.println("Test 1 Expected: 3 | Output (HashSet): " + solver.numJewelsInStonesHashSet(jewels1, stones1));

        String jewels2 = "z", stones2 = "ZZ";
        System.out.println("Test 2 Expected: 0 | Output (ASCII): " + solver.numJewelsInStones(jewels2, stones2));
    }
}

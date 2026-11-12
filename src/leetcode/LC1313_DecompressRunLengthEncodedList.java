package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// LeetCode #1313 - Decompress Run-Length Encoded List
// Difficulty: Easy
// Problem: We are given a run-length encoded list of integers nums. For each pair [freq, val] =
//          [nums[2*i], nums[2*i+1]] (with i >= 0), there are freq elements with value val.
//          Return the decompressed list as an integer array.
//
// =================================================================================================
// 💡 EVOLUTION OF SOLUTIONS (KRISH'S FULL PROGRESSION):
// =================================================================================================
//
// Approach 1 (Krish's Initial Solve - Dynamic ArrayList - 5 ms):
//   - Traverse pairs with pointers i and j stepping by 2.
//   - Append val to a List<Integer> ans freq times.
//   - Second pass copies elements from List<Integer> to primitive int[].
//   - Trade-off: Pays the "ArrayList Tax" — Autoboxing (int <-> Integer), dynamic resizing copies (grow()),
//     and double copying into a primitive array (5 ms).
//   - Complexity: O(N + totalSize) Time, O(totalSize) Space.
//
// Approach 2 (Optimal 2-Pass Direct Primitive Array Allocation - 1 ms Beats 100% ⚡):
//   - Pass 1: Compute totalSize upfront by summing all frequencies (nums[0] + nums[2] + ...).
//   - Direct Allocation: Allocate exact primitive int[] ans = new int[totalSize] once!
//   - Pass 2: Fill elements directly using write pointer ptr.
//   - Eliminates all heap object allocations, boxing, and resizing overhead entirely.
//   - Complexity: O(N + totalSize) Time, O(1) Auxiliary Space (only the output array).
// =================================================================================================

public class LC1313_DecompressRunLengthEncodedList {

    // Approach 2: Optimal 2-Pass Direct Primitive Array Allocation (1 ms Beats 100%)
    public int[] decompressRLElist(int[] nums) {
        int totalSize = 0;
        for (int i = 0; i < nums.length; i += 2) {
            totalSize += nums[i];
        }

        int[] ans = new int[totalSize];
        int ptr = 0;
        for (int i = 0; i < nums.length; i += 2) {
            int freq = nums[i];
            int val = nums[i + 1];
            while (freq > 0 && ptr < ans.length) {
                ans[ptr] = val;
                freq--;
                ptr++;
            }
        }
        return ans;
    }

    // Approach 1: Krish's Initial Dynamic ArrayList Solve (5 ms)
    public int[] decompressRLElistArrayList(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int i = 0;
        int j = 1;
        while (j < nums.length) {
            int freq = nums[i];
            int val = nums[j];
            for (int x = 0; x < freq; x++) {
                ans.add(val);
            }
            i += 2;
            j += 2;
        }
        int[] res = new int[ans.size()];
        for (int k = 0; k < ans.size(); k++) {
            res[k] = ans.get(k);
        }
        return res;
    }

    public static void main(String[] args) {
        LC1313_DecompressRunLengthEncodedList solver = new LC1313_DecompressRunLengthEncodedList();

        int[] test1 = {1, 2, 3, 4};
        System.out.println("Test 1 [1,2,3,4] (Optimal):   " + Arrays.toString(solver.decompressRLElist(test1)));
        System.out.println("Test 1 [1,2,3,4] (ArrayList): " + Arrays.toString(solver.decompressRLElistArrayList(test1)));

        int[] test2 = {1, 1, 2, 3};
        System.out.println("Test 2 [1,1,2,3] (Optimal):   " + Arrays.toString(solver.decompressRLElist(test2)));
    }
}

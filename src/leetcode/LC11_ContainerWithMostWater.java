package leetcode;

// LeetCode #11 - Container With Most Water
// Difficulty: Medium (Top Interview 150 / Blind 75)
// Problem: You are given an integer array height of length n. There are n vertical lines drawn such
//          that the two endpoints of the ith line are (i, 0) and (i, height[i]).
//          Find two lines that together with the x-axis form a container, such that the container
//          contains the most water. Return the maximum amount of water a container can store.
//          Notice that you may not slant the container.
//
// =================================================================================================
// 💡 EVOLUTION OF SOLUTIONS (KRISH'S FULL PROGRESSION):
// =================================================================================================
//
// Approach 1 (Canonical Two-Pointer Squeeze - Passed 5 ms ⚡):
//   - Two pointers i = 0 (leftmost) and j = height.length - 1 (rightmost).
//   - Bottleneck Insight: Water level is limited by the shorter wall:
//       effHeight = Math.min(height[i], height[j])
//       area = effHeight * (j - i)
//   - Greedy Pointer Decision: Width (j - i) is constantly decreasing. Moving the taller wall will
//     NEVER increase the area because water is still bottlenecked by the shorter wall (or worse).
//     The ONLY chance to find a larger area with smaller width is moving the SHORTER pointer inward!
//   - Complexity: O(N) Time, O(1) Auxiliary Space.
//
// Approach 2 (Fast Skip / Hop Optimization - Passed 2 ms Beats 98% ⚡):
//   - Observe that when we move inward from the shorter wall, if the next wall is EVEN SHORTER OR EQUAL
//     to the previous effHeight, the area is mathematically guaranteed to be smaller (shorter height + smaller width).
//   - Use inner while loops to skip all hopeless walls in one jump:
//       while (i < j && height[i] <= effHeight) i++;
//       while (i < j && height[j] <= effHeight) j--;
//   - Skips redundant math and drops runtime from 5 ms to 2 ms!
//   - Complexity: O(N) Time, O(1) Auxiliary Space.
// =================================================================================================

public class LC11_ContainerWithMostWater {

    // Approach 2: Fast Skip / Hop Optimization (2 ms Beats 98%)
    public int maxArea(int[] height) {
        int max = 0;
        int i = 0;
        int j = height.length - 1;

        while (i < j) {
            int effHeight = Math.min(height[i], height[j]);
            int area = effHeight * (j - i);
            max = Math.max(max, area);

            // Fast-forward past all walls that cannot possibly beat effHeight
            while (i < j && height[i] <= effHeight) i++;
            while (i < j && height[j] <= effHeight) j--;
        }
        return max;
    }

    // Approach 1: Canonical Two-Pointer Squeeze (5 ms)
    public int maxAreaCanonical(int[] height) {
        int max = 0;
        int i = 0;
        int j = height.length - 1;

        while (i < j) {
            int effHeight = Math.min(height[i], height[j]);
            int area = effHeight * (j - i);
            max = Math.max(max, area);

            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LC11_ContainerWithMostWater solver = new LC11_ContainerWithMostWater();

        int[] h1 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("Test 1 Expected: 49 | Output (2ms): " + solver.maxArea(h1));
        System.out.println("Test 1 Expected: 49 | Output (5ms): " + solver.maxAreaCanonical(h1));

        int[] h2 = {1, 1};
        System.out.println("Test 2 Expected: 1  | Output (2ms): " + solver.maxArea(h2));
    }
}

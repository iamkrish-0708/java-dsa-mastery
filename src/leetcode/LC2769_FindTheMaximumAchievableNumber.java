package leetcode;

// LeetCode #2769 - Find the Maximum Achievable Number
// Difficulty: Easy

public class LC2769_FindTheMaximumAchievableNumber {

    public int theMaximumAchievableX(int num, int t) {
        return (num + t * 2);
    }

    public static void main(String[] args) {
        LC2769_FindTheMaximumAchievableNumber solver = new LC2769_FindTheMaximumAchievableNumber();
        System.out.println("Test 1 (num=4, t=1): " + solver.theMaximumAchievableX(4, 1)); // 6
        System.out.println("Test 2 (num=3, t=2): " + solver.theMaximumAchievableX(3, 2)); // 7
    }
}

package leetcode;

import java.util.Arrays;

/**
 * LeetCode #2469: Convert the Temperature
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/convert-the-temperature/
 * 
 * Approach: Direct Mathematical Conversion
 * - Kelvin = Celsius + 273.15
 * - Fahrenheit = Celsius * 1.80 + 32.00
 * 
 * Time Complexity: O(1) - 0ms Beats 100%
 * Space Complexity: O(1) Auxiliary Space
 */
public class LC2469_ConvertTheTemperature {

    public double[] convertTemperature(double celsius) {
        double k = celsius + 273.15;
        double f = (celsius * 1.80) + 32.00;
        return new double[]{k, f};
    }

    public static void main(String[] args) {
        LC2469_ConvertTheTemperature solver = new LC2469_ConvertTheTemperature();

        // Test 1: celsius = 36.50 -> [309.65000, 97.70000]
        System.out.println("Test 1 Result: " + Arrays.toString(solver.convertTemperature(36.50)));

        // Test 2: celsius = 122.11 -> [395.26000, 251.79800]
        System.out.println("Test 2 Result: " + Arrays.toString(solver.convertTemperature(122.11)));
    }
}

package recursion;

/*In this problem, you are tasked with determining how much money you can rob from a line of houses without tripping the security systems. Each house has a certain amount of money. represented as a non-negative integer in an array nums. The catch is that robbing two directly adjacent houses will trigger the alarm, so you need to strategically choose which houses to rob to maximize your total haul for the night.*/

public class HouseRobber {
    
    static int solve(int[] house,int index){
        if(index >= house.length){
            return 0;
        }
        // include
        int inc = house[index] + solve(house,index + 2);
        // exclude
        int exc = 0 + solve(house,index + 1);

        return Math.max(inc,exc);
    }

    public static void main(String[] args) {
        int[] house = {2,7,9,3,1};
        int index = 0;
        int ans = solve(house,index);
        System.out.println("Output = "+ans);
    }
}

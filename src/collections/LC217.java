package collections;
//itemFrequency.put(order, itemFrequency.getOrDefault(order, 0) + 1);
import java.util.HashMap;

public class LC217 {
    public static void main(String[] args) {

        HashMap<Integer,Integer> dupliChecker=new HashMap<>();
        int[] nums={1,2,3,1};

        for(Integer num:nums){
            if(dupliChecker.containsKey(num)){
                return true;
            }
            dupliChecker.put(num,0);
        }return false;

    }
}

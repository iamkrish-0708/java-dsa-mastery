package collections;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class OrderAnalyticsEngine {
    public static void main(String[] args) {
        Map<String,Integer> itemFrequency=new HashMap<>();
        String[] orders = {"Laptop", "Mouse", "Laptop", "Keyboard", "Mouse", "Laptop"};

    for(String order:orders){
//            if(itemFrequency.containsKey(order)){
//                int value=itemFrequency.get(order);
//                itemFrequency.put(order,value+1);
//            }
//            else {
//                itemFrequency.put(order, 1);
//            }


            //ABOVE ENTIRE 7 LINE IF ELSE BLOCK IN ONE LINE BELOW:
              itemFrequency.put(order, itemFrequency.getOrDefault(order, 0) + 1);

        }

        for(Map.Entry<String , Integer> entry:itemFrequency.entrySet()){
            System.out.println(entry.getKey()+"="+entry.getValue());
        }

        TreeMap<Integer, Double> employeeSalaries = new TreeMap<>();
        employeeSalaries.put(104,75000.0);
        employeeSalaries.put(101,50000.0);
        employeeSalaries.put(103,62000.0);
        employeeSalaries.put(102,55000.0);

        for(Map.Entry<Integer,Double> entry2:employeeSalaries.entrySet()){
            System.out.println("Employee Id="+entry2.getKey()+"| Salary="+entry2.getValue());
        }

        System.out.println(employeeSalaries.firstKey());
        System.out.println(employeeSalaries.lastKey());
    }
}

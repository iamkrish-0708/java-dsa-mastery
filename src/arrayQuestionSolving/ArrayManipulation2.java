package arrayQuestionSolving;

import java.util.HashMap;
import java.util.Map;

public class ArrayManipulation2 {

    static void ModeOfArray(int[] arr){
        Map<Integer,Integer> eval=new HashMap<>();
        for(int n:arr){
            eval.put(n, eval.getOrDefault(n,0)+1);
        }
        int maxFreq=-1;
        int maxKey=-1;
        for(int key: eval.keySet()){
            int currKey=key;
            int currFreq=eval.get(key);
            if(currFreq>maxFreq){
                maxFreq=currFreq;
                maxKey=currKey;
            }
        }
        System.out.println("Mode = "+maxKey);
    }

    static void getExtremeFreq(int[] arr){
        Map<Integer,Integer> eval=new HashMap<>();
        for(int n:arr){
            eval.put(n, eval.getOrDefault(n,0)+1);
        }
        int maxFreq=-1;
        int maxKey=-1;
        int minFreq=-1;
        int minKey=-1;
        for(int key: eval.keySet()){
            int currKey=key;
            int currFreq=eval.get(key);
            if(currFreq>maxFreq){
                maxFreq=currFreq;
                maxKey=currKey;
            }
            else if(currFreq<maxFreq){
                minFreq=currFreq;
                minKey=currKey;
            }
        }
        System.out.println("(Number,Frequency)\n"+"Lowest->("+minKey+","+minFreq+")\n"+"Highest->("+maxKey+","+maxFreq+")\n");
    }
    public static void main(String[] args) {
    int [] arr={1,2,1,2,2,3};
    ModeOfArray(arr);
    getExtremeFreq(arr);
    }
}

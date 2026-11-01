package recursion;
// INCLUDE - EXCLUDE PATTERN

import java.util.*;

public class SubsequencesOfString {
    static void getAllSubsequences(String s, StringBuilder output, int i, List<String> ans){
        // base case
        if(i >= s.length()){
            ans.add(output.toString());
            return;
        }
        char ch = s.charAt(i);
        //include
        output.append(ch);
        getAllSubsequences(s,output,i+1,ans);
        //exclude
        output.deleteCharAt(output.length()-1);
        getAllSubsequences(s,output,i+1,ans);
    }
    public static void main(String[] args) {
        String s = "abc";
        StringBuilder output = new StringBuilder();
        int i = 0;
        List<String> ans = new ArrayList<>();
        getAllSubsequences(s,output,i,ans);
        for(String str : ans){
            System.out.println(str);
        }
        System.out.println("SUBSEQUENCE COUNT = "+ ans.size());
    }
}


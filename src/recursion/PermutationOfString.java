package recursion;

import java.util.ArrayList;
import java.util.List;

public class PermutationOfString {
    static void solve(String s,String curr,List<String> ans){
        // base case
        if( s.isEmpty() ){
            ans.add(curr);
            return;
        }

        for(int i=0;i<s.length();i++){
            char ch =  s.charAt(i);
            String remainingString = s.substring(0,i)+s.substring(i+1); // neglects character ch from string s
            solve(remainingString,curr + ch,ans);
        }
    }
    public static void main(String[] args) {
        String s = "ABC";
        List<String> ans = new ArrayList<>();
        solve(s,"",ans);
        for(String str:ans){
            System.out.println(str);
        }
    }
}

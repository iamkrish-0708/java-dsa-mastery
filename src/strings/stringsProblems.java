package strings;

import java.util.Locale;

public class stringsProblems {
    static void pallindromechecker(String str){
        String revStr="";
        for(int i=str.length()-1;i>=0;i--){
            char chs=str.charAt(i);
            revStr=revStr+chs;
        }
        if(revStr.equalsIgnoreCase(str)){
            System.out.println("STRING IS PALLINDROME");
        }else{
            System.out.println("STRING IS NOT PALLINDROME");
        }
    }
    public static void main() {
        String str="MY DOG NAME IS TOM";

        // PRINT EACH CHARACTER OF STRING
        for(int i=0;i<str.length();i++) {
                System.out.println(str.charAt(i));
        }

        // FIND STRING LENGTH WITHOUT INBUILT FUNCTION
        int count=0;
        for(int i=0;i<str.length();i++) {
            count++;
        }
        System.out.println("LENGTH OF STRING "+count);

        // COUNT VOWELS IN STRING
        int vowelCount=0;
        for(int i=0;i<str.length();i++) {
            char ch=str.toLowerCase(Locale.ROOT).charAt(i);
            if(ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u'){
                vowelCount++;
            }
        }
        System.out.println("NUMBER OF VOWELS IN GIVEN STRING IS "+vowelCount);

        // REVERSE A STRING
        String alert="HEAVY RAINFALL IN NEXT 2 HOURS";
        String revAlert="";
        for(int i=alert.length()-1;i>=0;i--){
            char chs=alert.charAt(i);
            revAlert=revAlert+chs;
        }
        System.out.println(revAlert);

        // CHECK STRING IS PALLINDROME OR NOT
        String ex1="CRAZY";
        String ex2="MOM";
        pallindromechecker(ex1);
        pallindromechecker(ex2);
    }
}

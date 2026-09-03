package strings;

import javax.print.DocFlavor;
import java.util.Scanner;

public class stringgsHW {

    // HW 1 - COUNT CONSONANTS OF STRING
    static void consonantCounter(String str){
            int count=0;
            for(int i=0;i<str.length();i++){
                char ch=str.charAt(i);
                if(ch=='a' || ch=='A' || ch=='e' || ch=='E' || ch=='i' || ch=='I' || ch=='o' || ch=='O' || ch=='u' || ch=='U' || ch==' '){
                    continue;
                }else {
                    count++;
                }
            }
            System.out.println("NUMBER OF CONSONANTS IN THE GIVEN STRING IS "+count);
    }

    // HW 2 - COUNT FREQUENCY OF A CHARACTER IN STRING
    static void freqCounter(String str,char chr){
        int count=0;
        for(int i=0;i<str.length();i++){
            char ch=str.charAt(i);
            if(ch==chr){
                count++;
            }
        }
        System.out.println("FRQUENCY OF CHARACTER "+chr+" IN GIVEN STRING IS "+count);
    }

    // HW 3 - WORD COUNTER
    static void wordCounter(String str){
        int count=0;
        for(int i=0;i<str.length();i++) {
            char ch = str.charAt(i);
            if (ch == ' ') {
                count++;
            }
        }
        count+=1; // so last word dont gets missed
        System.out.println("NUMBER OF WORDS IN STRING IS "+count);

    }

    public static void main() {
        Scanner sc=new Scanner(System.in);

        // HW 1 -
        String iostr="";
        System.out.print("ENTER A STRING TO COUNT CONSONANTS IN IT->");
        iostr=sc.nextLine();
        consonantCounter(iostr);

        // HW 2 -
        String character="";
        System.out.print("ENTER CHARACTER TO LOOK FOR IT'S FREQUENCY IN STRING->");
        character=sc.next();
        char chr=character.charAt(0);
        freqCounter(iostr,chr);

        // HW 3 -
        wordCounter(iostr);

        sc.close();
    }
}

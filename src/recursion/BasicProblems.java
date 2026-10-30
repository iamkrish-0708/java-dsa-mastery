package recursion;

import java.util.Scanner;

public class BasicProblems {

    static String PrintName(int n,String name,int sr){
        if(n==sr){
            return "";
        }
        sr+=1;
        System.out.println(sr+"-"+name);
        return PrintName(n,name,sr);
    }

    static int Print1toN(int n,int s){

        if(n<1){
            return -1;
        }
        if(s==n){
            return n;
        }
        System.out.println(s);
        s = s+1;
        return Print1toN(n,s);

    }

    static void PrintNto1(int n){
        if(n < 1){
            return;
        }
        System.out.println(n);
        n-=1;
        PrintNto1(n);
    }

    static void PrintArr(int[] arr,int size,int c){
        if(c==size){
            return;
        }
        System.out.println(arr[c]);
        c+=1;
        PrintArr(arr,size,c);
    }

    static void MaxElementInArr(int[] arr,int size,int c,int max){
        if(c==size){
            System.out.println("MAX ELEMENT = "+max);
            return;
        }
        max = Math.max(arr[c],max);
        c+=1;
        MaxElementInArr(arr,size,c,max);
    }

    static void MinElementInArr(int[] arr,int size,int c,int min){
        if(c==size){
            System.out.println("MIN ELEMENT = "+min);
            return;
        }
        min = Math.min(arr[c],min);
        c+=1;
        MinElementInArr(arr,size,c,min);
    }

    static void LinearSearch(int[] arr,int size,int c,int target){
        if(c==size){
            System.out.println("TARGET SEARCH UNSUCCESSFUL.");
            return;
        }
        if(arr[c]==target){
            System.out.println("TARGET SEARCH SUCCESSFUL.\nFOUND AT INDEX "+c);
            return;
        }
        c+=1;
        LinearSearch(arr,size,c,target);
    }

    static void FreqCount(int[] arr, int size, int c , int target,int freq){
        if(c==size){
            System.out.println(target+" APPEARED "+freq+" TIMES IN ARRAY.");
            return;
        }
        if(arr[c]==target){
            freq+=1;
        }
        c+=1;
        FreqCount(arr,size,c,target,freq);
    }

    // HERE PRINT IS AFTER RECURSIVE CALL WHICH HELPS US TO PRINT THE DIGITS IN THE SEQUENCE OF NUMBER INPUT IN OPPOSITE SCENARIO WHEN PRINT IS ABOVE RECURSIVE CALL WE WILL BE PRINTING DIGITS IN REVERSE ORDER OF SEQUENCE OF NUMBER INPUT.
    /*
        *DRY RUN*-
        NUM = 1245

        1 CALL :
            DIGIT = 5
            NUM = 124
            RECURSIVE CALL FOR 124
            DIGIT PRINT PENDING

        2 CALL :
            DIGIT = 4
            NUM = 12
            RECURSIVE CALL FOR 12
            DIGIT PRINT PENDING

        3 CALL :
            DIGIT = 2
            NUM = 1
            RECURSIVE CALL FOR 1
            DIGIT PRINT PENDING

        4 CALL :
            DIGIT = 1
            NUM = 0
            BASE CASE HIT
            PRINTS CURRENT DIGIT 1
            FINISH EXECUTION BY PRINTING PREVIOUSLY PENDING DIGIT PRINTS i.e. 2 -> 4 -> 5

         FINAL OUTPUT :
            1
            2
            4
            5
    */
    static void DigitPrint(int num){
        if(num<1){
            return;
        }
        int digit = num%10;
        num=num/10;
        DigitPrint(num);
        System.out.println(digit);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // PRINT NAME REPETITIVELY USING RECURSION
        String name;
        System.out.println("ENTER YOU'R NAME:");
        name = sc.nextLine();
        int no;
        System.out.println("Number OF PRINTS:");
        no=sc.nextInt();
        System.out.println(PrintName(no,name,0));


        //PRINT 1 TO N USING RECURSION
        int n;
        System.out.println("ENTER n = ");
        n=sc.nextInt();
        System.out.println(Print1toN(n,1));

        //PRINT N TO 1 USING RECURSION
        int x;
        System.out.println("ENTER x =");
        x = sc.nextInt();
        PrintNto1(x);

        //PRINT ARRAY USING RECURSION
        int[] arr = {1, 21, 33, 1, 12};
        PrintArr(arr,arr.length,0);

        //MAX ELEMENT IN ARRAY FIND USING RECURSION
        int max = arr[0];
        MaxElementInArr(arr,arr.length,1,max);

        //MAX ELEMENT IN ARRAY FIND USING RECURSION
        int min = arr[0];
        MinElementInArr(arr,arr.length,1,min);

        //LINEAR SEARCH USING RECURSION
        int target = 33;
        LinearSearch(arr,arr.length,0,target);

        //FREQUENCY OF ELEMENT IN ARRAY USING RECURSION
        int aim = 1;
        FreqCount(arr,arr.length,0,aim,0);

        //PRINT DIGITS USING RECURSION
        int num = 1245;
        DigitPrint(num);

        sc.close();
    }
}

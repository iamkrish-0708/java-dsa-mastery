package basics;

public class BasicMath {

    /*________________________________________________________________________________________________________*/
    static int reverseNumber(int num2) {
        int digit;
        int reversenum=0;
        while(num2!=0){
            digit=num2%10;
            reversenum=(reversenum*10)+digit;
            num2=num2/10;
        }
        return reversenum;
    }
    /*________________________________________________________________________________________________________*/
    static void pallindromeNUM(int num2){
        int reversenum=reverseNumber(num2);
        if(reversenum==num2){
            System.out.println("NUMBER IS PALLINDROME.");
        }else {
            System.out.println("NUMBER IS NOT PALLINDROME.");
        }
    }
/*________________________________________________________________________________________________________*/
    static void primenum(int prime) {
        if (prime <= 1) {
            System.out.println(prime + " IS NOT A PRIME NUMBER.");
            return;
        }
        boolean flag=true;
        outer:
        for(int i=2;i<prime;i++) {
            if (prime%i == 0) {
                flag=false;
                break outer;
            }
        }
        if(flag){
            System.out.println(prime+" IS A PRIME NUMBER.");
        }else{
            System.out.println(prime+" IS NOT A PRIME NUMBER.");
        }
    }
    /*________________________________________________________________________________________________________*/

    public static void main() {
        /*________________________________________________________________________________________________________*/
        // PRINT DIGITS OF NUMBER
        int amt=57162;
        while(amt!=0){
            System.out.println(amt%10); // HELPS SEPARATING LAST DIGIT OF NUMBER
            amt=amt/10; // INT DIVIDE BY INT GIVES AN INT OR IN SIMPLE WORD REMOVE LAST DIGIT
        }
        /*________________________________________________________________________________________________________*/
        // COUNT NUMBER OF DIGITS
        int num=231423;
        int count=0;
        while(num!=0){
            num=num/10;
            count++;
        }
        System.out.println("NUMBER OF DIGITS="+count);
        /*________________________________________________________________________________________________________*/
        // SUM OF DIGITS
        int num1=231;
        int sum=0;
        int temp;
        while(num1!=0){
            temp=num1%10;
            num1=num1/10;
            sum+=temp;
        }
        System.out.println("SUM OF DIGITS="+sum);
        /*________________________________________________________________________________________________________*/
        // REVERSE A NUMBER
        int num2=315;
        int digit;
        int reversenum=0;
        while(num2!=0){
            digit=num2%10;
            reversenum=(reversenum*10)+digit;
            num2=num2/10;
        }
        System.out.println(reversenum);
        /*________________________________________________________________________________________________________*/
        // PALLINDROME NUMBER CHECKER
        int test=3223;
        pallindromeNUM(test);

        // PRIME NUMBER CHECKER
        int prime=1;
        primenum(prime);
        /*________________________________________________________________________________________________________*/
    }
}

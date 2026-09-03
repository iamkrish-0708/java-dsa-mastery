package basics;

import java.util.Scanner;

public class BasicMath2 {


    // THIS IS OPTIMAL SOLUTION TO CHECK PRIME NUMBER AS IT ONLY NEEDS TO CHECK NUMBER/TEST UNTIL SQUARE ROOT OF NUMBER
    static void OptimalPrimeChecker(int n){
        if(n==1||n==0||n<0){
            System.out.println(n+" IS NOT A PRIME NUMBER.");
            return;
        }
        boolean flag=true;
        for(int i=2;i<=Math.sqrt(n);i++){
            if(n%i==0){
                flag=false;
                break;
            }
        }
        if(flag){
            System.out.println(n+" IS PRIME NUMBER.");
        }else{
            System.out.println(n+" IS NOT A PRIME NUMBER.");
        }
    }

    // HCF CALCULATE HCF(A,B)=HCF(B, A % B)
    static int hcf(int a, int b){
        int ans=0;
        int B=b;
        while(B!=0){
            int temp=B;
            B=a%B;
            ans=temp;
        }
        System.out.printf("HCF(%d,%d)->%d",a,b,ans);
        return ans;
    }

    // LCM CALCULATE LCM = (A*B)/HCF
    static void lcm(int a , int b){
        int hcf=hcf(a,b);
        int lcm=(a*b)/hcf;
        System.out.println();
        System.out.printf("LCM(%d,%d)->%d",a,b,lcm);
    }

    // ARMSTRONG NUMBER - 153= 1³ + 5³ + 3³
    static void armNUM(int arm){
        int sep;
        int temp=arm;
        int armstrg=0;
        while(temp!=0){
            sep=temp%10;
            armstrg=armstrg+(sep*sep*sep);
            temp=temp/10;
        }
        System.out.println();
        System.out.printf("ARMSTRONG NUMBER FOR %d IS %d",arm,armstrg);
    }

    // PERFECT NUMBER CHECK EX- NUMBER (6) ->FACTORS(1,2,3) 1+2+3=6 i.e. SUM OF FACTORS(EXCEPT NUMBER ITSELF)=NUMBER
    static void perfectnum(int num){
        int sum=1;
        for(int i=2;i<=Math.sqrt(num);i++){
            if(num%i==0){
                int firstfactor=i;
                int secondfactor=num/i;
                sum=sum+firstfactor+secondfactor;
            }
        }
        if(sum==num){
            System.out.println(num+  " IS A PERFECT NUMBER");
        }else{
            System.out.println(num+  " IS NOT A PERFECT NUMBER");
        }
    }

    // PRINT 1 TO N PRIME NUMBERS
    static void listOFprimenum(int N) {
        if(N<=1){
            System.out.println("NO PRIME NUMBERS FOR THAT END OF RANGE.");
        }
        for(int i=1;i<=N;i++){
            boolean flag=true;
            for(int j=2;j<=Math.sqrt(i);j++){
                if(i%j==0){
                    flag=false;
                    break;
                }
            }
            if(flag){
                System.out.println(i);
            }
        }

    }

    public static void main() {
        Scanner sc=new Scanner(System.in);

        int num;
        System.out.print("ENTER NUMBER:");
        num=sc.nextInt();
        OptimalPrimeChecker(num);

        int a;
        int b;
        System.out.println();
        System.out.println("ENTER TWO VALUES TO FIND THEIR HCF or GCD:");
        a=sc.nextInt();
        b=sc.nextInt();
        lcm(a,b);

        int ArmNum;
        System.out.println();
        System.out.println("ENTER NUMBER TO GET ITS ARMSTRONG NUMBER:");
        ArmNum=sc.nextInt();
        armNUM(ArmNum);

        int perfect;
        System.out.println();
        System.out.println("ENTER NUMBER TO CHECK IF IT IS A PERFECT NUMBER OR NOT:");
        perfect=sc.nextInt();
        perfectnum(perfect);

        int N;
        System.out.println();
        System.out.print("ENTER END OF RANGE TO PRINT ALL PRIME NUMBERS IN BETWEEN IT:-");
        N=sc.nextInt();
        listOFprimenum(N);
        sc.close();
    }
}

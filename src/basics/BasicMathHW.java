package basics;

import jdk.swing.interop.SwingInterOpUtils;

public class BasicMathHW {

    static void factorial(int n){
        System.out.println();
            int curr=n;
            for(int i=n-1;i>=1;i--){
                curr=curr*i;
            }
        System.out.printf("FACTORIAL OF %d IS %d",n,curr);
    }

    static void power(int a , int b) {
        System.out.println();
        if(b==0){
            System.out.printf("%d RAISE TO %d = 1",a,b);
        }

        int base=a;
        int power=b;
        int ans=1;
        for(int i=1;i<=b;i++){
            ans=ans*base;
        }
        System.out.printf("%d RAISE TO %d = %d",a,b,ans);
    }

    public static void main() {
        factorial(5);
        power(3,3);

    }
}

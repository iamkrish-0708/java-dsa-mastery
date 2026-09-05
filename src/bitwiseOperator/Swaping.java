package bitwiseOperator;

public class Swaping {
    public static void main(String[] args) {
        int n1=5;
        int n2=6;
        /*
        FOR SWAPPING WE CAN USE XOR (^) BITWISE OPERATOR
        a=5 and b=6
        {since num^num=0 and num^0=num}
        a=a^b; -eqn(1)
        b=a^b; [ b = (a^b)^b == a ] -eqn(2)  {using eqn 1}
        a=a^b; [ a = (a^b)^(a^b)^b == b ]  {using eqn 1 and 2}
        */

        n1=n1^n2;
        n2=n1^n2;
        n1=n1^n2;
        System.out.println(n1);
        System.out.println(n2);


        // question - [1,1,2,3,2,4,4] find the non repeating number from array
        int[] arr = {1, 1, 2, 3, 2, 4, 4};
        int ans = 0;

        for (int i = 0; i < arr.length; i++) {
            ans = ans ^ arr[i];
        }

        System.out.println("Non-repeating number: " + ans);

    }
}

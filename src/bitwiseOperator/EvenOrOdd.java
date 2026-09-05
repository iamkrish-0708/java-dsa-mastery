package bitwiseOperator;
/*IN BINARY REPRESENTATION IF RIGHT MOST BIT IS 0 IT MEANS NUMBER IS EVEN AND ODD WHEN IT IS 1
    USING NUMBER & 1 WE CAN DETERMINE IF NUMBER IS ODD OR EVEN
    IF NUMBER & 1 = 0 MEANS NUMBER IS EVEN WHEN =1 NUMBER IS ODD
    THIS IS MUCH FASTER WAY OF EVALUATING EVEN OR ODD
*/
import java.util.Scanner;

public class EvenOrOdd {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        boolean flag=true;
        while(flag){
            int n;
            System.out.print("Enter a number: ");
            n=sc.nextInt();
            sc.nextLine();
            int checker=n&1;
            if(checker==1){
                System.out.println(n+" is odd number.");
            }
            else{
                System.out.println(n+" is even number.");
            }
            System.out.println("Do you want to check for another number?(0 for no or type anything to continue)-");
            int response=sc.nextInt();
            sc.nextLine();
            if(response==0){
                System.out.println("Thank you!");
                flag=false;
            }
        }

        sc.close();
    }
}

package bitwiseOperator;


public class PowerOfTwo {
    public static void main(String[] args) {

        // ordinary method
        int num=17;
        int count=0;
        int n=num;
        while(n!=0){

            if((n&1)==1){
                count++;
            }
            n=n>>1;
        }
        if(count==1){
            System.out.println(num+" is possible value for 2 power something");
        }else{
            System.out.println(num+ " is not possible value for 2 power something");
        }

        // quick method
        /*
        n & (n-1)=0 it means give number is possible value for 2 power something *ALSO IT HELPS TO REMOVE LAST SET BIT OF ANY NUMBER*
        for example - 16 -> 16 & 15 i.e. [0001 0000] & [0000 1111] = [0000 0000] or 0
        */

        int n1=8;
        if( (n1&(n1-1))==0){
            System.out.println(n1+" is possible value for 2 power something");
        }
        else{
            System.out.println(n1+ " is not possible value for 2 power something");
        }
        // to get last set bit of number we use : number & (-number)

    }
}

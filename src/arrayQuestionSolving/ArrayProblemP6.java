package arrayQuestionSolving;

public class ArrayProblemP6 {

    // USED Kadane's Algorithm TO SOLVE THIS
    // FIRST CALCULATE SUM THEN CHECK IF MAXIMUM SUM IS LESS THAN CURRENT SUM IF YES THAN UPDATE MAXIMUM SUM
    // IF SUM BECOMES NEGATIVE SET SUM AS 0 AND MOVE AHEAD BECAUSE ADDING NEGATIVE NUMBERS TO THE SUM ONLY DECREASE IT SO IT IS BETTER TO IGNORE ANY FURTHER ADDITION
    static void MaxSumSubarray(int[] arr){
        int sum=0;
        int maxi= Integer.MIN_VALUE;
        for(int i=0;i< arr.length;i++){
            sum=sum+arr[i];
            if(maxi<sum){
                maxi=sum;
            }
            else if(sum<0){
                sum=0;
            }
        }
        System.out.println(maxi);
    }

    public static void main(String[] args) {
        int[] arr={-2,1,-3,4,-1,2,1,-5,4};
        MaxSumSubarray(arr);
    }
}

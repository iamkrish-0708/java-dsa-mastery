package arrayQuestionSolving;

public class ArrayProblemP5 {

    static void MissingNum(int[] arr){

        for(int i=0;i<arr.length;i++){
            int num=Math.abs(arr[i]);
            if(arr[num-1]>0) {
                arr[num - 1] = arr[num - 1]*(-1);
            }
        }
        for(int n=0;n<arr.length;n++){
            if(arr[n]>0){
                System.out.println("Missing number: "+(n+1));
            }
        }
    }
    public static void main(String[] args) {
        int[] arr1={5,5,1,2,2};
        MissingNum(arr1);
    }
}

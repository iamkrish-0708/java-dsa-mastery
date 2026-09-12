package arrayQuestionSolving;

public class ArrayProblemP3 {

    static void SortArray01(int[] arr){
        /*Method 1*/
//        int zeroCount=0;
//        for(int n:arr){
//            if(n==0){
//                zeroCount++;
//            }
//        }
//        for(int i=0;i<arr.length;i++){
//            if(zeroCount!=0){
//                arr[i]=0;
//                zeroCount--;
//            }
//            else{
//                arr[i]=1;
//            }
//        }
//        for(int n:arr){
//            System.out.println(n);
//        }
        /*TWO POINTER METHOD*/
        int i=0;
        int j=arr.length-1;
        while(i<=j){
            if(arr[i]==1 && arr[j]==0){
                int temp=arr[i];
                arr[i]=arr[j];
                arr[j]=temp;
                i++;
                j--;
            }
            else if(arr[i]==0){
                i++;
            }
            else if(arr[j]==1){
                j--;
            }
        }
        for(int n:arr){
          System.out.println(n);
    }

    }

    static void missingNum(int[] arr){
        int xorSum=0;
        for(int n:arr){
            xorSum=xorSum^n;
        }
        for(int i=0;i<=arr.length;i++){
            xorSum=xorSum^i;
        }
        System.out.println("Missing Number:");
        System.out.println(xorSum);
    }
    public static void main(String[] args) {
        int[] arr1 = {0, 1, 1, 0, 0, 1};
        SortArray01(arr1);
        int[] arr2={0,1,3,5,4};
        missingNum(arr2);
    }
}

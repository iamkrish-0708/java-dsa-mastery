package arrayQuestionSolving;

public class ArrayManipulation {

    static int[] RevArray(int[] arr){
        int tempI=0;
        int tempE=arr.length-1;
        for(int i=0;i< arr.length;i++){
            if(tempI==tempE){
                arr[tempI]=arr[tempE];
                return arr;
            }
            else if(tempI>tempE){
                return arr;
            }
            int temp=arr[tempI];
            arr[tempI]=arr[tempE];
            arr[tempE]=temp;
            tempI++;
            tempE--;
        }
        return arr;
    }

    static int[] shiftRight(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            result[(i + 1) % n] = arr[i];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr1={1,2,3,4,5};
        arr1=RevArray((arr1));
        System.out.println("Reverse array: ");
        for(int n:arr1){
            System.out.print(n+" ");
        }
        System.out.println();
        arr1=shiftRight(arr1);
        for(int n:arr1){
            System.out.println(n);
        }
    }
}

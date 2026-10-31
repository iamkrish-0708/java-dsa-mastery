package recursion;

public class BinarySearch {
    static void BS(int[] arr,int high , int low, int target){
        int mid = low + (high-low)/2;
        if(low > high){
            System.out.println("TARGET "+target+" IS ABSENT IN THE ARRAY");
            return;
        }
        if(arr[mid]==target){
            System.out.println("TARGET "+target+" FOUND AT INDEX "+mid);
            return;
        }
        if(arr[mid]<target){
            low = mid + 1;
        }
        else{
            high = mid - 1;
        }
        BS(arr,high,low,target);
    }
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        int target = 0;
        int low = 0;
        int high = arr.length - 1;
        BS(arr,high, low,target);
    }
}

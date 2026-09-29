package binarySearch;
/*
    given a sorted rotated array and we have to find pivot index
    if EX=[4,5,6,1,2,3] == pivot index is EX[2]=6
*/

public class SortRotatedArr {

    static void PivotIndex(int[] arr){
        int n= arr.length;
        int s=0;
        int e=arr.length-1;
        int ans=-1;
        while(s<=e){
            int mid=s+(e-s)/2;
            if(arr[mid]<=arr[n-1]){
                e=mid-1;
            }
            else{
                ans=mid;
                s=mid+1;
            }
        }
        System.out.println("Pivot index = "+ans);
    }

    public static void main(String[] args) {
        int[] arr={4,5,6,1,2,3};
        PivotIndex(arr);
    }
}

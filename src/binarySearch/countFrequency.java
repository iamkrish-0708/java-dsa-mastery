package binarySearch;
// sorted array is provided and target element frequency is to be counted

public class countFrequency {

    static void frequencyCounter(int[] arr,int target){
        int start=0;
        int end= arr.length-1;
        int leftEnd=0;
        int rightEnd=arr.length-1;
        while(start<=end){
            int mid=start+(end-start)/2;
            if (arr[mid] == target) {

                int left = mid;
                int right = mid;

                // Move left
                while (left >= 0 && arr[left] == target) {
                    left--;
                }

                // Move right
                while (right < arr.length && arr[right] == target) {
                    right++;
                }

                leftEnd = left + 1;
                rightEnd = right - 1;

                break;
            }
            else if(arr[mid]>target){
                end=mid-1;
            }
            else {
                start=mid+1;
            }
        }
        int freq=rightEnd-leftEnd+1;
        System.out.print("Target = "+target+ " repeated "+freq+" times");
    }

    static int lowerBound(int[] arr, int target){
        int s=0;
        int e=arr.length-1;
        int ans=-1;
        while (s<=e){
            int mid=s+(e-s)/2;
            if(arr[mid]>=target){
                ans=mid;
                e=mid-1;
            }else{
                s=mid+1;
            }
        }
        return ans;
    }

    static int upperBound(int[] arr, int target) {
        int s = 0;
        int e = arr.length - 1;
        int ans = arr.length;

        while (s <= e) {
            int mid = s + (e - s) / 2;

            if (arr[mid] > target) {
                ans = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr={1,1,1,2,2,2,2,3,3,3,3,};
        frequencyCounter(arr,3);

        int target=3;

        int lb=lowerBound(arr,target);

        int ub=upperBound(arr,target);

        int totalOccureneces=ub-lb;
        System.out.println();
        System.out.println(target+" repeated "+totalOccureneces+" times.");


    }
}

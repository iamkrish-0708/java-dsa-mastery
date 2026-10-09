package binarySearch;

public class MaxOneIn2DArray {
    static int oneFirstOccurrence(int[][] arr,int rowIndex){
        int totalCol=arr[0].length;
        int ans=-1;
        if(arr[rowIndex][totalCol-1]==0){
            return totalCol;
        }
        else{
            int s=0;
            int e=totalCol-1;
            while(s<=e){
                int mid=s+(e-s)/2;
                if(arr[rowIndex][mid]==1){
                    ans=mid;
                    e=mid-1;
                }
                else{
                   s=mid+1;
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int[][] arr = {{0, 0, 0, 1}, {0, 1, 1, 1}, {0, 0, 1, 1}, {0, 0, 0, 0}};
        int maxOneCount=-1;
        int r=arr.length;
        int c=arr[0].length;
        for(int i=0;i<r;i++){
            int firstOneOccurren=oneFirstOccurrence(arr,i);
            int oneCountOfRow=c-firstOneOccurren;
            if(maxOneCount<oneCountOfRow){
                maxOneCount=oneCountOfRow;
            }
        }
        System.out.println(maxOneCount);
    }
}

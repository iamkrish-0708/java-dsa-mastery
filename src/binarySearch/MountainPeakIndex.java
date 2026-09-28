package binarySearch;

public class MountainPeakIndex {

    /*
        DRY RUN
EX = [1,2,3,5,4,2,1]

S1 -> S=0, E=6, MID=3
     Since MID = S + (E-S)/2

     Check: EX[MID] >= EX[MID+1]
            5 >= 4  → TRUE

     We are on the descending side of the mountain.
     Therefore, MID can be the possible peak, so store:

     ANS = MID
     E = MID - 1

     This discards the right side.

S2 -> S=0, E=2, MID=1

     Check: EX[MID] < EX[MID+1]
            2 < 3 → TRUE

     We are on the ascending side.
     Therefore, the peak must be on the right side.

     S = MID + 1

S3 -> S=2, E=2, MID=2

     Check: EX[MID] < EX[MID+1]
            3 < 5 → TRUE

     We are still on the ascending side.
     Therefore, move to the right.

     S = MID + 1

S4 -> S=3, E=2

     Since S > E, the condition S <= E becomes FALSE.
     Therefore, the while loop terminates.

     ANS contains the peak index.

     FINAL ANSWER = 3
    */

    static void PeakIndex(int[] arr){
        int s=0;
        int e=arr.length-1;
        int peakIndex=-1;
        while(s<=e){
            int mid=s+(e-s)/2;
            if(arr[mid]>=arr[mid+1]){
                peakIndex=mid;
                e=mid-1;
            }
            else{
                s=mid+1;
            }
        }
        System.out.println("Peak (Element:Index) = ("+arr[peakIndex]+":"+peakIndex+")");
    }
    public static void main(String[] args) {
        int[] arr={1,2,3,5,4,2,1};
        PeakIndex(arr);
    }
}

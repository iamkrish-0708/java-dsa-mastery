package binarySearch;
/*The Aggressive Cows problem asks you to place k cows into n stalls located at given positions on a straight line so that the minimum distance between any two cows is as large as possible*/


import java.util.Arrays;

// EXAMPLE - stalls = [10, 1, 2, 7, 5], k = 3
public class AggressiveCows {
    static boolean isValid(int[] arr,int k,int minDistance){
        int cowCount=1;
        int lastPos=0;
        for(int i=1;i<arr.length;i++){
            if(arr[i]-arr[lastPos]>=minDistance){
                cowCount++;
                lastPos=i;
                if(cowCount==k){
                    return true;
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int[] stalls={10, 1, 2, 7, 5};
        int k=3;
        int start=0;
        int ans=-1;
        Arrays.sort(stalls);
        int end=stalls[stalls.length-1]-stalls[0];
        while(start<=end){
            int mid=start+(end-start)/2;
            if(isValid(stalls,k,mid)){
                ans=mid;
                // If mid is valid, then all smaller distances are also valid.
                // But we need the MAXIMUM possible minimum distance,
                // so we move right to check for a larger valid distance.
                start=mid+1;
            }
            else{
                // If mid is invalid, then all larger distances will also be invalid.
                // So we move left to check for a smaller distance.
                end=mid-1;
            }
        }
        System.out.println(ans);
    }
}
/*
DRY RUN:
Example:
stalls = {10, 1, 2, 7, 5}
k = 3

After sorting:
stalls = {1, 2, 5, 7, 10}

We need to place 3 cows such that the minimum distance
between any two cows is maximum.

Initial:
start = 0
end = 10 - 1 = 9
ans = -1


ITERATION 1:
start = 0, end = 9
mid = 0 + (9 - 0) / 2 = 4

Check isValid(4):

Place Cow 1 at position 1
lastPos = 0

i = 1:
stalls[1] - stalls[lastPos] = 2 - 1 = 1
1 < 4 -> cannot place cow

i = 2:
5 - 1 = 4
4 >= 4 -> place Cow 2 at 5
lastPos = 2

i = 3:
7 - 5 = 2
2 < 4 -> cannot place cow

i = 4:
10 - 5 = 5
5 >= 4 -> place Cow 3 at 10

cowCount == k -> return true

Since distance 4 is possible:
ans = 4
start = mid + 1 = 5


ITERATION 2:
start = 5, end = 9
mid = 5 + (9 - 5) / 2 = 7

Check isValid(7):

Place Cow 1 at position 1

i = 1:
2 - 1 = 1 < 7 -> cannot place

i = 2:
5 - 1 = 4 < 7 -> cannot place

i = 3:
7 - 1 = 6 < 7 -> cannot place

i = 4:
10 - 1 = 9 >= 7 -> place Cow 2 at 10

Only 2 cows can be placed.
Need 3 cows -> return false

Distance 7 is NOT possible:
end = mid - 1 = 6


ITERATION 3:
start = 5, end = 6
mid = 5 + (6 - 5) / 2 = 5

Check isValid(5):

Place Cow 1 at position 1

i = 1:
2 - 1 = 1 < 5 -> cannot place

i = 2:
5 - 1 = 4 < 5 -> cannot place

i = 3:
7 - 1 = 6 >= 5 -> place Cow 2 at 7
lastPos = 3

i = 4:
10 - 7 = 3 < 5 -> cannot place

Only 2 cows can be placed.
Need 3 cows -> return false

Distance 5 is NOT possible:
end = mid - 1 = 4


LOOP ENDS:
start = 5
end = 4

Since start > end, binary search stops.

FINAL ANSWER:
ans = 4


IMPORTANT IDEA:
We are binary searching on the ANSWER (minimum distance).

If a distance is VALID:
    -> try a larger distance
    -> start = mid + 1

If a distance is INVALID:
    -> try a smaller distance
    -> end = mid - 1

For this example:
Distance 4 -> VALID
Distance 7 -> INVALID
Distance 5 -> INVALID

Therefore, maximum possible minimum distance = 4.
*/

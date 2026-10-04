package binarySearch;

public class EkoSpoj {

    static boolean isValid(int[] arr, int m,int sawHeight){
        int woodCollection=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]>sawHeight){
                woodCollection=woodCollection+(arr[i]-sawHeight);
            }
        }
        if(woodCollection>=m){
            return true;
        }
        else{
            return false;
        }
    }

    public static void main(String[] args) {
        int[] tree={20, 15, 10, 17};
        int m=7;
        int start=0;
        int ans=-1;
        int max=-1;
        for(int i=0;i<tree.length;i++){
            if(max<tree[i]){
                max=tree[i];
            }
        }
        int end=max;
        while(start<=end){
            int mid=start+(end-start)/2;
            if(isValid(tree,m,mid)){
                ans=mid;
                start=mid+1;
            }
            else{
                end=mid-1;
            }
        }
        System.out.println(ans);
    }
}
/*
DRY RUN:
Example:
tree = {20, 15, 10, 17}
m = 7

We need to find the MAXIMUM sawHeight such that
at least 7 units of wood are collected.

Initial:
start = 0
end = maximum tree height = 20
ans = -1


ITERATION 1:
start = 0, end = 20
mid = 0 + (20 - 0) / 2 = 10

Check isValid(tree, 7, 10):

Tree 20:
20 > 10 -> wood = 20 - 10 = 10

Tree 15:
15 > 10 -> wood = 10 + (15 - 10) = 15

Tree 10:
10 > 10 -> false -> no wood

Tree 17:
17 > 10 -> wood = 15 + (17 - 10) = 22

Total wood = 22

22 >= 7 -> VALID

ans = 10
start = mid + 1 = 11


ITERATION 2:
start = 11, end = 20
mid = 11 + (20 - 11) / 2 = 15

Check isValid(tree, 7, 15):

Tree 20:
20 > 15 -> wood = 5

Tree 15:
15 > 15 -> false -> no wood

Tree 10:
10 > 15 -> false -> no wood

Tree 17:
17 > 15 -> wood = 5 + (17 - 15) = 7

Total wood = 7

7 >= 7 -> VALID

ans = 15
start = mid + 1 = 16


ITERATION 3:
start = 16, end = 20
mid = 16 + (20 - 16) / 2 = 18

Check isValid(tree, 7, 18):

Tree 20:
20 > 18 -> wood = 20 - 18 = 2

Tree 15:
15 > 18 -> false

Tree 10:
10 > 18 -> false

Tree 17:
17 > 18 -> false

Total wood = 2

2 < 7 -> INVALID

end = mid - 1 = 17


ITERATION 4:
start = 16, end = 17
mid = 16 + (17 - 16) / 2 = 16

Check isValid(tree, 7, 16):

Tree 20:
20 > 16 -> wood = 4

Tree 15:
15 > 16 -> false

Tree 10:
10 > 16 -> false

Tree 17:
17 > 16 -> wood = 4 + (17 - 16) = 5

Total wood = 5

5 < 7 -> INVALID

end = mid - 1 = 15


LOOP ENDS:
start = 16
end = 15

Since start > end, binary search stops.

FINAL ANSWER:
ans = 15


IMPORTANT IDEA:

We are binary searching on the SAW HEIGHT.

If sawHeight is VALID:
    -> We can cut less wood and still get enough wood.
    -> Try a HIGHER saw height.
    -> start = mid + 1

If sawHeight is INVALID:
    -> We are not getting enough wood.
    -> Need to cut lower.
    -> end = mid - 1

VALIDITY PATTERN:

Saw Height:
0  1  2  ...  15  16  17  18  19  20
✓  ✓  ✓  ...   ✓   ✗   ✗   ✗   ✗   ✗

Therefore:
Maximum valid saw height = 15
*/

package binarySearch;
/*
   BINARY SEARCH IS A SEARCHING ALGORITHM WHICH USES DIVIDE AND CONQUER RULE IN SEARCH OF TARGET ELEMENT.
   TIME COMPLEXITY OF BINARY SEARCH IS O(Log[n]) WAY FASTER THAN LINEAR SEARCH.
   CASES TO BE SATISFIED FOR BINARY SEARCH TO BE APPLIED-
   1. DATA SHOULD BE IN MONOTONIC ORDER (INCREASING OR DECREASING).
   2. DATA STRUCTURE SHOULD ALLOW RANDOM ACCESS TO ELEMENTS LIKE ARRAY AND ARRAYLIST.
   3. DIVISION OF DATA STRUCTURE SHOULD BE ALLOWED OR POSSIBLE.
*/

/*
   DRY RUN EX=[1,2,3,4,5,6,7,8,9,10] AND TARGET=10
   MID = START + (END-START)/2

   STEP 1 ->  START = 0 AND END = 9
              MID = 0+(9-0)/2 = 4
              EX[MID or 4]=5
              SINCE EX[MID]<TARGET
              HENCE NEGLECTING THE LEFT HALF OF ARRAY BY START= MID+1 i.e. 5


   STEP 2 ->  START = 5 AND END = 9
              MID = 5+(9-5)/2 = 7
              EX[MID or 7]=8
              SINCE EX[MID]<TARGET
              HENCE NEGLECTING THE LEFT HALF OF ARRAY BY START= MID+1 i.e. 8


   STEP 3 ->  START = 8 AND END = 9
              MID = 8+(9-8)/2 = 8
              EX[MID or 8]=9
              SINCE EX[MID]<TARGET
              HENCE NEGLECTING THE LEFT HALF OF ARRAY BY START= MID+1 i.e. 9


   STEP 4 ->  START = 9 AND END = 9
              MID = 9+(9-9)/2 = 9
              EX[MID or 9]=10
              SINCE EX[MID]==TARGET
              HENCE TARGET ELEMENT 10 IS FOUND AT INDEX 9


   FINAL OUTPUT -> Element 10 found at index 9
*/
public class CodeBinarySearch {

    static void binarySearch(int[] arr,int key){

            int start = 0;
            int end = arr.length - 1;

            while (start <= end) {

                // int mid = (start + end) / 2; this formula possess risk of integer overflow
                // safe formula to use in calculation of mid-index is written below
                int mid = start + (end - start)/2;

                if (arr[mid] == key) {
                    System.out.println("Element " + key + " found at index " + mid);
                    return;
                } else if (arr[mid] > key) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }

            System.out.println("Element " + key + " is not present in the array.");
        }


    public static void main(String[] args) {
        int[] arr={1,2,3,4,5,6,7,8,9,10};
        binarySearch(arr,10);
    }
}

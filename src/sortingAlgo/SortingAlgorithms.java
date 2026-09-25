package sortingAlgo;

public class SortingAlgorithms {

    static void printSortedArray(int[] sortedArray){
        System.out.println("___________________________________________________");
        System.out.println("sorted array:");
        for(int val:sortedArray){
            System.out.print(val+" ");
        }
        System.out.println();
    }

    // IF ARRAY SIZE IS 4 BUBBLE SORT CAN SORT THE ARRAY COMPLETELY IN MAXIMUM SIZE-1 (i.e. 3) ITERATIONS / ROUNDS
    /*
    DRY RUN OF EX= [5,4,6,1]

    R1 -> EX=[4,5,6,1] SINCE 5>4 HENCE THEY ARE SWAPPED
          EX=[4,5,6,1] SINCE 5<6 HENCE NO CHANGES MADE
          EX=[4,5,1,6] SINCE 6>1 HENCE THEY ARE SWAPPED
          END OF ROUND 1 !

    R2 -> EX=[4,5,1,6] SINCE 4<5 HENCE NO CHANGES MADE
          EX=[4,1,5,6] SINCE 5>1 HENCE THEY ARE SWAPPED
          EX=[4,1,5,6] SINCE 5<6 HENCE NO CHANGES MADE
          END OF ROUND 2 !

    R3 -> EX=[1,4,5,6] SINCE 4>1 HENCE THEY ARE SWAPPED
          EX=[1,4,5,6] SINCE 4<5 AND 5<6 HENCE NO CHANGES MADE
          END OF ROUND 3 !

    SUCCESSFULLY SORTING COMPLETED IN 3 ROUNDS SORTED ARRAY EX=[1,4,5,6].
    */
    static int[] bubbleSort(int[] arr){
        int n=arr.length;
        for(int i=0;i<n-1;i++){
            for (int j=0;j<n-i-1;j++){
                if(arr[j]>arr[j+1]){
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
        return arr;
    }

    /*
DRY RUN OF EX= [5,4,6,1]

R1 -> i=0 , minIndex=0 and j=1
      EX[minIndex]>EX[j] Hence minIndex= j or 1
      j++ i.e. j=2 but EX[minIndex]<EX[j] Hence minIndex = 1 remains unchanged
      j++ i.e. j=3 but EX[minIndex]>EX[j] Hence minIndex= j or 3
      Since j=EX.length we replace elements between EX[minIndex] and EX[i]
      Hence EX = [1,4,6,5]

R2 -> i=1 , minIndex=1 and j=2
      EX[minIndex]<EX[j] Hence minIndex = 1 remains unchanged
      j++ i.e. j=3 but EX[minIndex]<EX[j] Hence minIndex = 1 remains unchanged
      Since j=EX.length we replace elements between EX[minIndex] and EX[i]
      Since minIndex = i, no actual change takes place
      Hence EX = [1,4,6,5]

R3 -> i=2 , minIndex=2 and j=3
      EX[minIndex]>EX[j] Hence minIndex= j or 3
      Since j=EX.length we replace elements between EX[minIndex] and EX[i]
      Hence EX = [1,4,5,6]

R4 -> i=3 is not executed because i<arr.length-1 becomes false

FINAL SORTED ARRAY = [1,4,5,6]
    */
    static int[] selectionSort(int[] arr){
        for (int i=0;i<arr.length-1;i++){
            int minIndex=i;
            for(int j=i+1;j<arr.length;j++){
                if(arr[j] < arr[minIndex]){
                    minIndex=j;
                }
            }
            int temp=arr[i];
            arr[i]=arr[minIndex];
            arr[minIndex]=temp;
        }
        return arr;
    }

    /*
DRY RUN OF EX= [5,4,6,1]

R1 -> i=1 , prev=0 , curr=1 , currVal=4
      while condition -> prev>=0 && currVal<arr[prev]
      0>=0 && 4<5 -> TRUE
      arr[prev+1]=arr[prev] i.e. arr[1]=arr[0]
      Hence EX = [5,5,6,1]
      prev-- i.e. prev=-1
      while condition -> prev>=0 && currVal<arr[prev]
      -1>=0 -> FALSE
      while loop ends
      arr[prev+1]=currVal i.e. arr[0]=4
      Hence EX = [4,5,6,1]


R2 -> i=2 , prev=1 , curr=2 , currVal=6
      while condition -> prev>=0 && currVal<arr[prev]
      1>=0 && 6<5 -> FALSE
      while loop does not execute
      arr[prev+1]=currVal i.e. arr[2]=6
      Hence EX = [4,5,6,1]


R3 -> i=3 , prev=2 , curr=3 , currVal=1
      while condition -> prev>=0 && currVal<arr[prev]
      2>=0 && 1<6 -> TRUE
      arr[prev+1]=arr[prev] i.e. arr[3]=arr[2]
      Hence EX = [4,5,6,6]
      prev-- i.e. prev=1

      while condition -> prev>=0 && currVal<arr[prev]
      1>=0 && 1<5 -> TRUE
      arr[prev+1]=arr[prev] i.e. arr[2]=arr[1]
      Hence EX = [4,5,5,6]
      prev-- i.e. prev=0

      while condition -> prev>=0 && currVal<arr[prev]
      0>=0 && 1<4 -> TRUE
      arr[prev+1]=arr[prev] i.e. arr[1]=arr[0]
      Hence EX = [4,4,5,6]
      prev-- i.e. prev=-1

      while condition -> prev>=0 && currVal<arr[prev]
      -1>=0 -> FALSE
      while loop ends
      arr[prev+1]=currVal i.e. arr[0]=1
      Hence EX = [1,4,5,6]


R4 -> i=4 is not executed because i<arr.length becomes false

FINAL SORTED ARRAY = [1,4,5,6]
    */
    static int[] insertionSort(int[] arr){
        for(int i=1;i<arr.length;i++){
            int prev=i-1;
            int curr=i;
            int currVal=arr[i];
            // runs until all sorted elements are smaller than currVal
            while(prev>=0 && currVal<arr[prev]){
                arr[prev+1]=arr[prev];  // shifts prev elements ahead by one
                prev--;                 // helps checking remaining sorted elements by decrementing prev pointer
            }
            arr[prev+1]=currVal; // fills empty space formed by shifting of elements for sorting
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr={2,1,3,6};

        int[] bubbleSortedArray=bubbleSort(arr);
        printSortedArray(bubbleSortedArray);

        int[] selectionSort=selectionSort(arr);
        printSortedArray(selectionSort);

        int[] insertionSort=insertionSort(arr);
        printSortedArray(insertionSort);

    }
}

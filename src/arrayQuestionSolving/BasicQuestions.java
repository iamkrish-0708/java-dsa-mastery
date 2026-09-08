package arrayQuestionSolving;

public class BasicQuestions {

    static void getAvg(int[] arr){
        double sum=0;
        double size=arr.length;
        for(int a:arr){
            sum=sum+a;
        }
        System.out.println("Average="+sum/size);
    }

    static void mulByTen(int[] arr){
        int[] newArr=new int[arr.length];
        for(int i =0;i<arr.length;i++){
            int x=arr[i]*10;
            newArr[i]=x;
        }
        System.out.println("New Array:");
        for(int n:newArr){
            System.out.print(n+" ");
        }
        System.out.println();
    }

    static boolean linearSearch(int[] arr,int t){
        for(int a:arr){
            if(t==a){
                return true;
            }
        }
        return false;
    }

    static int maxNum(int[] arr){
        int max=arr[0];
        for(int i=1;i<arr.length;i++){
//            if(max<arr[i]){
//                max=arr[i];
//            }
            /*BELOW HERE IS INBUILT FUNCTION USAGE FOR GETTING MAX VALUE*/
            max=Math.max(max,arr[i]);
        }
        return max;
    }

    static void sumOfPostiveNegative(int[] arr){
        int positiveSum=0;
        int negativeSum=0;
        for(int a:arr){
            if(a>=0){
                positiveSum+=a;
            }
            else{
                negativeSum+=a;
            }
        }
        System.out.println("Sum of positve numbers= "+positiveSum);
        System.out.println("Sum of negative numbers= "+negativeSum);
    }

    static void countZeroOne(int[] arr){
        int zeroCount=0;
        int oneCount=0;
        for(int a:arr){
            if(a==1){
                oneCount++;
            }
            else{
                zeroCount++;
            }
        }
        System.out.println("Count(0,1)=("+zeroCount+","+oneCount+")");
    }

    static void unsortedElement(int[] arr){
        int ptr1=0;
        int ptr2=1;
        for(int i=1;i<arr.length;i++){
            if(arr[ptr1]<=arr[ptr2] ){
                ptr1++;
                ptr2++;
                continue;
            }
            else{
                System.out.println("Unsorted Element= "+arr[ptr2]);
                return;
            }
        }
        System.out.println("Array is sorted");

    }

    static void swapAlternateElements(int[] arr){
        int ptr1=0;

        for(int ptr2=1;ptr2<arr.length;){
            int temp=arr[ptr1];
            arr[ptr1]=arr[ptr2];  // 2,3,4,5,6,7
            arr[ptr2]=temp;
            ptr1=ptr1+2;
            ptr2=ptr2+2;
        }
        System.out.println("Swapped array elements: ");
        for(int a:arr){
            System.out.print(a+" ");
        }
        System.out.println();
    }

static void arrayIntersection(int[] arr1,int[] arr2){
        int[] commonArr=new int[arr1.length];
        int index=0;
        for(int i=0;i<arr1.length;i++){
            for(int j=0;j<arr2.length;j++){
                if(arr1[i]==arr2[j]){
                    commonArr[index]=arr1[i];
                    index++;
                }
            }
        }
    System.out.println("INTERSECTION ARRAY: ");
        for(int n:commonArr){
            System.out.print(n +" ");
        }
    System.out.println();
}

static void printAltExtremes(int[] arr){
        int end=arr.length-1;
        for(int i=0;i<=end;i++){
            if(i==end){
                System.out.print(arr[i]);
                return;
            }
            System.out.print(arr[i] +" " + arr[end]+" ");
            end--;
        }
}

    public static void main(String[] args) {
        int[] arr={1,2,3,4,5,11};
        getAvg(arr);
        mulByTen(arr);
        System.out.println(linearSearch(arr,12));
        System.out.println(maxNum(arr));

        int[] arr2={1,-1,2,-4,3,-5};
        sumOfPostiveNegative(arr2);
        sumOfPostiveNegative(arr);

        int[] arr3={0,1,1,1,0};
        countZeroOne(arr3);

        int[] arr4={4,5,2,7,8,11};
        unsortedElement(arr4);

        swapAlternateElements(arr4);

        int[] x={1,2,3,4,5};
        int[] y={5,4,3,2,1};

        arrayIntersection(x,y);

        printAltExtremes(x);
    }
}

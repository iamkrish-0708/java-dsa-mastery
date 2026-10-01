package binarySearch;

/*
    StudentCount=2 , BookCount=4 and pages=[10,20,30,40]

    RULE 1 - EVERY STUDENT MUST BE ALLOTED ATLEAST ONE BOOK
    RULE 2 - BOOK ALLOCATION SHOULD BE IN CONTIGUOUS MANNER
    RULE 3 - EACH BOOK SHOULD BE ALLOCATED TO A STUDENT

    EXPECTED ANSWER - RETURN MIN OF MAX PAGE AFTER SUCCESSFULL ALLOTMENT OF BOOK AMONG STUDENTS

    DRY RUN -

            ROUND 1 -> S1=[10,20,30] {PAGE COUNT=60} AND S2=[40] {PAGE COUNT=40}
                        MAX PAGE COUNT = 60
            ROUND 2 -> S1=[10,20] {PAGE COUNT=30} AND S2=[30,40] {PAGE COUNT=70}
                        MAX PAGE COUNT = 70
            ROUND 3 -> S1=[10] {PAGE COUNT=10} AND  S2=[20,30,40] {PAGE COUNT=90}
                        MAX PAGE COUNT = 90

            ANSWER -> 60 {SINCE ROUND 1 ALLOCATION GIVES MIN OF MAX PAGE OUT OF OTHER ALLOCATION}


    APPROACH -
            RANGE FOR FINDING POSSIBLE ANSWER IS BELOW :
            (MIN=MINIMUM COUNT OF PAGE) TO (MAX=MAXIMUM COUNT OF PAGES)

            HENCE FOR EXAMPLE [10,20,30,40]
            RANGE = 0 TO 100(SUM OF ALL BOOK PAGES)

*/

public class BookAllocation {

    static boolean isValid(int[] pages,int sc,int bc,int mid){
        int currentStudent=1;
        int pagesAllocated=0;
        for(int i=0;i<pages.length;i++){
            if(pages[i]>mid){
                return false;
            }
            if(pagesAllocated+pages[i]<=mid){
                pagesAllocated += pages[i];
            }
            else{
                currentStudent++;
                if(currentStudent>sc){
                    return false;
                }
                pagesAllocated=pages[i];
            }
        }
        return true;
    }
    public static void main(String[] args) {
        int[] bookPages={10,20,30,40};
        int studentCount=2;
        int bookCount=bookPages.length;
        int start=0;
        int sum=0;
        int ans=-1;
        if (studentCount > bookCount) {
            //return -1;
        }
        for(int i=0;i<bookPages.length;i++){
            sum+=bookPages[i];
        }
        int end=sum;

        while(start<=end){
            int mid=start+(end-start)/2;
            if(isValid(bookPages,studentCount,bookCount,mid)){
                ans=mid;
                end=mid-1;
            }
            else{
                start=mid+1;
            }
        }
        System.out.println(ans);
    }
}

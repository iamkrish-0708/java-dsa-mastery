package binarySearch;

/* WE ARE GIVEN n WHICH REPRESENTS MINIMUM NUMBER OF PRATA SHOULD BE MADE , chefCount REPRESENT NUMBER OF CHEFS AND ranks=[1,2,3,4] IT REPRESENT RANK OF iTH CHEF AT ranks[i] . ALSO CHEF WITH RANK 1 TAKES 1,2,3,4,...,n MINUTES FOR EACH RESPECTIVE PRATA AND CHEF WITH RANK 2 TAKES 2,4,6,...n MINUTES FOR EACH RESPECTIVE PRATA.
 WE HAVE TO FIND OUT MINIMUM TIME REQUIRED TO PREPARE TO n PRATA.*/
public class RotiPrataSpoj {

    static boolean isValid(int[] ranks, int n, int minTime) {
        int prataCount = 0;
        for (int r : ranks) {
            int time = 0;
            int ptr = 1;
            while (time + r * ptr <= minTime) {
                time += r * ptr;
                ptr++;
            }
            prataCount += ptr - 1;   // pratas actually finished
            if (prataCount >= n) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] ranks={1,2,3,4};
        int n=10;
        int chefCount=4;
        int start=0;
        int maxRank=-1;
        int ans=-1;
        for(int r:ranks){
            if(maxRank<r){
                maxRank=r;
            }
        }
        int end=maxRank*((n*(n+1))/2);
        while(start<=end){
            int mid=start+(end-start)/2;
            if(isValid(ranks,n,mid)){
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
/*
 DRY RUN
 Input: ranks = {1,2,3,4}, n = 10

 A chef of rank r finishes the k-th prata at total time r*(1+2+...+k) = r*k*(k+1)/2.
 So rank 1 finishes pratas at t = 1, 3, 6, 10, 15...
    rank 2 finishes pratas at t = 2, 6, 12...
    rank 3 finishes pratas at t = 3, 9, 18...
    rank 4 finishes pratas at t = 4, 12, 24...

 Search range: start = 0, end = maxRank * n(n+1)/2 = 4 * 55 = 220

 STEP 1: isValid(minTime = 12) (how the check works)
   rank 1: time 1,3,6,10 fit; next would be 15 > 12  -> 4 pratas, total 4
   rank 2: time 2,6 fit; next would be 12? 6+6 = 12 fits, next 12+8 = 20 > 12
           -> 2 + 1 = 3 pratas, total 7
   rank 3: time 3 fits, 3+6 = 9 fits, next 9+9 = 18 > 12 -> 2 pratas, total 9
   rank 4: time 4 fits, 4+8 = 12 fits, next 12+12 = 24 > 12 -> 2 pratas, total 11
   11 >= 10 -> true

 STEP 2: binary search trace
   start  end   mid   pratas made     valid?   action
     0    220   110   14+ (exit early)  yes     ans=110, end=109
     0    109    54   9+6 = 15+         yes     ans=54,  end=53
     0     53    26   6+4 = 10+         yes     ans=26,  end=25
     0     25    12   4+3+2+2 = 11      yes     ans=12,  end=11
     0     11     5   2+1+1+1 = 5       no      start=6
     6     11     8   3+2+1+1 = 7       no      start=9
     9     11    10   4+2+2+1 = 9       no      start=11
    11     11    11   4+2+2+1 = 9       no      start=12
    12     11    --   start > end, stop

 ans = 12 (minimum time to make at least 10 pratas)

 KEY IDEAS FOR REVISION
 - Binary search on the ANSWER (time), not on an array index.
 - isValid(t) is monotonic: if t works, any bigger t also works.
 - valid -> store ans and search left (end = mid-1) to find a smaller time.
 - invalid -> search right (start = mid+1).
 - Count only pratas that FINISH within t, so check "time + r*ptr <= t" BEFORE adding.
 - Use long for end/time if n is large, since r*n*(n+1)/2 can overflow int.
*/
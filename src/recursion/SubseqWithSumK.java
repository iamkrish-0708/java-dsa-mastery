package recursion;

public class SubseqWithSumK {
    static boolean Subsequences(int[] arr,int k,int i,int sum){
        if (sum == k) {
            return true;
        }
        if (i >= arr.length || sum > k) {
            return false;
        }

        return Subsequences(arr,k,i+1,sum+arr[i])||Subsequences(arr,k,i+1,sum);
    }
    public static void main(String[] args) {
        int[] arr = {10,1,2,7,6,1,5};
        int k = 8;
        System.out.println(Subsequences(arr,k,0,0));
    }
}

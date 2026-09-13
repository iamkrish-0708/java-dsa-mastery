package arrayQuestionSolving;

public class ArrayProblemP4 {

    static void PivotIndex(int[] arr) {
        int[] leftToRightSum = new int[arr.length];
        leftToRightSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            leftToRightSum[i] = leftToRightSum[i - 1] + arr[i];
        }
        int[] rightToLeftSum = new int[arr.length];
        rightToLeftSum[arr.length-1] = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            rightToLeftSum[i] = rightToLeftSum[i + 1] + arr[i];
        }
        int Pivot = -1;
        boolean flag = false;
        for (int i = 0; i < arr.length; i++) {
            // LINE 20 AND 21 ARE GUARD AGAINST ARRAY INDEX OUT OF BOUND EXCEPTION
            int leftSum = (i == 0) ? 0 : leftToRightSum[i - 1];
            int rightSum = (i == arr.length - 1) ? 0 : rightToLeftSum[i + 1];
            if (leftSum == rightSum) {
                Pivot = i;
                flag = true;
                break;
            }
        }
        if(flag) {
            System.out.println("Pivot Index For Given Array=" + Pivot);
        }
        else {
            System.out.println("Pivot Index Not Found.");
        }
    }

    public static void main(String[] args) {
        int[] arr1={1,2,3,4,6,0};
        PivotIndex(arr1);
    }
}

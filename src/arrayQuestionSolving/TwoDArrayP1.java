package arrayQuestionSolving;

import java.util.Scanner;

public class TwoDArrayP1 {

    static void RowWiseSum(int[][] arr,int row,int col){
        int sum=0;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                sum=sum+arr[i][j];
            }
            System.out.printf("ROW "+(i+1)+" SUM= "+sum);
            System.out.println();
            sum=0;
        }
    }

    static void ColumnWiseSum(int[][] arr,int row,int col){
        int sum=0;
        for(int j=0;j<col;j++){
            for(int i=0;i<row;i++){
                sum=sum+arr[i][j];
            }
            System.out.printf("COLUMN "+(j+1)+" SUM= "+sum);
            System.out.println();
            sum=0;
        }
    }

    static void WavePrinting(int[][] arr,int row,int col){
        boolean flag=true;
        System.out.println("WAVE PRINTING OF 2D ARRAY:-");
        for(int i=0;i<col;i++){
            if(flag) {
                for (int j = 0; j < row; j++) {
                    System.out.print(arr[j][i] + " ");
                }
                System.out.println();
                flag=false;
            }
            else{
                for(int j=row-1;j>=0;j--){
                    System.out.print(arr[j][i] + " ");
                }
                System.out.println();
                flag=true;
        }
        }
        }

        static void Transpose(int[][] arr,int row,int col){
        int newRow=col;
        int newCol=row;
        int[][] transpose=new int[newRow][newCol];
        for(int i=0;i<newRow;i++){
             for(int j=0;j<newCol;j++){
                 transpose[i][j]=arr[j][i];
             }
        }
            System.out.println("Transpose:");
            for(int i=0;i<newRow;i++){
                for(int j=0;j<newCol;j++){
                    System.out.print(transpose[i][j]+" ");
                }
                System.out.println();
            }
        }


    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int row;
        int col;
        System.out.println("ENTER NUMBER OF ROWS AND COLUMNS:");
        row=sc.nextInt();
        col=sc.nextInt();
        int[][] arr1=new int[row][col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                System.out.printf("ARRAY["+i+","+j+"]=");
                arr1[i][j]=sc.nextInt();
                System.out.println();
            }
        }
        RowWiseSum(arr1,row,col);
        ColumnWiseSum(arr1,row,col);
        WavePrinting(arr1,row,col);
        Transpose(arr1,row,col);

        sc.close();
    }
}

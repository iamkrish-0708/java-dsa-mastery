package arrayQuestionSolving;

import java.util.Scanner;

public class TwoDArrayP2 {

    static int[][] TwoDArrayInput(){
        Scanner sc=new Scanner(System.in);
        System.out.println("ENTER NUMBER OF ROW AND COLUMN:");
        int row=sc.nextInt();
        int col= sc.nextInt();
        int[][] array=new int[row][col];
        System.out.println("Enter array elements");
        System.out.println();
        for (int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                System.out.print(i+":"+j+"=");
                array[i][j]= sc.nextInt();
                System.out.println();
            }
        }
        sc.close();
        return array;
    }

    static int[][] Transpose(int[][] arr,int row , int col){
        int[][] transpose=new int[col][row];
        for(int i=0;i<col;i++){
            for(int j=0;j<row;j++){
                transpose[i][j]=arr[j][i];
            }
        }
        return transpose;
    }

    static void RotateBy90Degree(int[][] transpose,int row ,int col){
        int [][] rotatedArr=new int[row][col];
        for(int i=0;i<row;i++){
            int colEnd=col-1;
            for(int j=0;j<col;j++){
                rotatedArr[i][j]=transpose[i][colEnd];
                colEnd--;
            }
        }

        System.out.println();
        System.out.println("ROTATED ARRAY:");
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                System.out.print(rotatedArr[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] arr=TwoDArrayInput();
        int [][] transpose=Transpose(arr,arr.length,arr[0].length);
        RotateBy90Degree(transpose, transpose.length, transpose[0].length);
    }
}
